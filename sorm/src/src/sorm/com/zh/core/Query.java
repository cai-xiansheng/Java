package sorm.com.zh.core;

import sorm.com.zh.bean.ColumnInfo;
import sorm.com.zh.bean.TableInfo;
import sorm.com.zh.utils.JDBCUtils;
import sorm.com.zh.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cai-xiansheng
 * @Description 负责对外提供服务的核心类
 * @create 2020-08-10 9:23
 */
@SuppressWarnings("all")
public abstract class Query implements Cloneable{

    /**
     * 采用模板方法模式将JDBC封装成模板，便于重用
     * @param sql sql语句
     * @param params sql参数
     * @param clazz 记录要封装到的java对象
     * @param callBack CallBack的实现类，实现回调
     * @return
     */
    public Object executeQueryTemplate(String sql, Object[] params, Class clazz, CallBack callBack) {
        Connection connection = DBManager.getConnection();
        List list = null; // 存储查询结果的容器
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 给sql设参
            JDBCUtils.handleParams(preparedStatement, params);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            return callBack.doExecute(connection, preparedStatement, resultSet);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


    /**
     * 执行一个DML语句
     *
     * @param sql    sql语句
     * @param params 参数
     * @return
     */
    public int executeDML(String sql, Object[] params) {
        Connection connection = DBManager.getConnection();
        int count = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            // 给sql设参
            JDBCUtils.handleParams(preparedStatement, params);
            System.out.println(preparedStatement);
            count = preparedStatement.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            DBManager.close(preparedStatement, connection);
        }
        return count;
    }


    /**
     * 将一个对象存储到数据库中
     * 把对象不为null的属性往数据库
     *
     * @param object 要存储的对象
     */
    public void insert(Object object) {
        // 把对象存到表中，insert into 表名(id,uname,pwd) values(?,?,?)
        Class clazz = object.getClass();
        List<Object> params = new ArrayList<>(); // 存储sql的参数对象
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sql = new StringBuilder("insert into " + tableInfo.gettName() + "(");
        int countNotNullField = 0; // 计算不为null的属性值
        for (Field field : fields) {
            String fieldName = field.getName();
            Object fieldValue = ReflectUtils.invokeGet(fieldName, object);

            if (fieldValue != null) {
                countNotNullField++;
                sql.append(fieldName + ",");
                params.add(fieldValue);
            }
        }
        sql.setCharAt(sql.length() - 1, ')');
        sql.append(" values(");
        for (int i = 0; i < countNotNullField; i++) {
            sql.append("?,");
        }
        sql.setCharAt(sql.length() - 1, ')');

        executeDML(sql.toString(), params.toArray());
    }

    /**
     * 删除clazz表示类对应的表中的记录（指定主键值id的记录）
     *
     * @param clazz 跟表对应的类的Class对象
     * @param id    主键的值
     * @return
     * @instance delete from User where id = 2;
     */
    public void delete(Class clazz, Object id) {
        // Emp.class,2-->delete from emp where id = 2;

        // 通过Class对象找找TableInfo
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);

        // 获得主键
        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();
        String sql = "delete from " + tableInfo.gettName() + " where " + onlyPriKey.getName() + "=? ";

        executeDML(sql, new Object[]{id});

    }


    /**
     * 删除对象在数据库中对应的记录（对象所在的类对应到表，对象的主键的值对应到记录）
     *
     * @param object
     */
    public void delete(Object object) {
        Class clazz = object.getClass();
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey(); // 主键

        // 通过反射机制，调用属性对应的get方法或set方法

        Object priKeyValue = ReflectUtils.invokeGet(onlyPriKey.getName(), object);
        delete(clazz, priKeyValue);
    }


    /**
     * 更新对象对应的记录，并且只更新指定的字段的值
     *
     * @param object     索要更新的对象
     * @param fieldNames 更新的属性列表
     * @return 执行sql语句后影响记录的行数
     */
    public int update(Object object, String[] fieldNames) {
        // object{"uname","pwd"} --> update 表名 set uname = ?, pwd =? where id = ?;
        Class clazz = object.getClass();
        List<Object> params = new ArrayList<>();
        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        StringBuilder sql = new StringBuilder("update " + tableInfo.gettName() + " set ");
        for (String fName : fieldNames) {
            Object fValue = ReflectUtils.invokeGet(fName, object);
            params.add(fValue);
            sql.append(fName + "=?,");
        }
        sql.setCharAt(sql.length() - 1, ' ');
        sql.append("where " + tableInfo.getOnlyPriKey().getName() + "=? ");
        params.add(ReflectUtils.invokeGet(tableInfo.getOnlyPriKey().getName(), object)); // 增加主键的值
        return executeDML(sql.toString(), params.toArray());
    }


    /**
     * 查询返回多行记录，并将每行记录封装到clazz指定类的对象中
     *
     * @param sql    查询语句
     * @param clazz  封装数据的javabean类的Class对象
     * @param params sql的参数
     * @return 查询到的结果
     */
    public List queryRows(final String sql, final Class clazz, Object[] params) {

        return (List) executeQueryTemplate(sql, params, clazz, new CallBack() {
            @Override
            public Object doExecute(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
                List list = null;
                try {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    // 多行
                    while (resultSet.next()) {
                        if (list == null) {
                            list = new ArrayList();
                        }
                        Object rowObject = clazz.getDeclaredConstructor().newInstance(); // 调用javabean的无参构造器
                        // 在Java9之后反射中的newInstance()被弃用，所以我们使用clazz.getDeclaredConstructor().newInstance();
                        // 多列 select username,pwd,age from user where id > ? and age > 18
                        for (int i = 0; i < metaData.getColumnCount(); i++) {
                            String columnName = metaData.getColumnLabel(i + 1);
                            Object columnValue = resultSet.getObject(i + 1);

                            // 调用rowObj对象的setUsername方法，将columnValue的值设置进去
                            ReflectUtils.invokeSet(rowObject, columnName, columnValue);
                        }
                        list.add(rowObject);
                    }
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
                return list;
            }
        });
    }

    /**
     * 查询返回一行记录，并将每行记录封装到clazz指定类的对象中
     *
     * @param sql    查询语句
     * @param clazz  封装数据的javabean类的Class对象
     * @param params sql的参数
     * @return 查询到的结果
     */
    public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
        List list = queryRows(sql, clazz, params);
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    /**
     * 查询返回一个值（一行一列），并将该值返回
     *
     * @param sql    查询语句
     * @param params sql的参数
     * @return 查询到的结果
     */
    public Object queryValue(String sql, Object[] params) {

        return executeQueryTemplate(sql, params, null, new CallBack() {
            @Override
            public Object doExecute(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
                Object value = null;
                while (true) {
                    try {
                        if (!resultSet.next()) break;
                        // select count(*) from user
                        value = resultSet.getObject(1);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                return value;
            }
        });
    }


    /**
     * 查询返回一个值（一行一列），并将该值返回
     *
     * @param sql    查询语句
     * @param params sql的参数
     * @return 查询到的数字
     */
    public Number queryNumber(String sql, Object[] params) {
        return (Number) queryValue(sql, params);
    }

    /**
     * 分页查询
     *
     * @param pageNum 第几页数据
     * @param size    每页显示多少记录
     * @return
     */
    public abstract Object queryPagenate(int pageNum, int size);


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

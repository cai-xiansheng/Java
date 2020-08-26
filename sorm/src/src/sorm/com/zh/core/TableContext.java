package sorm.com.zh.core;

import sorm.com.zh.bean.ColumnInfo;
import sorm.com.zh.bean.TableInfo;
import sorm.com.zh.utils.JavaFileUtils;
import sorm.com.zh.utils.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cai-xiansheng
 * @Description 负责获取管理数据库所有表结构和类结构的关系，并可以根据表结构生成类结构
 * @create 2020-08-10 12:24
 */
public class TableContext {
    /**
     * 表名为key，表信息对象为value
     */
    private static Map<String, TableInfo> tableInfoMap = new HashMap<String, TableInfo>();
    /**
     * 将po的class对象和表信息对象关联起来，便于重用！
     */
    public static Map<Class, TableInfo> poClassTableMap = new HashMap<Class, TableInfo>();

    private TableContext() {
    }

    static {
        try {
            Connection connection = DBManager.getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();

            ResultSet tableRet = databaseMetaData.getTables(null, "%", "%", new String[]{"TABLE"});
            while (tableRet.next()) {
                String tableName = (String) tableRet.getObject("TABLE_NAME");
                TableInfo tableInfo = new TableInfo(tableName, new ArrayList<ColumnInfo>(),
                        new HashMap<String, ColumnInfo>());
                tableInfoMap.put(tableName, tableInfo);
                ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");// 查询所有的字段
                while (resultSet.next()) {
                    ColumnInfo columnInfo = new ColumnInfo(resultSet.getString("COLUMN_NAME"),
                            resultSet.getString("TYPE_NAME"), 0);
                    tableInfo.getColumns().put(resultSet.getString("COLUMN_NAME"), columnInfo);
                }
                ResultSet resultSet1 = databaseMetaData.getPrimaryKeys(null, "%", tableName); // 查询t_user表的主键
                while (resultSet1.next()) {
                    ColumnInfo columnInfo = (ColumnInfo) tableInfo.getColumns().get(resultSet1.getObject("COLUMN_NAME"));
                    columnInfo.setKeyType(1); // 设置为主键
                    tableInfo.getPriKeys().add(columnInfo);
                }
                if (tableInfo.getPriKeys().size() > 0) {
                    // 取唯一主键，方便使用，如果是联合主键则为空！
                    tableInfo.setOnlyPriKey(tableInfo.getPriKeys().get(0));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // 更新类结构
        updateJavaPOFile();

        // 加载PO包下面所有的类
        loadPOTables();
    }

    public static Map<String, TableInfo> getTableInfoMap() {
        return tableInfoMap;
    }


    /**
     * 根据表结构，更新配置的PO包下面的Java类
     * 实现了从表结构转化到类结构
     */
    public static void updateJavaPOFile() {
        Map<String, TableInfo> tableInfoMap = TableContext.getTableInfoMap();
        for (TableInfo tableInfo : tableInfoMap.values()) {
            JavaFileUtils.createJavaPOFile(tableInfo, new MySQLTypeConvertor());
        }
    }

    /**
     * 加载PO包下面的类
     */
    public static void loadPOTables() {
        for (TableInfo tableInfo : tableInfoMap.values()) {
            Class clazz = null;
            try {
                clazz = Class.forName(
                        DBManager.getConfiguration().getPoPackage() + "." + StringUtils.firstChar2UpperCase(tableInfo.gettName()));
                poClassTableMap.put(clazz, tableInfo);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    //public static void main(String[] args) {
    //    Map<String, TableInfo> tableInfoMap = getTableInfoMap();
    //    System.out.println(tableInfoMap);
    //}


}

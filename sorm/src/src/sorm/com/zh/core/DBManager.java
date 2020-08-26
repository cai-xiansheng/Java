package sorm.com.zh.core;

import sorm.com.zh.bean.Configuration;
import sorm.com.zh.pool.DBConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author cai-xiansheng
 * @Description 根据配置信息，维持连接对象的管理（增加连接池功能）
 * @create 2020-08-10 12:25
 */
public class DBManager {
    /**
     * 配置信息
     */
    private static Configuration configuration;
    /**
     * 连接池对象
     */
    private static DBConnectionPool pool;

    static {
        // 静态代码块
        Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("sorm/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        configuration = new Configuration();
        configuration.setDriver(properties.getProperty("driver"));
        configuration.setPoPackage(properties.getProperty("poPackage"));
        configuration.setPwd(properties.getProperty("pwd"));
        configuration.setSrcPath(properties.getProperty("srcPath"));
        configuration.setUrl(properties.getProperty("url"));
        configuration.setUser(properties.getProperty("user"));
        configuration.setUsingDB(properties.getProperty("usingDB"));
        configuration.setQueryClass(properties.getProperty("queryClass"));
        configuration.setPoolMaxSize(Integer.parseInt(properties.getProperty("poolMaxSize")));
        configuration.setPoolMinSize(Integer.parseInt(properties.getProperty("poolMinSize")));

        // 加载TableContext类信息
        //System.out.println(TableContext.class);
    }

    /**
     * 获得Connection连接的方法
     *
     * @return
     */
    public static Connection getConnection() {
        if (pool == null) {
            pool = new DBConnectionPool();
        }
        return pool.getConnection();
    }

    /**
     * 创建Connection连接的方法
     *
     * @return
     */
    public static Connection createConnection() {
        try {
            Class.forName(configuration.getDriver());
            return DriverManager.getConnection(configuration.getUrl(),
                    configuration.getUser(), configuration.getPwd()); // 直接建立连接，后期添加连接池处理，提高连接效率!
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close(statement, connection);
    }

    public static void close(Statement statement, Connection connection) {
        try {
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close(connection);
    }

    public static void close(Connection connection) {
        pool.close(connection);
    }

    /**
     * 获取配置对象
     *
     * @return
     */
    public static Configuration getConfiguration() {
        if (configuration == null) {
            return null;
        }
        return configuration;
    }


}

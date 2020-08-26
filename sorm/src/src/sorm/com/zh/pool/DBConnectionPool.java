package sorm.com.zh.pool;

import sorm.com.zh.core.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cai-xiansheng
 * @Description 连接池的类
 * @create 2020-08-11 17:59
 */
public class DBConnectionPool {
    /**
     * 连接池对象
     */
    private List<Connection> pool;
    /**
     * 最大连接数
     */
    private static final int POOL_MAX_SIZE = DBManager.getConfiguration().getPoolMaxSize();
    /**
     * 最小连接数
     */
    private static final int POOL_MIN_SIZE = DBManager.getConfiguration().getPoolMinSize();

    /**
     * 初始化连接池，使池中连接数达到最小值
     */
    public void initPool() {
        if (pool == null) {
            pool = new ArrayList<>();
        }
        while (pool.size() < DBConnectionPool.POOL_MIN_SIZE) {
            pool.add(DBManager.createConnection());
            System.out.println("初始化池，池中连接数：" + pool.size());
        }
    }

    /**
     * 从连接池中取出一个连接
     *
     * @return
     */
    public synchronized Connection getConnection() {
        int last_index = pool.size() - 1;
        Connection connection = pool.get(last_index);
        pool.remove(last_index);
        return connection;
    }

    /**
     * 将连接放回池中
     *
     * @param connection
     */
    public synchronized void close(Connection connection) {
        if (pool.size() > POOL_MAX_SIZE) {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            pool.add(connection);
        }
    }


    public DBConnectionPool() {
        initPool();
    }
}

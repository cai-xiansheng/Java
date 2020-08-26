package sorm.com.zh.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author cai-xiansheng
 * @Description 封装了JDBC查询常用的操作
 * @create 2020-08-10 12:27
 */
public class JDBCUtils {

    /**
     * 给sql传参
     * @param preparedStatement 预编译sql语句
     * @param params 参数
     */
    public static void handleParams(PreparedStatement preparedStatement, Object[] params) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                try {
                    preparedStatement.setObject(1 + i, params[i]);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}

package sorm.com.zh.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author cai-xiansheng
 * @Description
 * @create 2020-08-11 16:47
 */
public interface CallBack {
    public Object doExecute(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet);
}

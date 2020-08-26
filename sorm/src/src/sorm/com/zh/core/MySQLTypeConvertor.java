package sorm.com.zh.core;

/**
 * @author cai-xiansheng
 * @Description mysql数据类型和java数据类型的转换
 * @create 2020-08-10 14:06
 */
public class MySQLTypeConvertor implements TypeConvertor {

    @Override
    public String databaseType2JavaType(String columnType) {
        if ("varchar".equalsIgnoreCase(columnType)) {
            return "String";
        } else if ("int".equalsIgnoreCase(columnType)
                || "tinyint".equalsIgnoreCase(columnType)
                || "smallint".equalsIgnoreCase(columnType)
                || "integer".equalsIgnoreCase(columnType)
        ) {
            return "Integer";
        } else if ("bigint".equalsIgnoreCase(columnType)) {
            return "Long";
        } else if ("double".equalsIgnoreCase(columnType)
                || "float".equalsIgnoreCase(columnType)) {
            return "Double";
        } else if ("clob".equalsIgnoreCase(columnType)) {
            return "java.sql.Clob";
        } else if ("blob".equalsIgnoreCase(columnType)) {
            return "java.sql.Blob";
        } else if ("date".equalsIgnoreCase(columnType)) {
            return "java.sql.Date";
        } else if ("time".equalsIgnoreCase(columnType)) {
            return "java.sql.Time";
        } else if ("timestamp".equalsIgnoreCase(columnType)) {
            return "java.sql.Timestamp";
        }
        return null;
    }

    @Override
    public String javaType2DatabaseType(String javaDataType) {
        return null;
    }
}

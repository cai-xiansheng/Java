package sorm.com.zh.core;

/**
 * @author cai-xiansheng
 * @Description 创建Query对象的工厂类
 * @create 2020-08-10 12:18
 */
public class QueryFactory {

    private static QueryFactory factory = new QueryFactory();

    private static Query prototypeObject; // 原型模式

    static {
        try {
            Class clazz = Class.forName(DBManager.getConfiguration().getQueryClass()); // 加载指定的query类
            prototypeObject = (Query) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private QueryFactory() { // 私有构造器
    }

    public static Query createQuery(){
        try {
            return (Query) prototypeObject.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
        //return new MySQLQuery();
    }

}

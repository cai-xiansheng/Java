package sorm.com.zh.utils;

import java.lang.reflect.Method;

/**
 * @author cai-xiansheng
 * @Description 封装了反射常用的操作
 * @create 2020-08-10 12:28
 */
public class ReflectUtils {

    /**
     * 调用Object对象对应属性fieldName的get方法
     * @param fieldName
     * @param object
     * @return
     */
    public static Object invokeGet(String fieldName, Object object) {
        try {
            Class clazz = object.getClass();
            //Method method = clazz.getMethod("get" + StringUtils.firstChar2UpperCase(fieldName), null);
            Method method = clazz.getDeclaredMethod("get" + StringUtils.firstChar2UpperCase(fieldName), null);
            return method.invoke(object, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void invokeSet(Object object,String columnName,Object columnValue) {
        // 调用rowObj对象的setUsername方法，将columnValue的值设置进去
        Method method  = null;
        try {
            method = object.getClass().getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(columnName),
                    columnValue.getClass());
            method.invoke(object, columnValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

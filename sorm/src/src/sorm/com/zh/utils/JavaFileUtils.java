package sorm.com.zh.utils;

import sorm.com.zh.bean.ColumnInfo;
import sorm.com.zh.bean.JavaFieldGetSet;
import sorm.com.zh.bean.TableInfo;
import sorm.com.zh.core.DBManager;
import sorm.com.zh.core.TypeConvertor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cai-xiansheng
 * @Description 生成Java文件（源代码）的工具类
 * @create 2020-08-10 12:29
 */
public class JavaFileUtils {
    /**
     * 根据字段信息生成java属性信息。如：varchar username-->private String username; 以及相应的set和get方法
     *
     * @param columnInfo    字段信息
     * @param typeConvertor 类型转化器
     * @return
     */
    public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo columnInfo, TypeConvertor typeConvertor) {
        JavaFieldGetSet javaFieldGetSet = new JavaFieldGetSet();
        String javaFieldType = typeConvertor.databaseType2JavaType(columnInfo.getDataType());
        javaFieldGetSet.setFieldInfo("\tprivate " + javaFieldType + " " + columnInfo.getName() + ";\n");

        // 生成get源码
        StringBuilder getSrc = new StringBuilder();
        getSrc.append("\tpublic " + javaFieldType + " get" + StringUtils.firstChar2UpperCase(columnInfo.getName()) + "(){\n");
        getSrc.append("\t\treturn " + columnInfo.getName() + ";\n");
        getSrc.append("\t}\n");
        javaFieldGetSet.setGetInfo(getSrc.toString());

        // 生成get源码
        StringBuilder setSrc = new StringBuilder();
        setSrc.append("\tpublic void set" + StringUtils.firstChar2UpperCase(columnInfo.getName()) + "(");
        setSrc.append(javaFieldType + " " + columnInfo.getName() + "){\n");
        setSrc.append("\t\tthis." + columnInfo.getName() + "=" + columnInfo.getName() + ";\n");
        setSrc.append("\t}\n");
        javaFieldGetSet.setSetInfo(setSrc.toString());

        return javaFieldGetSet;

    }

    /**
     * 根据表信息生成java类的源代码
     *
     * @param tableInfo 表信息
     * @param convertor 数据类型转化器
     * @return Java类的源代码
     */
    public static String createJavaSrc(TableInfo tableInfo, TypeConvertor convertor) {
        Map<String, ColumnInfo> columnInfoMap = tableInfo.getColumns();
        List<JavaFieldGetSet> javaFieldGetSetList = new ArrayList<>();
        for (ColumnInfo columnInfo : columnInfoMap.values()) {
            javaFieldGetSetList.add(createFieldGetSetSRC(columnInfo, convertor));
        }

        StringBuilder src = new StringBuilder();
        // 生成package语句
        src.append("package " + DBManager.getConfiguration().getPoPackage() + ";\n\n");
        // 生成import语句
        src.append("import java.sql.*;\n");
        src.append("import java.util.*;\n\n");
        // 生成类声明语句
        src.append("public class " + StringUtils.firstChar2UpperCase(tableInfo.gettName()) + " {\n\n");
        // 生成属性列表
        for (JavaFieldGetSet javaFieldGetSet : javaFieldGetSetList) {
            src.append(javaFieldGetSet.getFieldInfo());
        }
        src.append("\n\n");
        // 生成get方法
        for (JavaFieldGetSet javaFieldGetSet : javaFieldGetSetList) {
            src.append(javaFieldGetSet.getGetInfo());
            src.append("\n");
        }
        // 生成set方法
        for (JavaFieldGetSet javaFieldGetSet : javaFieldGetSetList) {
            src.append(javaFieldGetSet.getSetInfo());
            src.append("\n");
        }
        // 生成类结束
        src.append("}\n");
        return src.toString();

    }

    public static void createJavaPOFile(TableInfo tableInfo, TypeConvertor typeConvertor) {
        String src = createJavaSrc(tableInfo, typeConvertor);

        String srcPath = DBManager.getConfiguration().getSrcPath() + "/";
        String packagePath = DBManager.getConfiguration().getPoPackage().replaceAll("\\.", "/");

        File file = new File(srcPath + packagePath);
        if (!file.exists()) {
            // 如果指定目录不存在，则帮助用户建立出来
            file.mkdirs();
        }


        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(file.getAbsoluteFile() + "/" + StringUtils.firstChar2UpperCase(tableInfo.gettName()) + ".java"));) {
            bufferedWriter.write(src);
            System.out.println("建立表" + tableInfo.gettName() + "对应的java类：" + StringUtils.firstChar2UpperCase(tableInfo.gettName()) + ".java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //ColumnInfo columnInfo = new ColumnInfo("id", "int", 0);
        //JavaFieldGetSet f = createFieldGetSetSRC(columnInfo,new MySQLTypeConvertor());
        //f.toString();
        //Map<String, TableInfo> tableInfoMap = TableContext.getTableInfoMap();
        //for(TableInfo tableInfo: tableInfoMap.values()) {
        //    JavaFileUtils.createJavaPOFile(tableInfo, new MySQLTypeConvertor());
        //}
    }
}

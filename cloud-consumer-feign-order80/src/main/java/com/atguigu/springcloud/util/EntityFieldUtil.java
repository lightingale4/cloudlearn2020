package com.atguigu.springcloud.util;


import com.atguigu.springcloud.entity.User;
import org.apache.commons.lang3.ArrayUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class EntityFieldUtil {
    private static final String filterStr = "serialVersionUID";
    public static final String record_suffix = "_var";
    private static final List<String> filterList = Arrays.asList("CREATE_DTME", "LAST_UPDTME", "create_dtme", "last_updtme", "lastUpdtme", "createDtme");
    private static final List<String> filterList_update = Arrays.asList("LAST_UPDTME", "last_updtme", "lastUpdtme");


    /**
     * 将对象的属性转化为sql的字段
     *
     * @param klass
     * @type upperCase
     * @retur "col0,col1,col2....."
     */
    public static String fieldSplit(Class klass, String regx) {
        StringBuilder stringBuilder = new StringBuilder();
        Field[] fields = klass.getDeclaredFields();
        Field[] superFields = klass.getSuperclass().getDeclaredFields();
        int len = fields.length, superLen = superFields.length;
        if (len > 0 || superLen > 0) {
            stringBuilder.setLength(0);
            for (int i = 0; i < len; i++) {
                stringBuilder.append(fields[i].getName().toUpperCase());
                if (i < len - 1) {
                    stringBuilder.append(regx);
                }
            }
            if (superLen > 0) {
                stringBuilder.append(regx);
                for (int i = 0; i < superLen; i++) {
                    stringBuilder.append(superFields[i].getName().toUpperCase());
                    if (i < superLen - 1) {
                        stringBuilder.append(regx);
                    }
                }
            }

        }
        return stringBuilder.toString();
    }

    /**
     * 生成通配符号字符串
     *
     * @param klass
     * @param regx
     * @retur "?,?,?,?"
     */
    public static String wildcardSplit(Class klass, String regx) {
        StringBuilder stringBuilder = new StringBuilder();
        Field[] fields = ArrayUtils.addAll(klass.getDeclaredFields(), klass.getSuperclass().getDeclaredFields());
        int len = fields.length;
        if (len > 0) {
            stringBuilder.setLength(0);
            for (int i = 0; i < len; i++) {
                if (filterList.contains(fields[i].getName())) {
                    stringBuilder.append("now()");
                } else {
                    stringBuilder.append("?");
                }
                if (i < len - 1) {
                    stringBuilder.append(regx);
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String wildCardSplitUpdate(Object dataObject, String regx) {
        StringBuilder stringBuilder = new StringBuilder();
        Field[] fields = ArrayUtils.addAll(dataObject.getClass().getDeclaredFields(), dataObject.getClass().getSuperclass().getDeclaredFields());
        int len = fields.length;
        if (len > 0) {
            Method method;
            for (int i = 0; i < len; i++) {
                if (filterList_update.contains(fields[i].getName())) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(regx);
                    }
                    stringBuilder.append(fields[i].getName().toUpperCase());
                    stringBuilder.append("=now()");
                    continue;
                }
                try {
                    method = dataObject.getClass().getDeclaredMethod("get" + fields[i].getName().toUpperCase());
                } catch (NoSuchMethodException e) {
                    try {
                        method = dataObject.getClass().getSuperclass().getDeclaredMethod("get" + fields[i].getName().toUpperCase());
                    } catch (NoSuchMethodException e1) {
                        continue;
                    }
                }
                try {
                    Object object = method.invoke(dataObject);
                    if (object != null) {
                        if (stringBuilder.length() > 0) {
                            stringBuilder.append(regx);
                        }
                        stringBuilder.append(fields[i].getName().toUpperCase());
                        stringBuilder.append("=?");
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    continue;
                }

            }
        }
        return stringBuilder.toString();
    }


    /**
     * 生成通配符号字符串
     *
     * @param klass
     * @param dataObject
     * @retur Object[] 通配符对应的数值
     */
    public static Object[] fieldSplitValue(Class klass, Object dataObject) {
        List<Object> objectList = null;

        try {
            Field[] fields = klass.getDeclaredFields();
            Field[] superFields = klass.getSuperclass().getDeclaredFields();
            int len = fields.length, superLen = superFields.length;
            if (len > 0 || superLen > 0) {
                objectList = new ArrayList<>();
                Method method;
                for (int i = 0; i < len; i++) {
                    if (filterStr.equalsIgnoreCase(fields[i].getName()) || filterList.contains(fields[i].getName())) {
                        continue;
                    }
                    method = dataObject.getClass().getDeclaredMethod("get" + fields[i].getName().toUpperCase());
                    Object object = method.invoke(dataObject);
                    if (object == null && (fields[i].getType() == Double.class || fields[i].getType() == Long.class || fields[i].getType() == Integer.class)) {
                        objectList.add(0);
                    } else {
                        objectList.add(object);
                    }
                }
                for (int i = 0; i < superLen; i++) {
                    if (filterStr.equalsIgnoreCase(superFields[i].getName()) || filterList.contains(superFields[i].getName())) {
                        continue;
                    }
                    method = dataObject.getClass().getSuperclass().getDeclaredMethod("get" + superFields[i].getName().toUpperCase());
                    objectList.add(method.invoke(dataObject));
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return objectList == null ? null : objectList.toArray();
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId(5L);
        user.setName("James");
        user.setAge(16);
        System.out.println(wildCardSplitUpdate(user, ","));

    }
}

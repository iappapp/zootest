package com.github.iappapp.util;

import java.lang.reflect.Constructor;
import java.util.List;

public class ReflectUtil {

    public static Object newInstance(String fullClassName) {
        try {
            Class<?> classBook = Class.forName(fullClassName);
            Object object = classBook.newInstance();
            System.out.println(object);
            return object;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Object constructor(String className, List<Object> objectList, Class<?>... classes) {
        try {
            Class<?> classObject = Class.forName(className);

            Constructor<?> declaredConstructor = classObject.getDeclaredConstructor(classes);
            declaredConstructor.setAccessible(true);

            Object object = declaredConstructor.newInstance(objectList);
            System.out.println(object);
            return object;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}

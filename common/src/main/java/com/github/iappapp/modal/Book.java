package com.github.iappapp.modal;

import com.github.iappapp.annotation.Export;
import com.github.iappapp.util.ReflectUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.InitializingBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

@Getter
@Setter
@ToString
public class Book implements InitializingBean {
    @Export(type = "csv", name = "标题", value = "title")
    private String title;

    @Export(type = "csv", name = "出版日期", value = "publishDate")
    private Date publishDate;

    @Export(type = "csv", name = "作者", value = "author")
    private String author;

    public void init() {
        System.out.println("book init");
    }

    public void destroy() {
        System.out.println("book destroy");
    }
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class clazz = classLoader.loadClass("com.github.iappapp.modal.Book");

        Constructor constructor = clazz.getDeclaredConstructor((Class[]) null);
        Book book = (Book) constructor.newInstance();
        System.out.println(book);

        Method setTitle = clazz.getMethod("setTitle", String.class);
        setTitle.invoke(book, "kill a man");
        System.out.println(book);

        Method setPublishDate = clazz.getMethod("setPublishDate", Date.class);
        setPublishDate.invoke(book, new Date());
        System.out.println(book);

        Method setAuthor = clazz.getMethod("setAuthor", String.class);
        setAuthor.invoke(book, "tom blade");
        System.out.println(book);

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") || methodName.startsWith("set")) {
                System.out.println(method.getName());
                for (Class pClass : method.getParameterTypes()) {
                    System.out.print(pClass.getCanonicalName() + " ");
                }
            }
        }
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getName());
            @SuppressWarnings("unchecked")
            Annotation annotations = field.getDeclaredAnnotation(Export.class);
            System.out.println(annotations);
        }

        book = (Book) ReflectUtil.newInstance("com.github.iappapp.modal.Book");
        System.out.println(book);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this);
        System.out.println("afterPropertiesSet bean init finished");
    }
}

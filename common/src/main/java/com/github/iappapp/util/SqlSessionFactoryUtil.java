package com.github.iappapp.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis下，全局唯一SqlSessionFactory，使用单例模式获取
 */
public class SqlSessionFactoryUtil {

    //首先创建静态成员变量sqlSessionFactory，静态变量被所有的对象所共享。
    private static SqlSessionFactory sqlSessionFactory = null;

    private SqlSessionFactoryUtil() {
    }

    //使用静态代码块保证线程安全问题
    static {

        String resource = "mybatis.xml";

        try {

            InputStream inputStream = Resources.getResourceAsStream(resource);

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "development");

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    /**
     *
     * @return the SqlSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    /**
     *
     * @return the SqlSession
     */
    public static SqlSession getSqlSession() {
        if (null == sqlSessionFactory) {
            return null;
        }
        return sqlSessionFactory.openSession(true);
    }

    public static <T> T getMapper(Class<T> tClass) {
        return getSqlSession().getMapper(tClass);
    }
}

package com.github.iappapp.util;

import com.github.iappapp.dao.mapper.UserExtMapper;
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

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static void main(String[] args) throws Exception {
        SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();

        UserExtMapper userExtMapper = session.getMapper(UserExtMapper.class);

        System.out.println(userExtMapper.listUser(1));
        session.close();
    }

}

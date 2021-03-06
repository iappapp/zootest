package com.github.iappapp;

import com.github.iappapp.dao.domain.User;
import com.github.iappapp.dao.mapper.UserExtMapper;
import com.github.iappapp.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class SqlSessionFactoryUtilTest {
    private static final Logger logger = LoggerFactory.getLogger(SqlSessionFactoryUtilTest.class);

    @Test
    public void listUser() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();

        UserExtMapper userExtMapper = session.getMapper(UserExtMapper.class);

        logger.info(userExtMapper.listUser(1, "root").toString());
        logger.info("listUser finish");
        session.close();
    }

    @Test
    public void testSelectUser() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        UserExtMapper userExtMapper = sqlSession.getMapper(UserExtMapper.class);

        logger.info(userExtMapper.selectUser(null).toString());
        logger.info("testSelectUser finish");

        logger.info("testQueryUser finish");
        logger.info(userExtMapper.queryUser(0, null).toString());

        logger.info("testSelectUserList start");

        Iterator<User> iterable = userExtMapper.selectUserList().iterator();
        while (iterable.hasNext()) {
            logger.info(iterable.next().toString());
        }

        logger.info(userExtMapper.selectUserByMap().toString());

        logger.info(userExtMapper.selectMap().toString());
        sqlSession.close();
    }
}

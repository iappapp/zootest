package com.github.iappapp;

import com.github.iappapp.dao.mapper.LocationExtMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
@EnableAsync
@ContextConfiguration(locations = {"classpath*:application.xml", "classpath*:spring-mvc.xml"})
public class BaseTest {
    @Autowired
    private LocationExtMapper locationExtMapper;
    @Test
    public void test() {
        log.info("locationExtMapper locationList={}", locationExtMapper.selectLocation(null));
    }
}

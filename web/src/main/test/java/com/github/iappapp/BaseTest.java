package com.github.iappapp;

import org.junit.runner.RunWith;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
@EnableAsync
@ContextConfiguration(locations = {"classpath*:application.xml", "classpath*:spring-mvc.xml"})
public abstract class BaseTest {

}

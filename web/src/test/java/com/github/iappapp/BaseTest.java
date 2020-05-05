package com.github.iappapp;

import org.junit.runner.RunWith;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
@EnableAsync
@WebAppConfiguration()
@ActiveProfiles("dev")
@ContextConfiguration(locations = {"classpath*:application.xml", "classpath*:spring-mvc.xml"})
public abstract class BaseTest {
    private static final String HOST = "http://localhost:8080/";

    private static final String CONTEXT_PATH = "";

    public static final String URL = HOST + CONTEXT_PATH;
}

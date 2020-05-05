package com.github.iappapp.service;

import com.github.iappapp.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BookServiceTest extends BaseTest {
    @Autowired
    private BookService bookService;

    @Test
    public void test() {
        String result = bookService.selectSomething("hello");
        log.info("result={}", result);
        result = bookService.selectSomething("hello");
        log.info("result={}", result);
    }
}

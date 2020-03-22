package com.github.iappapp.service.impl;

import com.github.iappapp.modal.Book;
import com.github.iappapp.service.BookService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

import java.util.UUID;

@Getter
@Setter
@Slf4j
public class BookServiceImpl implements BookService {
    private Book book;

    @Override
    public void addBook() {
        System.out.println("this is book service impl");
        System.out.println(book);
    }

    @Override
    @Cacheable(value = "redis-cache-for-annotation-", key = "#word")
    public String selectSomething(String word) {
        log.info("selectSomething into method");
        return UUID.randomUUID().toString();
    }
}

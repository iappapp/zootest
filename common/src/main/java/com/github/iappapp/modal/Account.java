package com.github.iappapp.modal;

import com.github.iappapp.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(autowire = Autowire.BY_TYPE)
@Slf4j
public class Account {
    @Autowired
    private BookService bookService;

    private String user;

    private String sex;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void doSomething() {
        bookService.selectSomething("hello");
    }

    public Account() {
    }
}

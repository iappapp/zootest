package com.github.iappapp.service.impl;

import com.github.iappapp.modal.Book;
import com.github.iappapp.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(value = "callService")
public class CallServiceImpl implements CallService {
    @Inject
    private Book book;

    @Autowired
    @Qualifier(value = "book")
    private Book anotherBook;

    @Override
    public void call() {
        System.out.println("call " + book);
        System.out.println("call you call me");
        System.out.println("another book is " + anotherBook);
    }
}

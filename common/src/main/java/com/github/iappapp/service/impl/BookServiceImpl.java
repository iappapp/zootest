package com.github.iappapp.service.impl;

import com.github.iappapp.modal.Book;
import com.github.iappapp.service.BookService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookServiceImpl implements BookService {
    private Book book;

    @Override
    public void addBook() {
        System.out.println("this is book service impl");
        System.out.println(book);
    }
}

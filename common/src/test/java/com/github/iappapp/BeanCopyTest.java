package com.github.iappapp;

import com.github.iappapp.modal.Book;
import com.github.iappapp.modal.LikeBook;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class BeanCopyTest {
    @Test
    public void copyTest() {
        Book book = new Book();
        book.setAuthor("hello");
        book.setTitle("kill a bird");
        book.setPublishDate(new Date());

        LikeBook likeBook = new LikeBook();
        BeanUtils.copyProperties(book, likeBook);
    }
}

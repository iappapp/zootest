package com.github.iappapp.controller;

import com.github.iappapp.modal.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private Book book;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object printBook() {
        return book.toString();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView returnIndex() {
        return new ModelAndView("/index");
    }
}

package com.github.iappapp.controller;

import com.github.iappapp.modal.Book;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

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

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView returnIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView returnHello() {
        Map<String, Object> map = Maps.newHashMap();
        map.putIfAbsent("userName", "user");
        map.putIfAbsent("userAge", 13);
        return new ModelAndView("hello").addAllObjects(map);
    }
}

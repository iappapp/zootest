package com.github.iappapp.modal;

import com.github.iappapp.annotation.Export;
import com.github.iappapp.util.ReflectUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

@Getter
@Setter
@ToString
public class LikeBook {
    @Export(type = "csv", name = "标题", value = "title")
    private String title;

    @Export(type = "csv", name = "出版日期", value = "publishDate")
    private Date publishDate;

    @Export(type = "csv", name = "作者", value = "author")
    private String author;
}

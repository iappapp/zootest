package com.github.iappapp.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public String login(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return "OK";
        } catch (AuthenticationException e) {
            return "FAIL";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        } catch (Exception ex) {

        }
        return "index";
    }
}



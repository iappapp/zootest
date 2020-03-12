package com.github.iappapp.shiro.service;

import com.github.iappapp.dao.domain.CustInfo;
import com.github.iappapp.dao.mapper.CustInfoExtMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginService {

    @Autowired
    private CustInfoExtMapper custInfoExtMapper;
    @Autowired
    private PasswordService passwordService;


    public CustInfo login(String username, String password) {
        CustInfo custInfo = custInfoExtMapper.listCustInfo(null).get(0);
        log.info("loginService login username={} password={} custInfo={}",
                username, password, custInfo);
        return custInfo;
    }
}

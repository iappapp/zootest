package com.github.iappapp.shiro.configuration;

import com.github.iappapp.shiro.realm.CustomerRealm;
import com.github.iappapp.shiro.session.RedisSessionDao;
import com.github.iappapp.util.RedisUtil;
import com.google.common.collect.Maps;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ShiroConfiguration {
    @Value("${loginUrl}")
    private String loginUrl;

    @Autowired
    private RedisUtil redisUtil;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setLoginUrl(loginUrl);
        Map<String, String> filter = Maps.newHashMap();
        filter.put("/index.html", "anon");
        filter.put("/*.jsp", "anon");
        filter.put("/*.ftl", "anon");
        filter.put("/*", "authc");
        factoryBean.setFilterChainDefinitionMap(filter);
        factoryBean.setSuccessUrl("/hello.ftl");
        factoryBean.setUnauthorizedUrl("index.html");

        return factoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        SecurityManager securityManager = new DefaultSecurityManager();
        ((DefaultSecurityManager) securityManager).setRealm(customerRealm());
        ((DefaultSecurityManager) securityManager).setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public CustomerRealm customerRealm() {
        return new CustomerRealm(hashedCredentialsMatcher());
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);

        return matcher;
    }

    @Bean
    public SessionManager sessionManager() {
        SessionManager sessionManager = new DefaultSessionManager();
        ((DefaultSessionManager) sessionManager).setSessionDAO(redisSessionDao());

        return sessionManager;
    }

    @Bean
    public RedisSessionDao redisSessionDao() {
        RedisSessionDao sessionDao = new RedisSessionDao();
        sessionDao.setRedisUtil(redisUtil);

        return sessionDao;
    }
}

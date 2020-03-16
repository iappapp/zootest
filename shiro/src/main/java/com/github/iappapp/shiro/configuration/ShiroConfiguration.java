package com.github.iappapp.shiro.configuration;

import com.github.iappapp.shiro.constant.ShiroConstant;
import com.github.iappapp.shiro.filter.MeLogOutFilter;
import com.github.iappapp.shiro.realm.CustomerRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.Filter;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Configuration
public class ShiroConfiguration {
    @Value("${loginUrl}")
    private String loginUrl;
    @Value(value = "${redis.host}")
    private String host;
    @Value(value = "${redis.port}")
    private Integer port;
    @Value(value = "${redis.password}")
    private String password;

    @Autowired
    private JedisPoolConfig poolConfig;
    @Autowired
    private JedisPool jedisPool;

    @Bean(value = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setLoginUrl(loginUrl);
        Map<String, String> filter = Maps.newHashMap();
        filter.put("index.jsp", "anon");
        filter.put("/jsp/*.jsp", "anon");
        filter.put("/ftl/*.ftl", "anon");
        filter.put("/logout", "logout");
        filter.put("/login", "anon");
        filter.put("/book/*", "authc");
        filter.put("/location/*", "authc");
        Map<String, Filter> filterMap = Maps.newConcurrentMap();
        filterMap.put("logout", meLogOutFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setFilterChainDefinitionMap(filter);
        factoryBean.setSecurityManager(securityManager());

        return factoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        SecurityManager securityManager = new DefaultWebSecurityManager();
        ((DefaultSecurityManager) securityManager).setRealm(customerRealm());
        ((DefaultSecurityManager) securityManager).setSessionManager(sessionManager());
        ((DefaultWebSecurityManager) securityManager).setCacheManager(redisCacheManager());
        return securityManager;
    }

    @Bean
    public CustomerRealm customerRealm() {
        return new CustomerRealm();
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);

        return matcher;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDao());
        sessionManager.setSessionIdCookie(sessionIdCookie());
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        // 指定session id
        // 去掉shiro登录时url里的JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDao() {
        RedisSessionDAO sessionDao = new RedisSessionDAO();
        sessionDao.setRedisManager(redisManager());
        sessionDao.setKeyPrefix(ShiroConstant.SHIRO_SESSION_PREFIX);
        sessionDao.setExpire(3600 * 1);
        sessionDao.setSessionIdGenerator(sessionIdGenerator());
        return sessionDao;
    }

    @Bean
    public SimpleCookie sessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName(ShiroConstant.SHIRO_COOKIE_NAME);
        return simpleCookie;
    }

    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        SessionIdGenerator sessionIdGenerator = new SessionIdGenerator() {
            @Override
            public Serializable generateId(Session session) {
                return UUID.randomUUID().toString();
            }
        };
        return sessionIdGenerator;
    }

    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        // 配置缓存过期时间 30分钟
        redisManager.setTimeout(1800);
        redisManager.setPassword(password.trim());
        redisManager.setJedisPool(jedisPool);
        redisManager.setJedisPoolConfig(poolConfig);
        return redisManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(redisManager());
        cacheManager.setPrincipalIdFieldName("name");
        cacheManager.setKeyPrefix(ShiroConstant.SHIRO_CACHE_PREFIX);
        cacheManager.setExpire(3600);
        return cacheManager;
    }

    @Bean
    public MeLogOutFilter meLogOutFilter() {
        MeLogOutFilter logOutFilter = new MeLogOutFilter();
        logOutFilter.setCacheManager(redisCacheManager());
        logOutFilter.setEnabled(false);
        return logOutFilter;
    }
}

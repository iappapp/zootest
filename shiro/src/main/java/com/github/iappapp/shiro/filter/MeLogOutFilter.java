package com.github.iappapp.shiro.filter;

import com.github.iappapp.dao.domain.CustInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.Locale;

@Slf4j
public class MeLogOutFilter extends LogoutFilter {
    private final static Logger logger = LoggerFactory.getLogger(MeLogOutFilter.class);

    @Value(value = "${loginUrl}")
    private String loginUrl;

    private Cache<String, Deque<Serializable>> cache;


    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        log.info("MeLogOutFilter preHandle start");
        Subject subject = getSubject(request, response);

        // Check if POST only logout is enabled
        if (isPostOnlyLogout()) {

            // check if the current request's method is a POST, if not redirect
            if (!WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
                return onLogoutRequestNotAPost(request, response);
            }
        }

        String redirectUrl = getRedirectUrl(request, response, subject);
        //try/catch added for SHIRO-298:
        try {
            CustInfo custInfo = (CustInfo) subject.getPrincipal();
            log.info("logout custInfo={}", custInfo);
            if (subject.isAuthenticated()) {
                subject.logout();
                cache.remove(custInfo.getName());
            }
        } catch (SessionException ise) {
            logger.info("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        issueRedirect(request, response, redirectUrl);
        return false;
    }

    // 设置Cache的key的前缀
    public void setCacheManager(CacheManager cacheManager) {
        // 必须和ehcache缓存配置中的缓存name一致
        this.cache = cacheManager.getCache("shiro-cache-");
    }
}

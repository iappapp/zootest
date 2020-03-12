package com.github.iappapp.shiro.realm;

import com.github.iappapp.dao.domain.CustInfo;
import com.github.iappapp.shiro.service.LoginService;
import com.github.iappapp.shiro.service.PermissionService;
import com.github.iappapp.shiro.service.RoleService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        CustInfo custInfo = (CustInfo) principals.getPrimaryPrincipal();

        authorizationInfo.setRoles(roleService.listRoleByUsername(custInfo.getName()));
        authorizationInfo.setStringPermissions(permissionService.listPermissionByUsername(custInfo.getName()));
        authorizationInfo.setObjectPermissions(Sets.newHashSet(new WildcardPermission("**")));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }
        log.info("doGetAuthenticationInfo username={} password={}", username, password);
        CustInfo user = null;
        try {
            user = loginService.login(username, password);
        } catch (Exception e) {
            log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}

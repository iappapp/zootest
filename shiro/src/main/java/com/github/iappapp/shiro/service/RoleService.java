package com.github.iappapp.shiro.service;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RoleService {

    public Set<String> listRoleByUserId(Integer userId) {
        return Sets.newHashSet("admin");
    }

    public Set<String> listRoleByUsername(String username) {
        return Sets.newHashSet("admin");
    }
}

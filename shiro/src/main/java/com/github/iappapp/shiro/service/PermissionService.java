package com.github.iappapp.shiro.service;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PermissionService {

    public Set<String> listPermissionByUserId(Integer userId) {
        return Sets.newHashSet("add");
    }

    public Set<String> listPermissionByUsername(String username) {
        return Sets.newHashSet("add");
    }
}

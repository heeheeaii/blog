package com.nu.blog.modules.service;

import com.nu.blog.modules.entity.Permission;
import com.nu.blog.modules.entity.RolePermission;

import java.util.List;
import java.util.Set;


public interface RolePermissionService {
    List<Permission> findPermissions(long roleId);
    void deleteByRoleId(long roleId);
    void add(Set<RolePermission> rolePermissions);

}

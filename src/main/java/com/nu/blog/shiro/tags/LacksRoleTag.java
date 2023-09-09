package com.nu.blog.shiro.tags;
public class LacksRoleTag extends RoleTag {
    protected boolean showBody(String roleName) {
        boolean hasRole = getSubject() != null && getSubject().hasRole(roleName);
        return !hasRole;
    }
}

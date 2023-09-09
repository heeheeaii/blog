package com.nu.blog.shiro.tags;
public class HasRoleTag extends RoleTag {
    protected boolean showBody(String roleName) {
        return getSubject() != null && getSubject().hasRole(roleName);
    }
}

package com.jinbang.model;

import javax.management.relation.Role;
import java.util.Set;

public class User_Roles_Rscs {
    private ShiroUser shiroUser;
    private Set<String> shiroRoles;
    private Set<String> shiroResources;

    public void setShiroUser(ShiroUser shiroUser) {
        this.shiroUser = shiroUser;
    }

    public void setShiroRoles(Set<String> shiroRoles) {
        this.shiroRoles = shiroRoles;
    }

    public void setShiroResources(Set<String> shiroResources) {
        this.shiroResources = shiroResources;
    }

    public ShiroUser getShiroUser() {
        return shiroUser;
    }

    public Set<String> getShiroRoles() {
        return shiroRoles;
    }

    public Set<String> getShiroResources() {
        return shiroResources;
    }
}

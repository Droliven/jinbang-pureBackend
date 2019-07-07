package com.jinbang.mapper;

import com.jinbang.model.ShiroRole;

import java.util.List;

public interface ShiroRoleMapper {
    String getRoleByRid(int rid);
    ShiroRole loadByRid(int rid);
    List<String> getall();
    Integer getRidByRole(String role);
}

package com.jinbang.mapper;

import com.jinbang.model.ShiroUser;

import java.util.List;

public interface ShiroUserMapper {
    int userReg(ShiroUser user);
    int updateUserById(ShiroUser user);
    int deleteUserById(int uid);
    ShiroUser loadByUserName(String name);
    String getPwdByName(String name);
    ShiroUser getUserById(int uid);
    List<ShiroUser> getAll();
    List<String> getNames();
    int maxUid();
    int getUidByName(String name);
}

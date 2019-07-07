package com.jinbang.mapper;

import com.jinbang.model.ShiroUserRole;

import java.util.List;

public interface ShiroUserRoleMapper {
    List<ShiroUserRole> getall();
    List<Integer> loadRidsByUid(int uid);
    List<Integer> loadUidsByRid(int rid);
    void createUser(int uid, int rid);
}
package com.jinbang.service;

import com.jinbang.mapper.*;
import com.jinbang.model.ShiroRole;
import com.jinbang.model.ShiroUser;
import com.jinbang.model.User_Roles_Rscs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShiroService {
    @Autowired
    ShiroUserMapper shiroUserMapper;
    @Autowired
    ShiroRoleMapper shiroRoleMapper;
    @Autowired
    ShiroResourceMapper shiroResourceMapper;
    @Autowired
    ShiroUserRoleMapper shiroUserRoleMapper;
    @Autowired
    ShiroRoleRscMapper shiroRoleRscMapper;

    public ShiroUser getUserByName(String name){
        return shiroUserMapper.loadByUserName(name);
    }

    public String getPwdByName(String name){
        return shiroUserMapper.getPwdByName(name);
    }

    public ShiroUser loadUserByName(String name) {
        return shiroUserMapper.loadByUserName(name);
    }

    public Set<String> getRolesByName(String name) {
        Set<String> roles = new HashSet<>();
        List<Integer> roleids = new ArrayList<>();
        int uid = shiroUserMapper.getUidByName(name);
        roleids = shiroUserRoleMapper.loadRidsByUid(uid);
        for (Integer rid : roleids) {
            roles.add(shiroRoleMapper.getRoleByRid(rid));
        }
        return new HashSet<>(roles);
    }
    public Set<String> getRscsByRole(String role) {
        Set<String> rscs = new HashSet<>();
        List<Integer> rscids = new ArrayList<Integer>();
        int rid = shiroRoleMapper.getRidByRole(role);
        rscids = shiroRoleRscMapper.loadRscidByRid(rid);
        for (Integer rscid : rscids) {
            rscs.add(shiroResourceMapper.getResourceByRscid(rid));
        }
        return rscs;
    }
    public Set<String> getRscsByName(String name) {
        Set<String> rscs = new HashSet<String>();
        Set<String> roles = new HashSet<>();
        roles = getRolesByName(name);
        for (String role : roles) {
            Set<String> rscsSet = new HashSet<>();
            rscsSet = getRscsByRole(role);
            rscs.addAll(rscsSet);
        }
        return rscs;
    }
    public User_Roles_Rscs userDetail(String name){
        User_Roles_Rscs user_roles_rscs = new User_Roles_Rscs();
        ShiroUser shiroUser = loadUserByName(name);
        Set<String> shiroRoles = getRolesByName(name);
        Set<String> shiroRscs = getRscsByName(name);
        user_roles_rscs.setShiroUser(shiroUser);
        user_roles_rscs.setShiroRoles(shiroRoles);
        user_roles_rscs.setShiroResources(shiroRscs);
        return user_roles_rscs;
    }
    public Integer regist(String name, String pwd, String role) {
        // 返回值为 0 成功，为 -1 失败
        Set<String> allnames = new HashSet<>(shiroUserMapper.getNames());
        if(allnames.contains(name)) {
            int uid = shiroUserMapper.maxUid() + 1;
            ShiroUser shiroUser = new ShiroUser();
            shiroUser.setUid(uid);
            shiroUser.setName(name);
            shiroUser.setPwd(pwd);
            shiroUserMapper.userReg(shiroUser);
            int rid = shiroRoleMapper.getRidByRole(role);
            shiroUserRoleMapper.createUser(uid, rid);
            return 0;
        } else {
            return -1;
        }


    }
}

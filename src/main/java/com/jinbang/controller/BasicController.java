package com.jinbang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jinbang.mapper.ShiroRoleMapper;
import com.jinbang.mapper.ShiroUserMapper;
import com.jinbang.model.ShiroUser;
import com.jinbang.model.User_Roles_Rscs;
import com.jinbang.service.ItemService;
import com.jinbang.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class BasicController {
    @Autowired
    ShiroUserMapper shiroUserMapper;

    @Autowired
    ItemService itemService;
    @Autowired
    ShiroService shiroService;
    @Autowired
    ShiroRoleMapper shiroRoleMapper;

    @GetMapping("/registradio")
    public Map<String,Object> regist (@RequestBody JSONObject request){
        Map<String, Object> map = new HashMap<String, Object>();
        Set<String> roles = new HashSet<String>(shiroRoleMapper.getall());
        map.put("data", roles);
        map.put("state", "success");
        return map;
    }

    @PostMapping("/regist")
    public Map<String,Object> regist (@RequestBody JSONObject request, HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject data = JSON.parseObject(request.get("data").toString());
        String username = data.get("name").toString();
        String pwd = data.get("pwd").toString();
        String role = data.get("role").toString();

        int rslt = shiroService.regist(username, role, role);
        if(rslt == 0) {
            map.put("state", "success");
        } else {
            map.put("state", "err");
        }
        return map;
    }

    @PostMapping("/home")
    public Map<String,Object> login (@RequestBody JSONObject request, HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject data = JSON.parseObject(request.get("data").toString());
        String name = data.get("name").toString();
        String serverSession = name + "," + System.currentTimeMillis();

        ShiroUser userDB = shiroUserMapper.loadByUserName(name);
        if(userDB != null){
            String pwdDB = userDB.getPwd();
            if(pwdDB.equals(request.get("pwd").toString())){
                System.out.println(name + " 密码正确登录成功");
                session.setAttribute(name, serverSession);
                User_Roles_Rscs user_roles_rscs = shiroService.userDetail(name);

                map.put("state", "success");
                map.put("data", user_roles_rscs);
                map.put("session", serverSession);
            } else {
                map.put("state", "err");
            }
        } else {
            System.out.println(name + " 不存在");
            map.put("state", "err");
        }
        return map;
    }

    @GetMapping("/logout")
    public Map<String,Object> signout(@RequestBody JSONObject request, HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        String clientsession = request.get("session").toString();
        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
            System.out.println(name + " 注销");
            session.removeAttribute(name);

            map.put("state", "success");
        } else{
            map.put("state", "err");
        }
        return map;
    }
}

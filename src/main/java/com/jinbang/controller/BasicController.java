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
    public ResponseEntity<JSONObject> regist (){
        JSONObject map = new JSONObject();

        Set<String> roles = new HashSet<String>(shiroRoleMapper.getall());
        map.put("data", roles);
        map.put("state", "success");
        return new ResponseEntity<JSONObject>(map, HttpStatus.OK);
    }

    @PostMapping("/regist")
    public ResponseEntity<JSONObject> regist (@RequestBody JSONObject request, HttpSession session){
        JSONObject map = new JSONObject();
        JSONObject data = JSON.parseObject(request.get("data").toString());
        String username = data.get("name").toString();
        String pwd = data.get("pwd").toString();
        String role = data.get("role").toString();

        int rslt = shiroService.regist(username, pwd, role);
        if(rslt == 0) {
            map.put("state", "success");
        } else {
            map.put("state", "err");
            map.put("msg", "用户名已存在");
        }
        return new ResponseEntity<JSONObject>(map, HttpStatus.OK);
    }

    @PostMapping("/home")
    public ResponseEntity<JSONObject> login (@RequestBody JSONObject request, HttpSession session){
        JSONObject map = new JSONObject();
        JSONObject data = JSON.parseObject(request.get("data").toString());
        String name = data.get("name").toString();
        String serverSession = name + "," + System.currentTimeMillis();

        ShiroUser userDB = shiroUserMapper.loadByUserName(name);
        if(userDB != null){
            String pwdDB = userDB.getPwd();
            if(pwdDB.equals(data.get("pwd").toString())){
                System.out.println(name + " 密码正确登录成功");
                session.setAttribute(name, serverSession);
                User_Roles_Rscs user_roles_rscs = shiroService.userDetail(name);

                map.put("state", "success");
                map.put("data", user_roles_rscs);
                map.put("session", serverSession);
            } else {
                map.put("state", "err");
                map.put("msg", "密码错误");
            }
        } else {
            System.out.println(name + " 不存在");
            map.put("state", "err");
            map.put("msg", "用户不存在");
        }
        return new ResponseEntity<JSONObject>(map, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<JSONObject> logout(@RequestBody JSONObject request, HttpSession session){
        JSONObject map = new JSONObject();
        JSONObject data = JSON.parseObject(request.get("data").toString());
        String clientsession = data.get("session").toString();
        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
            System.out.println(name + " 注销");
            session.removeAttribute(name);
            map.put("state", "success");
        } else{
            map.put("state", "err");
            map.put("msg", "用户未登录");
        }
        return new ResponseEntity<JSONObject>(map, HttpStatus.OK);
    }
}

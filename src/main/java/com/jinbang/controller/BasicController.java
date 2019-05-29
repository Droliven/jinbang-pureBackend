package com.jinbang.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinbang.mapper.UserMapper;
import com.jinbang.model.Item_Asr_Usr_IK_Kp;
import com.jinbang.model.User;
import com.jinbang.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class BasicController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ItemService itemService;

    @RequestMapping("/")
    public ResponseEntity<Map<String,Object>> cover(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", "index page");
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }
    @PostMapping("/home")
    public ResponseEntity<Map<String,Object>> signinPost(@RequestBody JSONObject request, HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        String name = request.get("name").toString();
        User userDB = userMapper.loadByUserName(name);
        if(userDB != null){
//            String pwdDB = DigestUtils.md5DigestAsHex(userDB.getPwd().getBytes());
            String pwdDB = userDB.getPwd();
//            System.out.println("user.getPwd() " + user.getPwd());
//            System.out.println("pwdDB " + pwdDB);
            if(pwdDB.equals(request.get("pwd").toString())){
                System.out.println(name + " 密码正确登录成功");
                // 写入 session
                session.setAttribute("name", name);
//                // 打印 cookie
//                Cookie[] cookies = request.getCookies();
//                if (cookies != null && cookies.length > 0) {
//                    for (Cookie cookie : cookies) {
//                        System.out.println(cookie.getName() + " : " + cookie.getValue());
//                    }
//                }
            } else {
//                System.out.println("密码错误");
                map.put("err", "Wrong pwd!");
                return new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
            }
        } else {
            System.out.println(name + " 不存在");
            map.put("err", "Name not exists!");
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
        }
        map.put("success", "Login successful!");
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }
    @RequestMapping("/signout")
    public ResponseEntity<Map<String,Object>> signout(HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        // 取出 session 中的 name
        Object name = session.getAttribute("name");
        System.out.println("注销！");
        session.removeAttribute("name");
        map.put("success", "Logged out!");
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }
}

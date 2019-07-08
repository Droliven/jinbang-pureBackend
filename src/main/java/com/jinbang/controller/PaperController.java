package com.jinbang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinbang.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class PaperController {
    @Autowired
    PaperService paperService;

    @PostMapping("/createEmptyPaper")
    public ResponseEntity<JSONObject> createEmptyPaper(@RequestBody JSONObject request, HttpSession session){
        JSONObject map = new JSONObject();

        String clientsession = request.get("session").toString();

        JSONObject data = JSON.parseObject(request.get("data").toString());
        JSONObject userdetail = data.getJSONObject("userdetail");
        Set<String> rscs = new HashSet<>(JSON.parseArray(userdetail.get("shiroResources").toString(), String.class));

        JSONObject realdata = data.getJSONObject("data");

        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
            if(rscs.contains("createEmptyPaper")) {
                // 业务代码
                int pid = paperService.createEmptyPaper(realdata);
                System.out.println("createEmptyPaper");

                String serverSession = name + "," + System.currentTimeMillis();
                session.setAttribute(name, serverSession);
                map.put("data", pid);
                map.put("state", "success");
                map.put("session", serverSession);
            } else {
                map.put("state", "err");
                map.put("msg", "没有权限");
            }
        } else {
            map.put("state", "err");
            map.put("msg", "未登录");
        }
        return new ResponseEntity<JSONObject>(map, HttpStatus.OK);
    }

    @PostMapping("/buildPaper")
    public ResponseEntity<JSONObject> buildPaper(@RequestBody JSONObject request, HttpSession session){
        JSONObject map = new JSONObject();

        String clientsession = request.get("session").toString();

        JSONObject data = JSON.parseObject(request.get("data").toString());
        JSONObject userdetail = data.getJSONObject("userdetail");
        Set<String> rscs = new HashSet<>(JSON.parseArray(userdetail.get("shiroResources").toString(), String.class));

        JSONObject realdata = data.getJSONObject("data");

        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
            if(rscs.contains("buildPaper")) {
                // 业务代码
                System.out.println("buildPaper");
                paperService.buildPaper(realdata);
                map.put("state", "success");
            } else {
                map.put("state", "err");
                map.put("msg", "没有权限");
            }
        } else {
            map.put("state", "err");
            map.put("msg", "未登录");
        }
        String serverSession = name + "," + System.currentTimeMillis();
        session.setAttribute(name, serverSession);
        map.put("session", serverSession);
        return new ResponseEntity<JSONObject>(map, HttpStatus.OK);
    }

    @PostMapping("/getAllPaperDetail")
    public ResponseEntity<JSONObject> getAllPaperDetail(@RequestBody JSONObject request, HttpSession session){
        JSONObject map = new JSONObject();

        String clientsession = request.get("session").toString();

        JSONObject data = JSON.parseObject(request.get("data").toString());
        JSONObject userdetail = data.getJSONObject("userdetail");
        Set<String> rscs = new HashSet<>(JSON.parseArray(userdetail.get("shiroResources").toString(), String.class));

//        JSONObject realdata = data.getJSONObject("data");

        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
            if(rscs.contains("getAllPaperDetail")) {
                // 业务代码
                System.out.println("getAllPaperDetail");
                JSONArray allPaperAndBuildpapers = paperService.getAllPaperDetail();

                String serverSession = name + "," + System.currentTimeMillis();
                session.setAttribute(name, serverSession);
                map.put("data", allPaperAndBuildpapers);
                map.put("state", "success");
                map.put("session", serverSession);
            } else {
                map.put("state", "err");
                map.put("msg", "没有权限");
            }
        } else {
            map.put("state", "err");
            map.put("msg", "未登录");
        }
//        System.out.println(map.toString());
        return new ResponseEntity<JSONObject>(map, HttpStatus.OK);
    }
}

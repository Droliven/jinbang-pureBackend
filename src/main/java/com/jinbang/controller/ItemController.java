package com.jinbang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinbang.mapper.ItemMapper;
import com.jinbang.mapper.KnowledgePointMapper;
import com.jinbang.mapper.ShiroUserMapper;
import com.jinbang.model.Item_Asr_Usr_IK_Kp;
import com.jinbang.service.ItemService;
import com.jinbang.service.KPPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    ShiroUserMapper userMapper;
    @Autowired
    KnowledgePointMapper knowledgePointMapper;
    @Autowired
    KPPathService kpPathService;

    @PostMapping("/itemradio")
    public ResponseEntity<JSONObject> itemradio(@RequestBody JSONObject request, HttpSession session){
        JSONObject map = new JSONObject();

        String clientsession = request.get("session").toString();

        JSONObject data = JSON.parseObject(request.get("data").toString());
        JSONObject userdetail = data.getJSONObject("userdetail");
        Set<String> rscs = new HashSet<>(JSON.parseArray(userdetail.get("shiroResources").toString(), String.class));

        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
            if(rscs.contains("itemradio")) {
                // 业务代码
                System.out.println("itemradio");
                List<HashMap> itemlist = itemService.itemradio();

                String serverSession = name + "," + System.currentTimeMillis();
                session.setAttribute(name, serverSession);
                map.put("data", itemlist);
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

    @PostMapping("/itemchoose")
    public ResponseEntity<JSONObject> itemchoose(@RequestBody JSONObject request, HttpSession session){
        JSONObject map = new JSONObject();

        String clientsession = request.get("session").toString();

        JSONObject data = JSON.parseObject(request.get("data").toString());
        JSONObject userdetail = data.getJSONObject("userdetail");
        Set<String> rscs = new HashSet<>(JSON.parseArray(userdetail.get("shiroResources").toString(), String.class));

        JSONObject realdata = data.getJSONObject("data");

        String name = clientsession.split(",")[0];

        if(session.getAttribute(name) != null) {
            if(rscs.contains("itemchoose")) {
                // 业务代码
                System.out.println("itemchoose");

                String type = "", grade = "", source = "", dataname = "";
                if(realdata.get("type") != null){
                    type = realdata.get("type").toString();
                }
                if(realdata.get("grade") != null){
                    grade = realdata.get("grade").toString();
                }
                if(realdata.get("source") != null){
                    source = realdata.get("source").toString();
                }
                if(realdata.get("name") != null){
                    dataname = realdata.get("name").toString();
                }
                if(!(realdata.get("path") == null || realdata.get("path").equals(""))){
                    String path = realdata.get("path").toString();
                    System.out.println("controller path: " + path);
                    String [] pathssplit = path.split("/");
                    String kp = pathssplit[pathssplit.length-1];
                    System.out.println("controller kp: " + kp);
                    List<Item_Asr_Usr_IK_Kp> item_asr_usr_ik_kps = itemService.itemchoose(type, grade, source, dataname, kp);
                    map.put("data", item_asr_usr_ik_kps);
                } else {
                    String path = "";
                    List<Item_Asr_Usr_IK_Kp> item_asr_usr_ik_kps = itemService.itemchoose(type, grade, source, name, path);
                    map.put("data", item_asr_usr_ik_kps);
                }
                String serverSession = name + "," + System.currentTimeMillis();
                session.setAttribute(name, serverSession);
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

    @PostMapping ("/itemDeleteByIids")
    public ResponseEntity<JSONObject> itemDeleteByIids(@RequestBody JSONObject request, HttpSession session){
        JSONObject map = new JSONObject();

        String clientsession = request.get("session").toString();

        JSONObject data = JSON.parseObject(request.get("data").toString());
        JSONObject userdetail = data.getJSONObject("userdetail");
        Set<String> rscs = new HashSet<>(JSON.parseArray(userdetail.get("shiroResources").toString(), String.class));

        JSONObject realdata = data.getJSONObject("data");

        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
            if(rscs.contains("itemDeleteByIids")) {
                System.out.println("itemDeleteByIids");
                JSONArray reqArray = JSON.parseArray(data.get("iids").toString());
                JSONObject jsonObject = itemService.itemDeleteByIids(reqArray);

                String serverSession = name + "," + System.currentTimeMillis();
                session.setAttribute(name, serverSession);
                map.put("data", jsonObject);
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

    @PostMapping("/getRestBranch")
    public ResponseEntity<JSONObject> getRestBranch(@RequestBody JSONObject request, HttpSession session){
        JSONObject map = new JSONObject();

        String clientsession = request.get("session").toString();

        JSONObject data = JSON.parseObject(request.get("data").toString());
        JSONObject userdetail = data.getJSONObject("userdetail");
        Set<String> rscs = new HashSet<>(JSON.parseArray(userdetail.get("shiroResources").toString(), String.class));

        JSONObject realdata = data.getJSONObject("data");

        String name = clientsession.split(",")[0];

        if(session.getAttribute(name) != null){
            if(rscs.contains("getRestBranch")) {
                // 业务代码
                String node = data.get("node").toString();
                JSONArray jsonArray = kpPathService.getRestBranch(node);
                System.out.println("getRestBranch");

                String serverSession = name + "," + System.currentTimeMillis();
                session.setAttribute(name, serverSession);
                map.put("data", jsonArray);
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

    @PostMapping("/addKpByPath")
    public ResponseEntity<JSONObject> addKpByPath(@RequestBody JSONObject request, HttpSession session) {
        JSONObject map = new JSONObject();

        String clientsession = request.get("session").toString();

        JSONObject data = JSON.parseObject(request.get("data").toString());
        JSONObject userdetail = data.getJSONObject("userdetail");
        Set<String> rscs = new HashSet<>(JSON.parseArray(userdetail.get("shiroResources").toString(), String.class));

        JSONObject realdata = data.getJSONObject("data");

        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
            if(rscs.contains("addKpByPath")) {
                // 业务代码
                String path = data.get("path").toString();;
                System.out.println("path: " + path);
                kpPathService.addKpByPath(path);
                System.out.println("addKpByPath");

                String serverSession = name + "," + System.currentTimeMillis();
                session.setAttribute(name, serverSession);
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

    @PostMapping ("/editItemFully")
    public ResponseEntity<JSONObject> editItemFully(@RequestBody JSONObject request, HttpSession session){
        JSONObject map = new JSONObject();

        String clientsession = request.get("session").toString();

        JSONObject data = JSON.parseObject(request.get("data").toString());
        JSONObject userdetail = data.getJSONObject("userdetail");
        Set<String> rscs = new HashSet<>(JSON.parseArray(userdetail.get("shiroResources").toString(), String.class));

        JSONObject realdata = data.getJSONObject("data");

        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
            if(rscs.contains("editItemFully")) {
                // 业务代码
                itemService.editItemFully(realdata);
                System.out.println("editItemFully");

                String serverSession = name + "," + System.currentTimeMillis();
                session.setAttribute(name, serverSession);
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

    @PostMapping("/addItemFully")
    public ResponseEntity<JSONObject> addItemFully(@RequestBody JSONObject request, HttpSession session) {
        JSONObject map = new JSONObject();

        String clientsession = request.get("session").toString();

        JSONObject data = JSON.parseObject(request.get("data").toString());
        JSONObject userdetail = data.getJSONObject("userdetail");
        Set<String> rscs = new HashSet<>(JSON.parseArray(userdetail.get("shiroResources").toString(), String.class));

        JSONObject realdata = data.getJSONObject("data");

        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
            if(rscs.contains("addItemFully")) {
                // 业务代码
                System.out.println("addItemFully");
                itemService.addItemFully(realdata);

                String serverSession = name + "," + System.currentTimeMillis();
                session.setAttribute(name, serverSession);
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
}

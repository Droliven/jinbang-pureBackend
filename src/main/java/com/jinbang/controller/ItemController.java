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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,Object> itemradio(@RequestBody JSONObject request, HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        String clientsession = request.get("session").toString();
        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
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
            map.put("msg", "未登录");
        }
        return map;
    }

    @PostMapping("/itemchoose")
    public Map<String,Object> itemchoose(@RequestBody JSONObject request, HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject data = JSON.parseObject(request.get("data").toString());
        String clientsession = request.get("session").toString();
        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
            System.out.println("itemchoose");

            String type = "", grade = "", source = "", dataname = "";
            if(data.get("type") != null){
                type = data.get("type").toString();
            }
            if(data.get("grade") != null){
                grade = data.get("grade").toString();
            }
            if(data.get("source") != null){
                source = data.get("source").toString();
            }
            if(data.get("name") != null){
                dataname = data.get("name").toString();
            }
            if(!(data.get("path") == null || data.get("path").equals(""))){
                String path = data.get("path").toString();
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
            map.put("msg", "未登录");
        }
        return map;
    }

    @PostMapping ("/itemDeleteByIids")
    public Map<String,Object> itemDeleteByIids(@RequestBody JSONObject request, HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject data = JSON.parseObject(request.get("data").toString());
        String clientsession = request.get("session").toString();
        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
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
            map.put("msg", "未登录");
        }
        return map;
    }

    @PostMapping("/getRestBranch")
    public Map<String,Object> getRestBranch(@RequestBody JSONObject request, HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject data = JSON.parseObject(request.get("data").toString());
        String clientsession = request.get("session").toString();
        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
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
            map.put("msg", "未登录");
        }
        return map;
    }

    @PostMapping("/addKpByPath")
    public Map<String,Object> addKpByPath(@RequestBody JSONObject request, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject data = JSON.parseObject(request.get("data").toString());
        String clientsession = request.get("session").toString();
        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
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
            map.put("msg", "未登录");
        }
        return map;
    }

    @PostMapping ("/editItemFully")
    public Map<String,Object> editItemFully(@RequestBody JSONObject request, HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject data = JSON.parseObject(request.get("data").toString());
        String clientsession = request.get("session").toString();
        String name = clientsession.split(",")[0];
        if(session.getAttribute(name) != null){
            itemService.editItemFully(data);
            System.out.println("editItemFully");

            String serverSession = name + "," + System.currentTimeMillis();
            session.setAttribute(name, serverSession);
            map.put("state", "success");
            map.put("session", serverSession);
        } else {
            map.put("state", "err");
            map.put("msg", "未登录");
        }
        return map;
    }

    @PostMapping("/addItemFully")
    public Map<String,Object> addItemFully(@RequestBody JSONObject request, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject data = JSON.parseObject(request.get("data").toString());
        String clientsession = request.get("session").toString();
        String name = clientsession.split(",")[0];
        if (session.getAttribute(name) != null) {
            System.out.println("addItemFully");
            itemService.addItemFully(data);

            String serverSession = name + "," + System.currentTimeMillis();
            session.setAttribute(name, serverSession);
            map.put("state", "success");
            map.put("session", serverSession);
        } else {
            map.put("state", "err");
            map.put("msg", "未登录");
        }
        return map;
    }
}

package com.jinbang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinbang.mapper.ItemMapper;
import com.jinbang.mapper.KnowledgePointMapper;
import com.jinbang.mapper.UserMapper;
import com.jinbang.model.Item_Asr_Usr_IK_Kp;
import com.jinbang.service.ItemService;
import com.jinbang.service.KPPathService;
import com.sun.net.httpserver.HttpsParameters;
import jdk.nashorn.internal.ir.Symbol;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*",
        maxAge = 3600,
        methods = {RequestMethod.GET})
@RestController
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    KnowledgePointMapper knowledgePointMapper;
    @Autowired
    KPPathService kpPathService;

    @GetMapping("/itemall")
    public ResponseEntity<Map<String,Object>> itemall(HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(session.getAttribute("name")!=null){
            List<Item_Asr_Usr_IK_Kp> item_asr_usr_ik_kps;
            item_asr_usr_ik_kps = itemService.itemall();
            map.put("itemall", item_asr_usr_ik_kps);
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
        } else {
            map.put("err", "Not Logged!");
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/itemradio")
    public ResponseEntity<Map<String,Object>> itemradio(HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        if(session.getAttribute("name")!=null){
            List<HashMap> itemlist = itemService.itemradio();
            map.put("itemradio", itemlist);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("err", "Not Logged!");
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/itemchoose")
    public ResponseEntity<Map<String,Object>> itemchoose(HttpServletRequest request, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(session.getAttribute("name")!=null){
            String type = request.getParameter("type");
            String grade = request.getParameter("grade");
            String source = request.getParameter("source");
            String name = request.getParameter("name");
//        System.out.println("type: " + type + ", grade: " + grade + ", source: " + source + ", name: " + name);
            List<Item_Asr_Usr_IK_Kp> item_asr_usr_ik_kps = itemService.itemchoose(type, grade, source, name);
            map.put("itemchoose", item_asr_usr_ik_kps);
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
        } else {
            map.put("err", "Not Logged!");
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
        }
    }

    //    @DeleteMapping ("/itemDeleteByIids")
//    public ResponseEntity<JSONObject> itemDeleteByIids(HttpServletRequest request, HttpSession session){
//        if(session.getAttribute("name")!=null){
//            JSONArray jsonArray = JSON.parseArray(request.getParameter("iids").toString());
//            JSONObject jsonObject = itemService.itemDeleteByIids(jsonArray);
//            return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
//        } else {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("err", "Not Logged!");
//            return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.BAD_REQUEST);
//        }
//    }
//    @DeleteMapping ("/itemDeleteByIids")
//    public JSONObject itemDeleteByIids(HttpServletRequest request){
//        JSONObject jsonParam = null;
//        try {
//            // 获取输入流
//            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
//            // 写入数据到Stringbuilder
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = streamReader.readLine()) != null) {
//                sb.append(line);
//            }
//            jsonParam = JSONObject.parseObject(sb.toString());
//            // 直接将json信息打印出来
//            System.out.println(jsonParam.toJSONString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return jsonParam;
//    }

    @DeleteMapping ("/itemDeleteByIids")
    public ResponseEntity<JSONObject> itemDeleteByIids(@RequestBody JSONObject jsonParam, HttpSession session){
        if(session.getAttribute("name")!=null){
            // 直接将json信息打印出来
            JSONArray jsonArray = JSON.parseArray(jsonParam.get("iids").toString());
//            System.out.println(jsonArray.toString());
            JSONObject jsonObject = itemService.itemDeleteByIids(jsonArray);
            return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("err", "Not Logged!");
            return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/getRestBranch")
    public ResponseEntity<JSONArray> getRestBranch(HttpServletRequest request, HttpSession session){
        if(session.getAttribute("name")!=null){
            String node = request.getParameter("node");
            JSONArray jsonArray = kpPathService.getRestBranch(node);
            return new ResponseEntity<JSONArray>(jsonArray, HttpStatus.OK);
        } else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.add("Not Logged!");
            return new ResponseEntity<JSONArray>(jsonArray, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addKpByPath")
    public ResponseEntity<String> addKpByPath(@RequestBody JSONObject jsonParam, HttpSession session) {
        if(session.getAttribute("name")!=null){
            String path = jsonParam.get("path").toString();
            kpPathService.addKpByPath(path);
            return new ResponseEntity<String>("Knowledgepoints Added!", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Not Logged!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping ("/editItemFully")
    public ResponseEntity<JSONObject> editItemFully(@RequestBody JSONObject jsonParam, HttpSession session){
        if(session.getAttribute("name")!=null){
            itemService.editItemFully(jsonParam);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Success!", "Edit fully!");
            return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Err!", "Not Logged!");
            return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addItemFully")
    public ResponseEntity<JSONObject> addItemFully(@RequestBody JSONObject jsonParam, HttpSession session){
        if(session.getAttribute("name")!=null){
            itemService.addItemFully(jsonParam);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Success!", "Add fully!");
            return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Err!", "Not Logged!");
            return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.BAD_REQUEST);
        }
    }
}

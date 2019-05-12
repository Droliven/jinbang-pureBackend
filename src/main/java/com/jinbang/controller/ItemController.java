package com.jinbang.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jinbang.mapper.ItemMapper;
import com.jinbang.mapper.UserMapper;
import com.jinbang.model.Item;
import com.jinbang.model.ItemTableJson;
import com.jinbang.model.Item_Asr_Usr_IK_Kp;
import com.jinbang.service.ItemService;
import javafx.beans.binding.ObjectExpression;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
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
}

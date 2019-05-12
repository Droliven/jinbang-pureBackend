package com.jinbang.controller;

import com.jinbang.mapper.ItemMapper;
import com.jinbang.mapper.UserMapper;
import com.jinbang.model.Item;
import com.jinbang.model.ItemTableJson;
import com.jinbang.model.Item_Asr_Usr_IK_Kp;
import com.jinbang.service.ItemService;
import javafx.beans.binding.ObjectExpression;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public List<Item_Asr_Usr_IK_Kp> itemall(){
        List<Item_Asr_Usr_IK_Kp> item_asr_usr_ik_kps;
        item_asr_usr_ik_kps = itemService.itemall();
        return item_asr_usr_ik_kps;
    }
    @GetMapping("/itemradio")
    public List<HashMap> itemradio(){
        List<HashMap> itemlist = itemService.itemradio();
        return itemlist;
    }
    @PostMapping("/itemchoose")
    public List<Item_Asr_Usr_IK_Kp> itemchoose(HttpServletRequest request){
        String type = request.getParameter("type");
        String grade = request.getParameter("grade");
        String source = request.getParameter("source");
        String name = request.getParameter("name");
//        System.out.println("type: " + type + ", grade: " + grade + ", source: " + source + ", name: " + name);
        List<Item_Asr_Usr_IK_Kp> item_asr_usr_ik_kps = itemService.itemchoose(type, grade, source, name);
        return item_asr_usr_ik_kps;
    }
}

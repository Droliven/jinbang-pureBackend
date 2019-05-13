package com.jinbang;


import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateUserStatement;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.jinbang.mapper.ItemMapper;
import com.jinbang.mapper.KnowledgePointMapper;
import com.jinbang.mapper.UserMapper;
import com.jinbang.model.Item_Asr_Usr_IK_Kp;
import com.jinbang.model.Knowledgepoint;
import com.jinbang.model.User;
import com.jinbang.service.ItemService;
import com.jinbang.service.KPPathService;
import com.mysql.cj.xdevapi.JsonArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JinbangApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    ItemService itemService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    KnowledgePointMapper knowledgePointMapper;

    @Test
    public void UserTest(){
        User user;
        user = userMapper.loadByUserName("翠花");
        System.out.println(user);
        System.out.println(userMapper.getAll());
    }

//    @Test
//    public void ItemTest(){
//        Item item = new Item();
//        item.setAsrid(4);
//        item.setContent("5+8=____.");
//        item.setDifficulty(5);
//        item.setGrade("小学一年级");
//        item.setUid(2);
//        item.setIid(4);
//        item.setSource("自编");
//        item.setType("填空");
//
//        System.out.println(itemService.addItem(item));
//        System.out.println(itemService.getItemLikeContent("小朋友"));
//    }
//    @Test
//    public void testBcrypt(){
//        // Hash a password for the first time
//        String pwd = "123456";
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
//        String encodedPwd1 = bCryptPasswordEncoder.encode(pwd);
//        System.out.println(encodedPwd1);
//        String encodedPwd2 = bCryptPasswordEncoder.encode(pwd);
//        System.out.println(encodedPwd2);
//
//        Boolean res = bCryptPasswordEncoder.matches(pwd, encodedPwd2);
//        System.out.println(res);
//        Boolean res2 = bCryptPasswordEncoder.matches(encodedPwd1, encodedPwd2);
//        System.out.println(res2);
//    }
//    @Test
//    public void MD5(){
//        String pwd = "123456";
//        String md5Password1 = DigestUtils.md5DigestAsHex(pwd.getBytes());
//        System.out.println(md5Password1);
//        String md5Password2 = DigestUtils.md5DigestAsHex(pwd.getBytes());
//        System.out.println(md5Password2);
//        String md5Password3 = DigestUtils.md5DigestAsHex(pwd.getBytes());
//        System.out.println(md5Password3);
//    }
//    @Test
//    public void testItemGetTypes(){
//        List<String> getTypes = itemMapper.getTypes();
//        System.out.println(getTypes.toString());
//    }
//    @Test
//    public void testJackson() {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            List<Map<String, Object>> list = new ArrayList<>();
//            Knowledgepoint knowledgepoint = knowledgePointMapper.getKpById(1);
//            Map<String, Object> map = new HashMap<>();
//            map.put(knowledgepoint.getKnowledgepoint(), new ArrayList<>());
////            System.out.println(map.toString());
//            list.add(map);
//
//
////            System.out.println(list.toString());
//            String node = objectMapper.writeValueAsString(list);
//            System.out.println(node);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
    private void getAllPathsHelp(JSONArray fullJson, String fatherPath, int fatherId){
    JSONArray jsonArray = new JSONArray();
    List<Knowledgepoint> knowledgepoints = knowledgePointMapper.getKpsByPreId(fatherId);
    if(knowledgepoints != null){
        for(int i = 0; i < knowledgepoints.size(); i++){
            Knowledgepoint knowledgepoint = knowledgepoints.get(i);
            int id = knowledgepoint.getKpid();
            String kp = knowledgepoint.getKnowledgepoint();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(kp, new JSONArray());
            String path = fatherPath + "[" + i + "]." + kp;
            jsonArray.add(jsonObject);
            Boolean flag = JSONPath.set(fullJson, fatherPath, jsonArray);
            getAllPathsHelp(fullJson, path, id);
        }
    }
}
    @Test
    public void getAllPaths() {
        JSONArray jsonArray = new JSONArray();
        List<Knowledgepoint> knowledgepoints = knowledgePointMapper.getKpsByDepth(1);
        if(knowledgepoints != null){
            for(int i = 0; i < knowledgepoints.size(); i++){
                Knowledgepoint knowledgepoint = knowledgepoints.get(i);
                String kp = knowledgepoint.getKnowledgepoint();
                int id = knowledgepoint.getKpid();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(kp, new JSONArray());
                String path = "$[" + i + "]." + kp;
                jsonArray.add(jsonObject);
                getAllPathsHelp(jsonArray, path, id);
            }
        }
        System.out.println(jsonArray.toString());
    }
}

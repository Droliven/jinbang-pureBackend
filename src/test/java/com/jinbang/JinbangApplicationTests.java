package com.jinbang;


import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateUserStatement;
import com.alibaba.fastjson.*;
import com.jinbang.mapper.AnswerMapper;
import com.jinbang.mapper.ItemMapper;
import com.jinbang.mapper.KnowledgePointMapper;
import com.jinbang.mapper.UserMapper;
import com.jinbang.model.Item;
import com.jinbang.model.Item_Asr_Usr_IK_Kp;
import com.jinbang.model.Knowledgepoint;
import com.jinbang.model.User;
import com.jinbang.service.ItemService;
import com.jinbang.service.KPPathService;
import com.jinbang.service.PaperService;
import com.mysql.cj.xdevapi.JsonArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

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
    @Autowired
    KPPathService kpPathService;
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    PaperService paperService;

//    @Test
//    public void UserTest(){
//        User user;
//        user = userMapper.loadByUserName("翠花");
//        System.out.println(user);
//        System.out.println(userMapper.getAll());
//    }
//
//    @Test
//    public void LikeTest(){
//        System.out.println(answerMapper.getAnswersLikeContent("By the time"));
////        System.out.println(itemMapper.getItemLikeContent("等这条"));
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
//    private void getAllPathsHelp(JSONArray fullJson, String fatherPath, int fatherId){
//    JSONArray jsonArray = new JSONArray();
//    List<Knowledgepoint> knowledgepoints = knowledgePointMapper.getKpsByPreId(fatherId);
//    if(knowledgepoints != null){
//        for(int i = 0; i < knowledgepoints.size(); i++){
//            Knowledgepoint knowledgepoint = knowledgepoints.get(i);
//            int id = knowledgepoint.getKpid();
//            String kp = knowledgepoint.getKnowledgepoint();
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put(kp, new JSONArray());
//            String path = fatherPath + "[" + i + "]." + kp;
//            jsonArray.add(jsonObject);
//            Boolean flag = JSONPath.set(fullJson, fatherPath, jsonArray);
//            getAllPathsHelp(fullJson, path, id);
//        }
//    }
//}
//    @Test
//    public void testaddKpByPath(){
//        System.out.println(kpPathService.getRestBranch("").toString());
//        kpPathService.addKpByPath("数学/统计学/正态分布");
//        System.out.println(kpPathService.getRestBranch("").toString());
//    }
    @Test
    public void createEmptyPaper(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pid", 5);
        jsonObject.put("title", "shangda");
        jsonObject.put("name", "翠花");
        paperService.createEmptyPaper(jsonObject);
    }
}

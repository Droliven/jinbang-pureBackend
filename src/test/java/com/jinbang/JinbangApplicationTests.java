package com.jinbang;


import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateUserStatement;
import com.alibaba.fastjson.*;
import com.jinbang.mapper.AnswerMapper;
import com.jinbang.mapper.ItemMapper;
import com.jinbang.mapper.KnowledgePointMapper;
import com.jinbang.model.Item;
import com.jinbang.model.Item_Asr_Usr_IK_Kp;
import com.jinbang.model.Knowledgepoint;
import com.jinbang.service.ItemService;
import com.jinbang.service.KPPathService;
import com.jinbang.service.PaperService;
import com.jinbang.service.ShiroService;
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
    ShiroService shiroService;

    @Test
    public void test(){
        Set<String> rscs = shiroService.getRscsByName("白老板");
        System.out.println(rscs.toString());
    }
}

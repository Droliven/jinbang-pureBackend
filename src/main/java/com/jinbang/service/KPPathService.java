package com.jinbang.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.jinbang.mapper.KnowledgePointMapper;
import com.jinbang.model.Knowledgepoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KPPathService {
    @Autowired
    KnowledgePointMapper knowledgePointMapper;

    public String getKPPathByKpid(int kpid){
        String path = "";
        Knowledgepoint knowledgepoint = knowledgePointMapper.getKpById(kpid);
        if(knowledgepoint.getPrepoint() == -1){
            return knowledgepoint.getKnowledgepoint();
        } else{
            path = knowledgepoint.getKnowledgepoint() + path;
//            System.out.println(path);
            while (knowledgepoint.getPrepoint() != -1){
                knowledgepoint = knowledgePointMapper.getKpById(knowledgepoint.getPrepoint());
                path = knowledgepoint.getKnowledgepoint() + "/" + path;
//                System.out.println(path);
            }
            return path;
        }
    }

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

    public JSONArray getAllPaths() {
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
        return jsonArray;
    }
}

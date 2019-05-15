package com.jinbang.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.jinbang.mapper.KnowledgePointMapper;
import com.jinbang.model.Knowledgepoint;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String getKPPathByKpid(int kpid) {
        String path = "";
        Knowledgepoint knowledgepoint = knowledgePointMapper.getKpById(kpid);
        if (knowledgepoint.getPrepoint() == -1) {
            return knowledgepoint.getKnowledgepoint();
        } else {
            path = knowledgepoint.getKnowledgepoint() + path;
//            System.out.println(path);
            while (knowledgepoint.getPrepoint() != -1) {
                knowledgepoint = knowledgePointMapper.getKpById(knowledgepoint.getPrepoint());
                path = knowledgepoint.getKnowledgepoint() + "/" + path;
//                System.out.println(path);
            }
            return path;
        }
    }

    private void getAllPathsHelp(JSONArray fullJson, String fatherPath, int fatherId) {
        JSONArray jsonArray = new JSONArray();
        List<Knowledgepoint> knowledgepoints = knowledgePointMapper.getKpsByPreId(fatherId);
        if (knowledgepoints != null) {
            for (int i = 0; i < knowledgepoints.size(); i++) {
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

    public JSONArray getRestBranch(String node){
        // 通过某一结点名来获取该结点下的子树，即剩余分支
        JSONArray jsonArray = new JSONArray();
        List<Knowledgepoint> knowledgepoints;
        if(node.isEmpty()){
            knowledgepoints = knowledgePointMapper.getKpsByDepth(1);
        } else {
            Knowledgepoint preKp = knowledgePointMapper.getKpByKp(node);
            knowledgepoints = knowledgePointMapper.getKpsByPreId(preKp.getKpid());
        }
        if (knowledgepoints != null) {
            for (int i = 0; i < knowledgepoints.size(); i++) {
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

//    public JSONObject addKpByByPath(String path){
//        int maxKpid = knowledgePointMapper.maxKpid();
//        // 开始切割为结点
//        String [] nodes = path.split("/");
//        for(int i=0; i<nodes.length; i++) {
//            Knowledgepoint knowledgepoint = knowledgePointMapper.getKpByKp(nodes[i]);
//            if(knowledgepoint == null) {
//                if(i == 0) {
//                    knowledgepoint.setKnowledgepoint(nodes[i]);
//                    knowledgepoint.setPrepoint(-1);
//                    knowledgepoint.setKpid(maxKpid + 1);
//                    knowledgepoint.setDepth(1);
//                } else {
//
//                }
//            }
//        }
//        Knowledgepoint knowledgepoint = new Knowledgepoint();
//        if(knowledgePointMapper.getKpByKp(nodes[0]) == null){
//            for(int i = 0; i < nodes.length; i++){
//                if()
//            }
//        }
//            knowledgepoint.setDepth(1);
//            knowledgepoint.setPrepoint(-1);
//            knowledgepoint.setKnowledgepoint(path);
//        } else {
//            int fatherId;
//            int fatherDepth;
//            String fatherNode;
//            String childNode;
//            for(int i=0; i < nodes.length - 1; i++){
//                Knowledgepoint kp;
//                fatherNode = nodes[i];
//                childNode = nodes[i+1];
//                kp = knowledgePointMapper.getKpByKp(fatherNode);
//                if(kp == null){
//
//                }
//
//                knowledgepoint = knowledgePointMapper.getKpByKp(fatherNode);
//
//
//            }
//        }
//
//
//        if(fatherPath.equals("")){
//            knowledgepoint.setDepth(1);
//            knowledgepoint.setPrepoint(-1);
//        } else{
//
//            for(String node : nodes){
//
//            }
//
//            int prePoint = knowledgePointMapper.getKpByKp(nodes[nodes.length - 1]).getKpid();
//            int depth = knowledgePointMapper.getKpByKp(nodes[nodes.length - 1]).getDepth() + 1;
//            knowledgepoint.setDepth(depth);
//            knowledgepoint.setPrepoint(prePoint);
//        }
//        knowledgepoint.setKpid(maxKpid + 1);
//        knowledgepoint.setKnowledgepoint(branchKp);
//        analyze = knowledgePointMapper.addKnowledgePoint(knowledgepoint);
//        result.put("Affected knowledgepoint rows", analyze);
//        return result;
//    }
}

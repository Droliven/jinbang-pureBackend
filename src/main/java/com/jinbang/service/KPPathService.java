package com.jinbang.service;

import com.alibaba.fastjson.*;
import com.jinbang.mapper.KnowledgePointMapper;
import com.jinbang.model.Knowledgepoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KPPathService {
    @Autowired
    KnowledgePointMapper knowledgePointMapper;

    // 获取本项目中的这种特定树结构下的所有 key, 不具推广性。递归算法
    private void getAllKeys(String jsonArrayString, Set<String> keySet) {
        List<Map<String, Object>> listMap = JSON.parseObject(jsonArrayString, new TypeReference<List<Map<String,Object>>>(){});
        for(int i=0; i < listMap.size(); i++){
            // 处理 JSONObject 元素
            String elemKey = listMap.get(i).keySet().toString();
//            System.out.println("key " + elemKey);
            if(elemKey.length() > 2){
                String key = elemKey.substring(1, elemKey.length()-1);
                keySet.add(key);
//                System.out.println("value " + listMap.get(i).get(key).toString());
                getAllKeys(listMap.get(i).get(key).toString(), keySet);
            }
        }
    }

    // 判断 node1 是否是 node2 的祖先
    private boolean isAncestor(String node1, String node2) {

        if(node1.equals(node2)) {
            return false;
        } else if(knowledgePointMapper.getKpByKp(node1) == null || knowledgePointMapper.getKpByKp(node2) == null) {
            return false;
        } else {
            JSONArray jsonOfNode1 = getRestBranch(node1);
            Set<String> keySetOfNode1 = new HashSet<String>();
            getAllKeys(jsonOfNode1.toString(), keySetOfNode1);
            if(keySetOfNode1.contains(node2)) {
                return true;
            } else {
                return false;
            }
        }
    }

    // 递归算法。给定前驱路径，递归获取下一层路径，存于 Json
    private void getAllPathsHelp(JSONArray fullJson, String fatherPath, int fatherId) {
        JSONArray jsonArray = new JSONArray();
        // 获取父亲的所有孩子，构成数组
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

    // 传入 结点 id 获取从本脉树根到该结点的路径
    // 格式：path： "数学/线性代数/矩阵运算"
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

    // 通过某一结点名来获取该结点下的子树，即剩余分支。递归算法
    public JSONArray getRestBranch(String node){
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

    // 参数格式：path： "数学/线性代数/矩阵运算"
    // 在 knowledgepoint 表中新建知识点树杈
    public void addKpByPath(String path){

        int res;
        // 默认 path 不为空
        int maxKpid;
        // 开始切割为结点
        String [] nodes = path.split("/");
        // 逐个判断
        Knowledgepoint newNode = new Knowledgepoint();
        for(int i=0; i<nodes.length; i++) {
            maxKpid = knowledgePointMapper.maxKpid();
            Knowledgepoint kpSearch = knowledgePointMapper.getKpByKp(nodes[i]);
            // 用户链条结点系统树中不存在
            if(kpSearch == null) {
                // 在用户链条中找父亲
                if (i > 0) {
                    String fatherName = nodes[i - 1];
                    Knowledgepoint father = knowledgePointMapper.getKpByKp(fatherName);
                    newNode.setDepth(father.getDepth() + 1);
                    newNode.setPrepoint(father.getKpid());
                } else {
                    newNode.setDepth(1);
                    newNode.setPrepoint(-1);
                }
                newNode.setKnowledgepoint(nodes[i]);
                newNode.setKpid(maxKpid + 1);
                res = knowledgePointMapper.addKnowledgePoint(newNode);
            } else {
                // 用户链条结点系统树中存在
                if(i > 0) {
                    String fatherInChain = knowledgePointMapper.getKpByKp(nodes[i-1]).getKnowledgepoint();
                    String fatherInTree = knowledgePointMapper.getKpById(kpSearch.getPrepoint()).getKnowledgepoint();
                    if(isAncestor(fatherInTree, fatherInChain)) {
                        // 断链重连
                        newNode.setPrepoint(knowledgePointMapper.getKpByKp(nodes[i-1]).getKpid());
                        res = knowledgePointMapper.updateKpById(newNode);
                    }
                }
            }
        }
    }
}

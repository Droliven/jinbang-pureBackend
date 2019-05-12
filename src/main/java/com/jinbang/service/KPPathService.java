package com.jinbang.service;

import com.jinbang.mapper.KnowledgePointMapper;
import com.jinbang.model.Knowledgepoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
//    public List<?> getPaths(){
//        List<?> paths = new ArrayList<>();
//
//        return paths;
//    }
}

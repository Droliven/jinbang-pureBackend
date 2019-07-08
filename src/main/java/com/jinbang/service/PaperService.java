package com.jinbang.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jinbang.mapper.*;
import com.jinbang.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperService {
    @Autowired
    PaperMapper paperMapper;
    @Autowired
    BuildpaperMapper buildpaperMapper;
    @Autowired
    ShiroUserMapper userMapper;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    AnswerMapper answerMapper;

    public int createEmptyPaper(JSONObject jsonParam){
        System.out.println("jsonParam: " + jsonParam.toString());
        String title = jsonParam.get("title").toString();
        String name = jsonParam.get("name").toString();
        int uid = userMapper.getUidByName(name);
        int maxPid = paperMapper.maxPid();
        if(maxPid == -1){
            maxPid = 0;
        }
//        System.out.println("maxPid: " + maxPid);
        int rslt = paperMapper.addPaperQuick(maxPid+1, title, uid);
        return maxPid+1;
    }


    public void buildPaper(JSONObject jsonParam){
        String title = jsonParam.getString("title");
        int pid = paperMapper.getPidByTitle(title);
        List<Map<String, Object>> items = JSON.parseObject(jsonParam.get("items").toString(), new TypeReference<List<Map<String,Object>>>(){});
        for (Map<String, Object> item: items) {
            Buildpaper buildpaper = new Buildpaper();
            buildpaper.setIid((Integer) item.get("iid"));
            buildpaper.setPid(pid);
            buildpaper.setItemorder((Integer) item.get("itemorder"));
            buildpaper.setScore((Integer) item.get("score"));
            buildpaper.setTimemin((Integer) item.get("timemin"));
            buildpaperMapper.addBuildpaper(buildpaper);
        }
    }

    public JSONArray getAllPaperDetail(){
        JSONArray allPaperAndBuildpapers = new JSONArray();
        List<Paper> papers = paperMapper.getAll();
        for (Paper paperItem: papers) {
            JSONObject paperAndBuildpaperItem = new JSONObject();
            paperAndBuildpaperItem.put("pid", paperItem.getPid());
            paperAndBuildpaperItem.put("title", paperItem.getTitle());
            String builder = userMapper.getUserById(paperItem.getUid()).getName();
            paperAndBuildpaperItem.put("builder", builder);
            paperAndBuildpaperItem.put("sumscore", paperItem.getSumscore());
            paperAndBuildpaperItem.put("sumtimemin", paperItem.getSumtimemin());
            paperAndBuildpaperItem.put("buildpapers", new JSONArray());
            List<Buildpaper> buildpapers = buildpaperMapper.getBpByPid(paperItem.getPid());
            if(builder != null){
                JSONArray thisBuildpapers = new JSONArray();
                for (Buildpaper bpitem: buildpapers) {
                    int iid = bpitem.getIid();
                    Item item = itemMapper.getItemById(iid);
                    int asrid = item.getAsrid();
                    Answer answer = answerMapper.getAnswerById(asrid);
                    JSONObject itemJson = JSON.parseObject(JSON.toJSONString(item));
                    JSONObject answerJson = JSON.parseObject(JSON.toJSONString(answer));
                    JSONObject bp = JSON.parseObject(JSON.toJSONString(bpitem));
                    bp.put("item", itemJson);
                    bp.put("answer", answerJson);
                    thisBuildpapers.add(bp);
                }
                paperAndBuildpaperItem.put("buildpapers", thisBuildpapers);
            }
            allPaperAndBuildpapers.add(paperAndBuildpaperItem);
        }
        return allPaperAndBuildpapers;
    }
}

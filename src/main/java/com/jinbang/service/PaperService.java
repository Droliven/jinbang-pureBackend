package com.jinbang.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jinbang.mapper.*;
import com.jinbang.model.Answer;
import com.jinbang.model.Buildpaper;
import com.jinbang.model.Item;
import com.jinbang.model.Paper;
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
    UserMapper userMapper;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    AnswerMapper answerMapper;

    /**
     *
     * @param jsonParam: {
     *                 title: "上大数据库原理2期末",
     *                 name: "翠花"
     * }
     * @return
     */
    public void createEmptyPaper(JSONObject jsonParam){
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
        System.out.println(rslt);
    }

    /**
     * @param jsonParam: {
     *                 pid: 1
     *                 items: [{
     *                     iid: 1,
     *                     itemorder: 1,
     *                     score: 10,
     *                     timemin: 5
     *                 }, {
     *                     iid: 1,
     *                     itemorder: 1,
     *                     score: 10,
     *                     timemin: 5
     *                 }]
     * }
     */
    public void buildPaper(JSONObject jsonParam){
        int pid = (Integer) jsonParam.get("pid");
        List<Map<String, Object>> items = JSON.parseObject(jsonParam.get("items").toString(), new TypeReference<List<Map<String,Object>>>(){});
        for (Map<String, Object> item: items) {
            Buildpaper buildpaper = new Buildpaper();
            buildpaper.setIid((Integer) item.get("iid"));
            buildpaper.setPid(pid);
            buildpaper.setItemorder((Integer) item.get("itemorder"));
            buildpaper.setScore((Integer) item.get("score"));
            buildpaper.setTimemin((Integer) item.get("timemin"));
            int rslt = buildpaperMapper.addBuildpaper(buildpaper);
        }
    }

    /**
     * @return [{
     *     pid: 1,
     *     title: "shangda",
     *     组卷员: "翠花",
     *     sumscore: 100,
     *     sumtimemin: 150,
     *     buildpapers: [{
     *          pid: 1,
     *          iid: 5,
     *          itemorder: 1
     *          score: 10,
     *          timemin: 10,
     *          item: {
     *
     *          },
     *          answer: {
     *
     *          }
     *     }, {
    *           pid: 1,
     *          iid: 5,
     *          itemorder: 1
     *          score: 10,
     *          timemin: 10,
     *          item: {
     *
     *          },
     *          answer: {
     *
     *          }
     *     }]
     * }]
     */

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

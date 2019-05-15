package com.jinbang.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.jinbang.mapper.*;
import com.jinbang.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    Item_kpMapper item_kpMapper;
    @Autowired
    KnowledgePointMapper knowledgePointMapper;
    @Autowired
    KPPathService kpPathService;
    @Autowired
    BuildpaperMapper buildpaperMapper;

    private Item_Asr_Usr_IK_Kp getItem_Asr_Usr_IK_KpByIid(int iid){
        Item_Asr_Usr_IK_Kp item_asr_usr_ik_kp = new Item_Asr_Usr_IK_Kp();
        Item item = itemMapper.getItemById(iid);
        User user = userMapper.getUserById(item.getUid());
        Answer answer = answerMapper.getAnswerById(item.getAsrid());
        List<Item_kp> item_kps = item_kpMapper.getItem_kpByIid(item.getIid());
        int [] item_kps_pids = new int [item_kps.size()];
        List<Knowledgepoint> knowledgepoints = new ArrayList<Knowledgepoint>();
        if(item_kps != null){
            for (Item_kp item_kp : item_kps) {
                int kpid = item_kp.getKpid();
                Knowledgepoint knowledgepoint = knowledgePointMapper.getKpById(kpid);
                knowledgepoints.add(knowledgepoint);
            }
        }
        String path = "";
        if(knowledgepoints != null){
            for (Knowledgepoint knowledgepoint : knowledgepoints) {
                path = kpPathService.getKPPathByKpid(knowledgepoint.getKpid()) + "," + path;
            }
            path = path.substring(0, path.length()-1);
        }
        item_asr_usr_ik_kp.setItem(item);
        item_asr_usr_ik_kp.setAnswer(answer);
        item_asr_usr_ik_kp.setUser(user);
        item_asr_usr_ik_kp.setItem_kps(item_kps);
        item_asr_usr_ik_kp.setKnowledgepoints(knowledgepoints);
        item_asr_usr_ik_kp.setkPPath(path);
        return item_asr_usr_ik_kp;
    }
    public List<Item_Asr_Usr_IK_Kp> itemall(){
        List<Item_Asr_Usr_IK_Kp> item_asr_usr_ik_kps = new ArrayList<Item_Asr_Usr_IK_Kp>();
        List<Item>  items = itemMapper.getAll();
        Item_Asr_Usr_IK_Kp item_asr_usr_ik_kp;
        for (Item item : items) {
            item_asr_usr_ik_kp = this.getItem_Asr_Usr_IK_KpByIid(item.getIid());
            item_asr_usr_ik_kps.add(item_asr_usr_ik_kp);
        }
        return item_asr_usr_ik_kps;
    }
    public List<HashMap> itemradio(){
        List<HashMap> results = new ArrayList<HashMap>();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        HashMap hashMap5 = new HashMap();
        List<String> types = itemMapper.getTypes();
        List<String> sources = itemMapper.getSources();
        List<String> grades = itemMapper.getGrades();
        List<String> names = userMapper.getNames();
        JSONArray paths = kpPathService.getRestBranch("");
        hashMap.put("types", types);
        results.add(hashMap);
        hashMap2.put("sources", sources);
        results.add(hashMap2);
        hashMap3.put("grades", grades);
        results.add(hashMap3);
        hashMap4.put("names", names);
        results.add(hashMap4);
        hashMap5.put("paths", paths);
        results.add(hashMap5);
        return results;
    }
    public List<Item_Asr_Usr_IK_Kp> itemchoose(String type, String grade, String source, String name){
        int uid;
        if(name!=null){
            uid = userMapper.loadByUserName(name).getUid();
        } else {
            uid = -1;
        }
        List<Item> items = itemMapper.getItemsByGradeSourceTypeUid(type, grade, source, uid);
//        System.out.println(items.size());
        List<Integer> iids = new ArrayList<Integer>();
        for (Item item : items) {
            iids.add(item.getIid());
        }
        List<Item_Asr_Usr_IK_Kp> item_asr_usr_ik_kps = new ArrayList<Item_Asr_Usr_IK_Kp>();
        for (int iid: iids) {
            item_asr_usr_ik_kps.add(this.getItem_Asr_Usr_IK_KpByIid(iid));
        }
        return item_asr_usr_ik_kps;
    }

    public JSONObject itemDeleteByIids(JSONArray iidsJson){
        List<Integer> iids = new ArrayList<>();
        for(int i=0; i < iidsJson.size(); i++){
            iids.add(iidsJson.getInteger(i));
        }
//        System.out.println("service: " + iids.toString());
        JSONObject deleteResult = new JSONObject();
        for (int iid : iids){
            Item item = itemMapper.getItemById(iid);
            if(item != null){
                int asrId = itemMapper.getItemById(iid).getAsrid();
                deleteResult.put("Affected buildpaper rows", buildpaperMapper.deleteBpByIid(iid));
                deleteResult.put("Affected item_kp rows", item_kpMapper.deleteItem_kpByIid(iid));
                deleteResult.put("Affected item rows", itemMapper.deleteItemById(iid));
                deleteResult.put("Affected answer rows",answerMapper.deleteAnswerById(asrId));
            }
        }
        return deleteResult;
    }

    // 深度更新题目
    public JSONObject editItemFully(JSONObject oldAndNewItem_Asr_Usr_IK_Kp){
        // 需要注意的是，本函数是在修改 题目，答案，关联的知识点 id, 并不真正修改 knowledgepoint, 以及 path
        // 对 knowledgepoint, 以及 path 的修改是在 用户点击 多级下拉框选择的时候编辑的
        // 由于 kp 可能发生 断链重连，所以旧的 kpid 不可信，只有 kp 才是可信的，所以需要前端传入旧的 kp

        // 首先帮前端开发者 check id, 相信旧的数据是对的
        int iid, asrid, oldKpid, newKpid, uid;
        iid = (int) JSONPath.eval(oldAndNewItem_Asr_Usr_IK_Kp, "$.old.item.iid");
        asrid = (int) JSONPath.eval(oldAndNewItem_Asr_Usr_IK_Kp, "$.old.answer.asrid");
        uid = (int) JSONPath.eval(oldAndNewItem_Asr_Usr_IK_Kp, "$.old.user.uid");
        // kp 可能发生 断链重连，所以旧的 kpid 不可信，只有 kp 才是可信的
        String oldKpStr = JSONPath.eval(oldAndNewItem_Asr_Usr_IK_Kp, "$.old.knowledgepoint.knowledgepoint").toString();
        String newKpStr = JSONPath.eval(oldAndNewItem_Asr_Usr_IK_Kp, "$.new.knowledgepoint.knowledgepoint").toString();
        if(!oldKpStr.equals(newKpStr)){
            Knowledgepoint oldKp = knowledgePointMapper.getKpByKp(oldKpStr);
            oldKp = knowledgePointMapper.getKpByKp(oldKpStr);
            oldKpid = oldKp.getKpid();
            Knowledgepoint newKp = knowledgePointMapper.getKpByKp(newKpStr);
            newKpid = newKp.getKpid();
            // 先 删除旧的 item_kp
            int rslt = item_kpMapper.deleteItem_kpByKpidAndIid(oldKpid, iid);
            // 再插入新的 item_kp
            Item_kp item_kp = new Item_kp();
            item_kp.setDegree(5);
            item_kp.setKpid(newKpid);
            item_kp.setIid(iid);
            rslt = item_kpMapper.addItem_kp(item_kp);
            // 再 check kpid
            JSONPath.set(oldAndNewItem_Asr_Usr_IK_Kp, "$.new.item.kpid", newKpid);
        }
        JSONPath.set(oldAndNewItem_Asr_Usr_IK_Kp, "$.new.item.iid", iid);
        JSONPath.set(oldAndNewItem_Asr_Usr_IK_Kp, "$.new.item.asrid", asrid);
        JSONPath.set(oldAndNewItem_Asr_Usr_IK_Kp, "$.new.answer.asrid", asrid);
        JSONPath.set(oldAndNewItem_Asr_Usr_IK_Kp, "$.new.item.uid", uid);
        // 再修改数据
        JSONObject itemJson = JSON.parseObject(JSONPath.eval(oldAndNewItem_Asr_Usr_IK_Kp, "$.new.item").toString());
        Item item = (Item) JSONObject.toJavaObject(itemJson, Item.class);
        JSONObject asrJson = JSON.parseObject(JSONPath.eval(oldAndNewItem_Asr_Usr_IK_Kp, "$.new.answer").toString());
        Answer answer = (Answer) JSONObject.toJavaObject(asrJson, Answer.class);

        // 答案 asrId 不会变
        int reltAnswer = answerMapper.updateAnswerById(answer);
        // 题目 iid 不会变
        int rsltItem = itemMapper.upgradeItemById(item);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Success!", "Edit fully!");
        return jsonObject;
    }

    // 新建题目
    public JSONObject addItemFully(JSONObject item_Asr_Usr_IK_Kp){
        // 需要注意的是，本函数是在增肌 题目，答案，关联的知识点 id, 并不真正修改 knowledgepoint, 以及 path
        // 对 knowledgepoint, 以及 path 的修改是在 用户点击 多级下拉框选择的时候编辑的

        // 对前端数据 check id
        int iid, uid, asrid, kpid;
        String name = JSONPath.eval(item_Asr_Usr_IK_Kp, "$.user.name").toString();
        iid = itemMapper.maxIid() + 1;
        uid = userMapper.getUidByName(name);
        asrid = answerMapper.maxAsrid() + 1;
        String kpStr = JSONPath.eval(item_Asr_Usr_IK_Kp, "$.knowledgepoint.knowledgepoint").toString();
        kpid = knowledgePointMapper.getKpidByName(kpStr);
        JSONPath.set(item_Asr_Usr_IK_Kp, "$.item.iid", iid);
        JSONPath.set(item_Asr_Usr_IK_Kp, "$.item.uid", uid);
        JSONPath.set(item_Asr_Usr_IK_Kp, "$.item.kpid", kpid);
        JSONPath.set(item_Asr_Usr_IK_Kp, "$.answer.asrid", asrid);
        JSONPath.set(item_Asr_Usr_IK_Kp, "$.item_kp.iid", iid);
        JSONPath.set(item_Asr_Usr_IK_Kp, "$.item_kp.kpid", kpid);

        // 构造 Pojo
        JSONObject answerJson = JSON.parseObject(JSONPath.eval(item_Asr_Usr_IK_Kp, "$.answer").toString());
        Answer answer = (Answer) JSONObject.toJavaObject(answerJson, Answer.class);
        JSONObject itemJson = JSON.parseObject(JSONPath.eval(item_Asr_Usr_IK_Kp, "$.item").toString());
        Item item = (Item) JSONObject.toJavaObject(itemJson, Item.class);
        JSONObject item_kpJson = JSON.parseObject(JSONPath.eval(item_Asr_Usr_IK_Kp, "$.item_kp").toString());
        Item_kp item_kp = (Item_kp) JSONObject.toJavaObject(item_kpJson, Item_kp.class);

        // 插入顺序 answer, item, item_kp
        int rslt;
        rslt = answerMapper.addAnswer(answer);
        rslt = itemMapper.addItem(item);
        rslt = item_kpMapper.addItem_kp(item_kp);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Success!", "Added!");
        return jsonObject;
    }
}

package com.jinbang.model;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Item_Asr_Usr_IK_Kp {
    private Item item;
    private Answer answer;
    private ShiroUser user;
    private List<Item_kp> item_kps;
    private List<Knowledgepoint> knowledgepoints;
    public String kPPath;

    public void setItem(Item item){
        this.item = item;
    }
    public void setAnswer(Answer answer){
        this.answer = answer;
    }
    public void setUser(ShiroUser user){
        this.user = user;
    }
    public void setItem_kps(List<Item_kp> item_kps){
        this.item_kps = item_kps;
    }
    public void setKnowledgepoints(List<Knowledgepoint> knowledgepoints){
        this.knowledgepoints = knowledgepoints;
    }
    public void setkPPath(String kPPath){
        this.kPPath = kPPath;
    }
    public Item getItem(){
        return item;
    }
    public Answer getAnswer(){
        return answer;
    }
    public ShiroUser getUser(){
        return user;
    }
    public List<Item_kp> getItem_kps(){
        return item_kps;
    }
    public List<Knowledgepoint> getKnowledgepoints(){
        return knowledgepoints;
    }
    public String getkPPath(){
        return kPPath;
    }
}

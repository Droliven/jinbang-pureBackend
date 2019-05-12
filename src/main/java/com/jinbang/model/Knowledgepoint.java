package com.jinbang.model;

public class Knowledgepoint {
    private int kpid;
    private String knowledgepoint;
    private int prepoint;
    private int depth;

    public int getKpid(){
        return kpid;
    }
    public int getPrepoint(){
        return prepoint;
    }
    public int getDepth(){
        return depth;
    }
    public String getKnowledgepoint(){
        return knowledgepoint;
    }
    public void setKpid(int kpid){
        this.kpid = kpid;
    }
    public void setKnowledgepoint(String knowledgepoint){
        this.knowledgepoint = knowledgepoint;
    }
    public void setPrepoint(int prepoint){
        this.prepoint = prepoint;
    }
    public void setDepth(int depth){
        this.depth = depth;
    }
}

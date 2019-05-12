package com.jinbang.model;

public class Paper {
    private int pid;
    private String title;
    private int uid;
    private int sumscore;
    private int sumtimemin;

    public int getPid(){
        return pid;
    }
    public String getTitle(){
        return title;
    }
    public int getUid(){
        return uid;
    }
    public int getSumscore(){
        return sumscore;
    }
    public int getSumtimemin(){
        return sumtimemin;
    }
    public void setPid(int pid){
        this.pid = pid;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setUid(int uid){
        this.uid = uid;
    }
    public void setSumscore(int sumscore){
        this.sumscore = sumscore;
    }
    public void setSumtimemin(int sumtimemin){
        this.sumtimemin = sumtimemin;
    }
}

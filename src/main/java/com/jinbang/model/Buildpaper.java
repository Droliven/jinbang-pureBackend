package com.jinbang.model;

public class Buildpaper {
    private int pid;
    private int iid;
    private int itemorder;
    private int sumscore;
    private int sumtimemin;

    public void setPid(int pid){
        this.pid = pid;
    }
    public void setIid(int iid){
        this.iid = iid;
    }
    public void setItemorder(int itemorder){
        this.itemorder = itemorder;
    }
    public void setSumscore(int sumscore){
        this.sumscore = sumscore;
    }
    public void setSumtimemin(int sumtimemin){
        this.sumtimemin = sumtimemin;
    }
    public int getPid(){
        return pid;
    }
    public int getIid(){
        return iid;
    }
    public int getItemorder(){
        return itemorder;
    }
    public int getSumscore(){
        return sumscore;
    }
    public int getSumtimemin(){
        return sumtimemin;
    }
}

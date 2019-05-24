package com.jinbang.model;

public class Buildpaper {
    private int pid;
    private int iid;
    private int itemorder;
    private int score;
    private int timemin;

    public void setPid(int pid){
        this.pid = pid;
    }
    public void setIid(int iid){
        this.iid = iid;
    }
    public void setItemorder(int itemorder){
        this.itemorder = itemorder;
    }
    public void setScore(int sumscore){
        this.score = sumscore;
    }
    public void setTimemin(int sumtimemin){
        this.timemin = sumtimemin;
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
    public int getScore(){
        return score;
    }
    public int getTimemin(){
        return timemin;
    }
}

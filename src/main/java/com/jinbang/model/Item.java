package com.jinbang.model;

public class Item {
    private int iid;
    private String type;
    private String grade;
    private String source;
    private int difficulty;
    private int uid;
    private int asrid;
    private String content;

    public void setIid(int iid){
        this.iid = iid;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setGrade(String grade){
        this.grade = grade;
    }
    public void setSource(String source){
        this.source = source;
    }
    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    public void setUid(int uid){
        this.uid = uid;
    }
    public void setAsrid(int asrid){
        this.asrid = asrid;
    }
    public void setContent(String content){
        this.content = content;
    }
    public int getIid(){
        return iid;
    }
    public String getType(){
        return type;
    }
    public int getDifficulty(){
        return difficulty;
    }
    public int getUid(){
        return uid;
    }
    public int getAsrid(){
        return asrid;
    }
    public String getGrade(){
        return grade;
    }
    public String getSource(){
        return source;
    }
    public String getContent(){
        return content;
    }
    @Override
    public String toString() {
        return "{\"iid\": " + getIid() + ",\"grade\": " + getGrade() + ",\"source\": " + getSource() + ",\"type\": " + getType() + ",\"asrid\": " + getAsrid() + ",\"uid\": " + getUid() + ",\"difficulty\": " + getDifficulty() + ",\"content\": " + getContent() + "}";
    }
}

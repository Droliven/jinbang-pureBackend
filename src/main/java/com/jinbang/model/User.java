package com.jinbang.model;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
    private int uid;
    private String name;
    @JSONField(serialize=false)
    private String pwd;
    private String authority;

    public int getUid(){
        return uid;
    }
    public String getName(){
        return name;
    }
    public String getPwd(){
        return pwd;
    }
    public String getAuthority(){
        return authority;
    }
    public void setUid(int uid){
        this.uid = uid;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPwd(String pwd){
        this.pwd = pwd;
    }
    public void setAuthority(String authority){
        this.authority = authority;
    }
}

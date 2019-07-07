package com.jinbang.model;

import com.alibaba.fastjson.annotation.JSONField;

public class ShiroUser {
    private int uid;
    private String name;
    @JSONField(serialize=false)
    private String pwd;
    @JSONField(serialize=false)
    private String salt;
    @JSONField(serialize = false)
    private String shar256;

    public int getUid(){
        return uid;
    }
    public String getName(){
        return name;
    }
    public String getPwd(){
        return pwd;
    }
    public String getSalt() {
        return salt;
    }
    public String getShar256() {
        return shar256;
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
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public void setShar256(String shar256) {
        this.shar256 = shar256;
    }
}
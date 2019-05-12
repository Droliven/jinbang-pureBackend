package com.jinbang.model;

import java.util.List;

public class ItemTableJson {
    private int code;
    private String msg;
    private int count;
    private List<Item_Asr_Usr_IK_Kp> data;

    public void setCode(int code){
        this.code = code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public void setCount(int count){
        this.count = count;
    }
    public void setData(List<Item_Asr_Usr_IK_Kp> item_asr_usr_ik_kps){
        this.data = item_asr_usr_ik_kps;
    }
    public int getCode(){
        return code;
    }
    public int getCount(){
        return count;
    }
    public String getMsg(){
        return msg;
    }
    public List<Item_Asr_Usr_IK_Kp> getData(){
        return data;
    }
}

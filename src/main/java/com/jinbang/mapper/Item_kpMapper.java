package com.jinbang.mapper;

import com.jinbang.model.Item_kp;

import java.util.List;

public interface Item_kpMapper {
    int updateItem_kpByIidAndKpid(Item_kp item_kp);
    int addItem_kp(Item_kp item_kp);
    int deleteItem_kpByKpid(int kpid);
    int deleteItem_kpByIid(int iid);
    int deleteItem_kpByKpidAndIid(int kpid, int iid);
    List<Item_kp> getItem_kpByIid(int iid);
}

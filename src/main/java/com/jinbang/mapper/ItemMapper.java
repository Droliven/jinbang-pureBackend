package com.jinbang.mapper;

import com.jinbang.model.Answer;
import com.jinbang.model.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemMapper {
    int deleteItemById(int iid);
    int upgradeItemById(Item item);
    int addItem(Item item);
    Item getItemById(int iid);
    List<Item> getAll();
    List<Item> getItemsByType(String type);
    List<Item> getItemsByGrade(String grade);
    List<Item> getItemsBySource(String source);
    List<Item> getItemsByUid(int uid);
    Item getItemByAsrid(int asrid);
    List<Item> getItemLikeContent(String content);
    List<String> getTypes();
    List<String> getSources();
    List<String> getGrades();
    List<Item> getItemsByGradeSourceTypeUid(String type, String grade, String source, int uid);
    int maxIid();
}

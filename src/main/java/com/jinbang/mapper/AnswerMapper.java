package com.jinbang.mapper;

import com.jinbang.model.Answer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerMapper {
    int deleteAnswerById(int asrid);
    int updateAnswerById(Answer answer);
    int addAnswer(Answer answer);
    Answer getAnswerById(int asrid);
    List<Answer> getAll();
    List<Answer> getAnswersLikeContent(@Param(value = "content") String content);
    int maxAsrid();
}

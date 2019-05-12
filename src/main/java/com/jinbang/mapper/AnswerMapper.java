package com.jinbang.mapper;

import com.jinbang.model.Answer;

import java.util.List;

public interface AnswerMapper {
    int deleteAnswerById(int asrid);
    int updateAnswerById(Answer answer);
    int addAnswer(Answer answer);
    Answer getAnswerById(int asrid);
    List<Answer> getAll();
    List<Answer> getAnswersLikeContent(String content);
}

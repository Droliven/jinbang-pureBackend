package com.jinbang.mapper;

import com.jinbang.model.Paper;

import java.util.List;

public interface PaperMapper {
    int deletePaperByPid(int pid);
    int updatePaperByPid(Paper paper);
    int addPaper(Paper paper);
    int addPaperQuick(int pid, String title, int uid);
    Paper getPaperById(int pid);
    List<Paper> getAll();
    List<Paper> getPaperByUid(int uid);
    List<Paper> getPaperLikeTitle(String title);
    int getPidByTitle(String title);
    int maxPid();
}

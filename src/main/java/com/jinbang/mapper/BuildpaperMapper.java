package com.jinbang.mapper;

import com.jinbang.model.Buildpaper;

import java.util.List;

public interface BuildpaperMapper {
    int deleteBpByPid(int pid);
    int deleteBpByIid(int iid);
    int deleteBpByPidAndIid(int pid, int iid);
    int updateBpByPidAndIid(int pid, int iid);
    int addBuildpaper(Buildpaper buildpaper);
    List<Buildpaper> getBpByPid(int pid);
    List<Buildpaper> getBpByIid(int iid);
    Buildpaper getBpByPidAndIid(int pid, int iid);
}

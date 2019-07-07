package com.jinbang.mapper;

import com.jinbang.model.ShiroRoleRsc;

import java.util.List;

public interface ShiroRoleRscMapper {
    List<ShiroRoleRsc> getall();
    List<Integer> loadRscidByRid(int rid);
    List<Integer> loadRidByRscid(int rscid);
}

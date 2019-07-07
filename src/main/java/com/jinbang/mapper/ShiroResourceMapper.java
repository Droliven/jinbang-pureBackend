package com.jinbang.mapper;

import com.jinbang.model.ShiroResource;

import java.util.List;

public interface ShiroResourceMapper {
    ShiroResource loadByRscid(int rscid);
    String getResourceByRscid(int rscid);
    List<ShiroResource> getall();
}

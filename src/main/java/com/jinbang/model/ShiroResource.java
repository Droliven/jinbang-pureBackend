package com.jinbang.model;

public class ShiroResource {
    private int rscid;
    private String resource;

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setRscid(int rscid) {
        this.rscid = rscid;
    }

    public int getRscid() {
        return rscid;
    }

    public String getResource() {
        return resource;
    }
}

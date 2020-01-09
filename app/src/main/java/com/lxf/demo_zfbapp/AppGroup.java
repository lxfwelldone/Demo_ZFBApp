package com.lxf.demo_zfbapp;

import java.util.List;

public class AppGroup {

    private int id;
    private String name;
    private List<App> apps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    public AppGroup(String name, List<App> apps) {
        this.name = name;
        this.apps = apps;
    }
}

package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/17.
 */

public class TaskPointsFrequencyBean implements Serializable {
    private String name;
    private String potinsName;
    private String taskid;
    private String options;
    private String isEnd;
    private int  potins_index;
    private String ishadden;

    public String getIshadden() {
        return ishadden;
    }

    public void setIshadden(String ishadden) {
        this.ishadden = ishadden;
    }

    public int getPotins_index() {
        return potins_index;
    }

    public void setPotins_index(int potins_index) {
        this.potins_index = potins_index;
    }

    public String getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(String isEnd) {
        this.isEnd = isEnd;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPotinsName() {
        return potinsName;
    }

    public void setPotinsName(String potinsName) {
        this.potinsName = potinsName;
    }
}

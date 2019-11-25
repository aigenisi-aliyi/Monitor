package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/6/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class PersonsDataBean implements Serializable {
    /**
     * id : 2
     * taskid : 1
     * subject : 新丰泰汽车配件产业园环评项目
     * explainInfo : 说明
     * factorid : 1
     * SamplingTimes : 2
     * personid : 2
     * username : 赵 峰
     */

    private String id;
    private String taskid;
    private String subject;
    private String explainInfo;
    private String factorid;
    private String SamplingTimes;
    private String personid;
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getExplainInfo() {
        return explainInfo;
    }

    public void setExplainInfo(String explainInfo) {
        this.explainInfo = explainInfo;
    }

    public String getFactorid() {
        return factorid;
    }

    public void setFactorid(String factorid) {
        this.factorid = factorid;
    }

    public String getSamplingTimes() {
        return SamplingTimes;
    }

    public void setSamplingTimes(String SamplingTimes) {
        this.SamplingTimes = SamplingTimes;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

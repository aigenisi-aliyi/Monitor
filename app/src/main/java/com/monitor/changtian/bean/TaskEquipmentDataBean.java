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

public class TaskEquipmentDataBean implements Serializable {

    /**
     * id : 2
     * taskid : 1
     * subject : 新丰泰汽车配件产业园环评项目
     * eid : 3
     * names : 可见分光光度计
     * userid : 1
     * username : 赵 涛
     * createtime : 2018-06-06 09:21:29
     */

    private String id;
    private String taskid;
    private String subject;
    private String eid;
    private String names;
    private String userid;
    private String username;
    private String createtime;

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

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}

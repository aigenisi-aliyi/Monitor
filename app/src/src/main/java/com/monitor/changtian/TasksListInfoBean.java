package com.monitor.changtian;

import java.io.Serializable;

/**
 * Created by ken on 2018/7/5.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TasksListInfoBean implements Serializable {

    /**
     * id : 8d1141e2-b608-4970-83f6-538934b081da
     * subject : 测试任务
     * content : 哒哒哒
     * userid : 1
     * username : 赵 涛
     * ctime : 2018-07-04 19:32:23
     * schemeid : 42efbbf5-a8d3-44aa-8623-3e6c29d289b9
     * schemename : 测试方案
     * manager : 我
     * address : 杭州市西湖区
     * starttime : 2018-07-05 00:00:00
     * endtime : 2018-07-06 00:00:00
     * coordinate : 108.73,34.25
     * remark :
     * conid : 18
     * pointsid : 6a9d32d6-064b-4ed1-ab68-a71021c56504
     */
    private String DaysNumber;
    private String frequency;
    private String id;
    private String subject;
    private String content;
    private String userid;
    private String username;
    private String ctime;
    private String schemeid;
    private String schemename;
    private String manager;
    private String address;
    private String starttime;
    private String endtime;
    private String coordinate;
    private String remark;
    private String conid;
    private String pointsid;

    private String taskstatusname;
    private String taskstatus;

    public String getTaskstatusname() {
        return taskstatusname;
    }

    public void setTaskstatusname(String taskstatusname) {
        this.taskstatusname = taskstatusname;
    }

    public String getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus;
    }

    private String isStatus;

    public String getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(String isStatus) {
        this.isStatus = isStatus;
    }

    public String getDaysNumber() {
        return DaysNumber;
    }

    public void setDaysNumber(String daysNumber) {
        DaysNumber = daysNumber;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid;
    }

    public String getSchemename() {
        return schemename;
    }

    public void setSchemename(String schemename) {
        this.schemename = schemename;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getConid() {
        return conid;
    }

    public void setConid(String conid) {
        this.conid = conid;
    }

    public String getPointsid() {
        return pointsid;
    }

    public void setPointsid(String pointsid) {
        this.pointsid = pointsid;
    }
}

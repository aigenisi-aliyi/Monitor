package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/19.
 */

public class TestProjectCycleListBean implements Serializable {

    /**
     * tpcid : 1
     * createtime : 2018-12-18 17:16:48
     * projectid : 140
     * projectname : 测试测试测试
     * taskNumber : 1
     * isEnd : 1
     * isEndname : 已结束
     * enduser : 4
     * username : admin
     * endtime : 2018-12-18 17:16:48
     * col1 :
     * col1name :
     * remark :
     * userid : 4
     * zqusername : admin
     */

    private String tpcid;
    private String createtime;
    private String projectid;
    private String projectname;
    private String taskNumber;
    private String isEnd;
    private String isEndname;
    private String enduser;
    private String username;
    private String endtime;
    private String col1;
    private String col1name;
    private String remark;
    private String userid;
    private String zqusername;

    public String getTpcid() {
        return tpcid;
    }

    public void setTpcid(String tpcid) {
        this.tpcid = tpcid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(String isEnd) {
        this.isEnd = isEnd;
    }

    public String getIsEndname() {
        return isEndname;
    }

    public void setIsEndname(String isEndname) {
        this.isEndname = isEndname;
    }

    public String getEnduser() {
        return enduser;
    }

    public void setEnduser(String enduser) {
        this.enduser = enduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol1name() {
        return col1name;
    }

    public void setCol1name(String col1name) {
        this.col1name = col1name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getZqusername() {
        return zqusername;
    }

    public void setZqusername(String zqusername) {
        this.zqusername = zqusername;
    }
}

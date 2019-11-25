package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/7/13.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class FieldsamplingInfo implements Serializable {


    /**
     * fsid : d0195684-8f5e-4eac-b333-17de2846604f
     * taskid : 05ecc7a7-a1f9-46cc-a900-d90fe6cafe42
     * address : 杭州市西湖区
     * coordinate : 108.73,34.25
     * pointsid : e5dfa07e-39a5-488c-ba6b-09970c903e56
     * leftcategoryid : 20
     * leftcategoryname : 地表水
     * createuser : 1
     * createusername : 赵 涛
     * remark :
     * createtime : 2018-07-12 15:26:40
     * IsEnd : 0
     * IsEndName : 否
     * endtime :
     * enduser :
     * endusername :
     * currentnumber : 1
     */

    private String fsid;
    private String taskid;
    private String address;
    private String coordinate;
    private String pointsid;
    private String leftcategoryid;
    private String leftcategoryname;
    private String createuser;
    private String createusername;
    private String remark;
    private String createtime;
    private String IsEnd;
    private String IsEndName;
    private String endtime;
    private String enduser;
    private String endusername;
    private String currentnumber;

    public String getFsid() {
        return fsid;
    }

    public void setFsid(String fsid) {
        this.fsid = fsid;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getPointsid() {
        return pointsid;
    }

    public void setPointsid(String pointsid) {
        this.pointsid = pointsid;
    }

    public String getLeftcategoryid() {
        return leftcategoryid;
    }

    public void setLeftcategoryid(String leftcategoryid) {
        this.leftcategoryid = leftcategoryid;
    }

    public String getLeftcategoryname() {
        return leftcategoryname;
    }

    public void setLeftcategoryname(String leftcategoryname) {
        this.leftcategoryname = leftcategoryname;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getIsEnd() {
        return IsEnd;
    }

    public void setIsEnd(String IsEnd) {
        this.IsEnd = IsEnd;
    }

    public String getIsEndName() {
        return IsEndName;
    }

    public void setIsEndName(String IsEndName) {
        this.IsEndName = IsEndName;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getEnduser() {
        return enduser;
    }

    public void setEnduser(String enduser) {
        this.enduser = enduser;
    }

    public String getEndusername() {
        return endusername;
    }

    public void setEndusername(String endusername) {
        this.endusername = endusername;
    }

    public String getCurrentnumber() {
        return currentnumber;
    }

    public void setCurrentnumber(String currentnumber) {
        this.currentnumber = currentnumber;
    }
}

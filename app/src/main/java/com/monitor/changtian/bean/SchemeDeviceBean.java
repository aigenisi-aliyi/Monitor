package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/7/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SchemeDeviceBean implements Serializable {


    /**
     * sdid : 20
     * schemeid : 42efbbf5-a8d3-44aa-8623-3e6c29d289b9
     * SchemeName : 测试方案
     * createuser : 1
     * username : 赵 涛
     * createtime : 2018-07-04 13:40:41
     * number : 1
     * pointsid : 6a9d32d6-064b-4ed1-ab68-a71021c56504
     * address : 杭州市西湖区
     * categoryid : 240
     * categoryname : 离子计
     */

    private String sdid;
    private String schemeid;
    private String SchemeName;
    private String createuser;
    private String username;
    private String createtime;
    private String sumnumber;
    private String pointsid;
    private String address;
    private String categoryid;
    private String categoryname;
    private String sumnumber_s;
    public String getSumnumber_s() {
        return sumnumber_s;
    }

    public void setSumnumber_s(String sumnumber_s) {
        this.sumnumber_s = sumnumber_s;
    }

    public String getSumnumber() {
        return sumnumber;
    }

    public void setSumnumber(String sumnumber) {
        this.sumnumber = sumnumber;
    }

    public String getSdid() {
        return sdid;
    }

    public void setSdid(String sdid) {
        this.sdid = sdid;
    }

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid;
    }

    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String SchemeName) {
        this.SchemeName = SchemeName;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
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


    public String getPointsid() {
        return pointsid;
    }

    public void setPointsid(String pointsid) {
        this.pointsid = pointsid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}

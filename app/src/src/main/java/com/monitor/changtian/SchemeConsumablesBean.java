package com.monitor.changtian;

import java.io.Serializable;

/**
 * Created by ken on 2018/7/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SchemeConsumablesBean implements Serializable {

    /**
     * scid : 19
     * schemeid : 42efbbf5-a8d3-44aa-8623-3e6c29d289b9
     * SchemeName : 测试方案
     * createuser : 1
     * username : 赵 涛
     * createtime : 2018-07-04 13:40:41
     * number : 1
     * pointsid : 6a9d32d6-064b-4ed1-ab68-a71021c56504
     * address : 杭州市西湖区
     * categoryid : 256
     * categoryname : 真空袋
     */

    private String scid;
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
    private String Consumablesid;
    private String ConsumablesName;

    public String getConsumablesid() {
        return Consumablesid;
    }

    public void setConsumablesid(String consumablesid) {
        Consumablesid = consumablesid;
    }

    public String getConsumablesName() {
        return ConsumablesName;
    }

    public void setConsumablesName(String consumablesName) {
        ConsumablesName = consumablesName;
    }

    public String getSumnumber() {
        return sumnumber;
    }

    public void setSumnumber(String sumnumber) {
        this.sumnumber = sumnumber;
    }

    private String number_s;

    public String getNumber_s() {
        return number_s;
    }

    public void setNumber_s(String number_s) {
        this.number_s = number_s;
    }

    public String getScid() {
        return scid;
    }

    public void setScid(String scid) {
        this.scid = scid;
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

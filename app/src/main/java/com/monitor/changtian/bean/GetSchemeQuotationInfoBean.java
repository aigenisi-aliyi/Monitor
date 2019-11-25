package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/7/30.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class GetSchemeQuotationInfoBean implements Serializable {

    /**
     * id : 0d1c677f-c1c0-40e2-bd54-a43668fbf24e
     * schemeid : ec4ca458-e020-4d5c-b5b6-7a847870ce46
     * schemename : 测试方案客户送样今天的数据
     * remark :
     * createuser : 1
     * username : 赵 涛
     * createtime : 2018-07-28 15:20:48
     * sumtotal : 10164
     * realprice : 0
     * sqstatus : 0
     * sqstatusname : 待初审
     */

    private String firsttrialprice;

    private String discountnumber;
    private String id;
    private String schemeid;
    private String schemename;
    private String remark;
    private String createuser;
    private String username;
    private String createtime;
    private String sumtotal;
    private String realprice;
    private String sqstatus;
    private String sqstatusname;

    public String getFirsttrialprice() {
        return firsttrialprice;
    }

    public void setFirsttrialprice(String firsttrialprice) {
        this.firsttrialprice = firsttrialprice;
    }

    public String getDiscountnumber() {
        return discountnumber;
    }

    public void setDiscountnumber(String discountnumber) {
        this.discountnumber = discountnumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getSumtotal() {
        return sumtotal;
    }

    public void setSumtotal(String sumtotal) {
        this.sumtotal = sumtotal;
    }

    public String getRealprice() {
        return realprice;
    }

    public void setRealprice(String realprice) {
        this.realprice = realprice;
    }

    public String getSqstatus() {
        return sqstatus;
    }

    public void setSqstatus(String sqstatus) {
        this.sqstatus = sqstatus;
    }

    public String getSqstatusname() {
        return sqstatusname;
    }

    public void setSqstatusname(String sqstatusname) {
        this.sqstatusname = sqstatusname;
    }
}

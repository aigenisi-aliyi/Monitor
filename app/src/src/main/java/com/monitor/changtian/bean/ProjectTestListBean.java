package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/19.
 */

public class ProjectTestListBean implements Serializable {

    /**
     * testno : 18-12-18-11-447
     * newsamplecode : 1812180084
     * controltype : 231
     * controlname : 平行样
     * oldsamplecode : 1812180077
     * parallelsample : 1812180075
     * factornames : 甲苯
     */

    private String ispass;
    private String col2;
    private String remark;
    private String testno;
    private String newsamplecode;
    private String controltype;
    private String controlname;
    private String oldsamplecode;
    private String parallelsample;
    private String factornames;
    public String getIspass() {
        return ispass;
    }
    public void setIspass(String ispass) {
        this.ispass = ispass;
    }
    public String getCol2() {
        return col2;
    }
    public void setCol2(String col2) {
        this.col2 = col2;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String ISTrue;

    public String getISTrue() {
        return ISTrue;
    }

    public void setISTrue(String ISTrue) {
        this.ISTrue = ISTrue;
    }

    public String getTestno() {
        return testno;
    }

    public void setTestno(String testno) {
        this.testno = testno;
    }

    public String getNewsamplecode() {
        return newsamplecode;
    }

    public void setNewsamplecode(String newsamplecode) {
        this.newsamplecode = newsamplecode;
    }

    public String getControltype() {
        return controltype;
    }

    public void setControltype(String controltype) {
        this.controltype = controltype;
    }

    public String getControlname() {
        return controlname;
    }

    public void setControlname(String controlname) {
        this.controlname = controlname;
    }

    public String getOldsamplecode() {
        return oldsamplecode;
    }

    public void setOldsamplecode(String oldsamplecode) {
        this.oldsamplecode = oldsamplecode;
    }

    public String getParallelsample() {
        return parallelsample;
    }

    public void setParallelsample(String parallelsample) {
        this.parallelsample = parallelsample;
    }

    public String getFactornames() {
        return factornames;
    }

    public void setFactornames(String factornames) {
        this.factornames = factornames;
    }
}

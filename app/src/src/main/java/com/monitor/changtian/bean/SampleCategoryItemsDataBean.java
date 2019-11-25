package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/6/1.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SampleCategoryItemsDataBean implements Serializable {

    /**
     * itemsid : 1
     * categoryid : 32
     * itemsname : 采样点位
     * itemsvalue : SampleName
     * itemstype : 35
     * itemstypename : string
     * ordernumber : 1
     * remark :
     * Ishas :
     */

    private String itemsid;
    private String categoryid;
    private String itemsname;
    private String itemsvalue;
    private String itemstype;
    private String itemstypename;
    private String ordernumber;
    private String remark;
    private String Ishas;
    /**
     * itemvalue :
     */

    private String itemvalue;

    public String getItemsid() {
        return itemsid;
    }

    public void setItemsid(String itemsid) {
        this.itemsid = itemsid;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getItemsname() {
        return itemsname;
    }

    public void setItemsname(String itemsname) {
        this.itemsname = itemsname;
    }

    public String getItemsvalue() {
        return itemsvalue;
    }

    public void setItemsvalue(String itemsvalue) {
        this.itemsvalue = itemsvalue;
    }

    public String getItemstype() {
        return itemstype;
    }

    public void setItemstype(String itemstype) {
        this.itemstype = itemstype;
    }

    public String getItemstypename() {
        return itemstypename;
    }

    public void setItemstypename(String itemstypename) {
        this.itemstypename = itemstypename;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIshas() {
        return Ishas;
    }

    public void setIshas(String Ishas) {
        this.Ishas = Ishas;
    }

    public String getItemvalue() {
        return itemvalue;
    }

    public void setItemvalue(String itemvalue) {
        this.itemvalue = itemvalue;
    }
}

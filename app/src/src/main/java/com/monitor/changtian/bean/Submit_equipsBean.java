package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/7/5.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class Submit_equipsBean implements Serializable {

    public String categoryid;
    public String number;
    public String Consumablesid;

    public String getConsumablesid() {
        return Consumablesid;
    }

    public void setConsumablesid(String consumablesid) {
        Consumablesid = consumablesid;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

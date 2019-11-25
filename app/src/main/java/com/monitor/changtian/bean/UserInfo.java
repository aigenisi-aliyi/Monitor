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

public class UserInfo implements Serializable {

    /**
     * id : 1
     * loginName : admin
     * userName : 赵 涛
     * sex : 男
     * phonenum :
     * birthday : 1982-01-01
     * worktime : 2005-01-01
     * isloss : 0
     * islossname : 否
     * unitid :
     * unitname :
     */

    private int id;
    private String loginName;
    private String userName;
    private String sex;
    private String phonenum;
    private String birthday;
    private String worktime;
    private String isloss;
    private String islossname;
    private String unitid;
    private String unitname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getIsloss() {
        return isloss;
    }

    public void setIsloss(String isloss) {
        this.isloss = isloss;
    }

    public String getIslossname() {
        return islossname;
    }

    public void setIslossname(String islossname) {
        this.islossname = islossname;
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }
}

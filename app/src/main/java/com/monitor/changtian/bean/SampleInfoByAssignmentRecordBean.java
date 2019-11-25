package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/17.
 */

public class SampleInfoByAssignmentRecordBean implements Serializable {

    /**
     * col1 : 201811010112
     * col3 : 赵峰
     * col8 : 2028
     * fid : 1121
     * factorname : Cl⁻
     * ftmid : 2028
     * methodname : 离子色谱法
     * roomname : 样品室1
     * address : 1-1-1
     */

    private String amountvalue;
    private String col1;
    private String col3;
    private String col8;
    private String fid;
    private String factorname;
    private String ftmid;
    private String methodname;
    private String roomname;
    private String address;


    public String getAmountvalue() {
        return amountvalue;
    }

    public void setAmountvalue(String amountvalue) {
        this.amountvalue = amountvalue;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public String getCol8() {
        return col8;
    }

    public void setCol8(String col8) {
        this.col8 = col8;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFactorname() {
        return factorname;
    }

    public void setFactorname(String factorname) {
        this.factorname = factorname;
    }

    public String getFtmid() {
        return ftmid;
    }

    public void setFtmid(String ftmid) {
        this.ftmid = ftmid;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

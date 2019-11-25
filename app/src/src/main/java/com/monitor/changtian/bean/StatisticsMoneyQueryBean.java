package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/6/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class StatisticsMoneyQueryBean  implements Serializable {

    /**
     * contractstatus : 0
     * number : 1
     * countnumber : 15
     * contractstatusname : 作废
     * ratiovalue : 6.67%
     */

    private int contractstatus;
    private int number;
    private int countnumber;
    private String contractstatusname;
    private String ratiovalue;

    public int getContractstatus() {
        return contractstatus;
    }

    public void setContractstatus(int contractstatus) {
        this.contractstatus = contractstatus;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCountnumber() {
        return countnumber;
    }

    public void setCountnumber(int countnumber) {
        this.countnumber = countnumber;
    }

    public String getContractstatusname() {
        return contractstatusname;
    }

    public void setContractstatusname(String contractstatusname) {
        this.contractstatusname = contractstatusname;
    }

    public String getRatiovalue() {
        return ratiovalue;
    }

    public void setRatiovalue(String ratiovalue) {
        this.ratiovalue = ratiovalue;
    }
}

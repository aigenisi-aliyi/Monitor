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

public class StatisticsQueryBean implements Serializable {

    /**
     * currentdaytotalmoney : 1020000.0
     * weeknumber : 15
     * TotalTailMoney : 1312000.0
     * weekInvalidmoney : 20000.0
     */

    private double currentdaytotalmoney;
    private int weeknumber;
    private double TotalTailMoney;
    private double weekInvalidmoney;

    public double getCurrentdaytotalmoney() {
        return currentdaytotalmoney;
    }

    public void setCurrentdaytotalmoney(double currentdaytotalmoney) {
        this.currentdaytotalmoney = currentdaytotalmoney;
    }

    public int getWeeknumber() {
        return weeknumber;
    }

    public void setWeeknumber(int weeknumber) {
        this.weeknumber = weeknumber;
    }

    public double getTotalTailMoney() {
        return TotalTailMoney;
    }

    public void setTotalTailMoney(double TotalTailMoney) {
        this.TotalTailMoney = TotalTailMoney;
    }

    public double getWeekInvalidmoney() {
        return weekInvalidmoney;
    }

    public void setWeekInvalidmoney(double weekInvalidmoney) {
        this.weekInvalidmoney = weekInvalidmoney;
    }
}

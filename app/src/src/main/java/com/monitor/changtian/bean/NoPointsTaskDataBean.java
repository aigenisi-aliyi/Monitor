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

public class NoPointsTaskDataBean implements Serializable {

    /**
     * pointsid : 0ae01751-de67-4b6d-903a-7c5b079d32be
     * explainInfo : 20
     * address : 杭州市西湖区
     * longitude : 108.73
     * latitude : 34.25
     * altitude : 0
     * remark :
     */
    private String DaysNumber;
    private String frequency;

    public String getDaysNumber() {
        return DaysNumber;
    }

    public void setDaysNumber(String daysNumber) {
        DaysNumber = daysNumber;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    private String pointsid;
    private String explainInfo;
    private String address;
    private String longitude;
    private String latitude;
    private String altitude;
    private String remark;
    private boolean isChoice;

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public String getPointsid() {
        return pointsid;
    }

    public void setPointsid(String pointsid) {
        this.pointsid = pointsid;
    }

    public String getExplainInfo() {
        return explainInfo;
    }

    public void setExplainInfo(String explainInfo) {
        this.explainInfo = explainInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

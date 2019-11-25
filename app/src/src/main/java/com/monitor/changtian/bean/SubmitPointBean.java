package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/8/2.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SubmitPointBean implements Serializable {
    private String qualitycontrol;
    private String qualitycontrolname;
    private String pointsid;
    private String numbers;
    private String pointname;
    private String setvalue;

    public String getSetvalue() {
        return setvalue;
    }

    public void setSetvalue(String setvalue) {
        this.setvalue = setvalue;
    }

    public String getQualitycontrolname() {
        return qualitycontrolname;
    }

    public void setQualitycontrolname(String qualitycontrolname) {
        this.qualitycontrolname = qualitycontrolname;
    }

    public String getPointname() {
        return pointname;
    }

    public void setPointname(String pointname) {
        this.pointname = pointname;
    }

    public String getQualitycontrol() {
        return qualitycontrol;
    }

    public void setQualitycontrol(String qualitycontrol) {
        this.qualitycontrol = qualitycontrol;
    }

    public String getPointsid() {
        return pointsid;
    }

    public void setPointsid(String pointsid) {
        this.pointsid = pointsid;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }
}

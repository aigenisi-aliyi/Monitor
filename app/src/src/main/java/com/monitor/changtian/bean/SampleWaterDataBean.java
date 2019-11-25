package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/5/17.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SampleWaterDataBean implements Serializable {


    /**
     * SampleId : 023e8d11-eb53-4bc2-bd82-f31c608d34e9
     * ProjecName : 西安市护城河水质检测
     * accords : 1
     * accordsname : 《水和废水监测分析办法》第四版
     * userid : 1
     * loginName : admin
     * userName : 张三
     * SampleDate : 2018-05-16
     * ContractNum : SXZC20180516
     * WeatherCondition : 26℃   多云
     */

    private String SampleId;
    private String ProjecName;
    private String accords;
    private String accordsname;
    private String userid;
    private String loginName;
    private String userName;
    private String SampleDate;
    private String ContractNum;
    private String WeatherCondition;
    private String ProjectName;
    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getSampleId() {
        return SampleId;
    }

    public void setSampleId(String SampleId) {
        this.SampleId = SampleId;
    }

    public String getProjecName() {
        return ProjecName;
    }

    public void setProjecName(String ProjecName) {
        this.ProjecName = ProjecName;
    }

    public String getAccords() {
        return accords;
    }

    public void setAccords(String accords) {
        this.accords = accords;
    }

    public String getAccordsname() {
        return accordsname;
    }

    public void setAccordsname(String accordsname) {
        this.accordsname = accordsname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getSampleDate() {
        return SampleDate;
    }

    public void setSampleDate(String SampleDate) {
        this.SampleDate = SampleDate;
    }

    public String getContractNum() {
        return ContractNum;
    }

    public void setContractNum(String ContractNum) {
        this.ContractNum = ContractNum;
    }

    public String getWeatherCondition() {
        return WeatherCondition;
    }

    public void setWeatherCondition(String WeatherCondition) {
        this.WeatherCondition = WeatherCondition;
    }
}

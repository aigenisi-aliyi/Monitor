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

public class SampleInfoDataBean implements Serializable {


    /**
     * SampleId : 3f782666-79b6-43e7-9acd-a3ea01e39da6
     * categoryid : 32
     * categoryname : 水质
     * ProjectName : 西安市护城河水质检测
     * accords : 1
     * accordsname : 《水和废水监测分析办法》第四版
     * userid : 1
     * userName : 赵 涛
     * SampleDate : 2018-05-16
     * ContractNum : SXZC20180516
     * WeatherCondition : 26℃   多云
     * InstrumentType :
     * SampleType : 40
     * SampleTypeName : 滤膜富集
     * InstrumentType1 :
     * FieldSituation :
     * SampleStatus :
     */

    private String SampleId;
    private String categoryid;
    private String categoryname;
    private String ProjectName;
    private String accords;
    private String accordsname;
    private String userid;
    private String userName;
    private String SampleDate;
    private String ContractNum;
    private String WeatherCondition;
    private String InstrumentType;
    private String SampleType;
    private String SampleTypeName;
    private String InstrumentType1;
    private String FieldSituation;
    private String SampleStatus;

    public String getSampleId() {
        return SampleId;
    }

    public void setSampleId(String SampleId) {
        this.SampleId = SampleId;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
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

    public String getInstrumentType() {
        return InstrumentType;
    }

    public void setInstrumentType(String InstrumentType) {
        this.InstrumentType = InstrumentType;
    }

    public String getSampleType() {
        return SampleType;
    }

    public void setSampleType(String SampleType) {
        this.SampleType = SampleType;
    }

    public String getSampleTypeName() {
        return SampleTypeName;
    }

    public void setSampleTypeName(String SampleTypeName) {
        this.SampleTypeName = SampleTypeName;
    }

    public String getInstrumentType1() {
        return InstrumentType1;
    }

    public void setInstrumentType1(String InstrumentType1) {
        this.InstrumentType1 = InstrumentType1;
    }

    public String getFieldSituation() {
        return FieldSituation;
    }

    public void setFieldSituation(String FieldSituation) {
        this.FieldSituation = FieldSituation;
    }

    public String getSampleStatus() {
        return SampleStatus;
    }

    public void setSampleStatus(String SampleStatus) {
        this.SampleStatus = SampleStatus;
    }
}

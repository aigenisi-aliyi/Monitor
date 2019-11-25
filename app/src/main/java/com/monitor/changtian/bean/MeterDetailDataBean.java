package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/5/25.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class MeterDetailDataBean implements Serializable {

    /**
     * id : 1
     * SampleName : 项目厂址
     * SampleOnlyNum : 201801038FQ-01-1-1-1
     * SampleNum : 01-1-1-1
     * SampleTime : 2018-01-23 00:00:00
     * flows :
     * watertmp : 3.4
     * humidity : 36.7
     * atmos : 89.64
     * WindSpeed : 1.9
     * SampleStatus : 合格
     * AnalysisItem : 非甲烷总经
     * remark :
     * SampleId : 4e6b659f-8095-42a4-a5da-b2313be977e4
     * stime : 14:27
     * dtime :
     * SampleMinute :
     * WindDirection : N
     * volume :
     */

    private String id;
    private String SampleName;
    private String SampleOnlyNum;
    private String SampleNum;
    private String SampleTime;
    private String flows;
    private String watertmp;
    private String humidity;
    private String atmos;
    private String WindSpeed;
    private String SampleStatus;
    private String AnalysisItem;
    private String remark;
    private String SampleId;
    private String stime;
    private String dtime;
    private String SampleMinute;
    private String WindDirection;
    private String volume;
    /**
     * SampleCount : 3
     * EastLongitude : 108.93
     * NorthLatitude : 34.27
     * Situation : 中性土壤
     * SamplingDepth : 10
     * Color : 黄色
     * texture : 良
     * explainInfo :
     */

    private String SampleCount;
    private String EastLongitude;
    private String NorthLatitude;
    private String Situation;
    private String SamplingDepth;
    private String Color;
    private String texture;
    private String explainInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSampleName() {
        return SampleName;
    }

    public void setSampleName(String SampleName) {
        this.SampleName = SampleName;
    }

    public String getSampleOnlyNum() {
        return SampleOnlyNum;
    }

    public void setSampleOnlyNum(String SampleOnlyNum) {
        this.SampleOnlyNum = SampleOnlyNum;
    }

    public String getSampleNum() {
        return SampleNum;
    }

    public void setSampleNum(String SampleNum) {
        this.SampleNum = SampleNum;
    }

    public String getSampleTime() {
        return SampleTime;
    }

    public void setSampleTime(String SampleTime) {
        this.SampleTime = SampleTime;
    }

    public String getFlows() {
        return flows;
    }

    public void setFlows(String flows) {
        this.flows = flows;
    }

    public String getWatertmp() {
        return watertmp;
    }

    public void setWatertmp(String watertmp) {
        this.watertmp = watertmp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAtmos() {
        return atmos;
    }

    public void setAtmos(String atmos) {
        this.atmos = atmos;
    }

    public String getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(String WindSpeed) {
        this.WindSpeed = WindSpeed;
    }

    public String getSampleStatus() {
        return SampleStatus;
    }

    public void setSampleStatus(String SampleStatus) {
        this.SampleStatus = SampleStatus;
    }

    public String getAnalysisItem() {
        return AnalysisItem;
    }

    public void setAnalysisItem(String AnalysisItem) {
        this.AnalysisItem = AnalysisItem;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSampleId() {
        return SampleId;
    }

    public void setSampleId(String SampleId) {
        this.SampleId = SampleId;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getSampleMinute() {
        return SampleMinute;
    }

    public void setSampleMinute(String SampleMinute) {
        this.SampleMinute = SampleMinute;
    }

    public String getWindDirection() {
        return WindDirection;
    }

    public void setWindDirection(String WindDirection) {
        this.WindDirection = WindDirection;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSampleCount() {
        return SampleCount;
    }

    public void setSampleCount(String SampleCount) {
        this.SampleCount = SampleCount;
    }

    public String getEastLongitude() {
        return EastLongitude;
    }

    public void setEastLongitude(String EastLongitude) {
        this.EastLongitude = EastLongitude;
    }

    public String getNorthLatitude() {
        return NorthLatitude;
    }

    public void setNorthLatitude(String NorthLatitude) {
        this.NorthLatitude = NorthLatitude;
    }

    public String getSituation() {
        return Situation;
    }

    public void setSituation(String Situation) {
        this.Situation = Situation;
    }

    public String getSamplingDepth() {
        return SamplingDepth;
    }

    public void setSamplingDepth(String SamplingDepth) {
        this.SamplingDepth = SamplingDepth;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getExplainInfo() {
        return explainInfo;
    }

    public void setExplainInfo(String explainInfo) {
        this.explainInfo = explainInfo;
    }
}

package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/5/18.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SampleDetailDataBean implements Serializable {

    /**
     * Id : 1
     * SampleName : A001
     * SampleNum : A001001
     * SampleTime : 2018-05-16 00:00:00
     * SampleCount : 12
     * flows : 15
     * watertmp : 20
     * PH : 4
     * Oxygen : 2
     * electrical : 1
     * SampleStatus : 正常
     * AnalysisItem : 水质采样分析
     * remark : 加入1ml硫酸锰溶液，2ml碱性碘化钾溶液，盖好盖子颠倒混合数次，静置。待棕色沉淀物降至瓶内一半时，再颠倒混合一次。
     * SampleId : 023e8d11-eb53-4bc2-bd82-f31c608d34e9
     */



    private String Id;
    private String SampleName;
    private String SampleNum;
    private String SampleTime;
    private String SampleCount;
    private String flows;
    private String watertmp;
    private String PH;
    private String Oxygen;
    private String electrical;
    private String SampleStatus;
    private String AnalysisItem;
    private String remark;
    private String SampleId;
    /**
     * id : 1
     * SampleOnlyNum : A-001001
     * EastLongitude : 108.93
     * NorthLatitude : 34.27
     * Situation : 中性土壤
     * SamplingDepth : 10
     * Color : 黄色
     * texture : 良
     * humidity : 中性
     * explainInfo :
     */

    private String id;
    private String SampleOnlyNum;
    private String EastLongitude;
    private String NorthLatitude;
    private String Situation;
    private String SamplingDepth;
    private String Color;
    private String texture;
    private String humidity;
    private String explainInfo;
    /**
     * atmos : 89.64
     * WindSpeed : 1.9
     * stime : 14:27
     * dtime :
     * SampleMinute :
     * WindDirection : N
     * volume :
     */

    private String atmos;
    private String WindSpeed;
    private String stime;
    private String dtime;
    private String SampleMinute;
    private String WindDirection;
    private String volume;


//
//    public String getId() {
//        return Id;
//    }
//
//    public void setId(String Id) {
//        this.Id = Id;
//    }

    public String getSampleName() {
        return SampleName;
    }

    public void setSampleName(String SampleName) {
        this.SampleName = SampleName;
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

    public String getSampleCount() {
        return SampleCount;
    }

    public void setSampleCount(String SampleCount) {
        this.SampleCount = SampleCount;
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

    public String getPH() {
        return PH;
    }

    public void setPH(String PH) {
        this.PH = PH;
    }

    public String getOxygen() {
        return Oxygen;
    }

    public void setOxygen(String Oxygen) {
        this.Oxygen = Oxygen;
    }

    public String getElectrical() {
        return electrical;
    }

    public void setElectrical(String electrical) {
        this.electrical = electrical;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSampleOnlyNum() {
        return SampleOnlyNum;
    }

    public void setSampleOnlyNum(String SampleOnlyNum) {
        this.SampleOnlyNum = SampleOnlyNum;
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

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getExplainInfo() {
        return explainInfo;
    }

    public void setExplainInfo(String explainInfo) {
        this.explainInfo = explainInfo;
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
}

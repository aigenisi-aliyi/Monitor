package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/17.
 */

public class SchemeFidsBean implements Serializable {

    /**
     * contentid : 604b3085-fa35-40ce-89da-84e97634f152
     * fid : 1166
     * factorname : 温度
     * AnalysisMethod : 2087
     * methodname : 气象仪法
     * frequency : 1
     * isMeteorParam : 1
     */
    private boolean isSelected; //自定义列表是否选中的标识
    private boolean isChoice_save;
    private boolean isChoice;
    private String contentid;
    private String fid;
    private String factorname;
    private String AnalysisMethod;
    private String methodname;
    private String frequency;
    private String isMeteorParam;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isChoice_save() {
        return isChoice_save;
    }

    public void setChoice_save(boolean choice_save) {
        isChoice_save = choice_save;
    }

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
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

    public String getAnalysisMethod() {
        return AnalysisMethod;
    }

    public void setAnalysisMethod(String AnalysisMethod) {
        this.AnalysisMethod = AnalysisMethod;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getIsMeteorParam() {
        return isMeteorParam;
    }

    public void setIsMeteorParam(String isMeteorParam) {
        this.isMeteorParam = isMeteorParam;
    }
}

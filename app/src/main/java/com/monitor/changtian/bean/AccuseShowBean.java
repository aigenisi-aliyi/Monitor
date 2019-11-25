package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/16.
 */

public class AccuseShowBean {
    private String potionName;
    private String qualitycontrolname;
    private String pointsid;
    private Boolean ishow;
    private String DaysNumber;//天数
    private String frequency;//频次

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDaysNumber() {
        return DaysNumber;
    }

    public void setDaysNumber(String DaysNumber) {
        this.DaysNumber = DaysNumber;
    }

    public String getPointsid() {
        return pointsid;
    }

    public void setPointsid(String pointsid) {
        this.pointsid = pointsid;
    }

    public Boolean getIshow() {
        return ishow;
    }

    public void setIshow(Boolean ishow) {
        this.ishow = ishow;
    }

    public List<FactorBean> details;

    public String getPotionName() {
        return potionName;
    }

    public void setPotionName(String potionName) {
        this.potionName = potionName;
    }

    public String getQualitycontrolname() {
        return qualitycontrolname;
    }

    public void setQualitycontrolname(String qualitycontrolname) {
        this.qualitycontrolname = qualitycontrolname;
    }

    public List<FactorBean> getDetails() {
        return details;
    }

    public void setDetails(List<FactorBean> details) {
        this.details = details;
    }

    public static class FactorBean implements Serializable {
        private String name;
        private String value;
        private String fid;
        private String setvalue;
        private String qualitycontrol;
        private String contactfnid;
        private String samplenumber;
        private String contentid;
        private String daysNumber;

        private String Bzwz;

        public String getBzwz() {
            return Bzwz;
        }

        public void setBzwz(String bzwz) {
            Bzwz = bzwz;
        }

        public String getDaysNumber() {
            return daysNumber;
        }

        public void setDaysNumber(String daysNumber) {
            this.daysNumber = daysNumber;
        }

        public String getContentid() {
            return contentid;
        }

        public void setContentid(String contentid) {
            this.contentid = contentid;
        }

        public String getContactfnid() {
            return contactfnid;
        }

        public void setContactfnid(String contactfnid) {
            this.contactfnid = contactfnid;
        }

        public String getSamplenumber() {
            return samplenumber;
        }

        public void setSamplenumber(String samplenumber) {
            this.samplenumber = samplenumber;
        }

        public String getQualitycontrol() {
            return qualitycontrol;
        }

        public void setQualitycontrol(String qualitycontrol) {
            this.qualitycontrol = qualitycontrol;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getSetvalue() {
            return setvalue;
        }

        public void setSetvalue(String setvalue) {
            this.setvalue = setvalue;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}

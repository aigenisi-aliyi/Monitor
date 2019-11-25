package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/8/2.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class GetqualitycontrolBean implements Serializable {

    /**
     * cqcid : 45
     * schemeid : b5203e40-39f4-44c6-abe4-22d93fe72660
     * schemename : 啊发发打发
     * IsUrgent : 0
     * IsUrgentName : 否
     * customername : 晴空防水
     * createtime : 2018-10-19 09:06:01
     * pcid : 53
     * contractcode : 20181000009
     * contractname : 我的合同10-19
     * contractstatus : 2
     * contractstatusname : 待付尾款
     * remark :
     * pointsid : 2c8514d4-5989-4c1f-997a-bf431d217082
     * pointsaddress : A点位
     * qualitycontrol : 230
     * qualitycontrolname : 空白样
     * numbers : 1
     * factors : [{"qualitycontrol":"230","qualitycontrolname":"空白样","fid":"637","factorname":"臭氧"}]
     */


    private Boolean ishow;

    public Boolean getIshow() {
        return ishow;
    }

    public void setIshow(Boolean ishow) {
        this.ishow = ishow;
    }

    private String cqcid;
    private String schemeid;
    private String schemename;
    private String IsUrgent;
    private String IsUrgentName;
    private String customername;
    private String createtime;
    private String pcid;
    private String contractcode;
    private String contractname;
    private String contractstatus;
    private String contractstatusname;
    private String remark;
    private String pointsid;
    private String pointsaddress;
    private String qualitycontrol;
    private String qualitycontrolname;
    private int numbers;
    private List<FactorsBean> factors;

    public String getCqcid() {
        return cqcid;
    }

    public void setCqcid(String cqcid) {
        this.cqcid = cqcid;
    }

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid;
    }

    public String getSchemename() {
        return schemename;
    }

    public void setSchemename(String schemename) {
        this.schemename = schemename;
    }

    public String getIsUrgent() {
        return IsUrgent;
    }

    public void setIsUrgent(String IsUrgent) {
        this.IsUrgent = IsUrgent;
    }

    public String getIsUrgentName() {
        return IsUrgentName;
    }

    public void setIsUrgentName(String IsUrgentName) {
        this.IsUrgentName = IsUrgentName;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public String getContractcode() {
        return contractcode;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
    }

    public String getContractname() {
        return contractname;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public String getContractstatus() {
        return contractstatus;
    }

    public void setContractstatus(String contractstatus) {
        this.contractstatus = contractstatus;
    }

    public String getContractstatusname() {
        return contractstatusname;
    }

    public void setContractstatusname(String contractstatusname) {
        this.contractstatusname = contractstatusname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPointsid() {
        return pointsid;
    }

    public void setPointsid(String pointsid) {
        this.pointsid = pointsid;
    }

    public String getPointsaddress() {
        return pointsaddress;
    }

    public void setPointsaddress(String pointsaddress) {
        this.pointsaddress = pointsaddress;
    }

    public String getQualitycontrol() {
        return qualitycontrol;
    }

    public void setQualitycontrol(String qualitycontrol) {
        this.qualitycontrol = qualitycontrol;
    }

    public String getQualitycontrolname() {
        return qualitycontrolname;
    }

    public void setQualitycontrolname(String qualitycontrolname) {
        this.qualitycontrolname = qualitycontrolname;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public List<FactorsBean> getFactors() {
        return factors;
    }

    public void setFactors(List<FactorsBean> factors) {
        this.factors = factors;
    }

    public static class FactorsBean {
        /**
         * qualitycontrol : 230
         * qualitycontrolname : 空白样
         * fid : 637
         * factorname : 臭氧
         */

        private String qualitycontrol;
        private String qualitycontrolname;
        private String fid;
        private String factorname;
        private String setvalue ;
        private String samplenumber;

        public String getSamplenumber() {
            return samplenumber;
        }

        public void setSamplenumber(String samplenumber) {
            this.samplenumber = samplenumber;
        }

        public String getSetvalue() {
            return setvalue;
        }

        public void setSetvalue(String setvalue) {
            this.setvalue = setvalue;
        }

        public String getQualitycontrol() {
            return qualitycontrol;
        }

        public void setQualitycontrol(String qualitycontrol) {
            this.qualitycontrol = qualitycontrol;
        }

        public String getQualitycontrolname() {
            return qualitycontrolname;
        }

        public void setQualitycontrolname(String qualitycontrolname) {
            this.qualitycontrolname = qualitycontrolname;
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
    }
}

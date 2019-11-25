package com.monitor.changtian.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/8/29.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SampleStatusBean implements Serializable {

    /**
     * sampingPacking : 聚乙烯瓶
     * sampingStatus : 555
     * samplemode : 0
     */

    private String sampingPacking;
    private String sampingStatus;
    private int samplemode;
    private String Isinvalid;
    private String remark;

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    /**
     * samplingamount :
     * samplingunitname : ㎏
     */

    private String samplingamount;
    private String samplingunitname;
    private String statevaluename;

    private String statevalue;
    /**
     * Isinvalidname : 否
     * taskpersons : [{"personid":"116","personloginname":"018","personname":"孙亮亮"},{"personid":"125","personloginname":"027","personname":"张传行"},{"personid":"134","personloginname":"037","personname":"王林辉"},{"personid":"140","personloginname":"043","personname":"王亮亮"},{"personid":"158","personloginname":"063","personname":"张育乾"}]
     */

    private String Isinvalidname;
    private List<TaskpersonsBean> taskpersons;


    public String getIsinvalid() {
        return Isinvalid;
    }

    public void setIsinvalid(String isinvalid) {
        Isinvalid = isinvalid;
    }

    public String getStatevalue() {
        return statevalue;
    }

    public void setStatevalue(String statevalue) {
        this.statevalue = statevalue;
    }

    public String getStatevaluename() {
        return statevaluename;
    }

    public void setStatevaluename(String statevaluename) {
        this.statevaluename = statevaluename;
    }

    public String getSampingPacking() {
        return sampingPacking;
    }

    public void setSampingPacking(String sampingPacking) {
        this.sampingPacking = sampingPacking;
    }

    public String getSampingStatus() {
        return sampingStatus;
    }

    public void setSampingStatus(String sampingStatus) {
        this.sampingStatus = sampingStatus;
    }

    public int getSamplemode() {
        return samplemode;
    }

    public void setSamplemode(int samplemode) {
        this.samplemode = samplemode;
    }

    public String getSamplingamount() {
        return samplingamount;
    }

    public void setSamplingamount(String samplingamount) {
        this.samplingamount = samplingamount;
    }

    public String getSamplingunitname() {
        return samplingunitname;
    }

    public void setSamplingunitname(String samplingunitname) {
        this.samplingunitname = samplingunitname;
    }

    public String getIsinvalidname() {
        return Isinvalidname;
    }

    public void setIsinvalidname(String Isinvalidname) {
        this.Isinvalidname = Isinvalidname;
    }

    public List<TaskpersonsBean> getTaskpersons() {
        return taskpersons;
    }

    public void setTaskpersons(List<TaskpersonsBean> taskpersons) {
        this.taskpersons = taskpersons;
    }

    public static class TaskpersonsBean implements Serializable, IPickerViewData {
        /**
         * personid : 116
         * personloginname : 018
         * personname : 孙亮亮
         */

        private String personid;
        private String personloginname;
        private String personname;

        public String getPersonid() {
            return personid;
        }

        public void setPersonid(String personid) {
            this.personid = personid;
        }

        public String getPersonloginname() {
            return personloginname;
        }

        public void setPersonloginname(String personloginname) {
            this.personloginname = personloginname;
        }

        public String getPersonname() {
            return personname;
        }

        public void setPersonname(String personname) {
            this.personname = personname;
        }

        @Override
        public String getPickerViewText() {
            return personname;
        }
    }
}

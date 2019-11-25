package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/9/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * へ　　　　／|
 * 　　/＼7　　∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　 │　　　　　ヽ　　 /　　〉
 * 　Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */

public class PotinsBean {
    public String potinID;
    public String potinName;
    public int fatherPoe;

    public int getFatherPoe() {
        return fatherPoe;
    }

    public void setFatherPoe(int fatherPoe) {
        this.fatherPoe = fatherPoe;
    }

    public String getPotinID() {
        return potinID;
    }

    public void setPotinID(String potinID) {
        this.potinID = potinID;
    }

    public String getPotinName() {
        return potinName;
    }

    public void setPotinName(String potinName) {
        this.potinName = potinName;
    }


    public List<SubmitPointBean> submitPointBeanList;

    public List<SubmitPointBean> getSubmitPointBeanList() {
        return submitPointBeanList;
    }

    public void setSubmitPointBeanList(List<SubmitPointBean> submitPointBeanList) {
        this.submitPointBeanList = submitPointBeanList;
    }

    public static class SubmitPointBean implements Serializable {
        private String qualitycontrol;
        private String qualitycontrolname;
        private String pointsid;
        private String fid;
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

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }
    }

}

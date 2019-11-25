package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/7/12.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class EquipsBean {


    /**
     * equipid :
     * calibrations : [{"Premeasurement":"","Postmeasurement":"","operationuser":"","calibrationquipid":""}]
     */

    private String equipid;
    private List<CalibrationsBean> calibrations;

    public String getEquipid() {
        return equipid;
    }

    public void setEquipid(String equipid) {
        this.equipid = equipid;
    }

    public List<CalibrationsBean> getCalibrations() {
        return calibrations;
    }

    public void setCalibrations(List<CalibrationsBean> calibrations) {
        this.calibrations = calibrations;
    }

    public static class CalibrationsBean implements Serializable {
        /**
         * Premeasurement :
         * Postmeasurement :
         * operationuser :
         * calibrationquipid :
         */

        private String Premeasurement;
        private String operationuser;
        private String calibrationquipid;
        private String operationtime;

        public String getOperationtime() {
            return operationtime;
        }

        public void setOperationtime(String operationtime) {
            this.operationtime = operationtime;
        }

        public String getPremeasurement() {
            return Premeasurement;
        }

        public void setPremeasurement(String Premeasurement) {
            this.Premeasurement = Premeasurement;
        }

//

        public String getOperationuser() {
            return operationuser;
        }

        public void setOperationuser(String operationuser) {
            this.operationuser = operationuser;
        }

        public String getCalibrationquipid() {
            return calibrationquipid;
        }

        public void setCalibrationquipid(String calibrationquipid) {
            this.calibrationquipid = calibrationquipid;
        }
    }
}

package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/12/19.
 */

public class QualitySubmitBean implements Serializable {

    /**
     * tpcid :
     * loginId :
     * details : [{"testno":"","samplenumber":"","ispass":"","remark":"","col2":""}]
     */

    private String tpcid;
    private String loginId;
    private List<DetailsBean> details;

    public String getTpcid() {
        return tpcid;
    }

    public void setTpcid(String tpcid) {
        this.tpcid = tpcid;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * testno :
         * samplenumber :
         * ispass :
         * remark :
         * col2 :
         */

        private String testno;
        private String samplenumber;
        private String ispass;
        private String remark;
        private String col2;

        public String getTestno() {
            return testno;
        }

        public void setTestno(String testno) {
            this.testno = testno;
        }

        public String getSamplenumber() {
            return samplenumber;
        }

        public void setSamplenumber(String samplenumber) {
            this.samplenumber = samplenumber;
        }

        public String getIspass() {
            return ispass;
        }

        public void setIspass(String ispass) {
            this.ispass = ispass;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCol2() {
            return col2;
        }

        public void setCol2(String col2) {
            this.col2 = col2;
        }
    }
}

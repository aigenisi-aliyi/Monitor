package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/6/1.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */


public class SampleInfoCompreData  implements Serializable {

    /**
     * SampleInfo : {"SampleId":"1763e20e-8fa5-469a-b88b-c1455c3eed5b","ProjectName":"土壤监测3","userName":"赵 涛","SampleDate":"2018-05-16","ContractNum":"SXZC20180516","WeatherCondition":"26℃   多云","SampleStatus":""}
     * SampleDetails : [{"detailid":"692844b6-181a-4d42-81d9-fa9126d69d12","SampleId":"1763e20e-8fa5-469a-b88b-c1455c3eed5b","SampleOnlyNum":"","SampleNum":"A000121545454222","remark":"","userid":"1","username":"赵 涛","createtime":"2018-06-01 14:45:47","SampleDetailItems":[{"itemid":"19","itemsInfo":"采样点位","itemsname":"SampleName","itemvalue":"1"},{"itemid":"20","itemsInfo":"东经","itemsname":"EastLongitude","itemvalue":"1"},{"itemid":"21","itemsInfo":"北纬","itemsname":"NorthLatitude","itemvalue":"1"},{"itemid":"22","itemsInfo":"土表植被及耕作情况","itemsname":"Situation","itemvalue":"1"},{"itemid":"23","itemsInfo":"采样深度","itemsname":"SamplingDepth","itemvalue":"1"},{"itemid":"24","itemsInfo":"颜色","itemsname":"Color","itemvalue":"4546446"},{"itemid":"25","itemsInfo":"质地","itemsname":"texture","itemvalue":"1"},{"itemid":"26","itemsInfo":"湿度","itemsname":"humidity","itemvalue":"1"},{"itemid":"27","itemsInfo":"采样方式","itemsname":"SamplingMethod","itemvalue":"1"},{"itemid":"28","itemsInfo":"采样数量","itemsname":"SampleCount","itemvalue":"4"},{"itemid":"29","itemsInfo":"分析项目","itemsname":"AnalysisItem","itemvalue":"14"},{"itemid":"30","itemsInfo":"说明","itemsname":"explainInfo","itemvalue":"14"}]}]
     * SamplePhotos : [{"id":"38","fujian":"/upload/files/33a0f6cd-433e-4868-942e-4b42d678069c253.png"}]
     */

    private SampleInfoBean SampleInfo;
    private List<SampleDetailsBean> SampleDetails;
    private List<SamplePhotosBean> SamplePhotos;

    public SampleInfoBean getSampleInfo() {
        return SampleInfo;
    }

    public void setSampleInfo(SampleInfoBean SampleInfo) {
        this.SampleInfo = SampleInfo;
    }

    public List<SampleDetailsBean> getSampleDetails() {
        return SampleDetails;
    }

    public void setSampleDetails(List<SampleDetailsBean> SampleDetails) {
        this.SampleDetails = SampleDetails;
    }

    public List<SamplePhotosBean> getSamplePhotos() {
        return SamplePhotos;
    }

    public void setSamplePhotos(List<SamplePhotosBean> SamplePhotos) {
        this.SamplePhotos = SamplePhotos;
    }

    public static class SampleInfoBean  implements Serializable{
        /**
         * SampleId : 1763e20e-8fa5-469a-b88b-c1455c3eed5b
         * ProjectName : 土壤监测3
         * userName : 赵 涛
         * SampleDate : 2018-05-16
         * ContractNum : SXZC20180516
         * WeatherCondition : 26℃   多云
         * SampleStatus :
         */

        private String SampleId;
        private String ProjectName;
        private String userName;
        private String SampleDate;
        private String ContractNum;
        private String WeatherCondition;
        private String SampleStatus;

        public String getSampleId() {
            return SampleId;
        }

        public void setSampleId(String SampleId) {
            this.SampleId = SampleId;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
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

        public String getSampleStatus() {
            return SampleStatus;
        }

        public void setSampleStatus(String SampleStatus) {
            this.SampleStatus = SampleStatus;
        }
    }

    public static class SampleDetailsBean implements Serializable{
        /**
         * detailid : 692844b6-181a-4d42-81d9-fa9126d69d12
         * SampleId : 1763e20e-8fa5-469a-b88b-c1455c3eed5b
         * SampleOnlyNum :
         * SampleNum : A000121545454222
         * remark :
         * userid : 1
         * username : 赵 涛
         * createtime : 2018-06-01 14:45:47
         * SampleDetailItems : [{"itemid":"19","itemsInfo":"采样点位","itemsname":"SampleName","itemvalue":"1"},{"itemid":"20","itemsInfo":"东经","itemsname":"EastLongitude","itemvalue":"1"},{"itemid":"21","itemsInfo":"北纬","itemsname":"NorthLatitude","itemvalue":"1"},{"itemid":"22","itemsInfo":"土表植被及耕作情况","itemsname":"Situation","itemvalue":"1"},{"itemid":"23","itemsInfo":"采样深度","itemsname":"SamplingDepth","itemvalue":"1"},{"itemid":"24","itemsInfo":"颜色","itemsname":"Color","itemvalue":"4546446"},{"itemid":"25","itemsInfo":"质地","itemsname":"texture","itemvalue":"1"},{"itemid":"26","itemsInfo":"湿度","itemsname":"humidity","itemvalue":"1"},{"itemid":"27","itemsInfo":"采样方式","itemsname":"SamplingMethod","itemvalue":"1"},{"itemid":"28","itemsInfo":"采样数量","itemsname":"SampleCount","itemvalue":"4"},{"itemid":"29","itemsInfo":"分析项目","itemsname":"AnalysisItem","itemvalue":"14"},{"itemid":"30","itemsInfo":"说明","itemsname":"explainInfo","itemvalue":"14"}]
         */

        private String detailid;
        private String SampleId;
        private String SampleOnlyNum;
        private String SampleNum;
        private String remark;
        private String userid;
        private String username;
        private String createtime;
        private List<SampleDetailItemsBean> SampleDetailItems;

        public String getDetailid() {
            return detailid;
        }

        public void setDetailid(String detailid) {
            this.detailid = detailid;
        }

        public String getSampleId() {
            return SampleId;
        }

        public void setSampleId(String SampleId) {
            this.SampleId = SampleId;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public List<SampleDetailItemsBean> getSampleDetailItems() {
            return SampleDetailItems;
        }

        public void setSampleDetailItems(List<SampleDetailItemsBean> SampleDetailItems) {
            this.SampleDetailItems = SampleDetailItems;
        }

        public static class SampleDetailItemsBean  implements Serializable {
            /**
             * itemid : 19
             * itemsInfo : 采样点位
             * itemsname : SampleName
             * itemvalue : 1
             */

            private String itemid;
            private String itemsInfo;
            private String itemsname;
            private String itemvalue;

            public String getItemid() {
                return itemid;
            }

            public void setItemid(String itemid) {
                this.itemid = itemid;
            }

            public String getItemsInfo() {
                return itemsInfo;
            }

            public void setItemsInfo(String itemsInfo) {
                this.itemsInfo = itemsInfo;
            }

            public String getItemsname() {
                return itemsname;
            }

            public void setItemsname(String itemsname) {
                this.itemsname = itemsname;
            }

            public String getItemvalue() {
                return itemvalue;
            }

            public void setItemvalue(String itemvalue) {
                this.itemvalue = itemvalue;
            }
        }
    }

    public static class SamplePhotosBean  implements Serializable {
        /**
         * id : 38
         * fujian : /upload/files/33a0f6cd-433e-4868-942e-4b42d678069c253.png
         */

        private String id;
        private String fujian;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFujian() {
            return fujian;
        }

        public void setFujian(String fujian) {
            this.fujian = fujian;
        }
    }
}

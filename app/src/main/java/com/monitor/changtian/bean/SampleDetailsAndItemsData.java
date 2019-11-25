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

public class SampleDetailsAndItemsData implements Serializable {

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
    private List<PhotosBean> photos;

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

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }

    public static class SampleDetailItemsBean implements Serializable{
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

    public static class PhotosBean  implements Serializable {
        /**
         * id : 53
         * fujian : /upload/files/ef3f3c86-fbed-48b6-bf9c-03a5a15dbe90151.mp4
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

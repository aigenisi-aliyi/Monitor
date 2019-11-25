package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/8/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskInfoAllBean implements Serializable {

    /**
     * id : b48947ca-7082-4570-8388-2923a71da739
     * subject :
     * content :
     * ctime : 2018-08-03 11:42:03
     * schemeid : 4cd8518d-3506-45cf-bcc8-83b17987167b
     * conid : 20
     * remark : 测试
     * schemename : 电厂漆房排气检测
     * starttime : 2018-08-04 00:02:00
     * endtime : 2018-08-08 00:00:00
     * address :
     * coordinate :
     * stopreason : 涂于岛屿
     * stoptime : 2018-08-07 17:13:36
     * stopuser : 1
     * isstoppass : 0
     * isstoppassname : 否
     * stopusername : 赵 涛
     * points : [{"pointsid":"1dbf508c-cc9d-41ab-86e2-e32d9cd25a2a","categoryid":"22","categoryname":"废气","address":"蒲城电厂东门","longitude":"109.051675","latitude":"34.308156","altitude":"1200","remark":"认真检测","heightvalue":"1","ispoisonname":"是","isradiationname":"否"}]
     * fujians : [{"id":"94","kid":"b48947ca-7082-4570-8388-2923a71da739","names":"","types":"1","typesname":"《水和废水监测分析办法》第四版","fujian":"/upload/files/fe162afd-37c9-4093-9f7f-728165df354c545.jpg","category":"6"},{"id":"95","kid":"b48947ca-7082-4570-8388-2923a71da739","names":"","types":"1","typesname":"《水和废水监测分析办法》第四版","fujian":"/upload/files/607e9714-2edf-4fa5-aab8-45525c692149361.jpg","category":"6"},{"id":"99","kid":"b48947ca-7082-4570-8388-2923a71da739","names":"","types":"1","typesname":"《水和废水监测分析办法》第四版","fujian":"/upload/files/4b0af7dc-b65b-4f62-acba-924ee49d0c8b935.jpg","category":"6"},{"id":"100","kid":"b48947ca-7082-4570-8388-2923a71da739","names":"","types":"1","typesname":"《水和废水监测分析办法》第四版","fujian":"/upload/files/80627395-051e-4f6c-af35-080f4b0c7f05653.jpg","category":"6"},{"id":"101","kid":"b48947ca-7082-4570-8388-2923a71da739","names":"","types":"1","typesname":"《水和废水监测分析办法》第四版","fujian":"/upload/files/4fa62a6d-242f-4e5d-a002-e1f32975db7f743.jpg","category":"6"}]
     */

    private String id;
    private String subject;
    private String content;
    private String ctime;
    private String schemeid;
    private String conid;
    private String remark;
    private String schemename;
    private String starttime;
    private String endtime;
    private String address;
    private String coordinate;
    private String stopreason;
    private String stoptime;
    private String stopuser;
    private String isstoppass;
    private String isstoppassname;
    private String stopusername;
    private List<PointsBean> points;
    private List<FujiansBean> fujians;
    /**
     * IsUrgent : 0
     * IsUrgentName : 否
     */

    private String IsUrgent;
    private String IsUrgentName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid;
    }

    public String getConid() {
        return conid;
    }

    public void setConid(String conid) {
        this.conid = conid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSchemename() {
        return schemename;
    }

    public void setSchemename(String schemename) {
        this.schemename = schemename;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getStopreason() {
        return stopreason;
    }

    public void setStopreason(String stopreason) {
        this.stopreason = stopreason;
    }

    public String getStoptime() {
        return stoptime;
    }

    public void setStoptime(String stoptime) {
        this.stoptime = stoptime;
    }

    public String getStopuser() {
        return stopuser;
    }

    public void setStopuser(String stopuser) {
        this.stopuser = stopuser;
    }

    public String getIsstoppass() {
        return isstoppass;
    }

    public void setIsstoppass(String isstoppass) {
        this.isstoppass = isstoppass;
    }

    public String getIsstoppassname() {
        return isstoppassname;
    }

    public void setIsstoppassname(String isstoppassname) {
        this.isstoppassname = isstoppassname;
    }

    public String getStopusername() {
        return stopusername;
    }

    public void setStopusername(String stopusername) {
        this.stopusername = stopusername;
    }

    public List<PointsBean> getPoints() {
        return points;
    }

    public void setPoints(List<PointsBean> points) {
        this.points = points;
    }

    public List<FujiansBean> getFujians() {
        return fujians;
    }

    public void setFujians(List<FujiansBean> fujians) {
        this.fujians = fujians;
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

    public static class PointsBean {
        /**
         * pointsid : 1dbf508c-cc9d-41ab-86e2-e32d9cd25a2a
         * categoryid : 22
         * categoryname : 废气
         * address : 蒲城电厂东门
         * longitude : 109.051675
         * latitude : 34.308156
         * altitude : 1200
         * remark : 认真检测
         * heightvalue : 1
         * ispoisonname : 是
         * isradiationname : 否
         */

        private String pointsid;
        private String categoryid;
        private String categoryname;
        private String address;
        private String longitude;
        private String latitude;
        private String altitude;
        private String remark;
        private String heightvalue;
        private String ispoisonname;
        private String isradiationname;

        public String getPointsid() {
            return pointsid;
        }

        public void setPointsid(String pointsid) {
            this.pointsid = pointsid;
        }

        public String getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(String categoryid) {
            this.categoryid = categoryid;
        }

        public String getCategoryname() {
            return categoryname;
        }

        public void setCategoryname(String categoryname) {
            this.categoryname = categoryname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getAltitude() {
            return altitude;
        }

        public void setAltitude(String altitude) {
            this.altitude = altitude;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getHeightvalue() {
            return heightvalue;
        }

        public void setHeightvalue(String heightvalue) {
            this.heightvalue = heightvalue;
        }

        public String getIspoisonname() {
            return ispoisonname;
        }

        public void setIspoisonname(String ispoisonname) {
            this.ispoisonname = ispoisonname;
        }

        public String getIsradiationname() {
            return isradiationname;
        }

        public void setIsradiationname(String isradiationname) {
            this.isradiationname = isradiationname;
        }
    }

    public static class FujiansBean  implements Serializable {
        /**
         * id : 94
         * kid : b48947ca-7082-4570-8388-2923a71da739
         * names :
         * types : 1
         * typesname : 《水和废水监测分析办法》第四版
         * fujian : /upload/files/fe162afd-37c9-4093-9f7f-728165df354c545.jpg
         * category : 6
         */

        private String id;
        private String kid;
        private String names;
        private String types;
        private String typesname;
        private String fujian;
        private String category;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKid() {
            return kid;
        }

        public void setKid(String kid) {
            this.kid = kid;
        }

        public String getNames() {
            return names;
        }

        public void setNames(String names) {
            this.names = names;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public String getTypesname() {
            return typesname;
        }

        public void setTypesname(String typesname) {
            this.typesname = typesname;
        }

        public String getFujian() {
            return fujian;
        }

        public void setFujian(String fujian) {
            this.fujian = fujian;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }
}

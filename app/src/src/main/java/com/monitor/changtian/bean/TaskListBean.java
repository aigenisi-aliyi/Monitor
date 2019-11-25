package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/6/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskListBean implements Serializable {


    /**
     * id : b48947ca-7082-4570-8388-2923a71da739
     * subject :
     * content :
     * userid : 1
     * username : 赵 涛
     * ctime : 2018-08-03 11:42:03
     * schemeid : 4cd8518d-3506-45cf-bcc8-83b17987167b
     * schemename : 电厂漆房排气检测
     * manager :
     * address :
     * starttime : 2018-08-04 00:02:00
     * endtime : 2018-08-08 00:00:00
     * coordinate :
     * remark : 测试
     * conid : 20
     * auditor :
     * auditorname :
     * audittime :
     * AuditOpinion :
     * stopreason :
     * stoptime :
     * stopuser :
     * closeuser :
     * closetime :
     * managerstoptime :
     * managerstopreason :
     * managerstopuser :
     * managerstopusername :
     * isstoppass :
     * isstoppassname :
     * isclosepass :
     * isclosepassname :
     * closeauditor :
     * closeaudittime :
     * closeAuditOpinion :
     * stopusername :
     * closeusername :
     * closeauditorname :
     * points : [{"pointsid":"1dbf508c-cc9d-41ab-86e2-e32d9cd25a2a","categoryid":"22","categoryname":"废气","address":"蒲城电厂东门","longitude":"109.051675","latitude":"34.308156","altitude":"1200","remark":"认真检测","heightvalue":"1","ispoisonname":"是","isradiationname":"否"}]
     */


    private String address2;
    private String phone2;
    private String Contacts2;

    private String id;
    private String subject;
    private String content;
    private String userid;
    private String username;
    private String ctime;
    private String schemeid;
    private String schemename;
    private String manager;
    private String address;
    private String starttime;
    private String endtime;
    private String coordinate;
    private String remark;
    private String conid;
    private String auditor;
    private String auditorname;
    private String audittime;
    private String AuditOpinion;
    private String stopreason;
    private String stoptime;
    private String stopuser;
    private String closeuser;
    private String closetime;
    private String managerstoptime;
    private String managerstopreason;
    private String managerstopuser;
    private String managerstopusername;
    private String isstoppass;
    private String isstoppassname;
    private String isclosepass;
    private String isclosepassname;
    private String closeauditor;
    private String closeaudittime;
    private String closeAuditOpinion;
    private String stopusername;
    private String closeusername;
    private String closeauditorname;
    private List<PointsBean> points;

    private List<EquipsBean> equips;


    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getContacts2() {
        return Contacts2;
    }

    public void setContacts2(String contacts2) {
        Contacts2 = contacts2;
    }

    public List<EquipsBean> getEquips() {
        return equips;
    }

    public void setEquips(List<EquipsBean> equips) {
        this.equips = equips;
    }

    public static class EquipsBean implements Serializable {

        private String totalnumber;
        private String categoryname;

        public String getTotalnumber() {
            return totalnumber;
        }

        public void setTotalnumber(String totalnumber) {
            this.totalnumber = totalnumber;
        }

        public String getCategoryname() {
            return categoryname;
        }

        public void setCategoryname(String categoryname) {
            this.categoryname = categoryname;
        }
    }


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

    public String getSchemename() {
        return schemename;
    }

    public void setSchemename(String schemename) {
        this.schemename = schemename;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getConid() {
        return conid;
    }

    public void setConid(String conid) {
        this.conid = conid;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditorname() {
        return auditorname;
    }

    public void setAuditorname(String auditorname) {
        this.auditorname = auditorname;
    }

    public String getAudittime() {
        return audittime;
    }

    public void setAudittime(String audittime) {
        this.audittime = audittime;
    }

    public String getAuditOpinion() {
        return AuditOpinion;
    }

    public void setAuditOpinion(String AuditOpinion) {
        this.AuditOpinion = AuditOpinion;
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

    public String getCloseuser() {
        return closeuser;
    }

    public void setCloseuser(String closeuser) {
        this.closeuser = closeuser;
    }

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }

    public String getManagerstoptime() {
        return managerstoptime;
    }

    public void setManagerstoptime(String managerstoptime) {
        this.managerstoptime = managerstoptime;
    }

    public String getManagerstopreason() {
        return managerstopreason;
    }

    public void setManagerstopreason(String managerstopreason) {
        this.managerstopreason = managerstopreason;
    }

    public String getManagerstopuser() {
        return managerstopuser;
    }

    public void setManagerstopuser(String managerstopuser) {
        this.managerstopuser = managerstopuser;
    }

    public String getManagerstopusername() {
        return managerstopusername;
    }

    public void setManagerstopusername(String managerstopusername) {
        this.managerstopusername = managerstopusername;
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

    public String getIsclosepass() {
        return isclosepass;
    }

    public void setIsclosepass(String isclosepass) {
        this.isclosepass = isclosepass;
    }

    public String getIsclosepassname() {
        return isclosepassname;
    }

    public void setIsclosepassname(String isclosepassname) {
        this.isclosepassname = isclosepassname;
    }

    public String getCloseauditor() {
        return closeauditor;
    }

    public void setCloseauditor(String closeauditor) {
        this.closeauditor = closeauditor;
    }

    public String getCloseaudittime() {
        return closeaudittime;
    }

    public void setCloseaudittime(String closeaudittime) {
        this.closeaudittime = closeaudittime;
    }

    public String getCloseAuditOpinion() {
        return closeAuditOpinion;
    }

    public void setCloseAuditOpinion(String closeAuditOpinion) {
        this.closeAuditOpinion = closeAuditOpinion;
    }

    public String getStopusername() {
        return stopusername;
    }

    public void setStopusername(String stopusername) {
        this.stopusername = stopusername;
    }

    public String getCloseusername() {
        return closeusername;
    }

    public void setCloseusername(String closeusername) {
        this.closeusername = closeusername;
    }

    public String getCloseauditorname() {
        return closeauditorname;
    }

    public void setCloseauditorname(String closeauditorname) {
        this.closeauditorname = closeauditorname;
    }

    public List<PointsBean> getPoints() {
        return points;
    }

    public void setPoints(List<PointsBean> points) {
        this.points = points;
    }

    public static class PointsBean implements Serializable {
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
         * DaysNumber=3
         * frequency=3
         * ishadden=0
         */

        private List<FrequencysBeanBean> frequencys;


        public List<FrequencysBeanBean> getFrequencys() {
            return frequencys;
        }

        public void setFrequencys(List<FrequencysBeanBean> frequencys) {
            this.frequencys = frequencys;
        }

        public static class FrequencysBeanBean implements Serializable {

            private String frequencyname;
            private String isEnd;

            public String getFrequencyname() {
                return frequencyname;
            }

            public void setFrequencyname(String frequencyname) {
                this.frequencyname = frequencyname;
            }

            public String getIsEnd() {
                return isEnd;
            }

            public void setIsEnd(String isEnd) {
                this.isEnd = isEnd;
            }
        }

        private String ishadden;
        private Boolean isShow = false;
        private String DaysNumber;
        private String frequency;
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

        public String getIshadden() {
            return ishadden;
        }

        public void setIshadden(String ishadden) {
            this.ishadden = ishadden;
        }

        public Boolean getShow() {
            return isShow;
        }

        public void setShow(Boolean show) {
            isShow = show;
        }

        private List<FactorsBean> factors;

        public List<FactorsBean> getFactors() {
            return factors;
        }

        public void setFactors(List<FactorsBean> factors) {
            this.factors = factors;
        }

        public static class FactorsBean implements Serializable {

            private String contentid;
            private String fid;
            private String factorname;
            private String methodname;
            private String frequency;
            private String AnalysisMethod;

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

            public String getAnalysisMethod() {
                return AnalysisMethod;
            }

            public void setAnalysisMethod(String analysisMethod) {
                AnalysisMethod = analysisMethod;
            }
        }


        private List<TaskPointsFrequencyBean> taskPointsFrequencyBeans;

        public List<TaskPointsFrequencyBean> getTaskPointsFrequencyBeans() {
            return taskPointsFrequencyBeans;
        }

        public void setTaskPointsFrequencyBeans(List<TaskPointsFrequencyBean> taskPointsFrequencyBeans) {
            this.taskPointsFrequencyBeans = taskPointsFrequencyBeans;
        }

        public String getDaysNumber() {
            return DaysNumber;
        }

        public void setDaysNumber(String daysNumber) {
            DaysNumber = daysNumber;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }

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
}

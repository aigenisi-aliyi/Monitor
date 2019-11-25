package com.monitor.changtian.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/7/16.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class DetectionSchemeInfoBean implements Serializable {


    /**
     * schemeid : 09ac53b8-0c95-4018-b9c5-91ec942ef734
     * projectid : 26
     * projectname : 陕西宏远燃气天然气输配调压设备及煤化工配套设备生产基地项目
     * projectintroduction :
     * customername : 陕西宏远
     * Telephone : 56743561234
     * ContactNumber :
     * Contacts : 李善长
     * userid : 1
     * username : 赵 涛
     * createtime : 2018-07-25 18:09:03
     * schemeStatus : 待审核
     * SchemeName : 4s店漆房检测 2
     * introduction : 4s店漆房检测
     * samplemode : 0
     * samplemodename : 现场采样
     * isretention : 0
     * isretentionname : 否
     * retentiontime :
     * retentioninfo :
     * types : 1
     * typesname : 自测
     * taskNumber : 1
     * Intervaltime : 1
     * IsUrgent : 1
     * IsUrgentName : 是
     * leftcateogory : [{"categoryid":"22","categorycode":"","categoryname":"废气","criterionid":"96","criterionname":"《废气判定依据》","points":[{"pointsid":"7929ebac-116b-47af-a171-f93087bcb56d","address":"儿童椅","longitude":"109.050237","latitude":"34.301238","altitude":"200","heightvalue":"1","ispoison":"1","ispoisonname":"是","isradiation":"1","isradiationname":"是","remark":"344575678","attributes":[],"factors":[{"contentid":"f108dd6e-1566-41ae-95f7-ff339f863adb","fid":"133","factorname":"苯","SamplingStyle":"0","SamplingStyleName":"现场采样","AnalysisMethod":"25","methodname":"《苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"呵呵","TestingRequire":"而天然体有","frequency":"1","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"2f87695d-dfe7-4729-b817-c2db59a14214","equipments":[],"consumables":[]},{"contentid":"98b55eb7-8202-4035-8742-1f8052109fa7","fid":"134","factorname":"甲苯","SamplingStyle":"0","SamplingStyleName":"现场采样","AnalysisMethod":"26","methodname":"《甲苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"呵呵","TestingRequire":"而天然体有","frequency":"1","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"2f87695d-dfe7-4729-b817-c2db59a14214","equipments":[],"consumables":[]},{"contentid":"a4fe8a58-534c-4fad-aa92-d5c37f0eb2ca","fid":"136","factorname":"二甲苯","SamplingStyle":"0","SamplingStyleName":"现场采样","AnalysisMethod":"27","methodname":"《二甲苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"呵呵","TestingRequire":"而天然体有","frequency":"1","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"2f87695d-dfe7-4729-b817-c2db59a14214","equipments":[],"consumables":[]},{"contentid":"75fea4e7-850a-48c0-bbac-d3d3eb2ad487","fid":"189","factorname":"非甲烷总烃","SamplingStyle":"0","SamplingStyleName":"现场采样","AnalysisMethod":"28","methodname":"《非甲烷总烃检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"呵呵","TestingRequire":"而天然体有","frequency":"1","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"2f87695d-dfe7-4729-b817-c2db59a14214","equipments":[],"consumables":[]}]}]}]
     */

    private String maxdaynumber;
    private String taskcount;
    private String auditOpinion;
    private String schemeid;
    private String projectid;
    private String projectname;
    private String projectintroduction;
    private String customername;
    private String Telephone;
    private String ContactNumber;
    private String Contacts;
    private String userid;
    private String username;
    private String createtime;
    private String schemeStatus;
    private String SchemeName;
    private String introduction;
    private String samplemode;
    private String samplemodename;
    private String isretention;
    private String isretentionname;
    private String retentiontime;
    private String retentioninfo;
    private String types;
    private String typesname;
    private String taskNumber;
    private String Intervaltime;
    private String IsUrgent;
    private String IsUrgentName;
    private String AccountNumber;
    private List<LeftcateogoryBean> leftcateogory;

    public String getMaxdaynumbe() {
        return maxdaynumber;
    }

    public void setMaxdaynumbe(String maxdaynumber) {
        this.maxdaynumber = maxdaynumber;
    }

    public String getTaskcount() {
        return taskcount;
    }

    public void setTaskcount(String taskcount) {
        this.taskcount = taskcount;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectintroduction() {
        return projectintroduction;
    }

    public void setProjectintroduction(String projectintroduction) {
        this.projectintroduction = projectintroduction;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String ContactNumber) {
        this.ContactNumber = ContactNumber;
    }

    public String getContacts() {
        return Contacts;
    }

    public void setContacts(String Contacts) {
        this.Contacts = Contacts;
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

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
    }

    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String SchemeName) {
        this.SchemeName = SchemeName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSamplemode() {
        return samplemode;
    }

    public void setSamplemode(String samplemode) {
        this.samplemode = samplemode;
    }

    public String getSamplemodename() {
        return samplemodename;
    }

    public void setSamplemodename(String samplemodename) {
        this.samplemodename = samplemodename;
    }

    public String getIsretention() {
        return isretention;
    }

    public void setIsretention(String isretention) {
        this.isretention = isretention;
    }

    public String getIsretentionname() {
        return isretentionname;
    }

    public void setIsretentionname(String isretentionname) {
        this.isretentionname = isretentionname;
    }

    public String getRetentiontime() {
        return retentiontime;
    }

    public void setRetentiontime(String retentiontime) {
        this.retentiontime = retentiontime;
    }

    public String getRetentioninfo() {
        return retentioninfo;
    }

    public void setRetentioninfo(String retentioninfo) {
        this.retentioninfo = retentioninfo;
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

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getIntervaltime() {
        return Intervaltime;
    }

    public void setIntervaltime(String Intervaltime) {
        this.Intervaltime = Intervaltime;
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

    public List<LeftcateogoryBean> getLeftcateogory() {
        return leftcateogory;
    }

    public void setLeftcateogory(List<LeftcateogoryBean> leftcateogory) {
        this.leftcateogory = leftcateogory;
    }

    public static class LeftcateogoryBean implements Serializable, IPickerViewData {
        /**
         * categoryid : 22
         * categorycode :
         * categoryname : 废气
         * criterionid : 96
         * criterionname : 《废气判定依据》
         * points : [{"pointsid":"7929ebac-116b-47af-a171-f93087bcb56d","address":"儿童椅","longitude":"109.050237","latitude":"34.301238","altitude":"200","heightvalue":"1","ispoison":"1","ispoisonname":"是","isradiation":"1","isradiationname":"是","remark":"344575678","attributes":[],"factors":[{"contentid":"f108dd6e-1566-41ae-95f7-ff339f863adb","fid":"133","factorname":"苯","SamplingStyle":"0","SamplingStyleName":"现场采样","AnalysisMethod":"25","methodname":"《苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"呵呵","TestingRequire":"而天然体有","frequency":"1","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"2f87695d-dfe7-4729-b817-c2db59a14214","equipments":[],"consumables":[]},{"contentid":"98b55eb7-8202-4035-8742-1f8052109fa7","fid":"134","factorname":"甲苯","SamplingStyle":"0","SamplingStyleName":"现场采样","AnalysisMethod":"26","methodname":"《甲苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"呵呵","TestingRequire":"而天然体有","frequency":"1","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"2f87695d-dfe7-4729-b817-c2db59a14214","equipments":[],"consumables":[]},{"contentid":"a4fe8a58-534c-4fad-aa92-d5c37f0eb2ca","fid":"136","factorname":"二甲苯","SamplingStyle":"0","SamplingStyleName":"现场采样","AnalysisMethod":"27","methodname":"《二甲苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"呵呵","TestingRequire":"而天然体有","frequency":"1","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"2f87695d-dfe7-4729-b817-c2db59a14214","equipments":[],"consumables":[]},{"contentid":"75fea4e7-850a-48c0-bbac-d3d3eb2ad487","fid":"189","factorname":"非甲烷总烃","SamplingStyle":"0","SamplingStyleName":"现场采样","AnalysisMethod":"28","methodname":"《非甲烷总烃检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"呵呵","TestingRequire":"而天然体有","frequency":"1","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"2f87695d-dfe7-4729-b817-c2db59a14214","equipments":[],"consumables":[]}]}]
         */

        private String categoryid;
        private String categorycode;
        private String categoryname;
        private String criterionid;
        private String criterionname;
        private List<PointsBean> points;

        private List<FactorsBean> factors;

        public List<FactorsBean> getFactors() {
            return factors;
        }

        public void setFactors(List<FactorsBean> factors) {
            this.factors = factors;
        }

        @Override
        public String getPickerViewText() {
            return categoryname;
        }

        public static class FactorsBean implements Serializable {
            public boolean isChoice_save() {
                return isChoice_save;
            }

            public void setChoice_save(boolean choice_save) {
                isChoice_save = choice_save;
            }

            public boolean isChoice() {
                return isChoice;
            }

            public void setChoice(boolean choice) {
                isChoice = choice;
            }

            /**
             * contentid : 75c42e3f-7513-4813-949b-e218e217bd4b
             * fid : 133
             * factorname : 苯
             * SamplingStyle : 1
             * SamplingStyleName : 客户送样
             * AnalysisMethod : 25
             * methodname : 《苯检测分析方法》
             * tsid : 5
             * tsnames : 《空气和废气监测分析方法》（第二版）
             * explainInfo : 7
             * TestingRequire : 7
             * frequency : 7
             * DaysNumber : 7
             * types : 1
             * typesname : 自测
             * ptsid : 87
             * ptsnames : 《2009收费标准》
             * dscid : 7cbbbe7e-d66f-4e18-966b-2b994eae3155
             */

            private String decisiontablenames;
            private String decisionlevel;
            private boolean isChoice_save;
            private boolean isChoice;
            private String contentid;
            private String fid;
            private String factorname;
            private String SamplingStyle;
            private String SamplingStyleName;
            private String AnalysisMethod;
            private String methodname;
            private String tsid;
            private String tsnames;
            private String explainInfo;
            private String TestingRequire;
            private String frequency;
            private String DaysNumber;
            private String types;
            private String typesname;
            private String ptsid;
            private String ptsnames;
            private String dscid;

            public String getDecisiontablenames() {
                return decisiontablenames;
            }

            public void setDecisiontablenames(String decisiontablenames) {
                this.decisiontablenames = decisiontablenames;
            }

            public String getDecisionlevel() {
                return decisionlevel;
            }

            public void setDecisionlevel(String decisionlevel) {
                this.decisionlevel = decisionlevel;
            }

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

            public String getSamplingStyle() {
                return SamplingStyle;
            }

            public void setSamplingStyle(String SamplingStyle) {
                this.SamplingStyle = SamplingStyle;
            }

            public String getSamplingStyleName() {
                return SamplingStyleName;
            }

            public void setSamplingStyleName(String SamplingStyleName) {
                this.SamplingStyleName = SamplingStyleName;
            }

            public String getAnalysisMethod() {
                return AnalysisMethod;
            }

            public void setAnalysisMethod(String AnalysisMethod) {
                this.AnalysisMethod = AnalysisMethod;
            }

            public String getMethodname() {
                return methodname;
            }

            public void setMethodname(String methodname) {
                this.methodname = methodname;
            }

            public String getTsid() {
                return tsid;
            }

            public void setTsid(String tsid) {
                this.tsid = tsid;
            }

            public String getTsnames() {
                return tsnames;
            }

            public void setTsnames(String tsnames) {
                this.tsnames = tsnames;
            }

            public String getExplainInfo() {
                return explainInfo;
            }

            public void setExplainInfo(String explainInfo) {
                this.explainInfo = explainInfo;
            }

            public String getTestingRequire() {
                return TestingRequire;
            }

            public void setTestingRequire(String TestingRequire) {
                this.TestingRequire = TestingRequire;
            }

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

            public String getPtsid() {
                return ptsid;
            }

            public void setPtsid(String ptsid) {
                this.ptsid = ptsid;
            }

            public String getPtsnames() {
                return ptsnames;
            }

            public void setPtsnames(String ptsnames) {
                this.ptsnames = ptsnames;
            }

            public String getDscid() {
                return dscid;
            }

            public void setDscid(String dscid) {
                this.dscid = dscid;
            }
        }

        public String getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(String categoryid) {
            this.categoryid = categoryid;
        }

        public String getCategorycode() {
            return categorycode;
        }

        public void setCategorycode(String categorycode) {
            this.categorycode = categorycode;
        }

        public String getCategoryname() {
            return categoryname;
        }

        public void setCategoryname(String categoryname) {
            this.categoryname = categoryname;
        }

        public String getCriterionid() {
            return criterionid;
        }

        public void setCriterionid(String criterionid) {
            this.criterionid = criterionid;
        }

        public String getCriterionname() {
            return criterionname;
        }

        public void setCriterionname(String criterionname) {
            this.criterionname = criterionname;
        }

        public List<PointsBean> getPoints() {
            return points;
        }

        public void setPoints(List<PointsBean> points) {
            this.points = points;
        }

        public static class PointsBean implements Serializable {
            /**
             * pointsid : 7929ebac-116b-47af-a171-f93087bcb56d
             * address : 儿童椅
             * longitude : 109.050237
             * latitude : 34.301238
             * altitude : 200
             * heightvalue : 1
             * ispoison : 1
             * ispoisonname : 是
             * isradiation : 1
             * isradiationname : 是
             * remark : 344575678
             * attributes : []
             * <p>
             * factors : [{"contentid":"f108dd6e-1566-41ae-95f7-ff339f863adb","fid":"133","factorname":"苯","SamplingStyle":"0","SamplingStyleName":"现场采样","AnalysisMethod":"25","methodname":"《苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"呵呵","TestingRequire":"而天然体有","frequency":"1","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"2f87695d-dfe7-4729-b817-c2db59a14214","equipments":[],"consumables":[]},{"contentid":"98b55eb7-8202-4035-8742-1f8052109fa7","fid":"134","factorname":"甲苯","SamplingStyle":"0","SamplingStyleName":"现场采样","AnalysisMethod":"26","methodname":"《甲苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"呵呵","TestingRequire":"而天然体有","frequency":"1","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"2f87695d-dfe7-4729-b817-c2db59a14214","equipments":[],"consumables":[]},{"contentid":"a4fe8a58-534c-4fad-aa92-d5c37f0eb2ca","fid":"136","factorname":"二甲苯","SamplingStyle":"0","SamplingStyleName":"现场采样","AnalysisMethod":"27","methodname":"《二甲苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"呵呵","TestingRequire":"而天然体有","frequency":"1","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"2f87695d-dfe7-4729-b817-c2db59a14214","equipments":[],"consumables":[]},{"contentid":"75fea4e7-850a-48c0-bbac-d3d3eb2ad487","fid":"189","factorname":"非甲烷总烃","SamplingStyle":"0","SamplingStyleName":"现场采样","AnalysisMethod":"28","methodname":"《非甲烷总烃检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"呵呵","TestingRequire":"而天然体有","frequency":"1","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"2f87695d-dfe7-4729-b817-c2db59a14214","equipments":[],"consumables":[]}]
             */

            private int samplecount;
            private String pointsid;
            private String address;
            private String longitude;
            private String latitude;
            private String altitude;
            private String heightvalue;
            private String ispoison;
            private String ispoisonname;
            private String isradiation;
            private String isradiationname;
            private String remark;
            private List<?> attributes;
            private List<FactorsBean> factors;


            private String DaysNumber;
            private String frequency;

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

            public int getSamplecount() {
                return samplecount;
            }

            public void setSamplecount(int samplecount) {
                this.samplecount = samplecount;
            }

            public String getPointsid() {
                return pointsid;
            }

            public void setPointsid(String pointsid) {
                this.pointsid = pointsid;
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

            public String getHeightvalue() {
                return heightvalue;
            }

            public void setHeightvalue(String heightvalue) {
                this.heightvalue = heightvalue;
            }

            public String getIspoison() {
                return ispoison;
            }

            public void setIspoison(String ispoison) {
                this.ispoison = ispoison;
            }

            public String getIspoisonname() {
                return ispoisonname;
            }

            public void setIspoisonname(String ispoisonname) {
                this.ispoisonname = ispoisonname;
            }

            public String getIsradiation() {
                return isradiation;
            }

            public void setIsradiation(String isradiation) {
                this.isradiation = isradiation;
            }

            public String getIsradiationname() {
                return isradiationname;
            }

            public void setIsradiationname(String isradiationname) {
                this.isradiationname = isradiationname;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public List<?> getAttributes() {
                return attributes;
            }

            public void setAttributes(List<?> attributes) {
                this.attributes = attributes;
            }

            public List<FactorsBean> getFactors() {
                return factors;
            }

            public void setFactors(List<FactorsBean> factors) {
                this.factors = factors;
            }

            public static class FactorsBean implements Serializable {
                /**
                 * contentid : f108dd6e-1566-41ae-95f7-ff339f863adb
                 * fid : 133
                 * factorname : 苯
                 * SamplingStyle : 0
                 * SamplingStyleName : 现场采样
                 * AnalysisMethod : 25
                 * methodname : 《苯检测分析方法》
                 * tsid : 5
                 * tsnames : 《空气和废气监测分析方法》（第二版）
                 * explainInfo : 呵呵
                 * TestingRequire : 而天然体有
                 * frequency : 1
                 * DaysNumber : 7
                 * types : 1
                 * typesname : 自测
                 * ptsid : 87
                 * ptsnames : 《2009收费标准》
                 * dscid : 2f87695d-dfe7-4729-b817-c2db59a14214
                 * equipments : []
                 * consumables : []
                 */
                private String decisiontablenames;
                private String decisionlevel;
                private String contentid;
                private String fid;
                private String factorname;
                private String SamplingStyle;
                private String SamplingStyleName;
                private String AnalysisMethod;
                private String methodname;
                private String tsid;
                private String tsnames;
                private String explainInfo;
                private String TestingRequire;
                private String frequency;
                private String DaysNumber;
                private String types;
                private String typesname;
                private String ptsid;
                private String ptsnames;
                private String dscid;
                private List<EquipmentsBean> equipments;
                private List<ConsumablesBean> consumables;


                public String getDecisiontablenames() {
                    return decisiontablenames;
                }

                public void setDecisiontablenames(String decisiontablenames) {
                    this.decisiontablenames = decisiontablenames;
                }

                public String getDecisionlevel() {
                    return decisionlevel;
                }

                public void setDecisionlevel(String decisionlevel) {
                    this.decisionlevel = decisionlevel;
                }

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

                public String getSamplingStyle() {
                    return SamplingStyle;
                }

                public void setSamplingStyle(String SamplingStyle) {
                    this.SamplingStyle = SamplingStyle;
                }

                public String getSamplingStyleName() {
                    return SamplingStyleName;
                }

                public void setSamplingStyleName(String SamplingStyleName) {
                    this.SamplingStyleName = SamplingStyleName;
                }

                public String getAnalysisMethod() {
                    return AnalysisMethod;
                }

                public void setAnalysisMethod(String AnalysisMethod) {
                    this.AnalysisMethod = AnalysisMethod;
                }

                public String getMethodname() {
                    return methodname;
                }

                public void setMethodname(String methodname) {
                    this.methodname = methodname;
                }

                public String getTsid() {
                    return tsid;
                }

                public void setTsid(String tsid) {
                    this.tsid = tsid;
                }

                public String getTsnames() {
                    return tsnames;
                }

                public void setTsnames(String tsnames) {
                    this.tsnames = tsnames;
                }

                public String getExplainInfo() {
                    return explainInfo;
                }

                public void setExplainInfo(String explainInfo) {
                    this.explainInfo = explainInfo;
                }

                public String getTestingRequire() {
                    return TestingRequire;
                }

                public void setTestingRequire(String TestingRequire) {
                    this.TestingRequire = TestingRequire;
                }

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

                public String getPtsid() {
                    return ptsid;
                }

                public void setPtsid(String ptsid) {
                    this.ptsid = ptsid;
                }

                public String getPtsnames() {
                    return ptsnames;
                }

                public void setPtsnames(String ptsnames) {
                    this.ptsnames = ptsnames;
                }

                public String getDscid() {
                    return dscid;
                }

                public void setDscid(String dscid) {
                    this.dscid = dscid;
                }


                public List<EquipmentsBean> getEquipments() {
                    return equipments;
                }

                public void setEquipments(List<EquipmentsBean> equipments) {
                    this.equipments = equipments;
                }

                public List<ConsumablesBean> getConsumables() {
                    return consumables;
                }

                public void setConsumables(List<ConsumablesBean> consumables) {
                    this.consumables = consumables;
                }

                public static class EquipmentsBean implements Serializable {
                    /**
                     * categoryid : 240
                     * number : 1
                     * names : 离子计
                     */

                    private String categoryid;
                    private String number;
                    private String names;

                    public String getCategoryid() {
                        return categoryid;
                    }

                    public void setCategoryid(String categoryid) {
                        this.categoryid = categoryid;
                    }

                    public String getNumber() {
                        return number;
                    }

                    public void setNumber(String number) {
                        this.number = number;
                    }

                    public String getNames() {
                        return names;
                    }

                    public void setNames(String names) {
                        this.names = names;
                    }
                }

                public static class ConsumablesBean implements Serializable {
                    /**
                     * categoryid : 256
                     * number : 1
                     * names : 真空袋
                     */

                    private String categoryid;
                    private String number;
                    private String names;
                    private String Consumablesid;
                    private String Consumablesnames;

                    public String getConsumablesid() {
                        return Consumablesid;
                    }

                    public void setConsumablesid(String consumablesid) {
                        Consumablesid = consumablesid;
                    }

                    public String getConsumablesnames() {
                        return Consumablesnames;
                    }

                    public void setConsumablesnames(String consumablesnames) {
                        Consumablesnames = consumablesnames;
                    }

                    public String getCategoryid() {
                        return categoryid;
                    }

                    public void setCategoryid(String categoryid) {
                        this.categoryid = categoryid;
                    }

                    public String getNumber() {
                        return number;
                    }

                    public void setNumber(String number) {
                        this.number = number;
                    }

                    public String getNames() {
                        return names;
                    }

                    public void setNames(String names) {
                        this.names = names;
                    }

                }


            }
        }
    }
}

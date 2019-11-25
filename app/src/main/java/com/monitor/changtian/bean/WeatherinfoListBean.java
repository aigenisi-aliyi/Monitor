package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/4/24.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class WeatherinfoListBean implements Serializable {

    /**
     * schemeid : cf084564-89b6-4b74-b686-dd7bc2b2b378
     * projectid : 26
     * projectname : 陕西宏远燃气天然气输配调压设备及煤化工配套设备生产基地项目
     * projectintroduction :
     * customername : 陕西宏远
     * Telephone : 56743561234
     * ContactNumber :
     * Contacts : 李善长
     * userid : 1
     * username : 赵 涛
     * createtime : 2018-07-26 15:14:53
     * schemeStatus : 待审核
     * SchemeName : 4s店漆房检测 5 送样
     * introduction : 4s店漆房检测 5 送样4s店漆房检测 5 送样4s店漆房检测 5 送样4s店漆房检测 5 送样4s店漆房检测 5 送样
     * samplemode : 1
     * samplemodename : 客户送样
     * isretention : 0
     * isretentionname : 否
     * retentiontime :
     * retentioninfo :
     * types : 1
     * typesname : 自测
     * taskNumber : 1
     * Intervaltime : 5
     * IsUrgent : 1
     * IsUrgentName : 是
     * leftcateogory : [{"categoryid":"22","categorycode":"","categoryname":"废气","criterionid":"96","criterionname":"《废气判定依据》","factors":[{"contentid":"75c42e3f-7513-4813-949b-e218e217bd4b","fid":"133","factorname":"苯","SamplingStyle":"1","SamplingStyleName":"客户送样","AnalysisMethod":"25","methodname":"《苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"7","TestingRequire":"7","frequency":"7","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"7cbbbe7e-d66f-4e18-966b-2b994eae3155"},{"contentid":"a4187111-00c7-4db4-b4da-eeac033bf184","fid":"136","factorname":"二甲苯","SamplingStyle":"1","SamplingStyleName":"客户送样","AnalysisMethod":"27","methodname":"《二甲苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"6","TestingRequire":"6","frequency":"6","DaysNumber":"6","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"7cbbbe7e-d66f-4e18-966b-2b994eae3155"},{"contentid":"afa9f4b7-a049-4451-baab-c1a84f6aa264","fid":"134","factorname":"甲苯","SamplingStyle":"1","SamplingStyleName":"客户送样","AnalysisMethod":"26","methodname":"《甲苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"7","TestingRequire":"7","frequency":"7","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"7cbbbe7e-d66f-4e18-966b-2b994eae3155"}]}]
     */

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
    private List<LeftcateogoryBean> leftcateogory;

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

    public static class LeftcateogoryBean {
        /**
         * categoryid : 22
         * categorycode :
         * categoryname : 废气
         * criterionid : 96
         * criterionname : 《废气判定依据》
         * factors : [{"contentid":"75c42e3f-7513-4813-949b-e218e217bd4b","fid":"133","factorname":"苯","SamplingStyle":"1","SamplingStyleName":"客户送样","AnalysisMethod":"25","methodname":"《苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"7","TestingRequire":"7","frequency":"7","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"7cbbbe7e-d66f-4e18-966b-2b994eae3155"},{"contentid":"a4187111-00c7-4db4-b4da-eeac033bf184","fid":"136","factorname":"二甲苯","SamplingStyle":"1","SamplingStyleName":"客户送样","AnalysisMethod":"27","methodname":"《二甲苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"6","TestingRequire":"6","frequency":"6","DaysNumber":"6","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"7cbbbe7e-d66f-4e18-966b-2b994eae3155"},{"contentid":"afa9f4b7-a049-4451-baab-c1a84f6aa264","fid":"134","factorname":"甲苯","SamplingStyle":"1","SamplingStyleName":"客户送样","AnalysisMethod":"26","methodname":"《甲苯检测分析方法》","tsid":"5","tsnames":"《空气和废气监测分析方法》（第二版）","explainInfo":"7","TestingRequire":"7","frequency":"7","DaysNumber":"7","types":"1","typesname":"自测","ptsid":"87","ptsnames":"《2009收费标准》","dscid":"7cbbbe7e-d66f-4e18-966b-2b994eae3155"}]
         */

        private String categoryid;
        private String categorycode;
        private String categoryname;
        private String criterionid;
        private String criterionname;
        private List<FactorsBean> factors;

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

        public List<FactorsBean> getFactors() {
            return factors;
        }

        public void setFactors(List<FactorsBean> factors) {
            this.factors = factors;
        }

        public static class FactorsBean {
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
    }
}

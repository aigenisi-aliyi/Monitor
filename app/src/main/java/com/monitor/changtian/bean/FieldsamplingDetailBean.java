package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/7/13.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class FieldsamplingDetailBean implements Serializable {

    /**
     * sampdetailid : 0aeb2db0-4e43-4381-82c9-c54c1975a45a
     * sampid : a8635ea1-2ba8-4998-900d-36dda1491dc9
     * onlynumber : 22222233645645746747641
     * sampingstatus : 正常
     * sampingPacking : 260
     * sampingPackingname : 玻璃瓶
     * starttime : 2018-10-01 00:27:00
     * endtime : 2018-10-01 02:00:00
     * samplingamount : 100
     * samplingunit : 263
     * samplingunitname : ㎏
     * sampinguser : 1
     * remark :
     * samplestyle : 277
     * samplestylename : 气袋采集
     * sampletype : 0
     * sampletypename : 非质控样
     * weathercondition : 多云
     * temperature : 29
     * analysisitems : 8
     * sampingusers : [{"id":1,"loginName":"admin","userName":"赵 涛"}]
     * noscenefactors : [{"contentid":"01132b8d-b4e0-446d-b3a3-f3f784ea9c89","fid":"8","factorname":"氨氮","AnalysisMethod":"13","methodname":"《水和废水监测分析方法》方法1"}]
     * scenefactors : [{"sfmid":"f9655066-53b9-421f-9f8a-706c88ea4318","fid":"1","factorname":"水温","remark":"","ftmid":"7","methodname":"检测方法名称1","measuredvalue":"66","unit":"°C"}]
     * reagent : [{"id":"31","reagentid":"6","names":"烧杯","remark":"","dosage":"99","unit":"个"}]
     * equips : [{"fseid":"85c2b9e8-78c6-4131-956a-539965d5b376","equipid":"27","EquipmentNumber":"PH1407002222","names":"可见分光光度计","barCode":"","FactoryNumber":"","ModelSpecification":"UV-5500PC","calibrations":[{"id":"8","equipid":"27","EquipmentNumber":"PH1407002222","names":"可见分光光度计","Premeasurement":"66","Postmeasurement":"999","operationtime":"2018-07-13 13:38:11","operationuser":"12","operationusername":"4567","calibrationquipid":"36","calEquipmentNumber":"PH140","calnames":"可见分光光度计"}]}]
     * fujians : [{"id":"73","kid":"0aeb2db0-4e43-4381-82c9-c54c1975a45a","names":"","types":"","typesname":"","fujian":"/upload/files/6830fc1d-69f2-4bba-b107-c4362ca905da843.jpg","category":"4"}]
     */


    private String categoryid;
    private String sampdetailid;
    private String sampid;
    private String onlynumber;
    private String sampingstatus;
    private String sampingPacking;
    private String sampingPackingname;
    private String starttime;
    private String endtime;
    private String samplingamount;
    private String samplingunit;
    private String samplingunitname;
    private String sampinguser;
    private String remark;
    private String samplestyle;
    private String samplestylename;
    private String sampletype;
    private String sampletypename;
    private String weathercondition;
    private String temperature;
    private String analysisitems;
    private List<SampingusersBean> sampingusers;
    private List<NoscenefactorsBean> noscenefactors;
    private List<ScenefactorsBean> scenefactors;
    private List<ReagentBean> reagent;
    private List<EquipsBean> equips;
    private List<FujiansBean> fujians;

    public String getCategoryid() {
        return categoryid;
    }
    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }
    /**
     * pointsid : 1dbf508c-cc9d-41ab-86e2-e32d9cd25a2a
     * sampingname : 测试
     * pretestvalue :
     * posttestvalue :
     * scenefactors : []
     * reagent : []
     * equips : []
     * fujians : []
     */




    private String pointsid;
    private String sampingname;
    private String pretestvalue;
    private String posttestvalue;
    /**
     * statevalue : 1
     * statevaluename : 待校对
     * proofreader : 71
     * proofreadername : 孙亚龙
     * prooftime :
     * auditor :
     * auditorname :
     * auditopinion :
     * audittime :
     * ispass : 0
     * ispassname : 否
     * soilsampletool : 铁锹
     * soilvegetation : 无
     * soilcolor : 红色
     * soiltexture : 502
     * soiltexturename : 砂土
     * soilhumidity : 497
     * soilhumidityname : 干
     * scenefactors : []
     * reagent : []
     * equips : []
     * fujians : []
     */

    private String statevalue;
    private String statevaluename;
    private String proofreader;
    private String proofreadername;
    private String prooftime;
    private String auditor;
    private String auditorname;
    private String auditopinion;
    private String audittime;
    private String ispass;
    private String ispassname;
    private String soilsampletool;
    private String soilvegetation;
    private String soilcolor;
    private String soiltexture;
    private String soiltexturename;
    private String soilhumidity;
    private String soilhumidityname;


    public String getSampdetailid() {
        return sampdetailid;
    }

    public void setSampdetailid(String sampdetailid) {
        this.sampdetailid = sampdetailid;
    }

    public String getSampid() {
        return sampid;
    }

    public void setSampid(String sampid) {
        this.sampid = sampid;
    }

    public String getOnlynumber() {
        return onlynumber;
    }

    public void setOnlynumber(String onlynumber) {
        this.onlynumber = onlynumber;
    }

    public String getSampingstatus() {
        return sampingstatus;
    }

    public void setSampingstatus(String sampingstatus) {
        this.sampingstatus = sampingstatus;
    }

    public String getSampingPacking() {
        return sampingPacking;
    }

    public void setSampingPacking(String sampingPacking) {
        this.sampingPacking = sampingPacking;
    }

    public String getSampingPackingname() {
        return sampingPackingname;
    }

    public void setSampingPackingname(String sampingPackingname) {
        this.sampingPackingname = sampingPackingname;
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

    public String getSamplingamount() {
        return samplingamount;
    }

    public void setSamplingamount(String samplingamount) {
        this.samplingamount = samplingamount;
    }

    public String getSamplingunit() {
        return samplingunit;
    }

    public void setSamplingunit(String samplingunit) {
        this.samplingunit = samplingunit;
    }

    public String getSamplingunitname() {
        return samplingunitname;
    }

    public void setSamplingunitname(String samplingunitname) {
        this.samplingunitname = samplingunitname;
    }

    public String getSampinguser() {
        return sampinguser;
    }

    public void setSampinguser(String sampinguser) {
        this.sampinguser = sampinguser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSamplestyle() {
        return samplestyle;
    }

    public void setSamplestyle(String samplestyle) {
        this.samplestyle = samplestyle;
    }

    public String getSamplestylename() {
        return samplestylename;
    }

    public void setSamplestylename(String samplestylename) {
        this.samplestylename = samplestylename;
    }

    public String getSampletype() {
        return sampletype;
    }

    public void setSampletype(String sampletype) {
        this.sampletype = sampletype;
    }

    public String getSampletypename() {
        return sampletypename;
    }

    public void setSampletypename(String sampletypename) {
        this.sampletypename = sampletypename;
    }

    public String getWeathercondition() {
        return weathercondition;
    }

    public void setWeathercondition(String weathercondition) {
        this.weathercondition = weathercondition;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getAnalysisitems() {
        return analysisitems;
    }

    public void setAnalysisitems(String analysisitems) {
        this.analysisitems = analysisitems;
    }

    public List<SampingusersBean> getSampingusers() {
        return sampingusers;
    }

    public void setSampingusers(List<SampingusersBean> sampingusers) {
        this.sampingusers = sampingusers;
    }

    public List<NoscenefactorsBean> getNoscenefactors() {
        return noscenefactors;
    }

    public void setNoscenefactors(List<NoscenefactorsBean> noscenefactors) {
        this.noscenefactors = noscenefactors;
    }

    public List<ScenefactorsBean> getScenefactors() {
        return scenefactors;
    }

    public void setScenefactors(List<ScenefactorsBean> scenefactors) {
        this.scenefactors = scenefactors;
    }

    public List<ReagentBean> getReagent() {
        return reagent;
    }

    public void setReagent(List<ReagentBean> reagent) {
        this.reagent = reagent;
    }

    public List<EquipsBean> getEquips() {
        return equips;
    }

    public void setEquips(List<EquipsBean> equips) {
        this.equips = equips;
    }

    public List<FujiansBean> getFujians() {
        return fujians;
    }

    public void setFujians(List<FujiansBean> fujians) {
        this.fujians = fujians;
    }

    public String getPointsid() {
        return pointsid;
    }

    public void setPointsid(String pointsid) {
        this.pointsid = pointsid;
    }

    public String getSampingname() {
        return sampingname;
    }

    public void setSampingname(String sampingname) {
        this.sampingname = sampingname;
    }

    public String getPretestvalue() {
        return pretestvalue;
    }

    public void setPretestvalue(String pretestvalue) {
        this.pretestvalue = pretestvalue;
    }

    public String getPosttestvalue() {
        return posttestvalue;
    }

    public void setPosttestvalue(String posttestvalue) {
        this.posttestvalue = posttestvalue;
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

    public String getProofreader() {
        return proofreader;
    }

    public void setProofreader(String proofreader) {
        this.proofreader = proofreader;
    }

    public String getProofreadername() {
        return proofreadername;
    }

    public void setProofreadername(String proofreadername) {
        this.proofreadername = proofreadername;
    }

    public String getProoftime() {
        return prooftime;
    }

    public void setProoftime(String prooftime) {
        this.prooftime = prooftime;
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

    public String getAuditopinion() {
        return auditopinion;
    }

    public void setAuditopinion(String auditopinion) {
        this.auditopinion = auditopinion;
    }

    public String getAudittime() {
        return audittime;
    }

    public void setAudittime(String audittime) {
        this.audittime = audittime;
    }

    public String getIspass() {
        return ispass;
    }

    public void setIspass(String ispass) {
        this.ispass = ispass;
    }

    public String getIspassname() {
        return ispassname;
    }

    public void setIspassname(String ispassname) {
        this.ispassname = ispassname;
    }

    public String getSoilsampletool() {
        return soilsampletool;
    }

    public void setSoilsampletool(String soilsampletool) {
        this.soilsampletool = soilsampletool;
    }

    public String getSoilvegetation() {
        return soilvegetation;
    }

    public void setSoilvegetation(String soilvegetation) {
        this.soilvegetation = soilvegetation;
    }

    public String getSoilcolor() {
        return soilcolor;
    }

    public void setSoilcolor(String soilcolor) {
        this.soilcolor = soilcolor;
    }

    public String getSoiltexture() {
        return soiltexture;
    }

    public void setSoiltexture(String soiltexture) {
        this.soiltexture = soiltexture;
    }

    public String getSoiltexturename() {
        return soiltexturename;
    }

    public void setSoiltexturename(String soiltexturename) {
        this.soiltexturename = soiltexturename;
    }

    public String getSoilhumidity() {
        return soilhumidity;
    }

    public void setSoilhumidity(String soilhumidity) {
        this.soilhumidity = soilhumidity;
    }

    public String getSoilhumidityname() {
        return soilhumidityname;
    }

    public void setSoilhumidityname(String soilhumidityname) {
        this.soilhumidityname = soilhumidityname;
    }

    public static class SampingusersBean implements Serializable {
        /**
         * id : 1
         * loginName : admin
         * userName : 赵 涛
         */

        private int id;
        private String loginName;
        private String userName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static class NoscenefactorsBean implements Serializable {
        /**
         * contentid : 01132b8d-b4e0-446d-b3a3-f3f784ea9c89
         * fid : 8
         * factorname : 氨氮
         * AnalysisMethod : 13
         * methodname : 《水和废水监测分析方法》方法1
         */

        private String contentid;
        private String fid;
        private String factorname;
        private String AnalysisMethod;
        private String methodname;

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
    }

    public static class ScenefactorsBean implements Serializable {
        /**
         * sfmid : f9655066-53b9-421f-9f8a-706c88ea4318
         * fid : 1
         * factorname : 水温
         * remark :
         * ftmid : 7
         * methodname : 检测方法名称1
         * measuredvalue : 66
         * unit : °C
         */

        private String sfmid;
        private String fid;
        private String factorname;
        private String remark;
        private String ftmid;
        private String methodname;
        private String measuredvalue;
        private String unit;

        public String getSfmid() {
            return sfmid;
        }

        public void setSfmid(String sfmid) {
            this.sfmid = sfmid;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getFtmid() {
            return ftmid;
        }

        public void setFtmid(String ftmid) {
            this.ftmid = ftmid;
        }

        public String getMethodname() {
            return methodname;
        }

        public void setMethodname(String methodname) {
            this.methodname = methodname;
        }

        public String getMeasuredvalue() {
            return measuredvalue;
        }

        public void setMeasuredvalue(String measuredvalue) {
            this.measuredvalue = measuredvalue;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    public static class ReagentBean implements Serializable {
        /**
         * id : 31
         * reagentid : 6
         * names : 烧杯
         * remark :
         * dosage : 99
         * unit : 个
         */

        private String id;
        private String reagentid;
        private String names;
        private String remark;
        private String dosage;
        private String unit;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReagentid() {
            return reagentid;
        }

        public void setReagentid(String reagentid) {
            this.reagentid = reagentid;
        }

        public String getNames() {
            return names;
        }

        public void setNames(String names) {
            this.names = names;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getDosage() {
            return dosage;
        }

        public void setDosage(String dosage) {
            this.dosage = dosage;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    public static class EquipsBean implements Serializable {
        /**
         * fseid : 85c2b9e8-78c6-4131-956a-539965d5b376
         * equipid : 27
         * EquipmentNumber : PH1407002222
         * names : 可见分光光度计
         * barCode :
         * FactoryNumber :
         * ModelSpecification : UV-5500PC
         * calibrations : [{"id":"8","equipid":"27","EquipmentNumber":"PH1407002222","names":"可见分光光度计","Premeasurement":"66","Postmeasurement":"999","operationtime":"2018-07-13 13:38:11","operationuser":"12","operationusername":"4567","calibrationquipid":"36","calEquipmentNumber":"PH140","calnames":"可见分光光度计"}]
         */

        private String fseid;
        private String equipid;
        private String EquipmentNumber;
        private String names;
        private String barCode;
        private String FactoryNumber;
        private String ModelSpecification;
        private List<CalibrationsBean> calibrations;

        public String getFseid() {
            return fseid;
        }

        public void setFseid(String fseid) {
            this.fseid = fseid;
        }

        public String getEquipid() {
            return equipid;
        }

        public void setEquipid(String equipid) {
            this.equipid = equipid;
        }

        public String getEquipmentNumber() {
            return EquipmentNumber;
        }

        public void setEquipmentNumber(String EquipmentNumber) {
            this.EquipmentNumber = EquipmentNumber;
        }

        public String getNames() {
            return names;
        }

        public void setNames(String names) {
            this.names = names;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getFactoryNumber() {
            return FactoryNumber;
        }

        public void setFactoryNumber(String FactoryNumber) {
            this.FactoryNumber = FactoryNumber;
        }

        public String getModelSpecification() {
            return ModelSpecification;
        }

        public void setModelSpecification(String ModelSpecification) {
            this.ModelSpecification = ModelSpecification;
        }

        public List<CalibrationsBean> getCalibrations() {
            return calibrations;
        }

        public void setCalibrations(List<CalibrationsBean> calibrations) {
            this.calibrations = calibrations;
        }

        public static class CalibrationsBean {
            /**
             * id : 8
             * equipid : 27
             * EquipmentNumber : PH1407002222
             * names : 可见分光光度计
             * Premeasurement : 66
             * Postmeasurement : 999
             * operationtime : 2018-07-13 13:38:11
             * operationuser : 12
             * operationusername : 4567
             * calibrationquipid : 36
             * calEquipmentNumber : PH140
             * calnames : 可见分光光度计
             */

            private String id;
            private String equipid;
            private String EquipmentNumber;
            private String names;
            private String Premeasurement;
            private String Postmeasurement;
            private String operationtime;
            private String operationuser;
            private String operationusername;
            private String calibrationquipid;
            private String calEquipmentNumber;
            private String calnames;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getEquipid() {
                return equipid;
            }

            public void setEquipid(String equipid) {
                this.equipid = equipid;
            }

            public String getEquipmentNumber() {
                return EquipmentNumber;
            }

            public void setEquipmentNumber(String EquipmentNumber) {
                this.EquipmentNumber = EquipmentNumber;
            }

            public String getNames() {
                return names;
            }

            public void setNames(String names) {
                this.names = names;
            }

            public String getPremeasurement() {
                return Premeasurement;
            }

            public void setPremeasurement(String Premeasurement) {
                this.Premeasurement = Premeasurement;
            }

            public String getPostmeasurement() {
                return Postmeasurement;
            }

            public void setPostmeasurement(String Postmeasurement) {
                this.Postmeasurement = Postmeasurement;
            }

            public String getOperationtime() {
                return operationtime;
            }

            public void setOperationtime(String operationtime) {
                this.operationtime = operationtime;
            }

            public String getOperationuser() {
                return operationuser;
            }

            public void setOperationuser(String operationuser) {
                this.operationuser = operationuser;
            }

            public String getOperationusername() {
                return operationusername;
            }

            public void setOperationusername(String operationusername) {
                this.operationusername = operationusername;
            }

            public String getCalibrationquipid() {
                return calibrationquipid;
            }

            public void setCalibrationquipid(String calibrationquipid) {
                this.calibrationquipid = calibrationquipid;
            }

            public String getCalEquipmentNumber() {
                return calEquipmentNumber;
            }

            public void setCalEquipmentNumber(String calEquipmentNumber) {
                this.calEquipmentNumber = calEquipmentNumber;
            }

            public String getCalnames() {
                return calnames;
            }

            public void setCalnames(String calnames) {
                this.calnames = calnames;
            }
        }
    }

    public static class FujiansBean implements Serializable {
        /**
         * id : 73
         * kid : 0aeb2db0-4e43-4381-82c9-c54c1975a45a
         * names :
         * types :
         * typesname :
         * fujian : /upload/files/6830fc1d-69f2-4bba-b107-c4362ca905da843.jpg
         * category : 4
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

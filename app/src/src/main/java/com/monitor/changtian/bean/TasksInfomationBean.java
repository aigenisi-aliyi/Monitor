package com.monitor.changtian.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/7/11.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TasksInfomationBean implements Serializable {


    /**
     * id : d3667d24-114a-4a5f-a681-92436fa4d541
     * schemeid : 42efbbf5-a8d3-44aa-8623-3e6c29d289b9
     * address : 杭州市西湖区华星路
     * coordinate : 108.73,34.25
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
     * points : [{"pointsid":"6a9d32d6-064b-4ed1-ab68-a71021c56504","categoryid":"20","categoryname":"地表水","address":"杭州市西湖区华星路","longitude":"108.73","latitude":"34.25","altitude":"0","remark":"","heightvalue":"","ispoisonname":"","isradiationname":"","charges":[{"cqcid":"3","qualitycontrol":"230","qualitycontrolname":"空白样","pointsid":"6a9d32d6-064b-4ed1-ab68-a71021c56504","pointsaddress":"杭州市西湖区华星路","numbers":"1"},{"cqcid":"4","qualitycontrol":"231","qualitycontrolname":"平行样","pointsid":"6a9d32d6-064b-4ed1-ab68-a71021c56504","pointsaddress":"杭州市西湖区华星路","numbers":"2"}],"factors":[{"contentid":"924a867a-dbae-4910-a968-61c39e240f98","fid":"1","factorname":"水温","AnalysisMethod":"7","methodname":"检测方法名称1"},{"contentid":"0582e17c-f3e3-4f16-9d27-fbbbde28cbc4","fid":"3","factorname":"二氧化氮","AnalysisMethod":"8","methodname":"检测方法名称2"}],"scenefactors":[]}]
     * equips : [{"id":"27","EquipmentNumber":"PH1407002222","names":"可见分光光度计","barCode":"","FactoryNumber":"","ModelSpecification":"UV-5500PC","Accuracy":"±0.5","buyDate":"2014-03-27","place":"1","roomname":"样品室","CollarState":"0","productionUnit":"上海元析仪器有限公司","RegulationNumber":"YQ00301","ExpireDate":"2020-04-10","remark":"","equiptype":"170","equiptypename":"计量仪器","IsEnabled":"1","IsEnabledName":"是","categoryid":"240","categoryname":"离子计","detectionlimit":""},{"id":"36","EquipmentNumber":"PH140","names":"可见分光光度计","barCode":"","FactoryNumber":"","ModelSpecification":"UV-5500PC","Accuracy":"±0.5","buyDate":"2014-03-27","place":"1","roomname":"样品室","CollarState":"0","productionUnit":"上海元析仪器有限公司","RegulationNumber":"YQ00301","ExpireDate":"2019-04-10","remark":"","equiptype":"170","equiptypename":"计量仪器","IsEnabled":"1","IsEnabledName":"是","categoryid":"244","categoryname":"冷原子吸收测汞仪","detectionlimit":""}]
     * consumables : [{"id":"4","ConsumablesNumber":"中和剂","names":"中和剂","barCode":"","FactoryNumber":"","ModelSpecification":"","buyDate":"","place":"11","roomname":"耗材室","remark":"","productionUnit":"","ExpireDate":"2080-06-06 00:00:00","Consumablestype":"223","Consumablestypename":"化验","IsEnabled":"1","IsEnabledName":"是","IsConfecting":"1","IsConfectingName":"是","categoryid":"254","categoryname":"量杯","unit":"毫升","IsImport":"否"},{"id":"5","ConsumablesNumber":"混合剂","names":"混合剂","barCode":"","FactoryNumber":"","ModelSpecification":"","buyDate":"","place":"11","roomname":"耗材室","remark":"","productionUnit":"","ExpireDate":"2080-06-06 00:00:00","Consumablestype":"224","Consumablestypename":"分析","IsEnabled":"1","IsEnabledName":"是","IsConfecting":"1","IsConfectingName":"是","categoryid":"253","categoryname":"PH试纸","unit":"毫升","IsImport":"否"},{"id":"6","ConsumablesNumber":"烧杯","names":"烧杯","barCode":"","FactoryNumber":"","ModelSpecification":"","buyDate":"","place":"11","roomname":"耗材室","remark":"","productionUnit":"","ExpireDate":"2080-06-06 00:00:00","Consumablestype":"223","Consumablestypename":"化验","IsEnabled":"1","IsEnabledName":"是","IsConfecting":"1","IsConfectingName":"是","categoryid":"256","categoryname":"真空袋","unit":"个","IsImport":"否"}]
     * samplinguser : [{"userid":"1","username":"赵 涛"}]
     */

    private String id;
    private String schemeid;
    private String address;
    private String coordinate;
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
    private List<ConsumablesBean> consumables;
    private List<SamplinguserBean> samplinguser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid;
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

    public List<EquipsBean> getEquips() {
        return equips;
    }

    public void setEquips(List<EquipsBean> equips) {
        this.equips = equips;
    }

    public List<ConsumablesBean> getConsumables() {
        return consumables;
    }

    public void setConsumables(List<ConsumablesBean> consumables) {
        this.consumables = consumables;
    }

    public List<SamplinguserBean> getSamplinguser() {
        return samplinguser;
    }

    public void setSamplinguser(List<SamplinguserBean> samplinguser) {
        this.samplinguser = samplinguser;
    }

    public static class PointsBean implements Serializable, IPickerViewData {
        /**
         * pointsid : 6a9d32d6-064b-4ed1-ab68-a71021c56504
         * categoryid : 20
         * categoryname : 地表水
         * address : 杭州市西湖区华星路
         * longitude : 108.73
         * latitude : 34.25
         * altitude : 0
         * remark :
         * heightvalue :
         * ispoisonname :
         * isradiationname :
         * charges : [{"cqcid":"3","qualitycontrol":"230","qualitycontrolname":"空白样","pointsid":"6a9d32d6-064b-4ed1-ab68-a71021c56504","pointsaddress":"杭州市西湖区华星路","numbers":"1"},{"cqcid":"4","qualitycontrol":"231","qualitycontrolname":"平行样","pointsid":"6a9d32d6-064b-4ed1-ab68-a71021c56504","pointsaddress":"杭州市西湖区华星路","numbers":"2"}]
         * factors : [{"contentid":"924a867a-dbae-4910-a968-61c39e240f98","fid":"1","factorname":"水温","AnalysisMethod":"7","methodname":"检测方法名称1"},{"contentid":"0582e17c-f3e3-4f16-9d27-fbbbde28cbc4","fid":"3","factorname":"二氧化氮","AnalysisMethod":"8","methodname":"检测方法名称2"}]
         * scenefactors : []
         */

        private float distance;
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
        private List<ChargesBean> charges;
        private List<FactorsBean> factors;
        private List<ScenefactorsBean> scenefactors;

        public float getDistance() {
            return distance;
        }

        public void setDistance(float distance) {
            this.distance = distance;
        }

        public List<ScenefactorsBean> getScenefactors() {
            return scenefactors;
        }

        public void setScenefactors(List<ScenefactorsBean> scenefactors) {
            this.scenefactors = scenefactors;
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

        public List<ChargesBean> getCharges() {
            return charges;
        }

        public void setCharges(List<ChargesBean> charges) {
            this.charges = charges;
        }

        public List<FactorsBean> getFactors() {
            return factors;
        }

        public void setFactors(List<FactorsBean> factors) {
            this.factors = factors;
        }

        @Override
        public String getPickerViewText() {
            return address;
        }


        public static class ScenefactorsBean implements Serializable {
            /**
             * contentid : 00349a9d-3f3e-43c2-9ea1-20b1a688741e
             * fid : 1
             * factorname : 水温
             * AnalysisMethod : 7
             * methodname : 检测方法名称1
             */

            private String contentid;
            private String fid;
            private String factorname;
            private String AnalysisMethod;
            private String methodname;
            private String value;


            private boolean isChoice_save;
            private boolean isChoice;

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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
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


        public static class ChargesBean implements Serializable, IPickerViewData {
            /**
             * cqcid : 3
             * qualitycontrol : 230
             * qualitycontrolname : 空白样
             * pointsid : 6a9d32d6-064b-4ed1-ab68-a71021c56504
             * pointsaddress : 杭州市西湖区华星路
             * numbers : 1
             */

            private String cqcid;
            private String qualitycontrol;
            private String qualitycontrolname;
            private String pointsid;
            private String pointsaddress;
            private String numbers;

            public String getCqcid() {
                return cqcid;
            }

            public void setCqcid(String cqcid) {
                this.cqcid = cqcid;
            }

            public String getQualitycontrol() {
                return qualitycontrol;
            }

            public void setQualitycontrol(String qualitycontrol) {
                this.qualitycontrol = qualitycontrol;
            }

            public String getQualitycontrolname() {
                return qualitycontrolname;
            }

            public void setQualitycontrolname(String qualitycontrolname) {
                this.qualitycontrolname = qualitycontrolname;
            }

            public String getPointsid() {
                return pointsid;
            }

            public void setPointsid(String pointsid) {
                this.pointsid = pointsid;
            }

            public String getPointsaddress() {
                return pointsaddress;
            }

            public void setPointsaddress(String pointsaddress) {
                this.pointsaddress = pointsaddress;
            }

            public String getNumbers() {
                return numbers;
            }

            public void setNumbers(String numbers) {
                this.numbers = numbers;
            }

            @Override
            public String getPickerViewText() {
                return qualitycontrolname;
            }
        }

        public static class FactorsBean implements Serializable {
            /**
             * contentid : 924a867a-dbae-4910-a968-61c39e240f98
             * fid : 1
             * factorname : 水温
             * AnalysisMethod : 7
             * methodname : 检测方法名称1
             */


            private boolean isChoice_save;
            private boolean isChoice;

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
    }

    public static class EquipsBean implements Serializable {


        /**
         * id : 27
         * EquipmentNumber : PH1407002222
         * names : 可见分光光度计
         * barCode :
         * FactoryNumber :
         * ModelSpecification : UV-5500PC
         * Accuracy : ±0.5
         * buyDate : 2014-03-27
         * place : 1
         * roomname : 样品室
         * CollarState : 0
         * productionUnit : 上海元析仪器有限公司
         * RegulationNumber : YQ00301
         * ExpireDate : 2020-04-10
         * remark :
         * equiptype : 170
         * equiptypename : 计量仪器
         * IsEnabled : 1
         * IsEnabledName : 是
         * categoryid : 240
         * categoryname : 离子计
         * detectionlimit :
         */
        private boolean isChoice_save;
        private boolean isChoice;
        private String id;
        private String EquipmentNumber;
        private String names;
        private String barCode;
        private String FactoryNumber;
        private String ModelSpecification;
        private String Accuracy;
        private String buyDate;
        private String place;
        private String roomname;
        private String CollarState;
        private String productionUnit;
        private String RegulationNumber;
        private String ExpireDate;
        private String remark;
        private String equiptype;
        private String equiptypename;
        private String IsEnabled;
        private String IsEnabledName;
        private String categoryid;
        private String categoryname;
        private String detectionlimit;


        private String Beforemeasurement; //测量前
        private String Aftermeasurement;  //测量后

        public String getBeforemeasurement() {
            return Beforemeasurement;
        }

        public void setBeforemeasurement(String beforemeasurement) {
            Beforemeasurement = beforemeasurement;
        }

        public String getAftermeasurement() {
            return Aftermeasurement;
        }

        public void setAftermeasurement(String aftermeasurement) {
            Aftermeasurement = aftermeasurement;
        }

        private List<CalibrationsBean> calibrations;


        public List<CalibrationsBean> getCalibrations() {
            return calibrations;
        }

        public void setCalibrations(List<CalibrationsBean> calibrations) {
            this.calibrations = calibrations;
        }

        public static class CalibrationsBean implements Serializable {

            private String Innames;
            private String names;
            private String devname;

            public String getInnames() {
                return Innames;
            }

            public void setInnames(String innames) {
                Innames = innames;
            }

            public String getDevname() {
                return devname;
            }

            public void setDevname(String devname) {
                this.devname = devname;
            }

            public String getNames() {
                return names;
            }

            public void setNames(String names) {
                this.names = names;
            }

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getAccuracy() {
            return Accuracy;
        }

        public void setAccuracy(String Accuracy) {
            this.Accuracy = Accuracy;
        }

        public String getBuyDate() {
            return buyDate;
        }

        public void setBuyDate(String buyDate) {
            this.buyDate = buyDate;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getRoomname() {
            return roomname;
        }

        public void setRoomname(String roomname) {
            this.roomname = roomname;
        }

        public String getCollarState() {
            return CollarState;
        }

        public void setCollarState(String CollarState) {
            this.CollarState = CollarState;
        }

        public String getProductionUnit() {
            return productionUnit;
        }

        public void setProductionUnit(String productionUnit) {
            this.productionUnit = productionUnit;
        }

        public String getRegulationNumber() {
            return RegulationNumber;
        }

        public void setRegulationNumber(String RegulationNumber) {
            this.RegulationNumber = RegulationNumber;
        }

        public String getExpireDate() {
            return ExpireDate;
        }

        public void setExpireDate(String ExpireDate) {
            this.ExpireDate = ExpireDate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getEquiptype() {
            return equiptype;
        }

        public void setEquiptype(String equiptype) {
            this.equiptype = equiptype;
        }

        public String getEquiptypename() {
            return equiptypename;
        }

        public void setEquiptypename(String equiptypename) {
            this.equiptypename = equiptypename;
        }

        public String getIsEnabled() {
            return IsEnabled;
        }

        public void setIsEnabled(String IsEnabled) {
            this.IsEnabled = IsEnabled;
        }

        public String getIsEnabledName() {
            return IsEnabledName;
        }

        public void setIsEnabledName(String IsEnabledName) {
            this.IsEnabledName = IsEnabledName;
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

        public String getDetectionlimit() {
            return detectionlimit;
        }

        public void setDetectionlimit(String detectionlimit) {
            this.detectionlimit = detectionlimit;
        }
    }

    public static class ConsumablesBean implements Serializable {
        /**
         * id : 4
         * ConsumablesNumber : 中和剂
         * names : 中和剂
         * barCode :
         * FactoryNumber :
         * ModelSpecification :
         * buyDate :
         * place : 11
         * roomname : 耗材室
         * remark :
         * productionUnit :
         * ExpireDate : 2080-06-06 00:00:00
         * Consumablestype : 223
         * Consumablestypename : 化验
         * IsEnabled : 1
         * IsEnabledName : 是
         * IsConfecting : 1
         * IsConfectingName : 是
         * categoryid : 254
         * categoryname : 量杯
         * unit : 毫升
         * IsImport : 否
         */

        private boolean isChoice_save;
        private boolean isChoice;

        private String Value;
        private String id;
        private String ConsumablesNumber;
        private String names;
        private String barCode;
        private String FactoryNumber;
        private String ModelSpecification;
        private String buyDate;
        private String place;
        private String roomname;
        private String remark;
        private String productionUnit;
        private String ExpireDate;
        private String Consumablestype;
        private String Consumablestypename;
        private String IsEnabled;
        private String IsEnabledName;
        private String IsConfecting;
        private String IsConfectingName;
        private String categoryid;
        private String categoryname;
        private String unit;
        private String IsImport;

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

        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getConsumablesNumber() {
            return ConsumablesNumber;
        }

        public void setConsumablesNumber(String ConsumablesNumber) {
            this.ConsumablesNumber = ConsumablesNumber;
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

        public String getBuyDate() {
            return buyDate;
        }

        public void setBuyDate(String buyDate) {
            this.buyDate = buyDate;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getRoomname() {
            return roomname;
        }

        public void setRoomname(String roomname) {
            this.roomname = roomname;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getProductionUnit() {
            return productionUnit;
        }

        public void setProductionUnit(String productionUnit) {
            this.productionUnit = productionUnit;
        }

        public String getExpireDate() {
            return ExpireDate;
        }

        public void setExpireDate(String ExpireDate) {
            this.ExpireDate = ExpireDate;
        }

        public String getConsumablestype() {
            return Consumablestype;
        }

        public void setConsumablestype(String Consumablestype) {
            this.Consumablestype = Consumablestype;
        }

        public String getConsumablestypename() {
            return Consumablestypename;
        }

        public void setConsumablestypename(String Consumablestypename) {
            this.Consumablestypename = Consumablestypename;
        }

        public String getIsEnabled() {
            return IsEnabled;
        }

        public void setIsEnabled(String IsEnabled) {
            this.IsEnabled = IsEnabled;
        }

        public String getIsEnabledName() {
            return IsEnabledName;
        }

        public void setIsEnabledName(String IsEnabledName) {
            this.IsEnabledName = IsEnabledName;
        }

        public String getIsConfecting() {
            return IsConfecting;
        }

        public void setIsConfecting(String IsConfecting) {
            this.IsConfecting = IsConfecting;
        }

        public String getIsConfectingName() {
            return IsConfectingName;
        }

        public void setIsConfectingName(String IsConfectingName) {
            this.IsConfectingName = IsConfectingName;
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

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getIsImport() {
            return IsImport;
        }

        public void setIsImport(String IsImport) {
            this.IsImport = IsImport;
        }
    }

    public static class SamplinguserBean implements Serializable, IPickerViewData {
        /**
         * userid : 1
         * username : 赵 涛
         */

        private boolean isChoice_save;
        private boolean isChoice;

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

        private String userid;
        private String loginname;
        private String username;

        public String getLoginname() {
            return loginname;
        }

        public void setLoginname(String loginname) {
            this.loginname = loginname;
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

        @Override
        public String getPickerViewText() {
            return username;
        }
    }
}

package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/12/19.
 */

public class QueryTestRecordBean implements Serializable {

    /**
     * bh : 18-12-18-11-447
     * factors :
     * tdate : 2018-12-18
     * pdf01 :
     * pdf02 : /upload/test/70a2f33f-e35e-4431-b3d5-b5c617a14209_1812180084.pdf
     * CheckStatus : 1
     * ApproveStatus : 1
     * process : 1234142
     * tdata : {"common_para":{"dev":"气相色谱仪","devnum":"YQ06101","column":"11","detector":"12","thickness":"13","qhsTemp":"14","zxTemp":"15","jcqTemp":"16","press_h2":"17","press_air":"22","roomTemp":"18","roomHum":"19","press_load":"20","jyl":"21","Amethod":"溶剂萃取-填充柱气相色谱法"},"std_curve":[{"fname":"甲苯","counts":"7","m":"1.12386","b":"0.626720","r":"0.99983","quxian":[],"cdata":[{"dnum":"1","values":"0.500000","Areas":"0.859727"},{"dnum":"2","values":"1.00000","Areas":"1.45375"},{"dnum":"3","values":"5.00000","Areas":"5.96091"},{"dnum":"4","values":"10.00000","Areas":"12.78160"},{"dnum":"5","values":"20.00000","Areas":"24.39545"},{"dnum":"6","values":"50.00000","Areas":"56.14156"},{"dnum":"7","values":"100.00000","Areas":"113.02094"}]}],"test_result":[{"smid":"1812180084","counts":"1","volume":"1","factors":[{"fname":"甲苯","rdata":{"area":"3.70722","thickness":"2.74100 ng/ul ","m":"1.12386","b":"0.626720","r":"0.99983"}}]}]}
     */

    private String bh;
    private String factors;
    private String tdate;
    private String pdf01;
    private String pdf02;
    private String CheckStatus;
    private String ApproveStatus;
    private String process;
    private TdataBean tdata;

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public String getPdf01() {
        return pdf01;
    }

    public void setPdf01(String pdf01) {
        this.pdf01 = pdf01;
    }

    public String getPdf02() {
        return pdf02;
    }

    public void setPdf02(String pdf02) {
        this.pdf02 = pdf02;
    }

    public String getCheckStatus() {
        return CheckStatus;
    }

    public void setCheckStatus(String CheckStatus) {
        this.CheckStatus = CheckStatus;
    }

    public String getApproveStatus() {
        return ApproveStatus;
    }

    public void setApproveStatus(String ApproveStatus) {
        this.ApproveStatus = ApproveStatus;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public TdataBean getTdata() {
        return tdata;
    }

    public void setTdata(TdataBean tdata) {
        this.tdata = tdata;
    }

    public static class TdataBean  implements Serializable{
        /**
         * common_para : {"dev":"气相色谱仪","devnum":"YQ06101","column":"11","detector":"12","thickness":"13","qhsTemp":"14","zxTemp":"15","jcqTemp":"16","press_h2":"17","press_air":"22","roomTemp":"18","roomHum":"19","press_load":"20","jyl":"21","Amethod":"溶剂萃取-填充柱气相色谱法"}
         * std_curve : [{"fname":"甲苯","counts":"7","m":"1.12386","b":"0.626720","r":"0.99983","quxian":[],"cdata":[{"dnum":"1","values":"0.500000","Areas":"0.859727"},{"dnum":"2","values":"1.00000","Areas":"1.45375"},{"dnum":"3","values":"5.00000","Areas":"5.96091"},{"dnum":"4","values":"10.00000","Areas":"12.78160"},{"dnum":"5","values":"20.00000","Areas":"24.39545"},{"dnum":"6","values":"50.00000","Areas":"56.14156"},{"dnum":"7","values":"100.00000","Areas":"113.02094"}]}]
         * test_result : [{"smid":"1812180084","counts":"1","volume":"1","factors":[{"fname":"甲苯","rdata":{"area":"3.70722","thickness":"2.74100 ng/ul ","m":"1.12386","b":"0.626720","r":"0.99983"}}]}]
         */

        private CommonParaBean common_para;
        private List<StdCurveBean> std_curve;
        private List<TestResultBean> test_result;

        public CommonParaBean getCommon_para() {
            return common_para;
        }

        public void setCommon_para(CommonParaBean common_para) {
            this.common_para = common_para;
        }

        public List<StdCurveBean> getStd_curve() {
            return std_curve;
        }

        public void setStd_curve(List<StdCurveBean> std_curve) {
            this.std_curve = std_curve;
        }

        public List<TestResultBean> getTest_result() {
            return test_result;
        }

        public void setTest_result(List<TestResultBean> test_result) {
            this.test_result = test_result;
        }

        public static class CommonParaBean {
            /**
             * dev : 气相色谱仪
             * devnum : YQ06101
             * column : 11
             * detector : 12
             * thickness : 13
             * qhsTemp : 14
             * zxTemp : 15
             * jcqTemp : 16
             * press_h2 : 17
             * press_air : 22
             * roomTemp : 18
             * roomHum : 19
             * press_load : 20
             * jyl : 21
             * Amethod : 溶剂萃取-填充柱气相色谱法
             */

            private String dev;
            private String devnum;
            private String column;
            private String detector;
            private String thickness;
            private String qhsTemp;
            private String zxTemp;
            private String jcqTemp;
            private String press_h2;
            private String press_air;
            private String roomTemp;
            private String roomHum;
            private String press_load;
            private String jyl;
            private String Amethod;

            public String getDev() {
                return dev;
            }

            public void setDev(String dev) {
                this.dev = dev;
            }

            public String getDevnum() {
                return devnum;
            }

            public void setDevnum(String devnum) {
                this.devnum = devnum;
            }

            public String getColumn() {
                return column;
            }

            public void setColumn(String column) {
                this.column = column;
            }

            public String getDetector() {
                return detector;
            }

            public void setDetector(String detector) {
                this.detector = detector;
            }

            public String getThickness() {
                return thickness;
            }

            public void setThickness(String thickness) {
                this.thickness = thickness;
            }

            public String getQhsTemp() {
                return qhsTemp;
            }

            public void setQhsTemp(String qhsTemp) {
                this.qhsTemp = qhsTemp;
            }

            public String getZxTemp() {
                return zxTemp;
            }

            public void setZxTemp(String zxTemp) {
                this.zxTemp = zxTemp;
            }

            public String getJcqTemp() {
                return jcqTemp;
            }

            public void setJcqTemp(String jcqTemp) {
                this.jcqTemp = jcqTemp;
            }

            public String getPress_h2() {
                return press_h2;
            }

            public void setPress_h2(String press_h2) {
                this.press_h2 = press_h2;
            }

            public String getPress_air() {
                return press_air;
            }

            public void setPress_air(String press_air) {
                this.press_air = press_air;
            }

            public String getRoomTemp() {
                return roomTemp;
            }

            public void setRoomTemp(String roomTemp) {
                this.roomTemp = roomTemp;
            }

            public String getRoomHum() {
                return roomHum;
            }

            public void setRoomHum(String roomHum) {
                this.roomHum = roomHum;
            }

            public String getPress_load() {
                return press_load;
            }

            public void setPress_load(String press_load) {
                this.press_load = press_load;
            }

            public String getJyl() {
                return jyl;
            }

            public void setJyl(String jyl) {
                this.jyl = jyl;
            }

            public String getAmethod() {
                return Amethod;
            }

            public void setAmethod(String Amethod) {
                this.Amethod = Amethod;
            }
        }

        public static class StdCurveBean  implements Serializable{
            /**
             * fname : 甲苯
             * counts : 7
             * m : 1.12386
             * b : 0.626720
             * r : 0.99983
             * quxian : []
             * cdata : [{"dnum":"1","values":"0.500000","Areas":"0.859727"},{"dnum":"2","values":"1.00000","Areas":"1.45375"},{"dnum":"3","values":"5.00000","Areas":"5.96091"},{"dnum":"4","values":"10.00000","Areas":"12.78160"},{"dnum":"5","values":"20.00000","Areas":"24.39545"},{"dnum":"6","values":"50.00000","Areas":"56.14156"},{"dnum":"7","values":"100.00000","Areas":"113.02094"}]
             */
            private Boolean ishow;
            private String fname;
            private String counts;
            private String m;
            private String b;
            private String r;
            private List<?> quxian;
            private List<CdataBean> cdata;

            public Boolean getIshow() {
                return ishow;
            }

            public void setIshow(Boolean ishow) {
                this.ishow = ishow;
            }

            public String getFname() {
                return fname;
            }

            public void setFname(String fname) {
                this.fname = fname;
            }

            public String getCounts() {
                return counts;
            }

            public void setCounts(String counts) {
                this.counts = counts;
            }

            public String getM() {
                return m;
            }

            public void setM(String m) {
                this.m = m;
            }

            public String getB() {
                return b;
            }

            public void setB(String b) {
                this.b = b;
            }

            public String getR() {
                return r;
            }

            public void setR(String r) {
                this.r = r;
            }

            public List<?> getQuxian() {
                return quxian;
            }

            public void setQuxian(List<?> quxian) {
                this.quxian = quxian;
            }

            public List<CdataBean> getCdata() {
                return cdata;
            }

            public void setCdata(List<CdataBean> cdata) {
                this.cdata = cdata;
            }

            public static class CdataBean {
                /**
                 * dnum : 1
                 * values : 0.500000
                 * Areas : 0.859727
                 */

                private String dnum;
                private String values;
                private String Areas;

                public String getDnum() {
                    return dnum;
                }

                public void setDnum(String dnum) {
                    this.dnum = dnum;
                }

                public String getValues() {
                    return values;
                }

                public void setValues(String values) {
                    this.values = values;
                }

                public String getAreas() {
                    return Areas;
                }

                public void setAreas(String Areas) {
                    this.Areas = Areas;
                }
            }
        }

        public static class TestResultBean  implements Serializable{
            /**
             * smid : 1812180084
             * counts : 1
             * volume : 1
             * factors : [{"fname":"甲苯","rdata":{"area":"3.70722","thickness":"2.74100 ng/ul ","m":"1.12386","b":"0.626720","r":"0.99983"}}]
             */

            private String smid;
            private String counts;
            private String volume;
            private Boolean ishow;
            private List<FactorsBean> factors;

            public Boolean getIshow() {
                return ishow;
            }

            public void setIshow(Boolean ishow) {
                this.ishow = ishow;
            }

            public String getSmid() {
                return smid;
            }

            public void setSmid(String smid) {
                this.smid = smid;
            }

            public String getCounts() {
                return counts;
            }

            public void setCounts(String counts) {
                this.counts = counts;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public List<FactorsBean> getFactors() {
                return factors;
            }

            public void setFactors(List<FactorsBean> factors) {
                this.factors = factors;
            }

            public static class FactorsBean {
                /**
                 * fname : 甲苯
                 * rdata : {"area":"3.70722","thickness":"2.74100 ng/ul ","m":"1.12386","b":"0.626720","r":"0.99983"}
                 */

                private String fname;
                private RdataBean rdata;

                public String getFname() {
                    return fname;
                }

                public void setFname(String fname) {
                    this.fname = fname;
                }

                public RdataBean getRdata() {
                    return rdata;
                }

                public void setRdata(RdataBean rdata) {
                    this.rdata = rdata;
                }

                public static class RdataBean {
                    /**
                     * area : 3.70722
                     * thickness : 2.74100 ng/ul
                     * m : 1.12386
                     * b : 0.626720
                     * r : 0.99983
                     */

                    private String area;
                    private String thickness;
                    private String m;
                    private String b;
                    private String r;

                    public String getArea() {
                        return area;
                    }

                    public void setArea(String area) {
                        this.area = area;
                    }

                    public String getThickness() {
                        return thickness;
                    }

                    public void setThickness(String thickness) {
                        this.thickness = thickness;
                    }

                    public String getM() {
                        return m;
                    }

                    public void setM(String m) {
                        this.m = m;
                    }

                    public String getB() {
                        return b;
                    }

                    public void setB(String b) {
                        this.b = b;
                    }

                    public String getR() {
                        return r;
                    }

                    public void setR(String r) {
                        this.r = r;
                    }
                }
            }
        }
    }
}

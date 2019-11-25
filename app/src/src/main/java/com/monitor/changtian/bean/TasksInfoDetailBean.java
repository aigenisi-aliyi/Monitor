package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/7/5.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TasksInfoDetailBean implements Serializable {

    /**
     * id : 8d1141e2-b608-4970-83f6-538934b081da
     * subject : 测试任务
     * content : 哒哒哒
     * userid : 1
     * username : 赵 涛
     * ctime : 2018-07-04 19:32:23
     * schemeid : 42efbbf5-a8d3-44aa-8623-3e6c29d289b9
     * schemename : 测试方案
     * manager : 我
     * address : 杭州市西湖区
     * starttime : 2018-07-05 00:00:00
     * endtime : 2018-07-06 00:00:00
     * coordinate : 108.73,34.25
     * remark :
     * conid : 18
     * pointsid : 6a9d32d6-064b-4ed1-ab68-a71021c56504
     * samplinguser : [{"userid":"2","username":"赵 峰"}]
     * assistuser : [{"userid":"12","username":"4567"}]
     * cars : [{"id":"7","vid":"0582e17c-f3e3-4f16-9d27-fbbbde28cbc4","vlicense":"A88","vnum":"2","vtypes":"","vtypesid":"21","vitem":"52","vitemName":"宣传车","brand":"154","brandname":"长城","imgurl":""}]
     * equips : [{"id":"6","categoryid":"240","categoryname":"离子计","totalnumber":"3"},{"id":"7","categoryid":"244","categoryname":"冷原子吸收测汞仪","totalnumber":"3"}]
     * consumables : [{"id":"4","categoryid":"254","categoryname":"量杯","totalnumber":"3"},{"id":"5","categoryid":"253","categoryname":"PH试纸","totalnumber":"3"},{"id":"6","categoryid":"256","categoryname":"真空袋","totalnumber":"3"}]
     * factors : [{"contentid":"924a867a-dbae-4910-a968-61c39e240f98","fid":"1","factorname":"水温","SamplingStyle":"1","SamplingStyleName":"现场采样","AnalysisMethod":"7","methodname":"检测方法名称1","explainInfo":"","userid":"1","username":"赵 涛","creattime":"2018-07-04 13:40:41","frequency":"1","TestingRequire":"按正常标准检测","cycle":"1","types":"1","typesname":"自测","users":[{"userid":"1","username":"赵 涛"}]}]
     */

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
    private String pointsid;
    private List<SamplinguserBean> samplinguser;
    private List<AssistuserBean> assistuser;
    private List<CarsBean> cars;
    private List<EquipsBean> equips;
    private List<ConsumablesBean> consumables;
    private List<FactorsBean> factors;


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

    public String getPointsid() {
        return pointsid;
    }

    public void setPointsid(String pointsid) {
        this.pointsid = pointsid;
    }

    public List<SamplinguserBean> getSamplinguser() {
        return samplinguser;
    }

    public void setSamplinguser(List<SamplinguserBean> samplinguser) {
        this.samplinguser = samplinguser;
    }

    public List<AssistuserBean> getAssistuser() {
        return assistuser;
    }

    public void setAssistuser(List<AssistuserBean> assistuser) {
        this.assistuser = assistuser;
    }

    public List<CarsBean> getCars() {
        return cars;
    }

    public void setCars(List<CarsBean> cars) {
        this.cars = cars;
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

    public List<FactorsBean> getFactors() {
        return factors;
    }

    public void setFactors(List<FactorsBean> factors) {
        this.factors = factors;
    }

    public static class SamplinguserBean  implements Serializable{
        /**
         * userid : 2
         * username : 赵 峰
         */

        private String userid;
        private String username;

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
    }

    public static class AssistuserBean implements Serializable{
        /**
         * userid : 12
         * username : 4567
         */

        private String userid;
        private String username;

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
    }

    public static class CarsBean   implements Serializable{
        /**
         * id : 7
         * vid : 0582e17c-f3e3-4f16-9d27-fbbbde28cbc4
         * vlicense : A88
         * vnum : 2
         * vtypes :
         * vtypesid : 21
         * vitem : 52
         * vitemName : 宣传车
         * brand : 154
         * brandname : 长城
         * imgurl :
         */

        private String id;
        private String vid;
        private String vlicense;
        private String vnum;
        private String vtypes;
        private String vtypesid;
        private String vitem;
        private String vitemName;
        private String brand;
        private String brandname;
        private String imgurl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getVlicense() {
            return vlicense;
        }

        public void setVlicense(String vlicense) {
            this.vlicense = vlicense;
        }

        public String getVnum() {
            return vnum;
        }

        public void setVnum(String vnum) {
            this.vnum = vnum;
        }

        public String getVtypes() {
            return vtypes;
        }

        public void setVtypes(String vtypes) {
            this.vtypes = vtypes;
        }

        public String getVtypesid() {
            return vtypesid;
        }

        public void setVtypesid(String vtypesid) {
            this.vtypesid = vtypesid;
        }

        public String getVitem() {
            return vitem;
        }

        public void setVitem(String vitem) {
            this.vitem = vitem;
        }

        public String getVitemName() {
            return vitemName;
        }

        public void setVitemName(String vitemName) {
            this.vitemName = vitemName;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getBrandname() {
            return brandname;
        }

        public void setBrandname(String brandname) {
            this.brandname = brandname;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }

    public static class EquipsBean   implements Serializable{
        /**
         * id : 6
         * categoryid : 240
         * categoryname : 离子计
         * totalnumber : 3
         */

        private String id;
        private String categoryid;
        private String categoryname;
        private String totalnumber;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getTotalnumber() {
            return totalnumber;
        }

        public void setTotalnumber(String totalnumber) {
            this.totalnumber = totalnumber;
        }
    }

    public static class ConsumablesBean   implements Serializable{
        /**
         * id : 4
         * categoryid : 254
         * categoryname : 量杯
         * totalnumber : 3
         */

        private String id;
        private String categoryid;
        private String categoryname;
        private String totalnumber;
        private String Consumablesid;
        private String ConsumablesName;

        public String getConsumablesid() {
            return Consumablesid;
        }

        public void setConsumablesid(String consumablesid) {
            Consumablesid = consumablesid;
        }

        public String getConsumablesName() {
            return ConsumablesName;
        }

        public void setConsumablesName(String consumablesName) {
            ConsumablesName = consumablesName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getTotalnumber() {
            return totalnumber;
        }

        public void setTotalnumber(String totalnumber) {
            this.totalnumber = totalnumber;
        }
    }

    public static class FactorsBean   implements Serializable{
        /**
         * contentid : 924a867a-dbae-4910-a968-61c39e240f98
         * fid : 1
         * factorname : 水温
         * SamplingStyle : 1
         * SamplingStyleName : 现场采样
         * AnalysisMethod : 7
         * methodname : 检测方法名称1
         * explainInfo :
         * userid : 1
         * username : 赵 涛
         * creattime : 2018-07-04 13:40:41
         * frequency : 1
         * TestingRequire : 按正常标准检测
         * cycle : 1
         * types : 1
         * typesname : 自测
         * users : [{"userid":"1","username":"赵 涛"}]
         */

        private String contentid;
        private String fid;
        private String factorname;
        private String SamplingStyle;
        private String SamplingStyleName;
        private String AnalysisMethod;
        private String methodname;
        private String explainInfo;
        private String userid;
        private String username;
        private String creattime;
        private String frequency;
        private String TestingRequire;
        private String cycle;
        private String types;
        private String typesname;
        private List<UsersBean> users;

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

        public String getExplainInfo() {
            return explainInfo;
        }

        public void setExplainInfo(String explainInfo) {
            this.explainInfo = explainInfo;
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

        public String getCreattime() {
            return creattime;
        }

        public void setCreattime(String creattime) {
            this.creattime = creattime;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }

        public String getTestingRequire() {
            return TestingRequire;
        }

        public void setTestingRequire(String TestingRequire) {
            this.TestingRequire = TestingRequire;
        }

        public String getCycle() {
            return cycle;
        }

        public void setCycle(String cycle) {
            this.cycle = cycle;
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

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class UsersBean  implements Serializable{
            /**
             * userid : 1
             * username : 赵 涛
             */

            private String userid;
            private String username;

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
        }
    }
}

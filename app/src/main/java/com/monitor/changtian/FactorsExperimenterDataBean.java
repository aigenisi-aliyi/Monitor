package com.monitor.changtian;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/7/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class FactorsExperimenterDataBean implements Serializable {


    /**
     * contentid : 9f6b3c1a-306e-4650-b2c9-53fc2ac24351
     * fid : 2
     * factorname : 二氧化硫
     * SamplingStyle : 1
     * SamplingStyleName : 现场采样
     * AnalysisMethod : 7
     * methodname : 检测方法名称1
     * explainInfo :
     * userid : 1
     * username : 赵 涛
     * creattime : 2018-06-25 15:56:38
     * frequency : 1
     * TestingRequire :
     * cycle : 一天
     * types : 1
     * typesname : 自测
     * users : [{"id":1,"loginName":"admin","userName":"赵 涛"}]
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
    private List<UsersBeanSave> user_save;  //保存的Bean

    private Boolean isshow = true;

    public Boolean getIsshow() {
        return isshow;
    }

    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
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


    public List<UsersBeanSave> getUser_save() {
        return user_save;
    }

    public void setUser_save(List<UsersBeanSave> user_save) {
        this.user_save = user_save;
    }


    public static class UsersBeanSave implements Serializable {
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


    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean implements Serializable, IPickerViewData {
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


        @Override
        public String getPickerViewText() {
            return userName;
        }
    }
}

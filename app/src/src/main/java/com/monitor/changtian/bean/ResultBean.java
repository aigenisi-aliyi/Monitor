package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/5/18.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ResultBean implements Serializable {

    /**
     * result : 1 表示成功 0表示失败
     */
    /**
     * errormessage
     */
    private String result;
    private String errormessage;
    private String sampdetailid;
    private String tockenInfo;
    private String DevType;

    /**
     * 噪声校准 返回字段
     */
    private String isqualified;

    public String getIsqualified() {
        return isqualified;
    }

    public void setIsqualified(String isqualified) {
        this.isqualified = isqualified;
    }

    public String getDevType() {
        return DevType;
    }

    public void setDevType(String devType) {
        DevType = devType;
    }

    public String getTockenInfo() {
        return tockenInfo;
    }

    public void setTockenInfo(String tockenInfo) {
        this.tockenInfo = tockenInfo;
    }

    private List<RolejarryBean> rolejarry;

    public String getSampdetailid() {
        return sampdetailid;
    }

    public void setSampdetailid(String sampdetailid) {
        this.sampdetailid = sampdetailid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public List<RolejarryBean> getRolejarry() {
        return rolejarry;
    }

    public void setRolejarry(List<RolejarryBean> rolejarry) {
        this.rolejarry = rolejarry;
    }

    public static class RolejarryBean implements Serializable {
        /**
         * roleid : 7
         * RoleName : 质控人员
         */

        private String roleid;
        private String RoleName;

        private boolean isSelected; //自定义列表是否选中的标识

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getRoleid() {
            return roleid;
        }

        public void setRoleid(String roleid) {
            this.roleid = roleid;
        }

        public String getRoleName() {
            return RoleName;
        }

        public void setRoleName(String RoleName) {
            this.RoleName = RoleName;
        }
    }
}

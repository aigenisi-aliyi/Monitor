package com.monitor.changtian.bean.EventBus;

/**
 * Created by ken on 2018/6/5.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Function:
 */

public class PushMessage {


    /**
     * loginId : admin
     * content : 您有一条新的审批信息
     * sampleid : 3f782666-79b6-43e7-9acd-a3ea01e39da6
     */

    private String loginId;
    private String content;
    private String subid;
    private String remark;
    private String mtypes;

    public String getMtypes() {
        return mtypes;
    }

    public void setMtypes(String mtypes) {
        this.mtypes = mtypes;
    }

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

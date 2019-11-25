package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/6/26.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class MoneyRecordBean implements Serializable {

    /**
     * id : 27
     * contractid : 1
     * contractcode : 00100011
     * contractname : 测试
     * receivetype : 22
     * receivetypename : 转账
     * receivemoney : 10000
     * remark : 备注
     * PaymentDate : 2018-06-28 00:00:00
     * createtime : 2018-06-28 10:04:44
     * userid : 1
     * username : 赵 涛
     * files : /upload/files/677f681c-d760-4c62-89d9-5055e42cb6c7819.png
     */

    private String id;
    private String contractid;
    private String contractcode;
    private String contractname;
    private String receivetype;
    private String receivetypename;
    private String receivemoney;
    private String remark;
    private String PaymentDate;
    private String createtime;
    private String userid;
    private String username;
    private String files;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractid() {
        return contractid;
    }

    public void setContractid(String contractid) {
        this.contractid = contractid;
    }

    public String getContractcode() {
        return contractcode;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
    }

    public String getContractname() {
        return contractname;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public String getReceivetype() {
        return receivetype;
    }

    public void setReceivetype(String receivetype) {
        this.receivetype = receivetype;
    }

    public String getReceivetypename() {
        return receivetypename;
    }

    public void setReceivetypename(String receivetypename) {
        this.receivetypename = receivetypename;
    }

    public String getReceivemoney() {
        return receivemoney;
    }

    public void setReceivemoney(String receivemoney) {
        this.receivemoney = receivemoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String PaymentDate) {
        this.PaymentDate = PaymentDate;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }
}

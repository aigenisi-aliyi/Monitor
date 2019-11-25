package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/6/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ProjectcontractBean implements Serializable {

    /**
     * id : 18
     * projectid : 26
     * projectnames : 陕西宏远燃气天然气输配调压设备及煤化工配套设备生产基地项目
     * customerid : 39
     * customernames : 陕西宏远
     * customercontactnumber :
     * customertelephone : 56743561234
     * schemeid : 42efbbf5-a8d3-44aa-8623-3e6c29d289b9
     * schemename : 测试方案3
     * IsUrgent : 0
     * IsUrgentName : 否
     * schemecreatetime : 2018-07-04 13:40:41
     * contractcode : 00100011
     * contractname : 测试点位信息
     * signDate : 2018-06-27
     * latestpaydate : 2018-09-20
     * salesmanid : 1
     * salesmanname : 赵 涛
     * remark :
     * createuser : 1
     * username : 赵 涛
     * createtime : 2018-06-27 16:36:28
     * introduction : 简介
     * totalmoney : 100000
     * AdvanceCharge : 8000
     * contractstatus : 3
     * contractstatusname : 已付清
     * sumpaynumber : 100000
     * tailmoney : 0
     * isqualitycontrol :
     * isqualitycontrolname :
     * qualitycontroluser :
     * qualitycontrolusername :
     * qualitycontroltime :
     * fujians : []
     */

    private String taskstatus;
    private String id;
    private String projectid;
    private String projectnames;
    private String customerid;
    private String customernames;
    private String customercontactnumber;
    private String customertelephone;
    private String schemeid;
    private String schemename;
    private String IsUrgent;
    private String IsUrgentName;
    private String schemecreatetime;
    private String contractcode;
    private String contractname;
    private String signDate;
    private String latestpaydate;
    private String salesmanid;
    private String salesmanname;
    private String remark;
    private String createuser;
    private String username;
    private String createtime;
    private String introduction;
    private String totalmoney;
    private String AdvanceCharge;
    private String contractstatus;
    private String contractstatusname;
    private int sumpaynumber;
    private int tailmoney;
    private String isqualitycontrol;
    private String isqualitycontrolname;
    private String qualitycontroluser;
    private String qualitycontrolusername;
    private String qualitycontroltime;
    private List<?> fujians;
    private String completetime;

    public String getCompletetime() {
        return completetime;
    }

    public void setCompletetime(String completetime) {
        this.completetime = completetime;
    }

    public String getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus;
    }

    /**
     * samplemode : 0
     * samplemodename : 现场采样
     * sumpaynumber : 8000.0
     * tailmoney : 2000.0
     * fujians : []
     */

    private String samplemode;
    private String samplemodename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectnames() {
        return projectnames;
    }

    public void setProjectnames(String projectnames) {
        this.projectnames = projectnames;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomernames() {
        return customernames;
    }

    public void setCustomernames(String customernames) {
        this.customernames = customernames;
    }

    public String getCustomercontactnumber() {
        return customercontactnumber;
    }

    public void setCustomercontactnumber(String customercontactnumber) {
        this.customercontactnumber = customercontactnumber;
    }

    public String getCustomertelephone() {
        return customertelephone;
    }

    public void setCustomertelephone(String customertelephone) {
        this.customertelephone = customertelephone;
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

    public String getSchemecreatetime() {
        return schemecreatetime;
    }

    public void setSchemecreatetime(String schemecreatetime) {
        this.schemecreatetime = schemecreatetime;
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

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getLatestpaydate() {
        return latestpaydate;
    }

    public void setLatestpaydate(String latestpaydate) {
        this.latestpaydate = latestpaydate;
    }

    public String getSalesmanid() {
        return salesmanid;
    }

    public void setSalesmanid(String salesmanid) {
        this.salesmanid = salesmanid;
    }

    public String getSalesmanname() {
        return salesmanname;
    }

    public void setSalesmanname(String salesmanname) {
        this.salesmanname = salesmanname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(String totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getAdvanceCharge() {
        return AdvanceCharge;
    }

    public void setAdvanceCharge(String AdvanceCharge) {
        this.AdvanceCharge = AdvanceCharge;
    }

    public String getContractstatus() {
        return contractstatus;
    }

    public void setContractstatus(String contractstatus) {
        this.contractstatus = contractstatus;
    }

    public String getContractstatusname() {
        return contractstatusname;
    }

    public void setContractstatusname(String contractstatusname) {
        this.contractstatusname = contractstatusname;
    }

    public int getSumpaynumber() {
        return sumpaynumber;
    }

    public void setSumpaynumber(int sumpaynumber) {
        this.sumpaynumber = sumpaynumber;
    }

    public int getTailmoney() {
        return tailmoney;
    }

    public void setTailmoney(int tailmoney) {
        this.tailmoney = tailmoney;
    }

    public String getIsqualitycontrol() {
        return isqualitycontrol;
    }

    public void setIsqualitycontrol(String isqualitycontrol) {
        this.isqualitycontrol = isqualitycontrol;
    }

    public String getIsqualitycontrolname() {
        return isqualitycontrolname;
    }

    public void setIsqualitycontrolname(String isqualitycontrolname) {
        this.isqualitycontrolname = isqualitycontrolname;
    }

    public String getQualitycontroluser() {
        return qualitycontroluser;
    }

    public void setQualitycontroluser(String qualitycontroluser) {
        this.qualitycontroluser = qualitycontroluser;
    }

    public String getQualitycontrolusername() {
        return qualitycontrolusername;
    }

    public void setQualitycontrolusername(String qualitycontrolusername) {
        this.qualitycontrolusername = qualitycontrolusername;
    }

    public String getQualitycontroltime() {
        return qualitycontroltime;
    }

    public void setQualitycontroltime(String qualitycontroltime) {
        this.qualitycontroltime = qualitycontroltime;
    }

    public List<?> getFujians() {
        return fujians;
    }

    public void setFujians(List<?> fujians) {
        this.fujians = fujians;
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
}

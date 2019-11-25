package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/8/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class EquipInOutStockDataBean implements Serializable {

    /**
     * id : 2
     * equipid : 2
     * names : 紫外光度计1-1
     * equipstatus : 1
     * equipstatusname : 是
     * types : 1
     * typesname : 是
     * useddate : 2018-06-22 00:00:00
     * optionuser : 1
     * username : 赵 涛
     * remark : 修改后的
     * taskid : 8d1141e2-b608-4970-83f6-538934b081da
     * address : 陕西省西安市未央区凤城一路
     * createuser : 1
     * createusername : 张三
     * createtime : 2018-06-22 00:00:00
     */

    private String barCode;
    private String id;
    private String equipid;
    private String names;
    private String equipstatus;
    private String equipstatusname;
    private String types;
    private String typesname;
    private String useddate;
    private String optionuser;
    private String username;
    private String remark;
    private String taskid;
    private String address;
    private String createuser;
    private String createusername;
    private String createtime;
    /**
     * conid : 1
     * constatus : 1
     * constatusname : 是
     */

    private String conid;
    private String constatus;
    private String constatusname;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

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

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEquipstatus() {
        return equipstatus;
    }

    public void setEquipstatus(String equipstatus) {
        this.equipstatus = equipstatus;
    }

    public String getEquipstatusname() {
        return equipstatusname;
    }

    public void setEquipstatusname(String equipstatusname) {
        this.equipstatusname = equipstatusname;
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

    public String getUseddate() {
        return useddate;
    }

    public void setUseddate(String useddate) {
        this.useddate = useddate;
    }

    public String getOptionuser() {
        return optionuser;
    }

    public void setOptionuser(String optionuser) {
        this.optionuser = optionuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getConid() {
        return conid;
    }

    public void setConid(String conid) {
        this.conid = conid;
    }

    public String getConstatus() {
        return constatus;
    }

    public void setConstatus(String constatus) {
        this.constatus = constatus;
    }

    public String getConstatusname() {
        return constatusname;
    }

    public void setConstatusname(String constatusname) {
        this.constatusname = constatusname;
    }
}

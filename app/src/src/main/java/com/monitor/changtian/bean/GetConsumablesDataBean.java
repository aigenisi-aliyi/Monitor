package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/8/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class GetConsumablesDataBean implements Serializable {

    /**
     * id : 1
     * ConsumablesNumber : 试纸
     * names : 试纸
     * barCode :
     * FactoryNumber :
     * ModelSpecification :
     * buyDate :
     * place : 11
     * roomname : 耗材室
     * remark :
     * productionUnit :
     * ExpireDate : 2080-06-06 00:00:00
     * Consumablestype : 222
     * Consumablestypename : 采样
     * IsEnabled : 1
     * IsEnabledName : 是
     * IsConfecting :
     * IsConfectingName :
     * categoryid : 1
     * categoryname : PH试纸
     */
    private boolean isChoice_save;
    private boolean isChoice;

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
}

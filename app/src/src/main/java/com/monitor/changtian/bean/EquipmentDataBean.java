package com.monitor.changtian.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;

/**
 * Created by ken on 2018/7/9.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class EquipmentDataBean implements Serializable, IPickerViewData {

    /**
     * id : 21
     * EquipmentNumber : PH1407008
     * names : 可见分光光度计
     * barCode :
     * FactoryNumber : 9
     * ModelSpecification : UV-5500PC
     * Accuracy : ±0.5
     * buyDate : 2018-07-20
     * place : 1
     * roomname : 样品室
     * CollarState : 0
     * productionUnit : 上海元析仪器有限公司
     * RegulationNumber : YQ00301
     * ExpireDate : 2018-07-29
     * remark :
     * equiptype : 170
     * equiptypename : 计量仪器
     * IsEnabled : 1
     * IsEnabledName : 是
     * categoryid : 233
     * categoryname : 原子吸收分光光度计
     */


    private boolean isSelected; //自定义列表是否选中的标识
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * ConsumablesNumber : 试纸
     * Consumablestype : 222
     * Consumablestypename : 采样
     * IsConfecting :
     * IsConfectingName :
     */

    private String ConsumablesNumber;
    private String Consumablestype;
    private String Consumablestypename;
    private String IsConfecting;
    private String IsConfectingName;

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

    public String getConsumablesNumber() {
        return ConsumablesNumber;
    }

    public void setConsumablesNumber(String ConsumablesNumber) {
        this.ConsumablesNumber = ConsumablesNumber;
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

    @Override
    public String getPickerViewText() {
        return names + "(" + EquipmentNumber + ")";
    }
}

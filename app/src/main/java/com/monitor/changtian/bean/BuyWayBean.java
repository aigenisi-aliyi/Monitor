package com.monitor.changtian.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;

/**
 * Created by ken on 2018/6/26.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class BuyWayBean implements Serializable , IPickerViewData {

    /**
     * Id : 21
     * typeCode : buyWay
     * Name : 支付方式
     * DataCode : 1
     * DataValue : 现金
     * sortId :
     * Remark :
     */

    private int Id;
    private String typeCode;
    private String Name;
    private String DataCode;
    private String DataValue;
    private String sortId;
    private String Remark;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDataCode() {
        return DataCode;
    }

    public void setDataCode(String DataCode) {
        this.DataCode = DataCode;
    }

    public String getDataValue() {
        return DataValue;
    }

    public void setDataValue(String DataValue) {
        this.DataValue = DataValue;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    @Override
    public String getPickerViewText() {
        return DataValue;
    }
}

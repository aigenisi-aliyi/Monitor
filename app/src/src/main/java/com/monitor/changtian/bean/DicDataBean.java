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

public class DicDataBean implements Serializable, IPickerViewData {

    /**
     * Id : 170
     * typeCode : equiptype
     * Name : 仪器设备类型
     * DataCode : 1
     * DataValue : 计量仪器
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
    private boolean isChoice;
    private boolean isChoice_save;

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public boolean isChoice_save() {
        return isChoice_save;
    }

    public void setChoice_save(boolean choice_save) {
        isChoice_save = choice_save;
    }

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

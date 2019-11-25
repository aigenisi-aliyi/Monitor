package com.monitor.changtian.bean;

import java.io.Serializable;
public class CalibrateDevListBean implements Serializable {
    /**
     * id : 21
     * EquipmentNumber : 258
     * names : 分析仪002
     */
    private int id;
    private int EquipmentNumber;
    private String names;

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEquipmentNumber(int equipmentNumber) {
        this.EquipmentNumber = equipmentNumber;
    }

    public int getEquipmentNumber() {
        return EquipmentNumber;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getNames() {
        return names;
    }
}

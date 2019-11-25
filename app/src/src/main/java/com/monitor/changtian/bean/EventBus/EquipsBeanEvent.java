package com.monitor.changtian.bean.EventBus;

import com.monitor.changtian.bean.EquipsBean;

import java.util.ArrayList;

/**
 * Created by ken on 2018/7/12.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class EquipsBeanEvent {

    private int pos;
    private String names;
    private String devname;
    private String type;
    private String Innames;

    public String getInnames() {
        return Innames;
    }

    public void setInnames(String innames) {
        Innames = innames;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    private ArrayList<EquipsBean> calibrationsBeanArrayList = new ArrayList<>();

    public EquipsBeanEvent(String Innames, String type, int pos, ArrayList<EquipsBean> calibrationsBeanArrayList, String names, String devname) {

        this.Innames = Innames;
        this.type = type;
        this.pos = pos;
        this.calibrationsBeanArrayList = calibrationsBeanArrayList;
        this.names = names;
        this.devname = devname;
    }

    public ArrayList<EquipsBean> getCalibrationsBeanArrayList() {
        return calibrationsBeanArrayList;
    }

    public void setCalibrationsBeanArrayList(ArrayList<EquipsBean> calibrationsBeanArrayList) {
        this.calibrationsBeanArrayList = calibrationsBeanArrayList;
    }
}

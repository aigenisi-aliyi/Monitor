package com.monitor.changtian.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/13.
 */

public class DayNum_FrequencyBean implements Serializable ,IPickerViewData {

    private String name;
    private String num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}

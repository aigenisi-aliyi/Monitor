package com.monitor.changtian.bean.EventBus;

import com.clj.fastble.data.BleDevice;

/**
 * Created by Administrator on 2018/11/6.
 */

public class MeteorologicalEvent {

    private String title;
    private BleDevice bleDevice;

    public MeteorologicalEvent(String title, BleDevice bleDevice) {
        this.title = title;
        this.bleDevice = bleDevice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BleDevice getBleDevice() {
        return bleDevice;
    }

    public void setBleDevice(BleDevice bleDevice) {
        this.bleDevice = bleDevice;
    }
}
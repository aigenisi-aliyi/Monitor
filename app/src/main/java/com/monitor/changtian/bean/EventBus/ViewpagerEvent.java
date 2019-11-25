package com.monitor.changtian.bean.EventBus;

/**
 * Created by ken on 2018/5/15.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ViewpagerEvent {
    public int type;

    public ViewpagerEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

package com.monitor.changtian.bean.EventBus;

/**
 * Created by ken on 2018/5/14.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class AddEvent {
    private int target;
    private int istar;

    public AddEvent(int target) {
        this.target = target;
    }

    public AddEvent(int target, int istar) {
        this.target = target;
        this.istar = istar;
    }

    public int isIstar() {
        return istar;
    }

    public void setIstar(int istar) {
        this.istar = istar;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}

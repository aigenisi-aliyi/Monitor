package com.monitor.changtian.bean.EventBus;

/**
 * Created by ken on 2018/4/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class FinishEvent {

    public void FinishEvent(){

    }

    public FinishEvent(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

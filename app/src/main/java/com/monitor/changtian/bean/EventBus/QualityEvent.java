package com.monitor.changtian.bean.EventBus;

/**
 * Created by Administrator on 2018/12/20.
 */

public class QualityEvent  {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public QualityEvent(String message) {
        this.message = message;
    }
}

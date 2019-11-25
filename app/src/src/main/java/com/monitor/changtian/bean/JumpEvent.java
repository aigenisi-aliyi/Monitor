package com.monitor.changtian.bean;

/**
 * Created by Administrator on 2018/10/22.
 */

public class JumpEvent {


    public JumpEvent(String status) {
        this.status = status;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

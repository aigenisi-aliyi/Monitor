package com.monitor.changtian.bean.EventBus;

/**
 * Created by ken on 2018/5/29.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class BarCodeEvent {
    private String codeNum;

    public BarCodeEvent(String codeNum) {
        this.codeNum = codeNum;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }
}

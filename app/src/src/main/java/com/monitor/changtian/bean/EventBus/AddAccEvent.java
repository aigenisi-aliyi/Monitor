package com.monitor.changtian.bean.EventBus;

/**
 * Created by Administrator on 2018/12/14.
 */

public class AddAccEvent {


    private String codeNum;

    public AddAccEvent(String codeNum) {
        this.codeNum = codeNum;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }
}

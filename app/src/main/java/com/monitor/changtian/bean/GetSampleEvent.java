package com.monitor.changtian.bean;

/**
 * Created by Administrator on 2018/12/17.
 */

public class GetSampleEvent {


    private String codeNum;

    public GetSampleEvent(String codeNum) {
        this.codeNum = codeNum;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }
}

package com.monitor.changtian.bean.EventBus;

/**
 * Created by Administrator on 2018/12/21.
 */

public class SongEvent  {

    private String  CodeNum;

    public SongEvent(String codeNum) {
        CodeNum = codeNum;
    }

    public String getCodeNum() {
        return CodeNum;
    }

    public void setCodeNum(String codeNum) {
        CodeNum = codeNum;
    }
}

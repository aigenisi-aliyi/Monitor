package com.monitor.changtian.bean.EventBus;

/**
 * Created by ken on 2018/6/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class UpdataEvent {

    public UpdataEvent(String content) {
        this.content = content;
    }

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

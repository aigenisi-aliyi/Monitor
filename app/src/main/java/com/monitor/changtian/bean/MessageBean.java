package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/5/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class MessageBean implements Serializable {


    /**
     * userName : 张三
     * content : 您有一条新的任务请查收
     * stime : 2018-05-27T16:29:52
     */

    private String userName;
    private String content;
    private String stime;
    /**
     * subid : 3f782666-79b6-43e7-9acd-a3ea01e39da6
     */

    private String subid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }
}

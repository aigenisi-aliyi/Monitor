package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/5/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskBean implements Serializable {


    /**
     * subject : 新丰泰汽车配件产业园环评项目
     * userName : 张三
     * ctime : 2018-05-28 11:09:47
     * content : 渭河边自来水取水口，取样5升
     * subname : 污水检测
     * source : 取样
     * Clientor : 张伟
     * ClientTel : 15389234567
     * coordinate : 108.865243,34.394156
     */

    private String subject;
    private String userName;
    private String ctime;
    private String content;
    private String subname;
    private String source;
    private String Clientor;
    private String ClientTel;
    private String coordinate;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getClientor() {
        return Clientor;
    }

    public void setClientor(String Clientor) {
        this.Clientor = Clientor;
    }

    public String getClientTel() {
        return ClientTel;
    }

    public void setClientTel(String ClientTel) {
        this.ClientTel = ClientTel;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}

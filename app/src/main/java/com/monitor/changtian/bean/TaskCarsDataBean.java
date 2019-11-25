package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/6/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskCarsDataBean implements Serializable {

    /**
     * id : 2
     * taskid : 1
     * subject : 新丰泰汽车配件产业园环评项目
     * vid : 0c5ef046-c0e8-4235-a080-2eb4b38fabdc
     * vlicense : 吉A68641
     * imgurl : 636410002801286710.png
     * createtime : 2018-06-05 18:10:16
     * userid : 1
     * username : 赵 涛
     */

    private String id;
    private String taskid;
    private String subject;
    private String vid;
    private String vlicense;
    private String imgurl;
    private String createtime;
    private String userid;
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVlicense() {
        return vlicense;
    }

    public void setVlicense(String vlicense) {
        this.vlicense = vlicense;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

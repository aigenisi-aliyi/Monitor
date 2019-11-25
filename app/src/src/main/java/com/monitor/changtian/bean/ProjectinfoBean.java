package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/4/25.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ProjectinfoBean implements Serializable {
    private String projectinfo_name;
    private String projectinfo_pic;

    public String getProjectinfo_name() {
        return projectinfo_name;
    }

    public void setProjectinfo_name(String projectinfo_name) {
        this.projectinfo_name = projectinfo_name;
    }

    public String getProjectinfo_pic() {
        return projectinfo_pic;
    }

    public void setProjectinfo_pic(String projectinfo_pic) {
        this.projectinfo_pic = projectinfo_pic;
    }
}

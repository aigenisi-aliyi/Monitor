package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/17.
 */

public class TaskAllPointsFreQuencyBean implements Serializable {
    private List<TaskListBean.PointsBean> pointsBeanList;

    public List<TaskListBean.PointsBean> getPointsBeanList() {
        return pointsBeanList;
    }

    public void setPointsBeanList(List<TaskListBean.PointsBean> pointsBeanList) {
        this.pointsBeanList = pointsBeanList;
    }
}

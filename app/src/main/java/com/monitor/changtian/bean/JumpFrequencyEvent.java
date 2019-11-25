package com.monitor.changtian.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/22.
 */

public class JumpFrequencyEvent {

    private List<TaskListBean.PointsBean> pointsBeanList;

    public JumpFrequencyEvent(List<TaskListBean.PointsBean> pointsBeanList) {
        this.pointsBeanList = pointsBeanList;
    }

    public List<TaskListBean.PointsBean> getPointsBeanList() {
        return pointsBeanList;
    }

    public void setPointsBeanList(List<TaskListBean.PointsBean> pointsBeanList) {
        this.pointsBeanList = pointsBeanList;
    }
}

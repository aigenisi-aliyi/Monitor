package com.monitor.changtian.bean.EventBus;

import com.monitor.changtian.bean.TaskListBean;

/**
 * Created by ken on 2018/6/12.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskEvent {


    public TaskListBean taskBean;

    String taskid;

    public TaskEvent(TaskListBean taskBean, String taskid) {
        this.taskBean = taskBean;
        this.taskid = taskid;
    }

    public TaskListBean getTaskBean() {
        return taskBean;
    }

    public void setTaskBean(TaskListBean taskBean) {
        this.taskBean = taskBean;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }
}

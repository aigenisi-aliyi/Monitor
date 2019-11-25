package com.monitor.changtian.view;

import com.monitor.changtian.bean.TaskListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/19.
 */

public interface Acc_TaskView {
    void OnAcc_TaskList(List<TaskListBean> taskListBeans);

    void OnError(String s);
}

package com.monitor.changtian.view;

import com.monitor.changtian.TasksListInfoBean;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.Task_SapmleBean;
import com.monitor.changtian.bean.TasksInfoDetailBean;

import java.util.List;

/**
 * Created by ken on 2018/7/5.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface TaskContractInfoView {
    /**
     * 查询任务列表
     */
    void OnTasksListInfo(List<TasksListInfoBean> tasksListInfoBeans);

    /**
     * 查询任务详情
     */
    void OnTasksInfoDetail(List<TasksInfoDetailBean> tasksInfoDetailBeans);

    /**
     * 查询方案详情
     */
    void OnGetDetectionSchemeInfo(List<DetectionSchemeInfoBean> list);

    /**
     * 审核方案
     */
    void OnAddDetectionSchemeAudit(ResultBean resultBean);

    /**
     *
     */
    void onData(List<DicDataBean> dicDataBeans);

    void OnGetsampleInformation(List<Task_SapmleBean> dicDataBeans);

    void OnError(String s);
}

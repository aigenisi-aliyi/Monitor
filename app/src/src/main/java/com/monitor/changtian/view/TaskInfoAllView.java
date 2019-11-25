package com.monitor.changtian.view;

import com.monitor.changtian.bean.TaskInfoAllBean;

import java.util.List;

/**
 * Created by ken on 2018/8/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface TaskInfoAllView  {
    void OnGetTasksInfoAudit(List<TaskInfoAllBean> taskInfoAllBeans);
    void OnError(String s);
}

package com.monitor.changtian.view;

import com.monitor.changtian.bean.ProjectTestListBean;
import com.monitor.changtian.bean.QueryTestRecordBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TestProjectCycleListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/12/19.
 */

public interface QualitycontrolApprovalView {

    void OnGetTestProjectCycleList(List<TestProjectCycleListBean> testProjectCycleListBeans);

    void OnProjectTestListBean(List<ProjectTestListBean> projectTestListBeans);

    void OnQueryTestRecordBeans(List<QueryTestRecordBean> queryTestRecordBeans);

    void OnMessage(ResultBean resultBean);

    void OnError(String s);
}

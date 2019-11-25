package com.monitor.taskallocation.task_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.accuse.Acc_TaskinfoActivity_;
import com.monitor.changtian.R;
import com.monitor.changtian.TasksListInfoBean;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.Task_SapmleBean;
import com.monitor.changtian.bean.TasksInfoDetailBean;
import com.monitor.changtian.presenter.TaskContractInfoPresenter;
import com.monitor.changtian.view.TaskContractInfoView;
import com.monitor.taskallocation.TaskinfoActivity_;
import com.monitor.taskallocation.adapter.TaskContractInfoAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * 周期任务列表展示
 */
@EActivity(R.layout.activity_task_cycle_list)
public class TaskCycleListActivity extends BaseActvity implements TaskContractInfoView {

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    TaskContractInfoAdapter taskContractInfoAdapter;
    TaskContractInfoPresenter taskContractInfoPresenter;
    @Extra
    String currenttasknumber;
    @Extra
    String conid;
    @Extra
    String status;

    @AfterViews
    void init() {
        initImageBackText("周期任务列表");
        taskContractInfoPresenter = new TaskContractInfoPresenter(this, this);
        taskContractInfoAdapter = new TaskContractInfoAdapter(R.layout.ts_contractinfo_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(taskContractInfoAdapter);
//        initData();

        taskContractInfoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


                switch (view.getId()) {
                    case R.id.ll_items:
                        if (status != null) {
                            Acc_TaskinfoActivity_.intent(TaskCycleListActivity.this).taskStutas(taskContractInfoAdapter.getData().get(position).getTaskstatus()).taskid(taskContractInfoAdapter.getData().get(position).getId()).start();
                        } else {
                            TaskinfoActivity_.intent(TaskCycleListActivity.this).taskStutas(taskContractInfoAdapter.getData().get(position).getTaskstatus()).taskid(taskContractInfoAdapter.getData().get(position).getId()).start();
                        }
                        break;
                    case R.id.tv_subject:
                        msg(taskContractInfoAdapter.getData().get(position).getSubject());
                        break;
                }


            }
        });
    }

    public void initData() {
        ShowDialogtitle("加载中...", this);
        String datas1 = "{id:\"\",pagenumber:\"\",numbers:\"\",currenttasknumber:\"" + currenttasknumber + "\",conid:\"" + conid + "\"} ";
        taskContractInfoPresenter.GetTasksInfo(datas1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void OnTasksListInfo(List<TasksListInfoBean> tasksListInfoBeans) {
        DissDialog();
        if (tasksListInfoBeans.size() > 0) {
            taskContractInfoAdapter.setNewData(tasksListInfoBeans);
        }

    }

    @Override
    public void OnTasksInfoDetail(List<TasksInfoDetailBean> tasksInfoDetailBeans) {

    }

    @Override
    public void OnGetDetectionSchemeInfo(List<DetectionSchemeInfoBean> list) {

    }

    @Override
    public void OnAddDetectionSchemeAudit(ResultBean resultBean) {

    }

    @Override
    public void onData(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnGetsampleInformation(List<Task_SapmleBean> dicDataBeans) {

    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }
}

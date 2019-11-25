package com.monitor.changtian.activity.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.TaskPointFrequencyAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.TaskPointsFrequencyBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * 点位频次添加任务
 */
@EActivity(R.layout.activity_task_points_frequency_list)
public class TaskPointsFrequencyListActivity extends BaseActvity {

    @Extra
    String pointsId;
    @Extra
    String frequency;
    @Extra
    String taskid;

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    TaskPointFrequencyAdapter taskPointFrequencyAdapter;

    @AfterViews
    void init() {

        initImageBackText("点位频次界面");
        msg(taskid);
//        taskPointFrequencyAdapter = new TaskPointFrequencyAdapter(R.layout.task_points_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(taskPointFrequencyAdapter);
        initData();
        taskPointFrequencyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                /**
                 * 跳转到对应频次的样品列表
                 */
                SampleListInfoActivity_.intent(TaskPointsFrequencyListActivity.this)
                        .pointsid(pointsId)
                        .taskid(taskid)
                        .currentnumber((position + 1) + "").start();
            }
        });
    }

    public void initData() {
        int size = Integer.parseInt(frequency);
        List<TaskPointsFrequencyBean> taskPointsFrequencyBeans = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TaskPointsFrequencyBean taskPointsFrequencyBean = new TaskPointsFrequencyBean();
            taskPointsFrequencyBean.setName("第" + (i + 1) + "频次");
            taskPointsFrequencyBean.setPotinsName(pointsId);
            taskPointsFrequencyBeans.add(taskPointsFrequencyBean);
        }
        taskPointFrequencyAdapter.setNewData(taskPointsFrequencyBeans);
    }
}

package com.monitor.changtian.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.ScheduleAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.HomeRecordBean;
import com.monitor.changtian.bean.MessageBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.MessagePresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_task_pro)
public class TaskProActivity extends BaseActvity implements SubmitView<List<MessageBean>> {

    @Extra
    String SampleId;
    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    ScheduleAdapter scheduleAdapter;
    MessagePresenter messagePresenter;
    String type = "4";

    @AfterViews
    void init() {
        initImageBackText("项目进度详情");

        List<HomeRecordBean> homeRecordBeans = new ArrayList<>();
        scheduleAdapter = new ScheduleAdapter(R.layout.schedule_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(scheduleAdapter);
        messagePresenter = new MessagePresenter(this, this);
        initData();
        AddEmptyView(scheduleAdapter, rv_list);
        setEmptylistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    public void initData() {
        String data = "{loginName:\"" + MyApplication.getInstance().getUser() + "\",types:\"" + type + "\",SampleId:\"" + SampleId + "\",categoryid:\"\"}";
        messagePresenter.GetMsgList(data);

    }

    @Override
    public void onData(List<MessageBean> messageBeans) {
        scheduleAdapter.addData(messageBeans);
    }

    @Override
    public void onMessage(ResultBean t) {

    }

    @Override
    public void OnError(String s) {

    }
}

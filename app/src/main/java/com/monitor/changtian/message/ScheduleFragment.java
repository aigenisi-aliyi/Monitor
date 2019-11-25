package com.monitor.changtian.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.monitor.changtian.R;
import com.monitor.changtian.adapter.ScheduleAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.HomeRecordBean;
import com.monitor.changtian.bean.MessageBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.MessagePresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2018/5/14.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>    进度
 * Function:
 */

@EFragment(R.layout.fragment_schedule)
public class ScheduleFragment extends BaseFragment implements SubmitView<List<MessageBean>> {
    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    ScheduleAdapter scheduleAdapter;
    MessagePresenter messagePresenter;
    String type = "4";

    @AfterViews
    void init() {
        List<HomeRecordBean> homeRecordBeans = new ArrayList<>();
        scheduleAdapter = new ScheduleAdapter(R.layout.schedule_item);
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(scheduleAdapter);
        messagePresenter = new MessagePresenter(this, getActivity());
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
        String data = "{loginName:\"admin\",types:\"" + type + "\"}";
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

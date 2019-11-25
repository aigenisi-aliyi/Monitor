package com.monitor.changtian.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.monitor.changtian.R;
import com.monitor.changtian.adapter.TasksAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.MessageBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.MessagePresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by ken on 2018/5/14.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>             任务
 * Function:
 */

@EFragment(R.layout.fragment_task)
public class TaskFragment extends BaseFragment implements SubmitView<List<MessageBean>> {


    @ViewById(R.id.rv_list)
    RecyclerView rv_list;

    TasksAdapter tasksAdapter;

    MessagePresenter messagePresenter;

    String type = "1";

    @AfterViews
    void init() {
//        List<HomeRecordBean> homeRecordBeans = new ArrayList<>();
        tasksAdapter = new TasksAdapter(R.layout.tasks_item);
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(tasksAdapter);
        messagePresenter = new MessagePresenter(this, getActivity());
        initData();
        AddEmptyView(tasksAdapter, rv_list);
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

    //返回数据
    @Override
    public void onData(List<MessageBean> messageBeans) {
        tasksAdapter.addData(messageBeans);
    }

    @Override
    public void onMessage(ResultBean t) {

    }

    @Override
    public void OnError(String s) {

    }
}

package com.monitor.changtian.presenter;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.TaskListBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.Acc_TaskView;
import com.monitor.changtian.view.AddAccuseView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/19.
 */

public class Acc_TaskPresenter {


    private Acc_TaskView addAccuseView;

    public Acc_TaskPresenter(Acc_TaskView addAccuseView) {
        this.addAccuseView = addAccuseView;
    }


    //获取任务列表
    public void GetTaskList(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetTastList(data)
                .compose(Transformer.<List<TaskListBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<TaskListBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        addAccuseView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<TaskListBean> taskBeans) {
                        if (taskBeans != null) {
                            addAccuseView.OnAcc_TaskList(taskBeans);
                        }
                    }
                });

    }
}

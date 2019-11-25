package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.TaskInfoAllBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.TaskInfoAllView;

import java.util.List;

/**
 * Created by ken on 2018/8/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskInfoAllPresenter {


    private Dialog loading_dialog;
    private Context mContext;
    private TaskInfoAllView taskInfoAllView;

    public TaskInfoAllPresenter(TaskInfoAllView taskInfoAllView, Context mContext) {
        this.taskInfoAllView = taskInfoAllView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }

    public void GetTasksInfoAudit(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetTasksInfoAudit(data)
                .compose(Transformer.<List<TaskInfoAllBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<TaskInfoAllBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        loading_dialog.dismiss();
                        taskInfoAllView.OnError(s);
                    }
                    @Override
                    protected void onSuccess(List<TaskInfoAllBean> allBeans) {

                        if (allBeans != null) {
                            loading_dialog.dismiss();
                            taskInfoAllView.OnGetTasksInfoAudit(allBeans);
                        }
                    }
                });
    }
}

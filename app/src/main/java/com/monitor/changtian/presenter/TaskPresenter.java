package com.monitor.changtian.presenter;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.DetectionSchemeDataBean;
import com.monitor.changtian.bean.TaskBean;
import com.monitor.changtian.bean.TaskListBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.network.ApiService;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/5/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskPresenter extends BasePresenter {


    public TaskPresenter(SubmitView submitView, Context mContext) {
        super(submitView, mContext);
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
//                        loading_dialog.dismiss();
                        submitView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<TaskListBean> taskBeans) {
                        if (taskBeans != null) {
//                            loading_dialog.dismiss();
                            submitView.onData(taskBeans);
                        }
                    }
                });

    }

    //获取人员经纬度
    public void GetPosTask(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetPosTask(data)
                .compose(Transformer.<List<TaskBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<TaskBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        loading_dialog.dismiss();
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<TaskBean> taskBeans) {
                        if (taskBeans != null) {
                            loading_dialog.dismiss();
                            submitView.onData(taskBeans);
                        }
                    }
                });

    }


    /**
     * 获取检测方案
     */
    public void GetDetectionSchemeData(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetDetectionSchemeData(data)
                .compose(Transformer.<List<DetectionSchemeDataBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<DetectionSchemeDataBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        loading_dialog.dismiss();
                        ViseLog.d(s);
                        submitView.OnError(s);

                    }

                    @Override
                    protected void onSuccess(List<DetectionSchemeDataBean> detectionSchemeDataBeans) {
                        if (detectionSchemeDataBeans != null) {
                            loading_dialog.dismiss();
                            submitView.onData(detectionSchemeDataBeans);
                        }
                    }
                });
    }
}

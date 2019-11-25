package com.monitor.changtian.presenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.TasksListInfoBean;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.Task_SapmleBean;
import com.monitor.changtian.bean.TasksInfoDetailBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.TaskContractInfoView;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/7/5.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskContractInfoPresenter {


    private Dialog loading_dialog;
    private Context mContext;
    private TaskContractInfoView taskContractInfoView;

    public TaskContractInfoPresenter(TaskContractInfoView taskContractInfoView, Context mContext) {
        this.taskContractInfoView = taskContractInfoView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }



    public void GetsampleInformation(String data) {


        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetsampleInformation(data)
                .compose(Transformer.<List<Task_SapmleBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<Task_SapmleBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<Task_SapmleBean> tasksListInfoBeans) {
                        if (tasksListInfoBeans != null) {

                            taskContractInfoView.OnGetsampleInformation(tasksListInfoBeans);
                        }
                    }
                });
    }

    public void GetTasksInfo(String data) {


        RxHttpUtils
                .getSInstance()
                .writeTimeout(20)
                .readTimeout(20)
                .connectTimeout(20)
                .createSApi(ApiService.class)
                .GetTasksInfo(data)
                .compose(Transformer.<List<TasksListInfoBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<TasksListInfoBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<TasksListInfoBean> tasksListInfoBeans) {
                        if (tasksListInfoBeans != null) {

                            taskContractInfoView.OnTasksListInfo(tasksListInfoBeans);
                        }
                    }
                });
    }

    public void GetTasksInfoDetail(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetTasksInfoDetail(data)
                .compose(Transformer.<List<TasksInfoDetailBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<TasksInfoDetailBean>>() {
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
                    protected void onSuccess(List<TasksInfoDetailBean> tasksInfoDetailBeans) {
                        if (tasksInfoDetailBeans != null) {
                            loading_dialog.dismiss();
                            taskContractInfoView.OnTasksInfoDetail(tasksInfoDetailBeans);
                        }
                    }
                });
    }


    public void GetDetectionSchemeInfo(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetDetectionSchemeInfo(data)
                .compose(Transformer.<List<DetectionSchemeInfoBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<DetectionSchemeInfoBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        taskContractInfoView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<DetectionSchemeInfoBean> tasksInfoDetailBeans) {
                        if (tasksInfoDetailBeans != null) {
                            taskContractInfoView.OnGetDetectionSchemeInfo(tasksInfoDetailBeans);
                        }
                    }
                });
    }

    public void AddDetectionSchemeAudit(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddDetectionSchemeAudit(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        taskContractInfoView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {

                            taskContractInfoView.OnAddDetectionSchemeAudit(resultBean);
                        }
                    }
                });

    }


    public void GetDevData(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetDevData(data)
                .compose(Transformer.<List<DicDataBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<DicDataBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<DicDataBean> dicDataBeans) {
                        if (dicDataBeans != null) {

                            taskContractInfoView.onData(dicDataBeans);
                        }
                    }
                });
    }

    public void AddManagerTaskAudit(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddManagerTaskAudit(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        taskContractInfoView.OnError(s);
                    }
                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            loading_dialog.dismiss();
                            taskContractInfoView.OnAddDetectionSchemeAudit(resultBean);
                        }
                    }
                });
    }




    public void AddTaskEnd(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddTaskEnd(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
                        taskContractInfoView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            taskContractInfoView.OnAddDetectionSchemeAudit(resultBean);
                        }
                    }
                });

    }

}

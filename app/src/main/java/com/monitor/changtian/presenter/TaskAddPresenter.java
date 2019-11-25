package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.FactorsExperimenterDataBean;
import com.monitor.changtian.SchemeConsumablesBean;
import com.monitor.changtian.bean.CarDataListBean;
import com.monitor.changtian.bean.NoPointsTaskDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SamplingAssistDataBean;
import com.monitor.changtian.bean.SchemeDeviceBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.TaskAddView;
import com.vise.log.ViseLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ken on 2018/7/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskAddPresenter {

    private Dialog loading_dialog;
    private Context mContext;
    private TaskAddView taskAddView;

    public TaskAddPresenter(TaskAddView taskAddView, Context mContext) {
        this.taskAddView = taskAddView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }

    public void GetNoPointsTaskData(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetNoPointsTaskData(data)
                .compose(Transformer.<List<NoPointsTaskDataBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<NoPointsTaskDataBean>>() {
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
                    protected void onSuccess(List<NoPointsTaskDataBean> noPointsTaskDataBeans) {
                        if (noPointsTaskDataBeans != null) {
                            loading_dialog.dismiss();
                            taskAddView.OnNoPointsTaskData(noPointsTaskDataBeans);
                        }
                    }
                });
    }


    public void GetSamplingAssistData(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetSamplingAssistData(data)
                .compose(Transformer.<SamplingAssistDataBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<SamplingAssistDataBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return true;
                    }

                    @Override
                    protected void onError(String s) {
                        loading_dialog.dismiss();
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(SamplingAssistDataBean samplingAssistDataBean) {
                        if (samplingAssistDataBean != null) {
                            loading_dialog.dismiss();
                            taskAddView.OnSamplingAssistData(samplingAssistDataBean);
                        }
                    }
                });
    }


    public void GetFactorsExperimenterData(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetFactorsExperimenterData(data)
                .compose(Transformer.<List<FactorsExperimenterDataBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<FactorsExperimenterDataBean>>() {
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
                    protected void onSuccess(List<FactorsExperimenterDataBean> factorsExperimenterDataBeans) {
                        if (factorsExperimenterDataBeans != null) {
                            loading_dialog.dismiss();
                            taskAddView.OnFactorsExperimenterData(factorsExperimenterDataBeans);
                        }
                    }
                });
    }


    public void GetCarDataList(String data) {


        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetCarDataList(data)
                .compose(Transformer.<List<CarDataListBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<CarDataListBean>>() {
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
                    protected void onSuccess(List<CarDataListBean> carDataListBeans) {
                        if (carDataListBeans != null) {
                            loading_dialog.dismiss();
                            taskAddView.OnCarDataList(carDataListBeans);
                        }
                    }
                });
    }

    public void GetSchemeDevice(String data) {


        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetSchemeDevice(data)
                .compose(Transformer.<List<SchemeDeviceBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<SchemeDeviceBean>>() {
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
                    protected void onSuccess(List<SchemeDeviceBean> schemeDeviceBeans) {
                        if (schemeDeviceBeans != null) {
                            loading_dialog.dismiss();
                            taskAddView.OnSchemeDevice(schemeDeviceBeans);
                        }
                    }
                });
    }

    public void GetSchemeConsumables(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetSchemeConsumables(data)
                .compose(Transformer.<List<SchemeConsumablesBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<SchemeConsumablesBean>>() {
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
                    protected void onSuccess(List<SchemeConsumablesBean> schemeConsumablesBeans) {
                        if (schemeConsumablesBeans != null) {
                            loading_dialog.dismiss();
                            taskAddView.OnSchemeConsumables(schemeConsumablesBeans);
                        }
                    }
                });

    }


    /**
     * 提交任务
     */
    public void AddTasksInfo(String data) {

        Map<String, RequestBody> partMap = new HashMap<>();
        RequestBody dataBody = RequestBody.create(MediaType.parse("text/plain"), new String(data));
        partMap.put("data", dataBody);

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddTasksInfo(partMap)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        taskAddView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {

                            taskAddView.onSubmit(resultBean);
                        }
                    }
                });



    }
}

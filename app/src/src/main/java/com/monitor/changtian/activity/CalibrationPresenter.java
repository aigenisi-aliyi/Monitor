package com.monitor.changtian.activity;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.allen.library.utils.ToastUtils;
import com.monitor.changtian.bean.EquipInOutStockDataBean;
import com.monitor.changtian.bean.EquipStateBean;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.EquipsBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.presenter.ReceivePresenter;
import com.vise.log.ViseLog;

import java.util.List;

class CalibrationPresenter {
    private Dialog loading_dialog;
    private CalibrationView calibrationView;

    private ReceivePresenter receiveView;

    CalibrationPresenter(CalibrationView calibrationView, Context mContext) {
        this.calibrationView = calibrationView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
    }

    // 领取设备出库
    void AddEquipOutStock(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddEquipOutStock(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        calibrationView.OnError(s);
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            if (resultBean.getResult().equals("1")) {
                                ToastUtils.showToast("设备领取成功！");
                            } else {
                                ToastUtils.showToast(resultBean.getErrormessage());
                            }
                        }
                    }
                });

    }

    // 查看设备状态
    void getEquipmentState(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetEquipmentState(data)
                .compose(Transformer.<List<EquipStateBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<EquipStateBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        calibrationView.OnError(s);
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<EquipStateBean> equipStateBean) {
                     calibrationView.BarCodeDevState(equipStateBean);
                    }
                });

    }

    //获取设备列表
    void GetEquipmentData(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetEquipmentData(data)
                .compose(Transformer.<List<EquipmentDataBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<EquipmentDataBean>>() {
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
                    protected void onSuccess(List<EquipmentDataBean> dicDataBeans) {
                        if (dicDataBeans != null) {
                            loading_dialog.dismiss();
                            calibrationView.OnAllDevice(dicDataBeans);
                        }
                    }
                });

    }

    // 保存校准噪声记录
    void CalibrateSave(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .CalibrateSave(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        calibrationView.OnError(s);
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            calibrationView.SubmitVoice(resultBean);
                        }
                    }
                });

    }

    public void GetEquipInOutStockData(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetEquipInOutStockData(data)
                .compose(Transformer.<List<EquipInOutStockDataBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<EquipInOutStockDataBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
                        calibrationView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {

                        if (equipInOutStockDataBeans != null) {
                            calibrationView.OnQueryDevList(equipInOutStockDataBeans);
                        }
                    }
                });
    }
}

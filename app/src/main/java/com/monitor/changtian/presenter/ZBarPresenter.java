package com.monitor.changtian.presenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.activity.CalibrationView;
import com.monitor.changtian.activity.ZBarActivityView;
import com.monitor.changtian.bean.EquipStateBean;
import com.monitor.changtian.bean.EquipTypeBean;
import com.monitor.changtian.network.ApiService;
import com.vise.log.ViseLog;

import java.util.ArrayList;
import java.util.List;


public class ZBarPresenter {

    private CalibrationView calibrationView;
//    private ZBarActivityView view;

    public ZBarPresenter(CalibrationView calibrationView) {
        this.calibrationView = calibrationView;
    }

    public List<String> getTypes(List<String> types) {
        if (types == null) {
            return new ArrayList<String>();
        }
        types.add("2031");
        types.add("2071");
        types.add("2037");
        types.add("2050");
        types.add("3260");
        types.add("3012");
        types.add("QC-2B");
        types.add("2020");
        types.add("3030B");
        types.add("2040B");
        types.add("3072");
        types.add("6228");
        types.add("5688");
        types.add("5680");

//        types.add("GR-3030"); 不确定
//        types.add("3036"); 不用校准
//        types.add("3061"); 不用校
//        types.add("1210"); 不用校准
        return types;
    }


    // 查看设备状态

    /**
     * 设备类型接口
     *
     * @param data     参数
     * @param isReture 是否归还
     */
    public void getEquipmentType(String data) {
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
//        RxHttpUtils
//                .getSInstance()
//                .createSApi(ApiService.class)
//                .GetEquipmentType(data)
//                .compose(Transformer.<EquipTypeBean>switchSchedulers())
//                .subscribe(new CommonObserver<EquipTypeBean>() {
//                    @Override
//                    protected boolean isHideToast() {
//                        return false;
//                    }
//
//                    @Override
//                    protected void onError(String s) {
//                        view.onFailed(s);
//                        ViseLog.d(s);
//                    }
//
//                    @Override
//                    protected void onSuccess(EquipTypeBean bean) {
//                        view.onSuccess(bean,isReture);
//                    }
//                });
    }
}

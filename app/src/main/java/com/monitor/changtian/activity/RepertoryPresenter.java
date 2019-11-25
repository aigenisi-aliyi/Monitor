package com.monitor.changtian.activity;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.presenter.BasePresenter;
import com.monitor.changtian.view.RepertoryView;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/7/9.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class RepertoryPresenter extends BasePresenter {


    RepertoryView repertoryView;

    public RepertoryPresenter(RepertoryView repertoryView, SubmitView submitView, Context mContext) {
        super(submitView, mContext);
        this.repertoryView = repertoryView;
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
                        loading_dialog.dismiss();
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<DicDataBean> dicDataBeans) {
                        if (dicDataBeans != null) {
                            loading_dialog.dismiss();
                            repertoryView.OnDicDataBean(dicDataBeans);
                        }
                    }
                });
    }

    public void GetEquipmentData(String data) {
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
                            submitView.onData(dicDataBeans);
                        }
                    }
                });

    }


    public void GetConsumablesData(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetConsumablesData(data)
                .compose(Transformer.<List<EquipmentDataBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<EquipmentDataBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
//                        loading_dialog.dismiss();
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<EquipmentDataBean> dicDataBeans) {
                        if (dicDataBeans != null) {
//                            loading_dialog.dismiss();
                            submitView.onData(dicDataBeans);
                        }
                    }
                });

    }


}

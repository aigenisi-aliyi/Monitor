package com.monitor.changtian.presenter;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.PreDataBean;
import com.monitor.changtian.bean.PreInfoDataBean;
import com.monitor.changtian.bean.SampleInfoByPointInfoBean;
import com.monitor.changtian.bean.SchemeFidsBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.AddAccuseView;
import com.monitor.changtian.view.QueryBasicsView;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by Administrator on 2018/10/15.
 */

public class AddAccusePresenter {

    private AddAccuseView addAccuseView;
    public AddAccusePresenter(AddAccuseView addAccuseView) {
        this.addAccuseView = addAccuseView;
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

                        addAccuseView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<DicDataBean> dicDataBeans) {
                        if (dicDataBeans != null) {
                            addAccuseView.Onbasicslist_quality(dicDataBeans);
                        }
                    }
                });
    }


    public void GetSampleInfoByPointInfo(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetSampleInfoByPointInfo(data)
                .compose(Transformer.<SampleInfoByPointInfoBean>switchSchedulers())
                .subscribe(new CommonObserver<SampleInfoByPointInfoBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        addAccuseView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(SampleInfoByPointInfoBean sampleInfoByPointInfoBean) {
                        if (sampleInfoByPointInfoBean != null) {
                            addAccuseView.OnSampleInfoByPointInfo(sampleInfoByPointInfoBean);
                        }
                    }
                });
    }



    public void GetPreData(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetPreData(data)
                .compose(Transformer.<PreDataBean>switchSchedulers())
                .subscribe(new CommonObserver<PreDataBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        addAccuseView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(PreDataBean preDataBean) {
                        if (preDataBean != null) {
                            addAccuseView.OnGetPreData(preDataBean);
                        }
                    }
                });
    }


    public void OnGetPreInfoData(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetPreData4(data)
                .compose(Transformer.<List<PreInfoDataBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<PreInfoDataBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        addAccuseView.OnError(s);
                    }
                    @Override
                    protected void onSuccess(List<PreInfoDataBean> preDataBean) {
                        if (preDataBean != null) {
                            addAccuseView.OnGetPreInfoData(preDataBean);
                        }
                    }
                });
    }


    public void GetSchemeFids(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetSchemeFids(data)
                .compose(Transformer.<List<SchemeFidsBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<SchemeFidsBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        addAccuseView.OnError(s);
                    }
                    @Override
                    protected void onSuccess(List<SchemeFidsBean> preDataBean) {
                        if (preDataBean != null) {
                            addAccuseView.OnSchemeFidsData(preDataBean);
                        }
                    }
                });
    }
}

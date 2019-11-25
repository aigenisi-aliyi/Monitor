package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.network.ApiService;
import com.vise.log.ViseLog;

/**
 * Created by Administrator on 2018/11/12.
 */

public class MainPresenter {

    public SubmitView submitView;

    public MainPresenter(SubmitView submitView) {
        this.submitView = submitView;
    }

    //登录
    public void VerificationTockenInfo(String data) {


        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .VerificationTockenInfo(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        submitView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            submitView.onMessage(resultBean);
                        }
                    }
                });
    }
}

package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.MessageBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.network.ApiService;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/5/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class MessagePresenter {


    private Dialog loading_dialog;
    private Context mContext;
    private SubmitView submitView;

    public MessagePresenter(SubmitView submitView, Context mContext) {
        this.submitView = submitView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }

    public void GetMsgList(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetMsgList(data)
                .compose(Transformer.<List<MessageBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<MessageBean>>() {
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
                    protected void onSuccess(List<MessageBean> sampleWaterDataBeans) {
                        ViseLog.d(sampleWaterDataBeans.size());

                        if (sampleWaterDataBeans != null) {
                            loading_dialog.dismiss();
                            submitView.onData(sampleWaterDataBeans);
                        }
                    }
                });
    }
}

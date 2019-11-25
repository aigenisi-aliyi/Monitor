package com.monitor.changtian.presenter;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SampleInfoCompreData;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.network.ApiService;
import com.vise.log.ViseLog;

/**
 * Created by ken on 2018/6/1.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class AuditorPresenter extends BasePresenter {
    public AuditorPresenter(SubmitView submitView, Context mContext) {
        super(submitView, mContext);
    }

    public void GetSampleInfoCompreData(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetSampleInfoCompreData(data)
                .compose(Transformer.<SampleInfoCompreData>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<SampleInfoCompreData>() {
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
                    protected void onSuccess(SampleInfoCompreData sampleInfoCompreData) {
                        if (sampleInfoCompreData != null) {
                            loading_dialog.dismiss();
                            submitView.onData(sampleInfoCompreData);
                        }
                    }
                });
    }

    public void AddSampleAudit(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddSampleAudit(data)
                .compose(Transformer.<ResultBean>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<ResultBean>() {
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
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            loading_dialog.dismiss();
                            submitView.onMessage(resultBean);
                        }
                    }
                });
    }
}

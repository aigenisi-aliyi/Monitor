package com.monitor.changtian.presenter;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.SampleDetailsAndItemsData;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.network.ApiService;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/5/18.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class HomeRecordPresenter  extends BasePresenter{

    public HomeRecordPresenter(SubmitView submitView, Context mContext) {
        super(submitView, mContext);
    }


    public void GetSampleDetailsAndItemsData(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetSampleDetailsAndItemsData(data)
                .compose(Transformer.<List<SampleDetailsAndItemsData>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<SampleDetailsAndItemsData>>() {
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
                    protected void onSuccess(List<SampleDetailsAndItemsData> sampleInfoCompreData) {
                        if (sampleInfoCompreData != null) {
                            loading_dialog.dismiss();
                            submitView.onData(sampleInfoCompreData);
                        }
                    }
                });
    }


}

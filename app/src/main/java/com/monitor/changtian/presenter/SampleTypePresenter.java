package com.monitor.changtian.presenter;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.SampleTypeBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.SampleTypeView;

/**
 * Created by ken on 2018/9/29.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * へ　　　　／|
 * 　　/＼7　　∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　 │　　　　　ヽ　　 /　　〉
 * 　Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */

public class SampleTypePresenter {


    private SampleTypeView sampleTypeView;
    private Context mContext;

    public SampleTypePresenter(SampleTypeView sampleTypeView, Context mContext) {
        this.sampleTypeView = sampleTypeView;
        this.mContext = mContext;
    }


    public void GetsampleInfoBysamplecode(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetsampleInfoBysamplecode(data)
                .compose(Transformer.<SampleTypeBean>switchSchedulers())
                .subscribe(new CommonObserver<SampleTypeBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        sampleTypeView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(SampleTypeBean sampleTypeBean) {
                        if (sampleTypeBean != null) {
                            sampleTypeView.OnSampleType(sampleTypeBean);
                        }
                    }
                });
    }


}

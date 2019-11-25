package com.monitor.changtian.presenter;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.FinanceSampleView;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/9/7.
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

public class FinanceSamplePresenter  {

    private Context mContext;
    private FinanceSampleView financeSampleView;

    public FinanceSamplePresenter(FinanceSampleView financeSampleView, Context mContext) {
        this.financeSampleView = financeSampleView;
        this.mContext = mContext;
    }

    public void GetDetectionSchemeInfo(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetDetectionSchemeInfo(data)
                .compose(Transformer.<List<DetectionSchemeInfoBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<DetectionSchemeInfoBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
                        financeSampleView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<DetectionSchemeInfoBean> tasksInfoDetailBeans) {
                        if (tasksInfoDetailBeans != null) {
                            financeSampleView.OnGetDetectionSchemeInfo(tasksInfoDetailBeans);
                        }
                    }
                });
    }
}

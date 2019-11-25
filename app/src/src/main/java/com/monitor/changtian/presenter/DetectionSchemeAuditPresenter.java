package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.DetectionSchemeBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.DetectionSchemeView;

import java.util.List;

/**
 * Created by ken on 2018/7/25.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class DetectionSchemeAuditPresenter   {

    private DetectionSchemeView detectionSchemeView;
    private Dialog loading_dialog;
    private Context mContext;

    public DetectionSchemeAuditPresenter(DetectionSchemeView detectionSchemeView, Context mContext) {
        this.detectionSchemeView = detectionSchemeView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }
    /**
     * 查询合同
     */
    public void GetDetectionScheme(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetDetectionScheme(data)
                .compose(Transformer.<List<DetectionSchemeBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<DetectionSchemeBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return true;
                    }

                    @Override
                    protected void onError(String s) {
                        loading_dialog.dismiss();
                        detectionSchemeView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<DetectionSchemeBean> detectionSchemeBeans) {
                        if (detectionSchemeBeans != null) {
                            loading_dialog.dismiss();
                            detectionSchemeView.OnDetectionSchemeList(detectionSchemeBeans);
                        }
                    }
                });
    }
}

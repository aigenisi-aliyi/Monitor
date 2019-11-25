package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.GetfieldsamplingDetailListBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.SampleProofreadView;

import java.util.List;

/**
 * Created by ken on 2018/8/14.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SampleProofreadPresenter {
    private Dialog loading_dialog;
    private Context mContext;
    private SampleProofreadView sampleProofreadView;

    public SampleProofreadPresenter(SampleProofreadView sampleProofreadView, Context mContext) {
        this.sampleProofreadView = sampleProofreadView;
        this.mContext = mContext;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
    }


    //查询采样类型
    public void fieldsamplingDetailList(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .fieldsamplingDetailList(data)
                .compose(Transformer.<List<GetfieldsamplingDetailListBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<GetfieldsamplingDetailListBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        sampleProofreadView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<GetfieldsamplingDetailListBean> sampleWaterDataBeans) {
//                        ViseLog.d(sampleWaterDataBeans.size());

                        if (sampleWaterDataBeans != null) {
                            sampleProofreadView.OnList(sampleWaterDataBeans);
                        }
                    }
                });
    }


    public void proofreadingsamplingdetail(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .proofreadingsamplingdetail(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                       sampleProofreadView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {

                        if (resultBean != null) {

                            sampleProofreadView.OnMessage(resultBean);
                        }
                    }
                });
    }

    public void auditsamplingdetail(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .auditsamplingdetail(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        sampleProofreadView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {

                        if (resultBean != null) {

                            sampleProofreadView.OnMessage(resultBean);
                        }
                    }
                });
    }


}

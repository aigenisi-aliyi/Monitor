package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.GetqualitycontrolBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.AccuseView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ken on 2018/8/2.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class AccusePresenter {

    private Dialog loading_dialog;
    private Context mContext;
    private AccuseView accuseView;

    public AccusePresenter(AccuseView accuseView, Context mContext) {
        this.accuseView = accuseView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }

    public void Getqualitycontrol(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .Getqualitycontrol(data)
                .compose(Transformer.<List<GetqualitycontrolBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<GetqualitycontrolBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        accuseView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<GetqualitycontrolBean> getqualitycontrolBeans) {
                        if (getqualitycontrolBeans != null) {

                            accuseView.Getqualitycontrol(getqualitycontrolBeans);
                        }
                    }
                });
    }
    public void Addqualitycontrol(String data){

        Map<String, RequestBody> partMap = new HashMap<>();
        RequestBody dataBody = RequestBody.create(MediaType.parse("text/plain"), new String(data));
        partMap.put("data", dataBody);
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .Addqualitycontrol(partMap)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        accuseView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            loading_dialog.dismiss();
                            accuseView.Addqualitycontrol(resultBean);
                        }
                    }
                });
    }


}

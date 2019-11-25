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
 * Created by ken on 2018/5/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ForgetpwdPresenter {

    private Dialog loading_dialog;
    private Context mContext;
    private SubmitView submitView;

    public ForgetpwdPresenter(SubmitView submitView, Context mContext) {
        this.submitView = submitView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }
    //修改密码
    public void ModifyUserPwd(String data){

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .ModifyUserPwd(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }
                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
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

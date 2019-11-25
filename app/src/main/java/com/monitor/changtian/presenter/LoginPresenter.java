package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.UserInfo;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.network.ApiService;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/5/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class LoginPresenter extends BasePresenter {
    public LoginPresenter(SubmitView submitView, Context mContext) {
        super(submitView, mContext);
    }

    //登录
    public void UserLogin(String data, final Dialog dialog) {


        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .UserLogin(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        dialog.dismiss();
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

    //获取用户名信息

    public void GetUserInfo(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetUserInfo(data)
                .compose(Transformer.<List<UserInfo>>switchSchedulers())
                .subscribe(new CommonObserver<List<UserInfo>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<UserInfo> userInfos) {
                        if (userInfos != null) {

                            submitView.onData(userInfos);
                        }
                    }
                });
    }
}

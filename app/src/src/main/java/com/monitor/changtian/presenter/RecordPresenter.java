package com.monitor.changtian.presenter;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.network.ApiService;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/6/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class RecordPresenter extends BasePresenter {
    public RecordPresenter(SubmitView submitView, Context mContext) {
        super(submitView, mContext);
    }

    //获取审核人列表
    public void GetAllUserInfo(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetAllUserInfo(data)
                .compose(Transformer.<List<AllUserInfo>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<AllUserInfo>>() {
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
                    protected void onSuccess(List<AllUserInfo> allUserInfos) {
                        if (allUserInfos != null) {
                            loading_dialog.dismiss();
                            submitView.onData(allUserInfos);
                        }
                    }
                });
    }

    //提交审核
    public void SubmitSampleAudit(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .SubmitSampleAudit(data)
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

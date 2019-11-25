package com.monitor.changtian.presenter;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.ProjectcontractBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.network.ApiService;

import java.util.List;

/**
 * Created by ken on 2018/6/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class FinanceMainPresenter extends BasePresenter {
    public FinanceMainPresenter(SubmitView submitView, Context mContext) {
        super(submitView, mContext);
    }

    /**
     * 查询合同
     */
    public void Getprojectcontract(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .Getprojectcontract(data)
                .compose(Transformer.<List<ProjectcontractBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<ProjectcontractBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        loading_dialog.dismiss();
                        submitView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<ProjectcontractBean> projectcontractBeans) {
                        if (projectcontractBeans != null) {
                            loading_dialog.dismiss();
                            submitView.onData(projectcontractBeans);
                        }
                    }
                });
    }

}

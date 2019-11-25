package com.monitor.changtian.presenter;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.ProjectcontractBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.FinanceMainView;

import java.util.List;

/**
 * Created by ken on 2018/9/25.
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

public class FinanceMainsPresenter {

    public FinanceMainView financeMainView;
    public Context mContext;

    public FinanceMainsPresenter(FinanceMainView financeMainView, Context mContext) {
        this.financeMainView = financeMainView;
        this.mContext = mContext;

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
//                        loading_dialog.dismiss();
                        financeMainView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<ProjectcontractBean> projectcontractBeans) {
                        if (projectcontractBeans != null) {
                            financeMainView.onFinance(projectcontractBeans);
                        }
                    }
                });
    }
}

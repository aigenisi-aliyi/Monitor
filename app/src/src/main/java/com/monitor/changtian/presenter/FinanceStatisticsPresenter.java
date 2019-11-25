package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.StatisticsMoneyQueryBean;
import com.monitor.changtian.bean.StatisticsQueryBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.FinanceStatisticsView;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/6/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class FinanceStatisticsPresenter {

    private Dialog loading_dialog;
    private Context mContext;
    private FinanceStatisticsView financeStatisticsView;

    public FinanceStatisticsPresenter(FinanceStatisticsView financeStatisticsView, Context mContext) {
        this.financeStatisticsView = financeStatisticsView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }

    public void StatisticsQuery1() {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .StatisticsQuery1()
                .compose(Transformer.<StatisticsQueryBean>switchSchedulers())
                .subscribe(new CommonObserver<StatisticsQueryBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(StatisticsQueryBean statisticsQueryBean) {

                        if (statisticsQueryBean != null) {
                            financeStatisticsView.OnStatisticsQueryBean(statisticsQueryBean);
                        }
                    }
                });
    }

    public void StatisticsQuery(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .StatisticsQuery(data)
                .compose(Transformer.<List<StatisticsMoneyQueryBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<StatisticsMoneyQueryBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
                        financeStatisticsView.onError(s);
                    }

                    @Override
                    protected void onSuccess(List<StatisticsMoneyQueryBean> statisticsMoneyQueryBeanList) {

                        if (statisticsMoneyQueryBeanList != null) {
                            financeStatisticsView.StatisticsMoneyQueryBean(statisticsMoneyQueryBeanList);
                        }
                    }
                });
    }
}

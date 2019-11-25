package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.BuyWayBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.BaseEditView;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/6/26.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class BaseEditPresenter {

    private Dialog loading_dialog;
    private Context mContext;
    private BaseEditView<List<BuyWayBean>> baseEditView;

    public BaseEditPresenter(BaseEditView<List<BuyWayBean>> baseEditView, Context mContext) {
        this.baseEditView = baseEditView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }

    /**
     * 查询
     */
    public void GetBaseData(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetBaseData(data)
                .compose(Transformer.<List<BuyWayBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<BuyWayBean>>() {
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
                    protected void onSuccess(List<BuyWayBean> buyWayBeans) {
                        if (buyWayBeans != null) {
                            loading_dialog.dismiss();
                            baseEditView.Getinfo(buyWayBeans);
                        }
                    }
                });
    }

    /**
     * 增加
     */
    public void AddBaseData(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddBaseData(data)
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
                            baseEditView.Addinfo(resultBean);
                        }
                    }
                });
    }

    /**
     * 删除
     */
    public void DeleteDicData(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .DeleteDicData(data)
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
                            baseEditView.Remoinfo(resultBean);
                        }
                    }
                });
    }

}

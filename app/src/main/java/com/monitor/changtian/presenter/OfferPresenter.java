package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.GetSchemePriceDetailListBean;
import com.monitor.changtian.bean.GetSchemeQuotationInfoBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.OfferView;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/7/30.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class OfferPresenter {

    private OfferView offerView;
    private Dialog loading_dialog;
    private Context mContext;

    public OfferPresenter(OfferView offerView, Context mContext) {
        this.offerView = offerView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }

    /**
     * 查询列表
     *
     * @param data
     */
    public void GetSchemeQuotationInfo(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetSchemeQuotationInfo(data)
                .compose(Transformer.<List<GetSchemeQuotationInfoBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<GetSchemeQuotationInfoBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
                        offerView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<GetSchemeQuotationInfoBean> getSchemeQuotationInfoBeans) {
                        if (getSchemeQuotationInfoBeans != null) {
                            offerView.OnOfferList(getSchemeQuotationInfoBeans);
                        }
                    }
                });
    }

    /**
     * 查询详情
     */
    public void GetSchemePriceDetailList(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetSchemePriceDetailList(data)
                .compose(Transformer.<List<GetSchemePriceDetailListBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<GetSchemePriceDetailListBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        offerView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<GetSchemePriceDetailListBean> getSchemePriceDetailListBeans) {
                        if (getSchemePriceDetailListBeans != null) {
                            offerView.OnOffertrialinfo(getSchemePriceDetailListBeans);
                        }
                    }
                });


    }

    /**
     * 初步审核
     */
    public void GetOffer(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetOffer(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        offerView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            offerView.OnOfferTrial(resultBean);
                        }
                    }
                });

    }
    /**
     * 复审
     */
    public void GetOffered(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetOffered(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        offerView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            offerView.OnOfferTrial(resultBean);
                        }
                    }
                });

    }
}

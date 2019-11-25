package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.EquipInOutStockDataBean;
import com.monitor.changtian.bean.GetConsumablesDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.ReceiveView;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/8/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ReceivePresenter {

    private Dialog loading_dialog;
    private Context mContext;
    private ReceiveView receiveView;

    public ReceivePresenter(ReceiveView receiveView, Context mContext) {
        this.receiveView = receiveView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }

    public void AddEquipOutStock(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddEquipOutStock(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        receiveView.OnError(s);
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            receiveView.OnReceiveSubmit(resultBean);
                        }
                    }
                });

    }

    public void AddEquipInStock(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddEquipInStock(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        receiveView.OnError(s);
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            receiveView.OnReceiveSubmit(resultBean);
                        }
                    }
                });

    }



    public void AddConsumablesOutStock(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddConsumablesOutStock(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            receiveView.OnReceiveSubmit(resultBean);
                        }
                    }
                });
    }

    public void AddConsumablesInStock(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddConsumablesInStock(data)
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
                            receiveView.OnReceiveSubmit(resultBean);
                        }
                    }
                });
    }

    public void GetConsumablesDataByTaskid(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetConsumablesDataByTaskid(data)
                .compose(Transformer.<List<GetConsumablesDataBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<GetConsumablesDataBean>>() {
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
                    protected void onSuccess(List<GetConsumablesDataBean> consumablesDataBeans) {
                        if (consumablesDataBeans != null) {
                            loading_dialog.dismiss();
                            receiveView.OnQuerConsumablesDataBean(consumablesDataBeans);
                        }
                    }
                });
    }

    public void GetEquipInOutStockData(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetEquipInOutStockData(data)
                .compose(Transformer.<List<EquipInOutStockDataBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<EquipInOutStockDataBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
                        receiveView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {

                        if (equipInOutStockDataBeans != null) {
                            receiveView.OnQueryDevList(equipInOutStockDataBeans);
                        }
                    }
                });
    }

    public void GetConsumablesInOutStockOptionData(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetConsumablesInOutStockOptionData(data)
                .compose(Transformer.<List<EquipInOutStockDataBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<EquipInOutStockDataBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
                        receiveView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {

                        if (equipInOutStockDataBeans != null) {
                            receiveView.OnQueryConList(equipInOutStockDataBeans);
                        }
                    }
                });
    }
}

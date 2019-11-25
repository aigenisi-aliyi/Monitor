package com.monitor.changtian.presenter;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.FinanceListBean;
import com.monitor.changtian.bean.MoneyRecordBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.network.ApiService;
import com.vise.log.ViseLog;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ken on 2018/6/26.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class FinancePresenter extends BasePresenter {


    public FinancePresenter(SubmitView submitView, Context mContext) {
        super(submitView, mContext);
    }

    /**
     * 查询财务信息列表
     */
    public void GetProjectData(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetProjectData(data)
                .compose(Transformer.<List<FinanceListBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<FinanceListBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        loading_dialog.dismiss();
                        ViseLog.d(s);
                        submitView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<FinanceListBean> financeListBeans) {
                        if (financeListBeans != null) {
                            loading_dialog.dismiss();
                            submitView.onData(financeListBeans);
                        }
                    }
                });
    }

    /**
     * 确认打款
     */
    public void AddMoneyRecord(String loginId, String projectid, String receivetype,
                               String receivemoney, String PaymentDate,
                               String remark, List<File> picPath) {


        Map<String, RequestBody> partMap = new HashMap<>();
        RequestBody dataBody = RequestBody.create(MediaType.parse("text/plain"), new String(loginId));
        partMap.put("loginId", dataBody);

        RequestBody dataBody1 = RequestBody.create(MediaType.parse("text/plain"), new String(projectid));
        partMap.put("contractid", dataBody1);

        RequestBody dataBody2 = RequestBody.create(MediaType.parse("text/plain"), new String(receivetype));
        partMap.put("receivetype", dataBody2);


        RequestBody dataBody3 = RequestBody.create(MediaType.parse("text/plain"), new String(receivemoney));
        partMap.put("receivemoney", dataBody3);


        RequestBody dataBody4 = RequestBody.create(MediaType.parse("text/plain"), new String(PaymentDate));
        partMap.put("PaymentDate", dataBody4);

        RequestBody dataBody5 = RequestBody.create(MediaType.parse("text/plain"), new String(remark));
        partMap.put("remark", dataBody5);


        for (int i = 0; i < picPath.size(); i++) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), picPath.get(i));
            partMap.put("file" + i + "\"; filename=\"" + picPath.get(i).getName() + "\"", fileBody);
        }


        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddMoneyRecord(partMap)
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
                            submitView.onMessage(resultBean);
                        }
                    }
                });
    }

    /**
     * 查看明细
     */
    public void GetMoneyRecord(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetMoneyRecord(data)
                .compose(Transformer.<List<MoneyRecordBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<MoneyRecordBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        loading_dialog.dismiss();
                        ViseLog.d(s);
                        submitView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<MoneyRecordBean> moneyRecordBeans) {
                        if (moneyRecordBeans != null) {
                            loading_dialog.dismiss();
                            submitView.onData(moneyRecordBeans);
                        }
                    }
                });
    }

}

package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.QueryBasicsView;
import com.vise.log.ViseLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2018/7/12.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class QueryBasicsPresenter {

    private Dialog loading_dialog;
    private Context mContext;
    private QueryBasicsView queryBasicsView;

    List<ResultBean.RolejarryBean> rolejarryBeans = new ArrayList<>();

    public QueryBasicsPresenter(QueryBasicsView queryBasicsView, Context mContext) {
        this.queryBasicsView = queryBasicsView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }

    public List<ResultBean.RolejarryBean> getRolejarryBeans() {
        return rolejarryBeans;
    }

    public void setRolejarryBeans(List<ResultBean.RolejarryBean> rolejarryBeans) {
        this.rolejarryBeans = rolejarryBeans;
    }


    //获取审核人列表
    public void GetAllUserInfo(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetAllUserInfo(data)
                .compose(Transformer.<List<AllUserInfo>>switchSchedulers())
                .subscribe(new CommonObserver<List<AllUserInfo>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                    }

                    @Override
                    protected void onSuccess(List<AllUserInfo> allUserInfos) {
                        if (allUserInfos != null) {
                            queryBasicsView.OnAllPerson(allUserInfos);
                        }
                    }
                });
    }

    public void GetEquipmentData(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetEquipmentData(data)
                .compose(Transformer.<List<EquipmentDataBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<EquipmentDataBean>>() {
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
                    protected void onSuccess(List<EquipmentDataBean> dicDataBeans) {
                        if (dicDataBeans != null) {
                            loading_dialog.dismiss();
                            queryBasicsView.OnAllDevice(dicDataBeans);
                        }
                    }
                });

    }

    // 保存校准噪声记录
    public void CalibrateSave(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .CalibrateSave(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        queryBasicsView.OnError(s);
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            queryBasicsView.SubmitVoice(resultBean);
                        }
                    }
                });

    }


}

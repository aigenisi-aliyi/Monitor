package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.GetsamplemanageInfoBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.RoomListBean;
import com.monitor.changtian.bean.SampleInfoByAssignmentRecordBean;
import com.monitor.changtian.bean.SampleStatusBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.SampleView;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by ken on 2018/8/10.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SamplePresenter {
    private Dialog loading_dialog;
    public Context mContext;
    public SampleView sampleView;

    public SamplePresenter(SampleView sampleView, Context mContext) {
        this.sampleView = sampleView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }

    public void GetsamplemanageInfo(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetsamplemanageInfo(data)
                .compose(Transformer.<List<GetsamplemanageInfoBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<GetsamplemanageInfoBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        sampleView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<GetsamplemanageInfoBean> getsamplemanageInfoBeans) {
                        if (getsamplemanageInfoBeans != null) {
                            sampleView.OnGetsamplemanageInfo(getsamplemanageInfoBeans);
                        }
                    }
                });
    }

    public void GetRoomList(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetRoomList(data)
                .compose(Transformer.<List<RoomListBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<RoomListBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        sampleView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<RoomListBean> roomListBeans) {
                        if (roomListBeans != null) {
                            sampleView.OnRoomList(roomListBeans);
                        }
                    }
                });
    }

    public void Addsamplemanage(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .Addsamplemanage(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        sampleView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            sampleView.OnMessage(resultBean);
                        }
                    }
                });
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
                        sampleView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<AllUserInfo> allUserInfos) {
                        if (allUserInfos != null) {
                            sampleView.OnAllPerson(allUserInfos);
                        }
                    }
                });
    }

    public void GetsampleInfoByonlynumber(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetsampleInfoByonlynumber(data)
                .compose(Transformer.<SampleStatusBean>switchSchedulers())
                .subscribe(new CommonObserver<SampleStatusBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        loading_dialog.dismiss();
                        sampleView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(SampleStatusBean sampleStatusBeans) {
                        if (sampleStatusBeans != null) {
                            loading_dialog.dismiss();
                            sampleView.OnSampleStatus(sampleStatusBeans);
                        }
                    }
                });
    }


    public void Addsamplerejection(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .Addsamplerejection(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        sampleView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            sampleView.OnMessage(resultBean);
                        }
                    }
                });
    }


    public void GetSampleInfoByAssignmentRecord(String data){


        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetSampleInfoByAssignmentRecord(data)
                .compose(Transformer.<SampleInfoByAssignmentRecordBean>switchSchedulers())
                .subscribe(new CommonObserver<SampleInfoByAssignmentRecordBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        sampleView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(SampleInfoByAssignmentRecordBean resultBean) {
                        if (resultBean != null) {
                            sampleView.OnGetSampleInfoByAssignmentRecord(resultBean);
                        }
                    }
                });
    }

    public void CollarSample(String data){

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .CollarSample(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        sampleView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {

                        ViseLog.d("sssss");
                        if (resultBean != null) {
                            sampleView.OnMessage(resultBean);
                        }
                    }
                });
    }
}

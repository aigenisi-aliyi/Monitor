package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.luck.picture.lib.compress.Luban;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SampleCategoryItemsDataBean;
import com.monitor.changtian.bean.SampleInfoDataBean;
import com.monitor.changtian.bean.SampleType;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.AddView;
import com.vise.log.ViseLog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
//import top.zibin.luban.Luban;

/**
 * Created by ken on 2018/5/17.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:   录入信息
 */

public class AddPresenter {
    private Dialog loading_dialog;
    private Context mContext;
    private AddView addView;

    public AddPresenter(AddView addView, Context mContext) {
        this.addView = addView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }


    //查询采样类型
    public void GetDicData(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetDicData(data)
                .compose(Transformer.<List<SampleType>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<SampleType>>() {
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
                    protected void onSuccess(List<SampleType> sampleWaterDataBeans) {
                        ViseLog.d(sampleWaterDataBeans.size());

                        if (sampleWaterDataBeans != null) {
                            loading_dialog.dismiss();
                            addView.onTypeData(sampleWaterDataBeans);
                        }
                    }
                });
    }

    /**
     * 根据类型查询项目信息
     */
    public void GetSampleInfoData(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetSampleInfoData(data)
                .compose(Transformer.<List<SampleInfoDataBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<SampleInfoDataBean>>() {
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
                    protected void onSuccess(List<SampleInfoDataBean> sampleWaterDataBeans) {
                        ViseLog.d(sampleWaterDataBeans.size());

                        if (sampleWaterDataBeans != null) {
                            loading_dialog.dismiss();
                            addView.GetSampleInfoData(sampleWaterDataBeans);
                        }
                    }
                });

    }

    /**
     * 查询对应的列表信息
     */
    public void GetSampleCategoryItemsData(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetSampleCategoryItemsData(data)
                .compose(Transformer.<List<SampleCategoryItemsDataBean>>switchSchedulers(loading_dialog))
                .subscribe(new CommonObserver<List<SampleCategoryItemsDataBean>>() {
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
                    protected void onSuccess(List<SampleCategoryItemsDataBean> sampleWaterDataBeans) {
                        ViseLog.d(sampleWaterDataBeans.size());

                        if (sampleWaterDataBeans != null) {
                            loading_dialog.dismiss();
                            addView.GetSampleCategoryItemsData(sampleWaterDataBeans);
                        }
                    }
                });
    }

    //
//    //查询水质采样信息
//    public void GetSampleWaterData(String data) {
//
//        RxHttpUtils
//                .getSInstance()
//                .createSApi(ApiService.class)
//                .GetSampleWaterData(data)
//                .compose(Transformer.<List<SampleWaterDataBean>>switchSchedulers(loading_dialog))
//                .subscribe(new CommonObserver<List<SampleWaterDataBean>>() {
//                    @Override
//                    protected boolean isHideToast() {
//                        return false;
//                    }
//
//                    @Override
//                    protected void onError(String s) {
//                        loading_dialog.dismiss();
//                        ViseLog.d(s);
//                    }
//
//                    @Override
//                    protected void onSuccess(List<SampleWaterDataBean> sampleWaterDataBeans) {
//                        ViseLog.d(sampleWaterDataBeans.size());
//
//                        if (sampleWaterDataBeans != null) {
//                            loading_dialog.dismiss();
//                            submitView.onData(sampleWaterDataBeans);
//                        }
//                    }
//                });
//
//    }
//
//    //查询气体信息
//    public void GetSampleMeteorologyData(String data) {
//
//        RxHttpUtils
//                .getSInstance()
//                .createSApi(ApiService.class)
//                .GetSampleMeteorologyData(data)
//                .compose(Transformer.<List<SampleWaterDataBean>>switchSchedulers(loading_dialog))
//                .subscribe(new CommonObserver<List<SampleWaterDataBean>>() {
//                    @Override
//                    protected boolean isHideToast() {
//                        return false;
//                    }
//
//                    @Override
//                    protected void onError(String s) {
//                        loading_dialog.dismiss();
//                        ViseLog.d(s);
//                    }
//
//                    @Override
//                    protected void onSuccess(List<SampleWaterDataBean> sampleWaterDataBeans) {
//                        ViseLog.d(sampleWaterDataBeans.size());
//                        if (sampleWaterDataBeans != null) {
//                            loading_dialog.dismiss();
//                            submitView.onData(sampleWaterDataBeans);
//                        }
//                    }
//                });
//
//    }
//
    public void submitInfo(final String data, List<File> picPath, final int type) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < picPath.size(); i++) {
            strings.add(picPath.get(i).getAbsolutePath());
        }
        Flowable.just(strings)
                .observeOn(Schedulers.io())
                .map(new Function<List<String>, List<File>>() {
                    @Override
                    public List<File> apply(@NonNull List<String> list) throws Exception {
                        return Luban.with(MyApplication.getInstance()).load(list).get();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<File>>() {
                    @Override
                    public void accept(@NonNull List<File> list) throws Exception {

                        switch (type) {
                            case 0:
                                AddSampleDetailsInfo(data, list);
                                break;

                            case 1:
                                UpdateSampleDetailsInfo(data, list);
                                break;
                        }


                    }
                });
    }

    //提交采样信息
    public void AddSampleDetailsInfo(String data, List<File> picPath) {

        Map<String, RequestBody> partMap = new HashMap<>();
        RequestBody dataBody = RequestBody.create(MediaType.parse("text/plain"), new String(data));
        partMap.put("watercontent", dataBody);
        for (int i = 0; i < picPath.size(); i++) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), picPath.get(i));
            partMap.put("file" + i + "\"; filename=\"" + picPath.get(i).getName() + "\"", fileBody);
        }
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddSampleDetailsInfo(partMap)
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
                            addView.onMessage(resultBean);
                        }
                    }
                });
    }


    //提交采样信息
    public void UpdateSampleDetailsInfo(String data, List<File> picPath) {

        Map<String, RequestBody> partMap = new HashMap<>();
        RequestBody dataBody = RequestBody.create(MediaType.parse("text/plain"), new String(data));
        partMap.put("watercontent", dataBody);
        for (int i = 0; i < picPath.size(); i++) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), picPath.get(i));
            partMap.put("file" + i + "\"; filename=\"" + picPath.get(i).getName() + "\"", fileBody);
        }

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .UpdateSampleDetailsInfo(partMap)
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
                            addView.onMessage(resultBean);
                        }
                    }
                });
    }




}

package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.luck.picture.lib.compress.Luban;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.FieldsamplingDetailBean;
import com.monitor.changtian.bean.FieldsamplingDetailListBean;
import com.monitor.changtian.bean.FieldsamplingInfo;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.TaskinfoView;
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

/**
 * Created by ken on 2018/6/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskinfoPresenter {

    private TaskinfoView taskinfoView;
    private Dialog loading_dialog;
    private Context mContext;

    public TaskinfoPresenter(TaskinfoView taskinfoView, Context mContext) {
        this.taskinfoView = taskinfoView;
        this.mContext = mContext;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
    }


    public void AddsampleInformation(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddsampleInformation(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        taskinfoView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {

                            taskinfoView.onMessage(resultBean);
                        }
                    }
                });
    }

    public void OnPack(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetDevData(data)
                .compose(Transformer.<List<DicDataBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<DicDataBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<DicDataBean> dicDataBeans) {
                        if (dicDataBeans != null) {

                            taskinfoView.OnPack(dicDataBeans);
                        }
                    }
                });
    }

    public void OnUnit(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetDevData(data)
                .compose(Transformer.<List<DicDataBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<DicDataBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<DicDataBean> dicDataBeans) {
                        if (dicDataBeans != null) {

                            taskinfoView.OnUnit(dicDataBeans);
                        }
                    }
                });
    }

    public void OnStyle(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetDevData(data)
                .compose(Transformer.<List<DicDataBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<DicDataBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<DicDataBean> dicDataBeans) {
                        if (dicDataBeans != null) {

                            taskinfoView.OnStyle(dicDataBeans);
                        }
                    }
                });
    }

    public void OnSoilhumidity(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetDevData(data)
                .compose(Transformer.<List<DicDataBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<DicDataBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<DicDataBean> dicDataBeans) {
                        if (dicDataBeans != null) {

                            taskinfoView.OnSoilhumidity(dicDataBeans);
                        }
                    }
                });
    }

    public void OnSoiltexture(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetDevData(data)
                .compose(Transformer.<List<DicDataBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<DicDataBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {

                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<DicDataBean> dicDataBeans) {
                        if (dicDataBeans != null) {
                            taskinfoView.OnSoiltexture(dicDataBeans);
                        }
                    }
                });
    }

    /**
     * 查询任务返回详情
     */
    public void GetTasksInfomation(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetTasksInfomation(data)
                .compose(Transformer.<List<TasksInfomationBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<TasksInfomationBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        taskinfoView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<TasksInfomationBean> taskEquipmentDataBeans) {
                        if (taskEquipmentDataBeans != null) {
                            taskinfoView.OnGetTasksInfomation(taskEquipmentDataBeans);
                        }
                    }
                });
    }

    public void submitInfo(final String data, final List<File> picPath, final List<File> videoPath, final Map<String, String> allFile) {
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
                        AddSampleDetailsInfo(data, list, videoPath, allFile);
                    }
                });
    }

    //提交采样信息
    public void AddSampleDetailsInfo(String data, List<File> picPath, List<File> videoPath, final Map<String, String> allFile) {

        Map<String, RequestBody> partMap = new HashMap<>();
        RequestBody dataBody = RequestBody.create(MediaType.parse("text/plain"), new String(data));
        partMap.put("data", dataBody);
        for (int i = 0; i < picPath.size(); i++) {
            RequestBody fileBody_image = RequestBody.create(MediaType.parse("image/jpeg"), picPath.get(i));
            partMap.put("imagefile" + i + "\"; filename=\"" + picPath.get(i).getName() + "\"", fileBody_image);
        }
        for (int i = 0; i < videoPath.size(); i++) {
            RequestBody fileBody_video = RequestBody.create(MediaType.parse("video/mp4"), videoPath.get(i));
            partMap.put("videofile" + i + "\"; filename=\"" + videoPath.get(i).getName() + "\"", fileBody_video);
        }
        for (Map.Entry<String, String> entry : allFile.entrySet()) {
            if (!TextUtils.isEmpty(entry.getValue())) {
                File file_qx = new File(entry.getValue());
                RequestBody fileBody_qx = RequestBody.create(MediaType.parse("text/plain"), file_qx);
                partMap.put(entry.getKey() + "; filename=\"" + entry.getValue() + "\"", fileBody_qx);
            }
        }
//        if (qixiang.length() > 0) {
//            File file_qx = new File(qixiang);
//            RequestBody fileBody_qx = RequestBody.create(MediaType.parse("text/plain"), file_qx);
//            partMap.put("qixiang; filename=\"" + qixiang + "\"", fileBody_qx);
//        }
//        //  背景噪声文件 上传  2019年10月29日15:14:12
//        if (background_noise.length() > 0) {
//            File file_background_noise = new File(background_noise);
//            RequestBody fileBody_background_noise = RequestBody.create(MediaType.parse("text/plain"), file_background_noise);
//            partMap.put("background_noise; filename=\"" + background_noise + "\"", fileBody_background_noise);
//        }
//
//        if (sanlingyaoer.length() > 0) {
//            File file_3012 = new File(sanlingyaoer);
//            RequestBody fileBody_3012 = RequestBody.create(MediaType.parse("text/plain"), file_3012);
//            partMap.put("sanlingyaoer; filename=\"" + sanlingyaoer + "\"", fileBody_3012);
//        }
//
//        ViseLog.d(qixiang + "\n" + sanlingyaoer);
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddSampleDetailsInfo(partMap)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        taskinfoView.OnError(s);
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {

                            taskinfoView.onMessage(resultBean);
                        }
                    }
                });
    }


    public void GetfieldsamplingInfo(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetfieldsamplingInfo(data)
                .compose(Transformer.<List<FieldsamplingInfo>>switchSchedulers())
                .subscribe(new CommonObserver<List<FieldsamplingInfo>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        taskinfoView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<FieldsamplingInfo> taskEquipmentDataBeans) {
                        if (taskEquipmentDataBeans != null) {
                            taskinfoView.OnFieldsamplingInfo(taskEquipmentDataBeans);
                        }
                    }
                });
    }


    public void EndfieldsamplingInfo(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .EndfieldsamplingInfo(data)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        taskinfoView.OnError(s);
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {

                            taskinfoView.OnEndFieldsampling(resultBean);
                        }
                    }
                });
    }


    public void GetfieldsamplingDetailList(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetfieldsamplingDetailList(data)
                .compose(Transformer.<List<FieldsamplingDetailListBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<FieldsamplingDetailListBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        taskinfoView.OnError(s);
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<FieldsamplingDetailListBean> fieldsamplingDetailListBeans) {
                        if (fieldsamplingDetailListBeans != null) {

                            taskinfoView.OnGetfieldsamplingDetailList(fieldsamplingDetailListBeans);
                        }
                    }
                });
    }

    public void GetfieldsamplingDetail(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetfieldsamplingDetail(data)
                .compose(Transformer.<List<FieldsamplingDetailBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<FieldsamplingDetailBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        taskinfoView.OnError(s);

                    }

                    @Override
                    protected void onSuccess(List<FieldsamplingDetailBean> fieldsamplingDetailBeans) {
                        if (fieldsamplingDetailBeans != null) {

                            taskinfoView.OnGetfieldsamplingDetail(fieldsamplingDetailBeans);
                        }
                    }
                });
    }


    //提交采样信息
    public void AddTaskStop1(String data, List<File> picPath) {
        Map<String, RequestBody> partMap = new HashMap<>();
        RequestBody dataBody = RequestBody.create(MediaType.parse("text/plain"), new String(data));
        partMap.put("data", dataBody);
        for (int i = 0; i < picPath.size(); i++) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), picPath.get(i));
            partMap.put("file" + i + "\"; filename=\"" + picPath.get(i).getName() + "\"", fileBody);
        }


        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddTaskStop1(partMap)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        taskinfoView.OnError(s);
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {

                            taskinfoView.onMessage(resultBean);
                        }
                    }
                });
    }


}

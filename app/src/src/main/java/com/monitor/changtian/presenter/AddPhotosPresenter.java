package com.monitor.changtian.presenter;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.AddPhotosView;
import com.vise.log.ViseLog;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ken on 2018/9/29.
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

public class AddPhotosPresenter {

    private Context mContext;
    private AddPhotosView addPhotosView;

    public AddPhotosPresenter(AddPhotosView addPhotosView, Context mContext) {
        this.addPhotosView = addPhotosView;
        this.mContext = mContext;
    }


    //提交采样信息
    public void AddfujianInfo(String loginId, String category, String types, String kid, File picPath) {

        Map<String, RequestBody> partMap = new HashMap<>();

        RequestBody loginIdBody = RequestBody.create(MediaType.parse("text/plain"), new String(loginId));
        partMap.put("loginId", loginIdBody);
        RequestBody typesBody = RequestBody.create(MediaType.parse("text/plain"), new String(types));
        partMap.put("types", typesBody);
        RequestBody categoryBody = RequestBody.create(MediaType.parse("text/plain"), new String(category));
        partMap.put("category", categoryBody);
        RequestBody kidBody = RequestBody.create(MediaType.parse("text/plain"), new String(kid));
        partMap.put("kid", kidBody);

        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), picPath);
        partMap.put("file" + 1 + "\"; filename=\"" + picPath + "\"", fileBody);
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .AddfujianInfo(partMap)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
                        addPhotosView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            addPhotosView.OnAddPhotos(resultBean);
                        }
                    }
                });
    }

}

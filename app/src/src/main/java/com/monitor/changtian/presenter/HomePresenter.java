package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.base.BaseView;
import com.monitor.changtian.bean.WeatherBean;
import com.monitor.changtian.network.ApiService;
import com.vise.log.ViseLog;

/**
 * Created by ken on 2018/4/24.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class HomePresenter {
    private BaseView view;
    private Dialog loading_dialog;
    private Context mContext;

    public HomePresenter(BaseView view, Context mContext) {
        this.view = view;
        this.mContext = mContext;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("loading...").create();
    }


    public void GetWeatherBean(String cityname) {

        RxHttpUtils
                .getSInstance()
                .baseUrl("https://www.sojson.com/open/api/")
                .createSApi(ApiService.class)
                .GetWeatherBean(cityname)
                .compose(Transformer.<WeatherBean>switchSchedulers())
                .subscribe(new CommonObserver<WeatherBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return true;
                    }
                    @Override
                    protected void onError(String s) {
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(WeatherBean weatherBean) {
                        if(weatherBean.getStatus()==200){
                            view.onData(weatherBean);
                        }

                    }
                });


    }
}

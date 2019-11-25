package com.monitor.changtian.fragment;


import android.content.Intent;
import android.util.Log;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.jauker.widget.BadgeView;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.MapActivity;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.EventBus.JumpEvent;
import com.monitor.changtian.bean.EventBus.ViewpagerEvent;
import com.sunfusheng.marqueeview.MarqueeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ken on 2018/4/23.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {


    @ViewById(R.id.marqueeView)
    MarqueeView marqueeView;
    BadgeView badgeView;

    @ViewById(R.id.ll_audit)
    LinearLayout ll_audit;




    //声明AMapLocationClient类对象
    AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;


    /**
     * 初始化定位并设置定位回调监听
     */
    private void getCurrentLocationLatLng() {

        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        // 同时使用网络定位和GPS定位,优先返回最高精度的定位结果,以及对应的地址描述信息
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //只会使用网络定位
   /* mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);*/
        //只使用GPS进行定位
    /*mLocationOption.setLocationMode(AMapLocationMode.Device_Sensors);*/
        // 设置为单次定位 默认为false
        mLocationOption.setOnceLocation(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。默认连续定位 切最低时间间隔为1000ms
        mLocationOption.setInterval(10000);
        //设置是否返回地址信息（默认返回地址信息）
    /*mLocationOption.setNeedAddress(true);*/
        //关闭缓存机制 默认开启 ，在高精度模式和低功耗模式下进行的网络定位结果均会生成本地缓存,不区分单次定位还是连续定位。GPS定位结果不会被缓存。
    /*mLocationOption.setLocationCacheEnable(false);*/
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }


    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    amapLocation.getLatitude();//获取纬度
                    amapLocation.getLongitude();//获取经度
                    amapLocation.getAccuracy();//获取精度信息
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//国家信息
                    amapLocation.getProvince();//省信息
                    amapLocation.getCity();//城市信息
                    amapLocation.getDistrict();
                    amapLocation.getStreet();//街道信息

                    amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态


                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();
    }


    @AfterViews
    void init() {

        List<String> info = new ArrayList<>();
        info.add("关于护城河水质采样的样品未通过,请重新取样");
        info.add("重新采样！");
        info.add("赶快去采样！");
        info.add("立马去采样!");
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
        getCurrentLocationLatLng();
//
//
//        badgeView = new BadgeView(getActivity());
//        badgeView.setTargetView(ll_audit);
//        badgeView.setBadgeGravity(Gravity.RIGHT|Gravity.BOTTOM);
//        badgeView.setBackground(12, Color.parseColor("#9b2eef"));
//        badgeView.setText("new");


    }


    @Click(R.id.ll_history_record)
    public void ll_history_record() {


    }

    //我的审批
    @Click(R.id.ll_audit)
    public void ll_audit() {
        EventBus.getDefault().post(new JumpEvent(4));
        EventBus.getDefault().post(new ViewpagerEvent(1));
    }

    /**
     * 1= 我的任务   2=催办  3=进度 4=审批
     */
    //我的任务
    @Click(R.id.ll_task)
    public void ll_task() {
        EventBus.getDefault().post(new JumpEvent(1));
    }

    //我的催办
    @Click(R.id.ll_press)
    public void ll_press() {
        EventBus.getDefault().post(new JumpEvent(2));
        EventBus.getDefault().post(new ViewpagerEvent(2));

    }

    //任务进度
    @Click(R.id.ll_task_pro)
    public void ll_task_pro() {

    }

    //地图
    @Click(R.id.ll_map)
    public void ll_map() {

        getActivity().startActivity(new Intent(getActivity(), MapActivity.class));
    }


    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }
}

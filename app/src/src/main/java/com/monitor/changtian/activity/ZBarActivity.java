package com.monitor.changtian.activity;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.DevCodeEvent;
import com.monitor.changtian.bean.EventBus.AddAccEvent;
import com.monitor.changtian.bean.EventBus.BarCodeEvent;
import com.monitor.changtian.bean.EventBus.ParallelEvent;
import com.monitor.changtian.bean.EventBus.SongEvent;
import com.monitor.changtian.bean.GetSampleEvent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

@EActivity(R.layout.activity_zbar)
public class ZBarActivity extends BaseActvity implements QRCodeView.Delegate {

    //  获取布局id 绑定View
    @ViewById(R.id.zbarview)
    ZBarView mZBarView;

    //  获取闪光灯id 绑定View
    @ViewById(R.id.capture_flash)
    ImageView capture_flash;

    //  闪光灯 是否开启
    boolean isFlash = false;

    @Extra
    String type;
    @Extra
    String taskid;


    @AfterViews
    void init() {
        initImageBackText("条码扫描");

        //  设置二维码的代理
        mZBarView.setDelegate(this);
    }

    //  闪光灯按钮 打开或者 关闭 闪光灯
    @Click(R.id.capture_flash)
    public void capture_flash() {
        if (isFlash) {
            mZBarView.closeFlashlight(); // 关闭闪光灯
            capture_flash.setImageResource(R.mipmap.flash_default); //闪光灯按钮颜色
            isFlash = false;
        } else {
            mZBarView.openFlashlight(); // 打开闪光灯
            capture_flash.setImageResource(R.mipmap.flash_open);    //闪光灯按钮颜色
            isFlash = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mZBarView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZBarView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
        mZBarView.getScanBoxView().setOnlyDecodeScanBoxArea(true); // 仅识别扫描框中的码
        mZBarView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
        mZBarView.setType(BarcodeType.ALL, null); // 识别所有类型的码
        mZBarView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.1秒后开始识别

    }

    @Override
    protected void onStop() {
        mZBarView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZBarView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    //识别到震动
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    String back_calibration = "0";
    @Override
    public void onScanQRCodeSuccess(String result) {

        vibrate();
        if (type != null) {
            if (type.equals("平行")) {
                EventBus.getDefault().post(new ParallelEvent(result, "平行"));
            } else if (type.equals("关联")) {
                EventBus.getDefault().post(new ParallelEvent(result, "关联"));
            } else if (type.equals("领取设备")) {
                EventBus.getDefault().post(new DevCodeEvent(result));
            } else if (type.equals("质控")) {
                EventBus.getDefault().post(new AddAccEvent(result));
            } else if (type.equals("领样")) {
                EventBus.getDefault().post(new GetSampleEvent(result));
            } else if (type.equals("送样")) {
                EventBus.getDefault().post(new SongEvent(result));
            }else if (type.equals("归还")){   //  判断是否为归还设备 是归还设备 跳转到校准活动进行校准
                EventBus.getDefault().post(new BarCodeEvent(result));
            //  跳转到 测量后校准
                back_calibration = "1";
                CalibrationActivity_.intent(this).back_calibration(back_calibration).barCode(result).title("测后校准记录").start();
            }
        } else {
            EventBus.getDefault().post(new BarCodeEvent(result));
////            //  跳转到 测量后校准
//            back_calibration = "0";
//            CalibrationActivity_.intent(this).back_calibration(back_calibration).barCode(result).title("测前校准记录").start();

        }


//        关闭自己 传递扫描到的String 字符串到 添加信息界面
        finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = mZBarView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                mZBarView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                mZBarView.getScanBoxView().setTipText(tipText);
            }
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e("ls", "打开相机出错");
    }
}

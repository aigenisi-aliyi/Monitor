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
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.DevCodeEvent;
import com.monitor.changtian.bean.EquipInOutStockDataBean;
import com.monitor.changtian.bean.EquipStateBean;
import com.monitor.changtian.bean.EquipTypeBean;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.EventBus.AddAccEvent;
import com.monitor.changtian.bean.EventBus.BarCodeEvent;
import com.monitor.changtian.bean.EventBus.ParallelEvent;
import com.monitor.changtian.bean.EventBus.SongEvent;
import com.monitor.changtian.bean.GetConsumablesDataBean;
import com.monitor.changtian.bean.GetSampleEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.presenter.ZBarPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

@EActivity(R.layout.activity_zbar)
public class ZBarActivity extends BaseActvity implements QRCodeView.Delegate, CalibrationView {
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

    private ZBarPresenter presenter;
    private List<String> types = new ArrayList<>();

    private boolean isReturn = false;//是否归还设备


    @AfterViews
    void init() {
        initImageBackText("条码扫描");
        //  设置二维码的代理
        if ("归还".equals(type)) {
            back_calibration = "1";
        } else {
            back_calibration = "0";
        }

        mZBarView.setDelegate(this);
        presenter = new ZBarPresenter(this);
        presenter.getTypes(types);

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
                //        关闭自己 传递扫描到的String 字符串到 添加信息界面
                finish();
            } else if (type.equals("关联")) {
                EventBus.getDefault().post(new ParallelEvent(result, "关联"));
                //        关闭自己 传递扫描到的String 字符串到 添加信息界面
                finish();
            } else if (type.equals("领取设备")) {
                //增加接口，返回，是否需要进行设备校验（2031、2071、2037......）
                isReturn = false;
                String data_dev = "{barCode:\"" + result + "\"}";
                presenter.getEquipmentType(data_dev);
            } else if (type.equals("质控")) {
                EventBus.getDefault().post(new AddAccEvent(result));
                //        关闭自己 传递扫描到的String 字符串到 添加信息界面
                finish();
            } else if (type.equals("领样")) {
                EventBus.getDefault().post(new GetSampleEvent(result));
                //        关闭自己 传递扫描到的String 字符串到 添加信息界面
                finish();
            } else if (type.equals("送样")) {
                EventBus.getDefault().post(new SongEvent(result));
                //        关闭自己 传递扫描到的String 字符串到 添加信息界面
                finish();
            } else if (type.equals("收样")) {
                EventBus.getDefault().post(new BarCodeEvent(result));
                // 关闭自己 传递扫描到的String 字符串到 添加信息界面
                finish();
            } else if (type.equals("归还")) {   //  判断是否为归还设备 是归还设备 跳转到校准活动进行校准
                isReturn = true;
                String data_dev = "{barCode:\"" + result + "\"}";
                presenter.getEquipmentType(data_dev);
            } else if (type.equals("样品")) {
                EventBus.getDefault().post(new BarCodeEvent(result));
                //        关闭自己 传递扫描到的String 字符串到 添加信息界面
                finish();
            }
        } else {
//            //默认归还流程
//            isReturn = true;
//            String data_dev = "{barCode:\"" + result + "\"}";
//            presenter.getEquipmentType(data_dev);
        }
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
    public void BarCodeDevState(List<EquipStateBean> equipStateBeans) {
        if (equipStateBeans != null && equipStateBeans.size() > 0) {
            //获取第一个数据
            EquipStateBean bean = equipStateBeans.get(0);
            //需要校验设备类型
            if (bean != null) {
                //成功，都有值
                if (isCheck(bean.getModelSpecification())) {
                    //必须校验，跳转校验界面
                    CalibrationActivity_.intent(ZBarActivity.this).taskid(taskid).back_calibration(back_calibration).barCode(bean.getBarCode()).title("设备校准").start();
                }
                if (isReturn) {
                    //归还
//                    EventBus.getDefault().post(new BarCodeEvent(bean.getBarCode()));
                    EventBus.getDefault().post(new DevCodeEvent(bean.getBarCode()));
                } else {
                    //借取
                    EventBus.getDefault().post(new DevCodeEvent(bean.getBarCode()));
                }
            }
        }
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e("ls", "打开相机出错");
    }


    //    @Override
//    public void onSuccess(EquipTypeBean bean,boolean isReture) {
//        //需要校验设备类型
//        if (bean!=null){
//            if("0".equals(bean.getResult())){
//                //成功，都有值
//                if (bean.getCollarState() == 0&&isCheck(bean.getType())){
//                    //必须校验，跳转校验界面
//                    CalibrationActivity_.intent(ZBarActivity.this).taskid(taskid).back_calibration(back_calibration).start();
//                }
//                if (isReture){
//                    //归还
//                    EventBus.getDefault().post(new BarCodeEvent(bean.getBarCode()));
//                }else {
//                    //借取
//                    EventBus.getDefault().post(new DevCodeEvent(bean.getBarCode()));
//                }
//            }else {
//                if (isReture){
//                    //归还
//                    EventBus.getDefault().post(new BarCodeEvent(bean.getBarCode()));
//                }else {
//                    //借取
//                    EventBus.getDefault().post(new DevCodeEvent(bean.getBarCode()));
//                }
//            }
//        }
//        finish();
//    }
    // 在types中保存着数组 各种需要校准的设备型号
    private boolean isCheck(String eqType) {
        for (String typ : types) {
            if (eqType.contains(typ)) {
                return true;
            }
        }
        return false;
    }

//    @Override
//    public void onFailed(String s) {
//        msg(s);
//        Log.e("ls", s);
//    }

    @Override
    public void OnAllPerson(List<AllUserInfo> userInfos) {
    }

    @Override
    public void OnAllDevice(List<EquipmentDataBean> equipmentDataBeans) {
    }

    @Override
    public void OnQueryDevList(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {
    }

    @Override
    public void OnQueryConList(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {

    }

    @Override
    public void OnQuerConsumablesDataBean(List<GetConsumablesDataBean> consumablesDataBeans) {

    }

    @Override
    public void OnReceiveSubmit(ResultBean resultBean) {

    }

    @Override
    public void OnError(String s) {
        msg(s);
    }

    @Override
    public void SubmitVoice(ResultBean resultBean) {

    }


}

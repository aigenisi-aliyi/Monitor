package com.monitor.changtian.activity.task;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.CalibrationActivity_;
import com.monitor.changtian.activity.ZBarActivity_;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.EquipInOutStockDataBean;
import com.monitor.changtian.bean.EventBus.BarCodeEvent;
import com.monitor.changtian.bean.GetConsumablesDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.presenter.ReceivePresenter;
import com.monitor.changtian.view.ReceiveView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


@EActivity(R.layout.activity_equipmentreturn)
public class EquipmentreturnActivity extends BaseActvity implements ReceiveView {

    ReceivePresenter receivePresenter;

    @AfterViews
    void init() {

        initImageBackText("设备归还");
        //注册EvetnBus；
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        receivePresenter = new ReceivePresenter(this, this);
    }


    /**
     * 扫描
     */
    @Click(R.id.iv_scan)
    public void iv_scan() {
//        startActivity(new Intent(this, CaptureActivity.class));
//        ShowDialogtitle("请稍等...", this);
//        String data_dev = "{id:\"\",equipid:\"\",types:\"1\",optionuser:\"\",taskid:\"\",optionLoginName:\"\",isreturn:\"0\",barCode:\"" + event.getCodeNum() + "\"}";
//        receivePresenter.GetEquipInOutStockData(data_dev);
        ZBarActivity_.intent(this).type("归还").start();
    }


    @Subscribe
    public void BarCodeEv(BarCodeEvent event) {
        if (event != null) {
            /**
             * 查询领用的设备
             */
            ShowDialogtitle("请稍等...", this);
            String data_dev = "{id:\"\",equipid:\"\",types:\"1\",optionuser:\"\",taskid:\"\",optionLoginName:\"\",isreturn:\"0\",barCode:\"" + event.getCodeNum() + "\"}";
            receivePresenter.GetEquipInOutStockData(data_dev);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus7
    }

    @Override
    public void OnQueryDevList(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {

        DissDialog();
        if (equipInOutStockDataBeans.size() > 0) {
//            Equipmentreturn_infoActivity_.intent(this).equipInOutStockDataBean(equipInOutStockDataBeans.get(0)).start();
        } else {
            msg("未找到该设备的相关记录!");
            ZBarActivity_.intent(this).type("归还").start();
        }
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
        DissDialog();
    }
}

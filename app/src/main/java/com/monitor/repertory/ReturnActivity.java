package com.monitor.repertory;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.EquipInOutStockDataBean;
import com.monitor.changtian.bean.EventBus.BarCodeEvent;
import com.monitor.changtian.bean.GetConsumablesDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.presenter.ReceivePresenter;
import com.monitor.changtian.view.ReceiveView;
//import com.monitor.zxing.activity.CaptureActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_return)
public class ReturnActivity extends BaseActvity implements BaseActvity.Rightlistener, ReceiveView {

    @Extra
    EquipInOutStockDataBean equipInOutStockDataBean;
    @Extra
    String type;

    @ViewById(R.id.ll_dev)
    LinearLayout ll_dev;
    @ViewById(R.id.ll_con)
    LinearLayout ll_con;
    @ViewById(R.id.tv_bar_code)
    EditText tv_bar_code;
    @ViewById(R.id.tv_status)
    TextView tv_status;
    @ViewById(R.id.tv_con)
    TextView tv_con;
    @ViewById(R.id.tv_person)
    TextView tv_person;
    List<String> StatusList = new ArrayList<>();
    ReceivePresenter receivePresenter;

    @AfterViews
    void init() {
        //注册EvetnBus；
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        receivePresenter = new ReceivePresenter(this, this);
        if (type.equals("dev")) {
            tv_status.setHint("请选择设备状态");
            initRightOnclikText("设备归还", "确定", this);
            ll_dev.setVisibility(View.VISIBLE);
        } else {
            /**
             * 填充耗材名称
             */
            tv_con.setText(equipInOutStockDataBean.getNames());
            tv_status.setHint("请选择耗材状态");
            initRightOnclikText("耗材归还", "确定", this);
            ll_con.setVisibility(View.VISIBLE);
        }

        tv_person.setText(equipInOutStockDataBean.getUsername());
        StatusList.add("正常");
        StatusList.add("异常");
    }

    /**
     * 点击二维码扫描
     */
    @Click(R.id.iv_bar_code)
    public void iv_bar_code() {
//        startActivity(new Intent(ReturnActivity.this, CaptureActivity.class));
    }

    @Subscribe
    public void BarCodeEv(BarCodeEvent event) {
        if (event != null) {


            /**
             * 根据扫描返回的二维码信息和领用的信息对比 一直 通过 否则提示不一致
             */
            if (event.getCodeNum().equals(equipInOutStockDataBean.getBarCode())) {
                tv_bar_code.setText(event.getCodeNum());
            } else {
                msg("归还的二维码与领用的二维码不一致！");
                tv_bar_code.setText("");
            }

        }
    }


    /**
     * 选择状态
     */
    @Click(R.id.tv_status)
    public void tv_status() {


        hintKbTwo();

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String content = StatusList.get(options1);
                tv_status.setText(content);
            }
        })
                .setTitleText("请选择状态")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(StatusList);//一级选择器
        pvOptions.show();

    }

    /**
     * 提交按钮
     */
    String equipstatus = "";
    String barCode = "";

    @Override
    public void rightlistener() {


        if (tv_status.getText().toString().trim().length() == 0) {
            msg("请选择状态!");
            return;
        } else {
            if (tv_status.getText().toString().trim().equals("正常")) {
                equipstatus = "1";
            } else {
                equipstatus = "0";
            }
        }
        if (type.equals("dev")) {
            if (tv_bar_code.getText().toString().trim().length() == 0) {
                msg("条码信息不能为空!");
                return;
            } else {
                barCode = tv_bar_code.getText().toString().trim();
            }
            AgainDialog("确定要归还该设备吗?");
        } else {
            AgainDialog("确定要归还该耗材吗?");
        }
    }

    public void AgainTrue() {
        if (type.equals("dev")) {
            ShowDialogtitle("请稍等...",this);
            String data_dev = "{taskid:\"" + equipInOutStockDataBean.getTaskid() + "\", optionuser:\"" + equipInOutStockDataBean.getOptionuser() + "\",loginId:\"admin\",barCode:\"" + barCode + "\",equipstatus:\"" + equipstatus + "\",relationid:\"" + equipInOutStockDataBean.getId() + "\"}";
            receivePresenter.AddEquipInStock(data_dev);
        } else {
            ShowDialogtitle("请稍等...",this);
            String data_con = "{taskid:\"" + equipInOutStockDataBean.getTaskid() + "\", optionuser:\"" + equipInOutStockDataBean.getOptionuser() + "\",loginId:\"admin\",relationid:\"" + equipInOutStockDataBean.getId() + "\",conid:\"" + equipInOutStockDataBean.getConid() + "\",constatus:\"" + equipstatus + "\"}";
            receivePresenter.AddConsumablesInStock(data_con);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus7
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
        DissDialog();
        if (resultBean.getResult().equals("1")) {
            msg("成功");
            finish();
        } else {
            msg(resultBean.getErrormessage());
        }

    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }
}

package com.monitor.changtian.activity.task;

import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.EquipInOutStockDataBean;
import com.monitor.changtian.bean.GetConsumablesDataBean;
import com.monitor.changtian.bean.GetsamplemanageInfoBean;
import com.monitor.changtian.bean.JumpEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.RoomListBean;
import com.monitor.changtian.bean.SampleInfoByAssignmentRecordBean;
import com.monitor.changtian.bean.SampleStatusBean;
import com.monitor.changtian.presenter.ReceivePresenter;
import com.monitor.changtian.presenter.SamplePresenter;
import com.monitor.changtian.utils.DeleteFileUtil;
import com.monitor.changtian.view.ReceiveView;
import com.monitor.changtian.view.SampleView;
import com.monitor.sample.ReceiveSampleActivity;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_equipmentreturn_info)
public class Equipmentreturn_infoActivity extends BaseActvity implements SampleView, ReceiveView {


    @Extra
    EquipInOutStockDataBean equipInOutStockDataBean;

    @ViewById(R.id.tv_code)
    TextView tv_code;
    @ViewById(R.id.et_type)
    TextView et_type;
    @ViewById(R.id.tv_time)
    TextView tv_time;
    @ViewById(R.id.tv_person)
    TextView tv_person;
    @ViewById(R.id.tv_selectperson)
    TextView tv_selectperson;
    SamplePresenter samplePresenter;
    List<AllUserInfo> userInfoList = new ArrayList<>();
    String userid = "";
    String equipstatus = "";
    @ViewById(R.id.rb_normal)
    RadioButton rb_normal;
    @ViewById(R.id.rb_abnormal)
    RadioButton rb_abnormal;
    ReceivePresenter receivePresenter;

    @AfterViews
    void init() {

        initImageBackText("设备归还");
        samplePresenter = new SamplePresenter(this, this);
        receivePresenter = new ReceivePresenter(this, this);
        initData();
        initView(equipInOutStockDataBean);
    }

    public void initView(EquipInOutStockDataBean equipInOutStockDataBean) {

        if (equipInOutStockDataBean != null) {
            tv_code.setText(equipInOutStockDataBean.getBarCode());
            et_type.setText(equipInOutStockDataBean.getNames());
            tv_time.setText(equipInOutStockDataBean.getUseddate());
            tv_person.setText(equipInOutStockDataBean.getUsername());
            userid = equipInOutStockDataBean.getOptionuser();
            tv_selectperson.setText(equipInOutStockDataBean.getUsername());
        }
    }

    @Click(R.id.tv_selectperson)
    public void tv_selectperson() {
        hintKbTwo();
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(Equipmentreturn_infoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String content = userInfoList.get(options1).getPickerViewText();
                userid = userInfoList.get(options1).getId() + "";
                tv_selectperson.setText(content);
            }
        })
                .setTitleText("请选择归还人")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(userInfoList);//一级选择器
        pvOptions.show();
    }

    public void initData() {
        ShowDialogtitle("正在加载...", this);
        String Person_data = "{loginName:\"\",querystring:\"\",rolename:\"\"}";
        samplePresenter.GetAllUserInfo(Person_data);

//        ShowDialogtitle("请稍等...", this);
        String data_dev = "{id:\"\",equipid:\"\",types:\"1\",optionuser:\"\",taskid:\"\",optionLoginName:\"\",isreturn:\"0\",barCode:\"" + tv_code + "\"}";
        receivePresenter.GetEquipInOutStockData(data_dev);


    }


    @Click(R.id.stv_submit)
    public void stv_submit() {

        if (rb_normal.isChecked() || rb_abnormal.isChecked()) {

            if (rb_normal.isChecked()) {
                equipstatus = "1";
            } else if (rb_abnormal.isChecked()) {
                equipstatus = "0";
            }
            ViseLog.d(equipstatus);

            AgainDialog("确认要归还该设备吗?");
        } else {
            msg("请选择设备状态");
        }


    }

    public void AgainTrue() {
        ShowDialogtitle("请稍等...",this);
        String data = "{optionuser:\"" + userid + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\",barCode:\"" + tv_code.getText() + "\",equipstatus:\"" + equipstatus + "\"}";
        receivePresenter.AddEquipInStock(data);
    }


    @Override
    public void OnGetsamplemanageInfo(List<GetsamplemanageInfoBean> getsamplemanageInfoBeans) {

    }

    @Override
    public void OnMessage(ResultBean resultBean) {

    }

    @Override
    public void OnAllPerson(List<AllUserInfo> userInfos) {
        DissDialog();
        userInfoList.clear();
        userInfoList.addAll(userInfos);
    }

    @Override
    public void OnSampleStatus(SampleStatusBean sampleStatusBeans) {

    }

    @Override
    public void OnRoomList(List<RoomListBean> roomListBeans) {

    }

    @Override
    public void OnGetSampleInfoByAssignmentRecord(SampleInfoByAssignmentRecordBean sampleInfoByAssignmentRecordBean) {

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

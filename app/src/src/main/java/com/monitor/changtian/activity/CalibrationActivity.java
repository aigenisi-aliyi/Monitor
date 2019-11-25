package com.monitor.changtian.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.allen.library.utils.ToastUtils;
import com.bigkoo.pickerview.OptionsPickerView;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.task.Equipmentreturn_infoActivity_;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.EquipInOutStockDataBean;
import com.monitor.changtian.bean.EquipStateBean;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.GetConsumablesDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TasksInfomationBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_calibration)
public class CalibrationActivity extends BaseActvity implements BaseActvity.Rightlistener, CalibrationView {

    @ViewById(R.id.tv_dev_i)
    TextView tv_dev_i;
    @ViewById(R.id.tv_dev_person)
    TextView tv_dev_person;
    //    选择校准设备
    @ViewById(R.id.tv_dev_dev)

    TextView tv_dev_dev;

    @ViewById(R.id.rg_dev_stdvalue)
    RadioGroup rg_dev_stdvalue;
    @ViewById(R.id.rb_94)
    RadioButton rb_94;
    @ViewById(R.id.rb_114)
    RadioButton rb_114;
    //    测量值
    @ViewById(R.id.tv_dev_topvalue)
    EditText tv_dev_topvalue;
    @ViewById(R.id.tv_dev_endvalue)
    EditText tv_dev_endvalue;
    @Extra
    String taskid;
    @Extra
    String barCode;
    @Extra
    TasksInfomationBean.EquipsBean equipsBean;
    @Extra
    int pos;
    CalibrationPresenter calibrationPresenter;
    @Extra
    ArrayList<TasksInfomationBean.SamplinguserBean> userInfoLists;
    @Extra
    String title;

    //归还设备 为1
    @Extra
    String back_calibration;

    @Extra
    EquipInOutStockDataBean equipInOutStockDataBean;

    @AfterViews
    void init() {

        //      接收 传过来的 扫码 噪声设备值
        str_dev = barCode;
        //      校准人就是领取设备的人
        str_per = MyApplication.getInstance().getUser().toString().trim();
        //      设置点击监听事件 选择校准标准值 94 / 114
        selectRadioButton();

        initRightOnclikText(title, "提交", this);
        calibrationPresenter = new CalibrationPresenter(this, this);
//        String Person_data = "{loginName:\"\",querystring:\"\",rolename:\"\"}";
//        queryBasicsPresenter.GetAllUserInfo(Person_data);


        String device_data = "{id:\"\",names:\"\",EquipmentNumber:\"\",buyDates:\"\",buyeDated:\"\",productionUnit:\"\",RegulationNumber:\"\",ExpireDates:\"\",ExpireDated:\"\",equiptype:\"\",IsEnabled:\"1\",categoryid:\"\",isLarge:\"\",isTest:\"\",iseffective:\"\",equiptypename:\"校准\"}";
        calibrationPresenter.GetEquipmentData(device_data);
        if (equipsBean != null) {
            tv_dev_i.setText(equipsBean.getNames() + "(" + equipsBean.getEquipmentNumber() + ")");
            if (equipsBean.getCalibrations() != null && equipsBean.getCalibrations().size() > 0) {
                tv_dev_dev.setText(equipsBean.getCalibrations().get(0).getDevname());
//                DevCollarState = equipsBean.getCollarState();
//                tv_dev_person.setText(equipsBean.getCalibrations().get(0).getNames());
            }
        }
    }

    List<AllUserInfo> userInfoList = new ArrayList<>();
    List<EquipmentDataBean> equipmentDataBeanList = new ArrayList<>();

    String str_per = "";
    String str_dev = "";
    String Premeasurement = "94";
    String Postmeasurement = "";

    String DevCollarState = "";


    /**
     * 选择人员
     */
//    @Click(R.id.tv_dev_person)
//    public void tv_dev_person() {
//        hintKbTwo();
//        if (userInfoLists.size() > 0) {
//            OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
//                @Override
//                public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                    //返回的分别是三个级别的选中位置
//                    String content = userInfoLists.get(options1).getPickerViewText();
//                    str_per = userInfoLists.get(options1).getUserid() + "";
//                    tv_dev_person.setText(content);
//                }
//            })
//                    .setTitleText("选择校准人")
//                    .setDividerColor(Color.BLACK)
//                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
//                    .setContentTextSize(20)
//                    .setOutSideCancelable(true)// default is true
//                    .build();
//            pvOptions.setPicker(userInfoLists);//一级选择器
//            pvOptions.show();
//        }
//    }

    /**
     * 选择设备
     */
    @Click(R.id.tv_dev_dev)
    public void tv_dev_dev() {
        hintKbTwo();
        if (equipmentDataBeanList.size() > 0) {
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    String content = equipmentDataBeanList.get(options1).getPickerViewText();
                    str_dev = equipmentDataBeanList.get(options1).getId() + "";
                    tv_dev_dev.setText(content);
                }
            })
                    .setTitleText("选择校准设备")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .setOutSideCancelable(true)// default is true
                    .build();
            pvOptions.setPicker(equipmentDataBeanList);//一级选择器
            pvOptions.show();
        }
    }

    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //    点击提交校准（校准人、标准值、校准值）的信息
    @Override
    public void rightlistener() {
        if (tv_dev_dev.getText().toString().length() == 0) {
            msg("请选择校准设备");
            return;
        }

        if (tv_dev_topvalue.getText().toString().length() == 0) {
            msg("请输入测量值");
            return;
        }

        Postmeasurement = tv_dev_topvalue.getText().toString().trim();
        //  向后台提交噪声校准数据
        hintKbTwo();
        //  判断设备是否在库存中
//        JudgeDevInOrOut();
        CalibrateSave();
    }

    //            向后台提交 保存 校准记录
    private void CalibrateSave() {
        // data={equipid:"",loginId:"",CalibrateDevId:"",Premeasurement:"",Postmeasurement:"",explainInfo:""}
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("equipid", barCode);
            jsonObject.put("loginId", MyApplication.getInstance().getUser());
            jsonObject.put("CalibrateDevId", str_dev);
            jsonObject.put("Premeasurement", Premeasurement);
            jsonObject.put("Postmeasurement", Postmeasurement);
            jsonObject.put("explainInfo", "");

//            提交 噪声校准记录
            calibrationPresenter.CalibrateSave(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void JudgeDevInOrOut() {
        String data_dev = "{barCode:\"" + barCode + "\"}";
        calibrationPresenter.getEquipmentState(data_dev);

    }


    @Override
    public void SubmitVoice(ResultBean resultBean) {

//

//        String data = "{id:\"\",names:\"\",types:\"1\",EquipmentNumber:\"" + DevCollarState + "\",taskid:\"\",optionLoginName:\"\",isreturn:\"0\",barCode:\"" + barCode + "\"}";
//        calibrationPresenter.GetEquipmentData(data);

        if (resultBean.getResult().equals("1")) {
            if (resultBean.getIsqualified().equals("合格")) {
                msg("校准合格！");
                if (back_calibration.equals("1")) {
                    ShowDialogtitle("请稍等...", this);
                    String data_dev = "{id:\"\",equipid:\"\",types:\"1\",optionuser:\"\",taskid:\"\",optionLoginName:\"\",isreturn:\"0\",barCode:\"" + barCode + "\"}";
                    calibrationPresenter.GetEquipInOutStockData(data_dev);
//                     receivePresenter.GetEquipInOutStockData();

//                    Equipmentreturn_infoActivity_.intent(this).equipInOutStockDataBean(equipInOutStockDataBean).start();
                    DissDialog();
                } else if (back_calibration.equals("0")){
                    //  提交设备出库信息
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("taskid", taskid);
                        jsonObject.put("loginId", MyApplication.getInstance().getUser());
                        jsonObject.put("barCode", barCode);
                        calibrationPresenter.AddEquipOutStock(jsonObject.toString());
                        // 想用 这个Presenter 的 方法 这个在另外一个ReceiveDevActivity活动中
//                        if (back_calibration.equals("0")) {
//                            calibrationPresenter.AddEquipOutStock(jsonObject.toString());
//                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                // 不合格
                ToastUtils.showToast("不合格");
            }
        } else {
            ToastUtils.showToast(resultBean.getErrormessage());
        }
    }

    @Override
    public void BarCodeDevState(List<EquipStateBean> equipStateBeans) {
        if (back_calibration.equals("1")) {
            CalibrateSave();
        } else {
            if (equipStateBeans != null) {
                if (equipStateBeans.get(0).getCollarState().equals("1")) {
                    ToastUtils.showToast("设备已被领用！");
                    return;
                } else{
                    CalibrateSave();
//                                ToastUtils.showToast(equipInOutStockDataBean.get());
                }
            }
        }


    }


    /**
     * 获取RadioButton选中值
     */
    private void selectRadioButton() {
        rg_dev_stdvalue.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_94:
                        Premeasurement = "94";
                        break;
                    case R.id.rb_114:
                        Premeasurement = "114";
                        break;
                }
            }
        });
    }

    @Override
    public void OnAllPerson(List<AllUserInfo> userInfos) {

        userInfoList.clear();
        userInfoList.addAll(userInfos);
    }

    @Override
    public void OnAllDevice(List<EquipmentDataBean> equipmentDataBeans) {
        equipmentDataBeanList.clear();
        equipmentDataBeanList.addAll(equipmentDataBeans);
    }

    @Override
    public void OnQueryDevList(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {
        Equipmentreturn_infoActivity_.intent(this).equipInOutStockDataBean(equipInOutStockDataBeans.get(0)).start();


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

    }


}

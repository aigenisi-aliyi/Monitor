package com.monitor.changtian.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.monitor.changtian.utils.LogAndToastUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
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

    @ViewById(R.id.ll_std_voice)
    LinearLayout ll_std_voice;
    @ViewById(R.id.ll_std_lljz)
    LinearLayout ll_std_lljz;

    @ViewById(R.id.tv_dev_stdValue)
    EditText tv_dev_stdValue;
    @ViewById(R.id.et_yq1)
    EditText et_yq1;
    @ViewById(R.id.et_yq2)
    EditText et_yq2;
    @ViewById(R.id.et_yq3)
    EditText et_yq3;
    @ViewById(R.id.tv_avg)
    TextView tv_avg;

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
//        initVisibility(); //2019年11月5日
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
        //,barCode:\"" + barCode + "\"
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
                    judgeCalibrationtype(tv_dev_dev.getText().toString());
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

    //判断哪类设备
    private static int JudgeWhichDevType = 0;

    private void judgeCalibrationtype(String equipName) {
        //     选择设备后 显示 对应设备的输入信息
        if (equipName.contains("便携式气体综合校准装置")) { //  需要用到流量校准设备时
            JudgeWhichDevType = 0;
            ll_std_lljz.setVisibility(View.VISIBLE);
            ll_std_voice.setVisibility(View.GONE);
//            清空输入框
            tv_dev_topvalue.setText("");
        } else if (equipName.contains("声校准器")) { // 噪声设备 则显示噪声校准相关记录界面
            JudgeWhichDevType = 1;
            ll_std_lljz.setVisibility(View.GONE);
            ll_std_voice.setVisibility(View.VISIBLE);
//            清空输入框
            tv_dev_stdValue.setText("");
            et_yq1.setText("");
            et_yq2.setText("");
            et_yq3.setText("");
            tv_avg.setText("");
        } else { // 标气校准
            JudgeWhichDevType = 2;
            ll_std_lljz.setVisibility(View.GONE);
            ll_std_voice.setVisibility(View.GONE);
            et_yq1.setText("");
            et_yq2.setText("");
            et_yq3.setText("");
            tv_avg.setText("");
            tv_dev_topvalue.setText("");

            msg("其他校准设备正在更新...");
        }
    }

    private int isualified = 0;

    /**
     * 计算平均值
     */
    @Click(R.id.btn_figure)
    public void btn_figure() {
        //        判断这4个输入框是否为空
        if (!isEditViewEmpty()) {
            return;
        }
        //这里面是计算公式
        double std_value = Double.parseDouble(tv_dev_stdValue.getText().toString());
        double nub1 = Double.parseDouble(et_yq1.getText().toString());
        double nub2 = Double.parseDouble(et_yq2.getText().toString());
        double nub3 = Double.parseDouble(et_yq3.getText().toString());
        double sz_avege = ((nub1 - std_value) + (nub2 - std_value) + (nub3 - std_value)) / (3 * std_value);
        // 误差平均值修约到小数点后4位
        BigDecimal bigDecimal_tv_avg = new BigDecimal(String.valueOf(sz_avege)).setScale(4, BigDecimal.ROUND_HALF_EVEN);
        if (sz_avege >= -0.05 && sz_avege <= 0.05) {
            isualified = 1;
            msg("校准合格!");
        } else {
            isualified = -1;
            msg("校准不合格,请重新校准!");
            tv_avg.setText(String.valueOf(bigDecimal_tv_avg));
            return;
        }
        // 数值放入显示框
        tv_avg.setText(String.valueOf(bigDecimal_tv_avg));
    }

    //      判断输入框是否为空     是否为合法的字符串 是否可以转换为 double类型
    public boolean isEditViewEmpty() {
        try {
            if (JudgeWhichDevType == 0) {
                //            提交 噪声校准记录
                if (TextUtils.isEmpty(tv_dev_stdValue.getText().toString().trim())) {
                    msg("请输入标准值!");
                    return false;
                } else { // 输入字符串判断字符串是否合法 是否可以转换为double类型
//            if (tv_dev_stdValue.getText().toString().trim()) {
//                msg("输入的标准值不合法,请重新输入!");
//            }
                }
                if (TextUtils.isEmpty(et_yq1.getText().toString().trim())) {
                    msg("请输入仪器示值1!");
                    return false;
                }
                if (TextUtils.isEmpty(et_yq2.getText().toString().trim())) {
                    msg("请输入仪器示值2!");
                    return false;
                }
                if (TextUtils.isEmpty(et_yq3.getText().toString().trim())) {
                    msg("请输入仪器示值3!");
                    return false;
                }
            } else if (JudgeWhichDevType == 1) {
                if (tv_dev_dev.getText().toString().length() == 0) {
                    msg("请选择校准设备");
                    return false;
                }

                if (tv_dev_topvalue.getText().toString().length() == 0) {
                    msg("请输入测量值");
                    return false;
                }
                Postmeasurement = tv_dev_topvalue.getText().toString().trim();
            }
        } catch (Exception e) {
            msg("请确保输入正确!");
            e.printStackTrace();
        }
        return true;
    }

    public void msg(String msg) {
        LogAndToastUtil.toast(getApplicationContext(), msg);
    }

    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //    点击提交校准（校准人、标准值、校准值）的信息
    @Override
    public void rightlistener() {

        //  向后台提交噪声校准数据
        hintKbTwo();
        //  判断设备是否在库存中
//        JudgeDevInOrOut();
        CalibrateSave();
    }

    //            向后台提交 保存 校准记录
    private void CalibrateSave() {
        // data={equipid:"",loginId:"",CalibrateDevId:"",Premeasurement:"",Postmeasurement:"",explainInfo:""}
        if (JudgeWhichDevType == 0) {
            if (isualified == 1) {
                bxsqtzhczzzCalibrateSave();
            } else if (isualified == -1) {
                msg("校准不合格,请重新校准");
                return;
            }
        } else if (JudgeWhichDevType == 1) {
            if (!isEditViewEmpty()) {
                return;
            }
            noiseCalibrateSave();
        }
    }

    private void bxsqtzhczzzCalibrateSave() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("equipid", barCode);
            jsonObject.put("loginId", MyApplication.getInstance().getUser());
            jsonObject.put("CalibrateDevId", str_dev);
            jsonObject.put("Premeasurement", tv_dev_stdValue.getText().toString());
            jsonObject.put("Postmeasurement1", et_yq1.getText());
            jsonObject.put("Postmeasurement2", et_yq2.getText());
            jsonObject.put("Postmeasurement3", et_yq3.getText());
            jsonObject.put("explainInfo", "");
//            提交 7040校准记录
            calibrationPresenter.Gas7040Save(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // 噪声校准数据提交
    private void noiseCalibrateSave() {
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
        if (JudgeWhichDevType == 0) {
            if (isualified != 1) {
                msg("校准不合格!");
            } else {
                if ("0".equals(back_calibration)) {
                    //领取设备
                    finish();
                } else if ("1".equals(back_calibration)) {
                    //归还设备
                    try {
                        ShowDialogtitle("请稍等...", this);
                        String data_dev = "{id:\"\",equipid:\"\",types:\"0\",optionuser:\"\",taskid:\"\",optionLoginName:\"\",isreturn:\"1\",barCode:\"" + barCode + "\"}";
                        calibrationPresenter.GetEquipInOutStockData(data_dev);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //预留
                }
            }
        } else if (JudgeWhichDevType == 1) {
            if (resultBean.getResult().equals("1")) {
                if (resultBean.getIsqualified().equals("合格")) {
                    msg("校准合格！");
                    if ("0".equals(back_calibration)) {
                        //领取设备
                        finish();
                    } else if ("1".equals(back_calibration)) {
                        //归还设备
                        try {
                            ShowDialogtitle("请稍等...", this);
                            String data_dev = "{id:\"\",equipid:\"\",types:\"0\",optionuser:\"\",taskid:\"\",optionLoginName:\"\",isreturn:\"1\",barCode:\"" + barCode + "\"}";
                            calibrationPresenter.GetEquipInOutStockData(data_dev);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        //预留
                    }
                } else {
                    // 不合格
                    ToastUtils.showToast("不合格");
                }

            } else {
                ToastUtils.showToast(resultBean.getErrormessage());
            }
        }

//        String data = "{id:\"\",names:\"\",types:\"1\",EquipmentNumber:\"" + DevCollarState + "\",taskid:\"\",optionLoginName:\"\",isreturn:\"0\",barCode:\"" + barCode + "\"}";
//        calibrationPresenter.GetEquipmentData(data);
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
                } else {
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
        DissDialog();
        if (equipInOutStockDataBeans != null && equipInOutStockDataBeans.size() > 0) {
            Equipmentreturn_infoActivity_.intent(this).equipInOutStockDataBean(equipInOutStockDataBeans.get(0)).start();
            finish();
        } else {
            msg("返回数据错误！");
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

    }
}

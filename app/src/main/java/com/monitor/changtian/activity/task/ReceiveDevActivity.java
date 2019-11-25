package com.monitor.changtian.activity.task;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.allen.library.utils.ToastUtils;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.AddSampleInfoActivity;
import com.monitor.changtian.activity.CalibrationActivity;
import com.monitor.changtian.activity.CalibrationActivity_;
import com.monitor.changtian.activity.ZBarActivity_;
import com.monitor.changtian.adapter.ConsumablesDataAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.CalibrateDevListBean;
import com.monitor.changtian.bean.ConBean;
import com.monitor.changtian.bean.DevCodeEvent;
import com.monitor.changtian.bean.EquipInOutStockDataBean;
import com.monitor.changtian.bean.EventBus.BarCodeEvent;
import com.monitor.changtian.bean.GetConsumablesDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.presenter.ReceivePresenter;
import com.monitor.changtian.utils.LogAndToastUtil;
import com.monitor.changtian.view.ReceiveView;
//import com.monitor.zxing.activity.CaptureActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备耗材领用
 */
@EActivity(R.layout.activity_receive_dev)
public class ReceiveDevActivity extends BaseActvity implements ReceiveView, BaseActvity.Rightlistener {

    @Extra
    String type;
    @Extra
    String taskId;
    ReceivePresenter receivePresenter;
    @ViewById(R.id.tv_bar_code)
    TextView tv_bar_code;
    @ViewById(R.id.ll_dev)
    LinearLayout ll_dev;

    @ViewById(R.id.ll_con)
    LinearLayout ll_con;
    @ViewById(R.id.tv_con)
    TextView tv_con;
    ConsumablesDataAdapter consumablesDataAdapter;

    @AfterViews
    void init() {
        //注册EvetnBus；
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        receivePresenter = new ReceivePresenter(this, this);
        if (type.equals("dev")) {
            ll_dev.setVisibility(View.VISIBLE);
            initRightOnclikText("设备领用", "提交", this);
        } else {
            ll_con.setVisibility(View.VISIBLE);
            initRightOnclikText("耗材领用", "提交", this);
            /**
             * 查询相关耗材
             */
            String data = "{taskid:\"" + taskId + "\"}";
            receivePresenter.GetConsumablesDataByTaskid(data);
            consumablesDataAdapter = new ConsumablesDataAdapter(R.layout.nopoint_select_item);
        }
    }
    // 测量前的校准
    String back_calibration = "0";
    @Override
    public void OnReceiveSubmit(ResultBean resultBean) {
        DissDialog();
        //  判断设备是否可用
        if (!resultBean.getResult().equals("0")) {
            if (type.equals("dev")) {
                // 2 为噪音设备；其他非噪音设备
                if (resultBean.getDevType().equals("2")) {

//                    DeviceCalibrateDialog("确定要领用该设备吗?");
                    CalibrationActivity_.intent(this).back_calibration(back_calibration).taskid(taskId).title("测前校准记录").start();
                }
                if (resultBean.getErrormessage().equals("该设备已领用！")) {
                    msg(resultBean.getErrormessage() + "1111111111");
                } else {
                    msg(resultBean.getErrormessage() + "设备领取成功");
                }
            } else {
                if (tv_con.getText().toString().trim().length() == 0) {
                    msg("请先选择耗材!");
                    return;
                }
                AgainDialog("确定要领用这些耗材吗?");
            }
        }
        else if (resultBean.getErrormessage().equals("该设备属于噪声设备，请先校准！")){
            msg(resultBean.getErrormessage());
            String barCode = tv_bar_code.getText().toString().trim();
            CalibrationActivity_.intent(ReceiveDevActivity.this).taskid(taskId).barCode(barCode).back_calibration(back_calibration).title("测前校准记录").start();
        }else {    //  设备不可用时
            msg(resultBean.getErrormessage());
        }
    }

//    @Override
//    public void OnReceiveCalibrateDev(List<CalibrateDevListBean> calibrateDevListBeans) {
//        DissDialog();
//        if (calibrateDevListBeans.size() > 0) {
//            Equipmentreturn_infoActivity_.intent(this).equipInOutStockDataBean(calibrateDevListBeans.get(0)).start();
//        } else {
//            msg("未找到该设备的相关记录!");
//        }
//    }

    /**
     * 点击二维码扫描
     */
    @Click(R.id.iv_bar_code)
    public void iv_bar_code() {
//        startActivity(new Intent(ReceiveDevActivity.this, CaptureActivity.class));
        ZBarActivity_.intent(this).type("领取设备").taskid(taskId).start();
    }

    AlertDialog analysis_dia;

    /**
     * 耗材选择
     */
    String conid = "";//耗材id
    List<ConBean> conBeanList = new ArrayList<>();

    @Click(R.id.tv_con)
    public void tv_con() {
//
        final AlertDialog.Builder builder = new AlertDialog.Builder(ReceiveDevActivity.this);
        View view = LayoutInflater.from(ReceiveDevActivity.this).inflate(R.layout.analysis_item, null);
        builder.setView(view);
        analysis_dia = builder.show();
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText("请选择耗材");
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new GridLayoutManager(ReceiveDevActivity.this, 4));
        rv_list.setAdapter(consumablesDataAdapter);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringBuffer stringBuffernames = new StringBuffer();
                final StringBuffer stringBufferids = new StringBuffer();
                for (int i = 0; i < consumablesDataAdapter.getData().size(); i++) {
                    if (consumablesDataAdapter.getData().get(i).isChoice()) {
                        consumablesDataAdapter.getData().get(i).setChoice_save(true);
                        stringBufferids.append(consumablesDataAdapter.getData().get(i).getId() + ",");
                        stringBuffernames.append(consumablesDataAdapter.getData().get(i).getNames() + ",");
                    } else {
                        stringBuffernames.append("");
                        stringBufferids.append("");
                        consumablesDataAdapter.getData().get(i).setChoice_save(false);
                    }
                }
                if (stringBuffernames.toString() != null && stringBuffernames.toString().length() > 0) {
                    tv_con.setText(stringBuffernames.toString().substring(0, stringBuffernames.toString().length() - 1));
                    conid = stringBufferids.toString().substring(0, stringBufferids.toString().length() - 1);
                } else {
                    tv_con.setText("");
                    conid = "";
                }
                analysis_dia.dismiss();
            }
        });

        Button btn_false = view.findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analysis_dia.dismiss();
            }
        });
    }

    /**
     * 提交按钮
     */
    @Override
    public void rightlistener() {
        String barCode = tv_bar_code.getText().toString().trim();
        if (type.equals("dev")) {
            if (TextUtils.isEmpty(barCode)) {
                msg("请扫码");
                return;
            }
//            String data_dev = "{id:\"\",equipid:\"\",types:\"1\",optionuser:\"\",taskid:\"" + taskId + "\",optionLoginName:\"" + MyApplication.getInstance().getUser() + "\",isreturn:\"0,1\"}";
//            receivePresenter.GetEquipInOutStockData(data_dev);
            ShowDialogtitle("请稍等...",this);
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("taskid", taskId);
                jsonObject.put("loginId", MyApplication.getInstance().getUser());
                jsonObject.put("barCode", barCode);
                receivePresenter.AddEquipOutStock(jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void DeviceCalibrateDialog(String title) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage("      请仔细核对相关信息!");
        //    设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AgainFalse();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showSubmitDialog();
            }
        });
        builder.show();
    }

    private void showSubmitDialog() {
//                弹出一个 对话框  对话框标题为  “校准记录录入”
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ReceiveDevActivity.this);
//                显示一行文本 “校准记录录入”
        builder1.setTitle("校准记录录入");
        builder1.setMessage("请核对设备校准记录值");
//        一个单选框

        final EditText DevIdEdit = new EditText(ReceiveDevActivity.this);
        DevIdEdit.setHint("请选择校准设备");
        // 2个edit框 变量
        final RadioButton StdandRadio94 = new RadioButton(ReceiveDevActivity.this);
        final RadioButton StdandRadio114 = new RadioButton(ReceiveDevActivity.this);

        final EditText CalibrateEdit = new EditText(ReceiveDevActivity.this);
        CalibrateEdit.setHint("请输入校准值");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(DevIdEdit);
        layout.addView(StdandRadio94);
        layout.addView(StdandRadio114);

        layout.addView(CalibrateEdit);
        //  2个输入框  “填写一个标准值” 填写一个校准值 提交给后台
        builder1.setView(layout);
        builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String CalibrateDevId = DevIdEdit.getText().toString().trim();
                String Premeasurement = CalibrateEdit.getText().toString().trim();
                String Postmeasurement = StdandRadio94.getText().toString().trim();
                if (TextUtils.isEmpty(Premeasurement) || TextUtils.isEmpty(Postmeasurement) || TextUtils.isEmpty(CalibrateDevId)) {
                    ToastUtils.showToast("输入框不能为空！");
                    return;
                } else if (Postmeasurement.equals("94") || Postmeasurement.equals("114")) {
                    StringBuffer sb = new StringBuffer("{");
                    sb
                            .append("CalibrateDevId:").append("\"").append(CalibrateDevId).append("\"").append(",")
                            .append("Postmeasurement:").append("\"").append(Postmeasurement).append("\"").append(",")
                            .append("Premeasurement:").append("\"").append(Premeasurement).append("\"").append(",")
                            .append("taskid:").append("\"").append(taskId).append("\"").append(",")
                            .append("loginId:").append("\"").append(MyApplication.getInstance().getUser()).append("\"").append(",")
                            .append("barCode:").append("\"").append(tv_bar_code.getText().toString().trim())
                            .append("}");
//                向后台提交数据
                    ShowDialogtitle("请稍等...",ReceiveDevActivity.this);
                    receivePresenter.AddEquipOutStock(sb.toString());
                }
//                String data = "{taskid:\"" + taskId + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\",barCode:\"" + tv_bar_code.getText().toString().trim() + "\"}";

            }
        });
        builder1.show();
    }

    String consumables = "";

    @Override
    public void AgainDialog(String title) {
        super.AgainDialog(title);
    }

    public void AgainTrue() {


    }

    @Subscribe
    public void DevCodeEvents(DevCodeEvent event) {
        if (event != null) {
            tv_bar_code.setText(event.getCodeNum());
            // 判断是否为噪声设备 噪声设备 先校准 再领取
//            String data_dev = "{id:\"\",equipid:\"\",types:\"1\",optionuser:\"\",taskid:\"" + taskId + "\",optionLoginName:\"" + MyApplication.getInstance().getUser() + "\",isreturn:\"0,1\"}";
//            receivePresenter.GetEquipInOutStockData(data_dev);
        }

    }

    @Override
    public void OnQueryDevList(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {


    }

    @Override
    public void OnQueryConList(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {

    }

    @Override
    public void OnQuerConsumablesDataBean(List<GetConsumablesDataBean> consumablesDataBeans) {

        if (consumablesDataBeans != null) {
            consumablesDataAdapter.setNewData(consumablesDataBeans);
        }
    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus7
    }

    private String conids;


}

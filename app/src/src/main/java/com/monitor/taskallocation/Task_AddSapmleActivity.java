package com.monitor.taskallocation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.AddSampleInfoActivity;
import com.monitor.changtian.activity.ZBarActivity_;
import com.monitor.changtian.adapter.Task_AnalysisAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.AddTaskSampleBean;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EventBus.BarCodeEvent;
import com.monitor.changtian.bean.EventBus.SongEvent;
import com.monitor.changtian.bean.FieldsamplingDetailBean;
import com.monitor.changtian.bean.FieldsamplingDetailListBean;
import com.monitor.changtian.bean.FieldsamplingInfo;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.monitor.changtian.presenter.SampleCodePresenter;
import com.monitor.changtian.presenter.TaskinfoPresenter;
import com.monitor.changtian.view.SampleCodeView;
import com.monitor.changtian.view.TaskinfoView;
//import com.monitor.zxing.activity.CaptureActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.feezu.liuli.timeselector.TimeSelector;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_task__add_sapmle)
public class Task_AddSapmleActivity extends BaseActvity implements BaseActvity.Rightlistener, SampleCodeView, TaskinfoView {

    @ViewById(R.id.tv_potion)
    TextView tv_potion;
    @ViewById(R.id.tv_bar_code)
    TextView tv_bar_code;
    @ViewById(R.id.iv_bar_code)
    ImageView iv_bar_code;
    @ViewById(R.id.et_names)
    EditText et_names;
    @ViewById(R.id.et_statuss)
    EditText et_statuss;
    @ViewById(R.id.tv_pack)
    TextView tv_pack;
    @ViewById(R.id.et_namesss)
    EditText et_namesss;
    @ViewById(R.id.et_phone)
    EditText et_phone;
    @ViewById(R.id.et_sampleValue)
    EditText et_sampleValue;
    @ViewById(R.id.tv_unit)
    TextView tv_unit;
    @ViewById(R.id.tv_analysis)
    TextView tv_analysis;
    Task_AnalysisAdapter task_analysisAdapter;
    @ViewById(R.id.tv_time)
    TextView tv_time;
    @Extra
    String schemeid;
    @Extra
    ArrayList<DetectionSchemeInfoBean.LeftcateogoryBean> leftcateogoryBean;
    AlertDialog analysis_dia;
    String onlynumber = "";
    String sampingstatus = "";
    String sampingPacking = ""; //包装ID
    String starttime = "";
    String sampinguserphone = "";
    String sampinguser = "";// 采样人
    String samplingamount = "";//采样量
    String samplingunit = "";//采样单位
    String details = "";
    String Sampingname = "";
    String remark = "";
    String Categoryid = "";
    TaskinfoPresenter taskinfoPresenter;
    private TimeSelector timeSelector_mix;

    String loginId = MyApplication.getInstance().getUser();
    SampleCodePresenter sampleCodePresenter;

    @AfterViews
    void init() {

        initRightOnclikText("样品信息补充", "保存", this);
        //注册EvetnBus；
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        sampleCodePresenter = new SampleCodePresenter(this, this);
        task_analysisAdapter = new Task_AnalysisAdapter(R.layout.nopoint_select_item);
        taskinfoPresenter = new TaskinfoPresenter(this, this);
        String data_pack = "{Id:\"\",typeCode:\"sampingPacking\",DataValue:\"\"}";
        taskinfoPresenter.OnPack(data_pack);
        String data_unit = "{Id:\"\",typeCode:\"unit\",DataValue:\"\"}";
        taskinfoPresenter.OnUnit(data_unit);
    }

    @Override
    public void rightlistener() {
        starttime = tv_time.getText().toString().trim();
        sampinguser = et_namesss.getText().toString().trim();
        sampinguserphone = et_phone.getText().toString().trim();
        onlynumber = tv_bar_code.getText().toString().trim();
        sampingstatus = et_statuss.getText().toString().trim();
        Sampingname = et_names.getText().toString().trim();
        samplingamount = et_sampleValue.getText().toString().trim();

        List<AddTaskSampleBean> addTaskSampleBeans = new ArrayList<>();
        AddTaskSampleBean addTaskSampleBean = new AddTaskSampleBean();
        addTaskSampleBean.setOnlynumber(onlynumber);
        addTaskSampleBean.setSampingname(Sampingname);
        addTaskSampleBean.setSampingstatus(sampingstatus);
        addTaskSampleBean.setAnalysisitems(analysisitems);
        addTaskSampleBean.setCategoryid(Categoryid);
        addTaskSampleBean.setRemark(remark);
        addTaskSampleBean.setSamplingamount(samplingamount);
        addTaskSampleBean.setSamplingunit(samplingunit);
        addTaskSampleBean.setSampingPacking(sampingPacking);
        addTaskSampleBeans.add(addTaskSampleBean);

        details = JSON.toJSONString(addTaskSampleBeans);

        /**
         * 二次弹框确认
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认提交样品信息吗?");
        builder.setMessage("      请仔细核对相关信息!");
        //    设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ShowDialogtitle("请稍等...", Task_AddSapmleActivity.this);
                String data = "{schemeid:\"" + schemeid + "\",loginId:\"" + loginId + "\",sampingtime:\"" + starttime + "\",sampinguser:\"" + sampinguser + "\",sampinguserphone:\"" + sampinguserphone + "\",details:" + details + "}";
                taskinfoPresenter.AddsampleInformation(data);


            }
        });
        builder.show();


    }

    /**
     * 送样时间
     */

    @Click(R.id.tv_time)
    public void tv_time() {
        /**
         * 初始化时间选择
         */
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeSelector_mix = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                tv_time.setText(time);
            }
        }, format.format(new Date()), "2300-01-01 00:00");
        timeSelector_mix.setIsLoop(false);
        timeSelector_mix.setMode(TimeSelector.MODE.YMDHM);
        timeSelector_mix.show();
    }

    /**
     * 选择项目
     */
    @Click(R.id.tv_potion)
    public void tv_potion() {

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String content = leftcateogoryBean.get(options1).getPickerViewText();
                Categoryid = leftcateogoryBean.get(options1).getCategoryid();
                tv_potion.setText(content);

                task_analysisAdapter.setNewData(leftcateogoryBean.get(options1).getFactors());
            }
        })
                .setTitleText("选择项目")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(leftcateogoryBean);//一级选择器
        pvOptions.show();

    }

    /**
     * 点击二维码扫描
     */
    @Click(R.id.iv_bar_code)
    public void iv_bar_code() {
//        startActivity(new Intent(Task_AddSapmleActivity.this, CaptureActivity.class));

        ZBarActivity_.intent(this).type("送样").start();
    }

    String codea = "";

    @Subscribe
    public void SongEventsss(SongEvent event) {
        if (event != null) {

            if (event != null) {
//                ShowDialogtitle("请稍等...", Task_AddSapmleActivity.this);
//                codea = "tv_bar_code";
//                String data = "{samplecode:\"" + event.getCodeNum() + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\"}";
//                sampleCodePresenter.Getsamplecode(data);
            tv_bar_code.setText(event.getCodeNum());
            }
        }
    }


    List<DicDataBean> basicslist_pack = new ArrayList<>();
    List<DicDataBean> basicslist_unit = new ArrayList<>();

    @Click(R.id.tv_pack)
    public void tv_pack() {

        hintKbTwo();
        initpickview(basicslist_pack, tv_pack, "选择包装");
    }

    public void initpickview(final List<DicDataBean> dicDataBeans, final TextView view, final String title) {

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String content = dicDataBeans.get(options1).getPickerViewText();
                switch (title) {
                    case "选择包装":
                        sampingPacking = dicDataBeans.get(options1).getId() + "";
                        break;
                    case "选择单位":
                        samplingunit = dicDataBeans.get(options1).getId() + "";
                        break;
                }

                view.setText(content);
            }
        })
                .setTitleText(title)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(dicDataBeans);//一级选择器
        pvOptions.show();
    }

    @Click(R.id.tv_unit)
    public void tv_unit() {
        hintKbTwo();
        initpickview(basicslist_unit, tv_unit, "选择单位");
    }

    /**
     * 分析项目
     */
    String analysisitems = "";//分析项目

    @Click(R.id.tv_analysis)
    public void tv_analysis() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Task_AddSapmleActivity.this);
        View view = LayoutInflater.from(Task_AddSapmleActivity.this).inflate(R.layout.analysis_item, null);
        builder.setView(view);
        analysis_dia = builder.show();
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new GridLayoutManager(Task_AddSapmleActivity.this, 4));
        rv_list.setAdapter(task_analysisAdapter);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringBuffer stringBuffernames = new StringBuffer();
                final StringBuffer stringBufferids = new StringBuffer();
                for (int i = 0; i < task_analysisAdapter.getData().size(); i++) {
                    if (task_analysisAdapter.getData().get(i).isChoice()) {
                        task_analysisAdapter.getData().get(i).setChoice_save(true);
                        stringBufferids.append(task_analysisAdapter.getData().get(i).getFid() + ",");
                        stringBuffernames.append(task_analysisAdapter.getData().get(i).getFactorname() + ",");
                    } else {
                        stringBuffernames.append("");
                        stringBufferids.append("");
                        task_analysisAdapter.getData().get(i).setChoice_save(false);
                    }
                }
                if (stringBuffernames.toString() != null && stringBuffernames.toString().length() > 0) {
                    tv_analysis.setText(stringBuffernames.toString().substring(0, stringBuffernames.toString().length() - 1));
                    analysisitems = stringBufferids.toString().substring(0, stringBufferids.toString().length() - 1);
                } else {
                    tv_analysis.setText("");
                    analysisitems = "";
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus7
    }

    @Override
    public void OnGetTasksInfomation(List<TasksInfomationBean> tasksInfomationBeans) {

    }

    @Override
    public void OnPack(List<DicDataBean> dicDataBeans) {
        basicslist_pack.clear();
        basicslist_pack.addAll(dicDataBeans);
    }

    @Override
    public void OnUnit(List<DicDataBean> dicDataBeans) {
        basicslist_unit.clear();
        basicslist_unit.addAll(dicDataBeans);
    }

    @Override
    public void OnStyle(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void onMessage(ResultBean message) {
        DissDialog();
        if (message.getResult().equals("1")) {
            msg("成功");
            finish();
        } else {
            msg(message.getErrormessage());
        }
    }

    @Override
    public void OnFieldsamplingInfo(List<FieldsamplingInfo> fieldsamplingInfos) {

    }

    @Override
    public void OnEndFieldsampling(ResultBean message) {

    }

    @Override
    public void OnGetfieldsamplingDetailList(List<FieldsamplingDetailListBean> fieldsamplingDetailListBeans) {

    }

    @Override
    public void OnGetfieldsamplingDetail(List<FieldsamplingDetailBean> fieldsamplingDetailBeans) {

    }

    @Override
    public void OnSampleCode(ResultBean resultBean) {

        DissDialog();
        tv_bar_code.setText(resultBean.getResult());
    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }

    @Override
    public void OnSoilhumidity(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnSoiltexture(List<DicDataBean> dicDataBeans) {

    }

}

package com.monitor.taskallocation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.FactorsExperimenterDataBean;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.SchemeConsumablesBean;
import com.monitor.changtian.adapter.AgentiaAdater;
import com.monitor.changtian.adapter.AssistAdapter;
import com.monitor.changtian.adapter.CarInfoAdapter;
import com.monitor.changtian.adapter.NopointAdapter;
import com.monitor.changtian.adapter.SamplingAdapter;
import com.monitor.changtian.adapter.SchemeDeviceAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.CarDataListBean;
import com.monitor.changtian.bean.EventBus.ExperimentEvent;
import com.monitor.changtian.bean.NoPointsTaskDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SamplingAssistDataBean;
import com.monitor.changtian.bean.SamplinguserBean;
import com.monitor.changtian.bean.SchemeDeviceBean;
import com.monitor.changtian.bean.Submit_carBean;
import com.monitor.changtian.bean.Submit_equipsBean;
import com.monitor.changtian.presenter.TaskAddPresenter;
import com.monitor.changtian.view.TaskAddView;
import com.vise.log.ViseLog;

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

/**
 * 添加任务
 */
@EActivity(R.layout.activity_task__add)
public class Task_AddActivity extends BaseActvity implements TaskAddView {

    ArrayList<FactorsExperimenterDataBean> factorsExperimenterDataBeans = new ArrayList<>();
    String pointsid = "";
    String subject = "";
    String content = "";
    String loginId = MyApplication.getInstance().getUser();
    String manager = MyApplication.getInstance().getUser();
    String starttime = "";
    String endtime = "";
    String remark = "";
    String samplinguser = "";//采样人员
    String assistuser = "";  //辅助人员
    String cars = "";   //车辆信息
    String equips = ""; //设备信息
    String consumables = ""; //耗材
    String factors = "";//实验人员
    TaskAddPresenter addPresenter;
    @ViewById(R.id.et_names)
    TextView et_names;

    @Extra
    String frequency;

    @Extra
    String schid;

    @Extra
    String conid;
    AlertDialog dia;
    AlertDialog samp_dia;
    AlertDialog assist_dia;
    AlertDialog car_dia;
    AlertDialog dev_dia;
    AlertDialog con_dia;
    String address = "";
    String pinci = "";
    NopointAdapter nopointAdapter;
    SamplingAdapter samplingAdapter;
    AssistAdapter assistAdapter;
    CarInfoAdapter carInfoAdapter;
    AgentiaAdater agentiaAdater;
    SchemeDeviceAdapter schemeDeviceAdapter;
    @ViewById(R.id.tv_cars)
    TextView tv_cars;
    @ViewById(R.id.et_mix)
    TextView et_mix;
    @ViewById(R.id.et_max)
    TextView et_max;
    @ViewById(R.id.tv_address)
    TextView tv_address;
    @ViewById(R.id.tv_sampling)
    TextView tv_sampling;
    @ViewById(R.id.tv_assist)
    TextView tv_assist;
    @ViewById(R.id.et_remark)
    EditText et_remark;
    @ViewById(R.id.tv_pinci)
    TextView tv_pinci;

    @Extra
    String currenttasknumber;

    @Extra
    String schemename;

    @Extra
    String Taskcount;

    @AfterViews
    void init() {
//        msg(conid);
        //注册EvetnBus；
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initImageBackText("添加任务");
        addPresenter = new TaskAddPresenter(this, this);
        assistAdapter = new AssistAdapter(R.layout.nopoint_select_item);
        samplingAdapter = new SamplingAdapter(R.layout.nopoint_select_item);
        carInfoAdapter = new CarInfoAdapter(R.layout.car_selector_item);
        agentiaAdater = new AgentiaAdater(R.layout.agentia_edit_item);
        schemeDeviceAdapter = new SchemeDeviceAdapter(R.layout.agentia_edit_item);
        nopointAdapter = new NopointAdapter(R.layout.nopoint_select_item);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeSelector_mix = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                et_mix.setText(time);
                initPickData(time);
            }
        }, format.format(new Date()) + " 00:00", "2300-01-01 00:00");
        timeSelector_mix.setIsLoop(false);
        timeSelector_mix.setMode(TimeSelector.MODE.YMDHM);


        if (Taskcount.length() > 0) {

            ViseLog.d("slsls" + Taskcount);
            String tasknum = Integer.parseInt(Taskcount) + 1 + "";


            if (tasknum.length() == 1) {
                et_names.setText("(00" + tasknum + ")" + schemename);
            } else if (tasknum.length() == 2) {
                et_names.setText("(0" + tasknum + ")" + schemename);
            } else if (tasknum.length() == 3) {
                et_names.setText("(" + tasknum + ")" + schemename);
            }
        }


        /**
         * 初始化查询点位信息
         */
//        String data = "{conid:\"" + conid + "\"} ";
//        addPresenter.GetNoPointsTaskData(data);

        /**
         * 查询采样人员
         */
        String data_sampling = "{userid:\"\",username:\"\"}";
        addPresenter.GetSamplingAssistData(data_sampling);
//                    /**
//                     * 查询辅助人员
//                     */
//                    String data_assist = "{userid:\"\",username:\"\"}";
//                    addPresenter.GetSamplingAssistData(data_assist);
        /**
         * 查询车辆
         */
        String data_car = "{vid:\"\",vlicense:\"\",vitem:\"\"}";
        addPresenter.GetCarDataList(data_car);
        /**
         * 查询设备
         */
        String data_div = "{schemeid:\"" + schid + "\", categoryname:\"\",equiptype:\"\",equiptypename:\"采样\",categoryid:\"\"}";
        addPresenter.GetSchemeDevice(data_div);


        /**
         * 查询耗材
         */
        String data_con = "{schemeid:\"" + schid + "\", categoryname:\"\",categoryid:\"\"}";
        addPresenter.GetSchemeConsumables(data_con);
    }


    @Click(R.id.et_names)
    public void et_names() {
        msg(et_names.getText().toString().trim());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    private TimeSelector timeSelector_mix, timeSelector_max;

    /**
     * 开始时间选择
     */
    @Click(R.id.et_mix)
    public void et_mix() {
        timeSelector_mix.show();
    }

    public void initPickData(String datatime) {

        timeSelector_max = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                et_max.setText(time);
            }
        }, datatime, "2300-01-01 00:00");
        timeSelector_max.setIsLoop(false);
        timeSelector_max.setMode(TimeSelector.MODE.YMDHM);
    }

    /**
     * 结束时间选择
     */
    @Click(R.id.et_max)
    public void et_max() {

        if (et_mix.getText().toString().trim() != null && et_mix.getText().length() > 0) {

            timeSelector_max.show();
//            if (et_max.getText().toString().trim() != null && et_max.getText().toString().length() > 0) {
//                initPickData(et_max.getText().toString().trim());
//                timeSelector_max.show();
//            } else {
//                timeSelector_max.show();
//            }

        } else {
            msg("请先选择开始时间");
        }

    }

    public void initV() {
        et_mix.setText("");
        et_max.setText("");
        tv_sampling.setText("");
        tv_assist.setText("");
        tv_cars.setText("");
    }

    /**
     * 选择点位信息
     */
    @Click(R.id.tv_address)
    public void tv_address() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(Task_AddActivity.this);
        View view = LayoutInflater.from(Task_AddActivity.this).inflate(R.layout.dialog_nopoint_item, null);
        builder.setView(view);
        dia = builder.show();
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);

        Button btn_false = view.findViewById(R.id.btn_false);

        rv_list.setLayoutManager(new GridLayoutManager(Task_AddActivity.this, 2));
        rv_list.setAdapter(nopointAdapter);

        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia.dismiss();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pointsid.length() > 0 && pointsid.toString() != null) {
                    tv_address.setText(address);
                    tv_pinci.setText(pinci);
                    initV();
//                    /**
//                     * 查询实验人员
//                     */
//                    String data = "{pointsid:\"" + pointsid + "\"} ";
//                    addPresenter.GetFactorsExperimenterData(data);

                    dia.dismiss();
                } else {
                    msg("请选择点位信息");
                    dia.dismiss();
                }
            }
        });
        nopointAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                for (NoPointsTaskDataBean bean : nopointAdapter.getData()) {//全部设为未选中
                    bean.setChoice(false);
                }
                pointsid = nopointAdapter.getData().get(position).getPointsid();
                address = nopointAdapter.getData().get(position).getAddress();
//                pinci = nopointAdapter.getData().get(position).getDaysNumber() + "天" + nopointAdapter.getData().get(position).getFrequency() + "次";
                nopointAdapter.getData().get(position).setChoice(true);//点击的设为选中
                nopointAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 采样选择
     *
     * @param
     */
    @Click(R.id.ll_sampling)
    public void ll_sampling() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(Task_AddActivity.this);
        View view = LayoutInflater.from(Task_AddActivity.this).inflate(R.layout.dialog_sampling_item, null);
        builder.setView(view);
        samp_dia = builder.show();
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new GridLayoutManager(Task_AddActivity.this, 3));
        rv_list.setAdapter(samplingAdapter);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringBuffer stringBuffernames = new StringBuffer();
                for (int i = 0; i < samplingAdapter.getData().size(); i++) {
                    if (samplingAdapter.getData().get(i).isChoice()) {
                        samplingAdapter.getData().get(i).setChoice_save(true);
                        stringBuffernames.append(samplingAdapter.getData().get(i).getUserName() + ",");
                    } else {
                        stringBuffernames.append("");
                        samplingAdapter.getData().get(i).setChoice_save(false);
                    }
                }

                if (stringBuffernames.toString() != null && stringBuffernames.toString().length() > 0) {
                    tv_sampling.setText(stringBuffernames.toString().substring(0, stringBuffernames.toString().length() - 1));
                } else {
                    tv_sampling.setText("");
                }
                samp_dia.dismiss();
            }
        });

        Button btn_false = view.findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                samp_dia.dismiss();
            }
        });

    }


//    /**
//     * 实验人员选择
//     */
//    @Click(R.id.ll_experiment)
//    public void ll_experiment() {
//        if (factorsExperimenterDataBeanArrayList.size() > 0) {
//            ExperimentSelectorActivity_.intent(this).factorsExperimenterDataBeans(factorsExperimenterDataBeans).factorsExperimenterDataBeanArrayList(factorsExperimenterDataBeanArrayList).pointsid(pointsid).start();
//        } else {
//            msg("请先选择点位信息!");
//        }
//    }


    /**
     * 辅助人员选择
     */
    @Click(R.id.ll_assist)
    public void ll_assist() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(Task_AddActivity.this);
        View view = LayoutInflater.from(Task_AddActivity.this).inflate(R.layout.dialog_sampling_item, null);
        builder.setView(view);
        TextView tv_title = view.findViewById(R.id.tv_title);
        ImageView iv_image = view.findViewById(R.id.iv_image);

        tv_title.setText("辅助人员");
        iv_image.setImageResource(R.drawable.task4);
        assist_dia = builder.show();
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new GridLayoutManager(Task_AddActivity.this, 3));
        rv_list.setAdapter(assistAdapter);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringBuffer stringBuffernames = new StringBuffer();
                for (int i = 0; i < assistAdapter.getData().size(); i++) {
                    if (assistAdapter.getData().get(i).isChoice()) {
                        assistAdapter.getData().get(i).setChoice_save(true);
                        stringBuffernames.append(assistAdapter.getData().get(i).getUserName() + ",");
                    } else {
                        stringBuffernames.append("");
                        assistAdapter.getData().get(i).setChoice_save(false);
                    }
                }
                if (stringBuffernames.toString() != null && stringBuffernames.toString().length() > 0) {
                    tv_assist.setText(stringBuffernames.toString().substring(0, stringBuffernames.toString().length() - 1));
                } else {
                    tv_assist.setText("");
                }
                assist_dia.dismiss();
            }
        });
        Button btn_false = view.findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assist_dia.dismiss();
            }
        });


    }

    /**
     * 车辆选择
     */
    List<String> strings = new ArrayList<>();

    /**
     * 保存选中字段
     */
    @Click(R.id.ll_cars)
    public void ll_cars() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(Task_AddActivity.this);
        View view = LayoutInflater.from(Task_AddActivity.this).inflate(R.layout.dialog_car_item, null);
        builder.setView(view);
        car_dia = builder.show();
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new GridLayoutManager(Task_AddActivity.this, 2));
        rv_list.setAdapter(carInfoAdapter);
        Button btn_false = view.findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                car_dia.dismiss();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringBuffer stringBuffernames = new StringBuffer();
                for (int i = 0; i < carInfoAdapter.getData().size(); i++) {
                    if (carInfoAdapter.getData().get(i).isChoice()) {
                        carInfoAdapter.getData().get(i).setChoice_save(true);
                        stringBuffernames.append(carInfoAdapter.getData().get(i).getVlicense() + ",");
                    } else {
                        carInfoAdapter.getData().get(i).setChoice_save(false);
                        stringBuffernames.append("");
                    }
                }
                if (stringBuffernames.toString() != null && stringBuffernames.toString().length() > 0) {
                    tv_cars.setText(stringBuffernames.toString().substring(0, stringBuffernames.toString().length() - 1));
                } else {
                    tv_cars.setText("");
                }
                car_dia.dismiss();
            }
        });

    }

    /**
     * 设备处理
     */
    @Click(R.id.ll_dev)
    public void ll_dev() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Task_AddActivity.this);
        View view = LayoutInflater.from(Task_AddActivity.this).inflate(R.layout.dialog_car_item, null);
        builder.setView(view);
        dev_dia = builder.show();
        initDia_View(view, "设备", "设备编辑", R.drawable.device);
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new LinearLayoutManager(Task_AddActivity.this));
        rv_list.setAdapter(schemeDeviceAdapter);
        Button btn_false = view.findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < schemeDeviceAdapter.getData().size(); i++) {
                    schemeDeviceAdapter.getData().get(i).setSumnumber_s(schemeDeviceAdapter.getData().get(i).getSumnumber());
                }
                schemeDeviceAdapter.notifyDataSetChanged();
                dev_dia.dismiss();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < schemeDeviceAdapter.getData().size(); i++) {
                    if (schemeDeviceAdapter.getData().get(i).getSumnumber_s() != null) {
                        schemeDeviceAdapter.getData().get(i).setSumnumber(schemeDeviceAdapter.getData().get(i).getSumnumber_s());
                    }
                }
                schemeDeviceAdapter.notifyDataSetChanged();
                dev_dia.dismiss();
            }
        });
    }

    /**
     * 耗材处理
     */
    @Click(R.id.ll_agentia)
    public void ll_agentia() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(Task_AddActivity.this);
        View view = LayoutInflater.from(Task_AddActivity.this).inflate(R.layout.dialog_car_item, null);
        builder.setView(view);
        con_dia = builder.show();
        initDia_View(view, "耗材", "耗材编辑", R.drawable.shiji);
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);

        rv_list.setLayoutManager(new LinearLayoutManager(Task_AddActivity.this));
        rv_list.setAdapter(agentiaAdater);
        Button btn_false = view.findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < agentiaAdater.getData().size(); i++) {
                    agentiaAdater.getData().get(i).setNumber_s(agentiaAdater.getData().get(i).getSumnumber());
                }
                agentiaAdater.notifyDataSetChanged();
                con_dia.dismiss();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < agentiaAdater.getData().size(); i++) {
                    if (agentiaAdater.getData().get(i).getNumber_s() != null) {
                        agentiaAdater.getData().get(i).setSumnumber(agentiaAdater.getData().get(i).getNumber_s());
                    }

                }
                agentiaAdater.notifyDataSetChanged();
                con_dia.dismiss();
            }
        });

    }

    /**
     * 提交数据
     */
    @Click(R.id.stv_submit)
    public void stv_submit() {
        remark = et_remark.getText().toString().trim();
        starttime = et_mix.getText().toString().trim();
        endtime = et_max.getText().toString().trim();
        if (et_names.getText().toString().trim().length() == 0) {
            msg("请输入任务名称!");
            return;
        } else {
            subject = et_names.getText().toString().trim();
        }
        if (et_mix.getText().toString().trim().length() == 0) {
            msg("请选择开始时间!");
            return;
        }
        if (et_max.getText().toString().trim().length() == 0) {
            msg("请选择结束时间!");
            return;
        }
        if (tv_sampling.getText().toString().trim().length() == 0) {
            msg("请选择采样人员!");
            return;
        }



//        if (samplingAdapter.getData().size() < 1) {
//            msg("请至少选择2位采样人员!");
//            return;
//        }
//        if (factors.length() == 0) {
//            msg("请编辑实验人员!");
//            return;
//        }
//
//        if (tv_assist.getText().toString().trim().length() == 0) {
//            msg("请编辑辅助人员!");
//            return;
//        }
//        if (tv_cars.getText().toString().trim().length() == 0) {
//            msg("请选择车辆!");
//            return;
//        }
        /**
         * 采样人员
         */
        List<SamplinguserBean> samplinguserBeanList = new ArrayList<>();
        for (int i = 0; i < samplingAdapter.getData().size(); i++) {
            if (samplingAdapter.getData().get(i).isChoice()) {
                SamplinguserBean samplinguserBean = new SamplinguserBean();
                samplinguserBean.setUserid(samplingAdapter.getData().get(i).getId() + "");
                samplinguserBeanList.add(samplinguserBean);
            }
        }
        if(samplinguserBeanList.size()<2){
            msg("请最少选择2位采样人员!");
            return;
        }

        samplinguser = JSON.toJSONString(samplinguserBeanList);
        /**
         * 辅助人员
         */
        List<SamplinguserBean> samp_fuzhu = new ArrayList<>();
        for (int i = 0; i < assistAdapter.getData().size(); i++) {
            if (assistAdapter.getData().get(i).isChoice()) {
                SamplinguserBean samplinguserBean = new SamplinguserBean();
                samplinguserBean.setUserid(assistAdapter.getData().get(i).getId() + "");
                samp_fuzhu.add(samplinguserBean);
            }
        }
        assistuser = JSON.toJSONString(samp_fuzhu);
        /**
         * 车辆信息
         */
        List<Submit_carBean> carBeanList = new ArrayList<>();
        for (int i = 0; i < carInfoAdapter.getData().size(); i++) {
            if (carInfoAdapter.getData().get(i).isChoice()) {
                Submit_carBean carBean = new Submit_carBean();
                carBean.setVid(carInfoAdapter.getData().get(i).getVid() + "");
                carBeanList.add(carBean);
            }
        }
        cars = JSON.toJSONString(carBeanList);
        /**
         * 设备信息
         */
        List<Submit_equipsBean> equipsBeanList = new ArrayList<>();
        for (int i = 0; i < schemeDeviceAdapter.getData().size(); i++) {

            if (!schemeDeviceAdapter.getData().get(i).getSumnumber().equals("0")) {
                Submit_equipsBean equipsBean = new Submit_equipsBean();
                equipsBean.setCategoryid(schemeDeviceAdapter.getData().get(i).getCategoryid());
                equipsBean.setNumber(schemeDeviceAdapter.getData().get(i).getSumnumber());
                equipsBeanList.add(equipsBean);
            }
        }
        equips = JSON.toJSONString(equipsBeanList);
        /**
         * 耗材
         */
        List<Submit_equipsBean> consumablesbean = new ArrayList<>();
        for (int i = 0; i < agentiaAdater.getData().size(); i++) {

            if (!agentiaAdater.getData().get(i).getSumnumber().equals("0")) {

                Submit_equipsBean equipsBeans = new Submit_equipsBean();
                equipsBeans.setCategoryid(agentiaAdater.getData().get(i).getCategoryid());
                equipsBeans.setNumber(agentiaAdater.getData().get(i).getSumnumber());
                equipsBeans.setConsumablesid(agentiaAdater.getData().get(i).getConsumablesid());
                consumablesbean.add(equipsBeans);
            }

        }
        consumables = JSON.toJSONString(consumablesbean);
        ViseLog.d("sssss" + consumables);

        /**
         * 二次弹框确认
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认发布任务吗?");
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

                ShowDialogtitle("请稍等...", Task_AddActivity.this);
                String data = "{conid:\"" + conid + "\",currenttasknumber:\"" + currenttasknumber + "\",subject:\"" + subject + "\",content:\"" + content + "\",loginId:\"" + loginId + "\",manager:\"" + manager + "\",starttime:\"" + starttime + "\",endtime:\"" + endtime + "\",remark:\"" + remark + "\",samplinguser:" + samplinguser + ",assistuser:" + assistuser + ",cars:" + cars + ",equips:" + equips + ",consumables:" + consumables + "} ";
                addPresenter.AddTasksInfo(data);

            }
        });
        builder.show();

    }

    @Subscribe(sticky = true)
    public void exEvent(ExperimentEvent event) {
//        /**
//         * 数据回传
//         */
//        factorsExperimenterDataBeans = new ArrayList<>();
//        factorsExperimenterDataBeans.addAll(event.getFactorsExperimenterDataBeanArrayList());
//        List<Submit_factorsBean> submitFactorsBeanList = new ArrayList<>();
//        for (int i = 0; i < event.getFactorsExperimenterDataBeanArrayList().size(); i++) {
//            List<Submit_factorsBean.UsersBean> usersBeanList = new ArrayList<>();
//            Submit_factorsBean submit_factorsBean = new Submit_factorsBean();
//            submit_factorsBean.setContentid(event.getFactorsExperimenterDataBeanArrayList().get(i).getContentid());
//            Submit_factorsBean.UsersBean usersBean = new Submit_factorsBean.UsersBean();
//            usersBean.setUserid(event.getFactorsExperimenterDataBeanArrayList().get(i).getUser_save().get(0).getId() + "");
//            usersBeanList.add(usersBean);
//            submit_factorsBean.setUsers(usersBeanList);
//            submitFactorsBeanList.add(submit_factorsBean);
//        }
//        factors = JSON.toJSONString(submitFactorsBeanList);
//        ViseLog.d(factors);
    }

    @Override
    public void OnNoPointsTaskData(List<NoPointsTaskDataBean> noPointsTaskDataBeans) {

        if (noPointsTaskDataBeans.size() > 0 && noPointsTaskDataBeans != null) {
            pointsid = noPointsTaskDataBeans.get(0).getPointsid();
            tv_address.setText(noPointsTaskDataBeans.get(0).getAddress());
            nopointAdapter.setNewData(noPointsTaskDataBeans);
//            tv_pinci.setText(noPointsTaskDataBeans.get(0).getDaysNumber() + "天" + noPointsTaskDataBeans.get(0).getFrequency() + "次");
//            /**
//             * 查询实验人员
//             */
//            String data = "{pointsid:\"" + pointsid + "\"} ";
//            addPresenter.GetFactorsExperimenterData(data);
            /**
             * 查询采样人员
             */
            String data_sampling = "{userid:\"\",username:\"\"}";
            addPresenter.GetSamplingAssistData(data_sampling);
//            /**
//             * 查询辅助人员
//             */
//            String data_assist = "{userid:\"\",username:\"\"}";
//            addPresenter.GetSamplingAssistData(data_assist);
            /**
             * 查询车辆
             */
            String data_car = "{vid:\"\",vlicense:\"\",vitem:\"\"}";
            addPresenter.GetCarDataList(data_car);
            /**
             * 查询设备
             */
            String data_div = "{schemeid:\"\",pointsid:\"" + pointsid + "\", categoryname:\"\", equiptype:\"\",equiptypename:\"采样\",categoryid:\"\"}";
            addPresenter.GetSchemeDevice(data_div);
            /**
             * 查询耗材
             */
            String data_con = "{schemeid:\"\",pointsid:\"" + pointsid + "\", categoryname:\"\",categoryid:\"\"} ";
            addPresenter.GetSchemeConsumables(data_con);


        }

    }

    @Override
    public void OnSamplingAssistData(SamplingAssistDataBean samplingAssistDataBean) {
        samplingAdapter.setNewData(samplingAssistDataBean.getSampling());
        assistAdapter.setNewData(samplingAssistDataBean.getAssist());
    }

    ArrayList<FactorsExperimenterDataBean> factorsExperimenterDataBeanArrayList = new ArrayList<>();

    @Override
    public void OnFactorsExperimenterData(List<FactorsExperimenterDataBean> factorsExperimenterDataBeans) {
//        if (factorsExperimenterDataBeans.size() > 0) {
//            factorsExperimenterDataBeanArrayList = new ArrayList<>();
//            factorsExperimenterDataBeanArrayList.addAll(factorsExperimenterDataBeans);
//        }
    }

    /**
     * 返回车辆信息
     *
     * @param carDataListBeans
     */
    @Override
    public void OnCarDataList(List<CarDataListBean> carDataListBeans) {
        carInfoAdapter.setNewData(carDataListBeans);
    }

    @Override
    public void OnSchemeDevice(List<SchemeDeviceBean> schemeDeviceBeans) {
        schemeDeviceAdapter.setNewData(schemeDeviceBeans);
    }

    @Override
    public void OnSchemeConsumables(List<SchemeConsumablesBean> schemeConsumablesBeans) {
        agentiaAdater.setNewData(schemeConsumablesBeans);
    }

    /**
     * 提交任务
     *
     * @param resultBean
     */
    @Override
    public void onSubmit(ResultBean resultBean) {
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

    /**
     * dialog init
     *
     * @param view
     * @param strTitle
     * @param strContent
     * @param iv
     */
    public void initDia_View(View view, String strTitle, String strContent, int iv) {
        TextView tv_title = view.findViewById(R.id.tv_title);
        ImageView iv_icon = view.findViewById(R.id.iv_icon);
        TextView tv_content = view.findViewById(R.id.tv_content);
        tv_title.setText(strTitle);
        iv_icon.setImageResource(iv);
        tv_content.setText(strContent);
    }

}

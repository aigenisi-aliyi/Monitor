package com.monitor.accuse;

import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.AddSampleInfoActivity;
import com.monitor.changtian.activity.ZBarActivity_;
import com.monitor.changtian.adapter.AddCccuseAdapter;
import com.monitor.changtian.adapter.Analysi_YzAdapter;
import com.monitor.changtian.adapter.Analysi_Yz_GoneAdapter;
import com.monitor.changtian.adapter.AnalysisAdapter;
import com.monitor.changtian.adapter.PreInfoDataadapter;
import com.monitor.changtian.adapter.PreListDataAdapter;
import com.monitor.changtian.adapter.SchemeFidsAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.AccusePointsBean;
import com.monitor.changtian.bean.AccuseShowBean;
import com.monitor.changtian.bean.DayNum_FrequencyBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EventBus.AccuseBeanEvent;
import com.monitor.changtian.bean.EventBus.AddAccEvent;
import com.monitor.changtian.bean.EventBus.BarCodeEvent;
import com.monitor.changtian.bean.FaceItemBean;
import com.monitor.changtian.bean.PotinsBean;
import com.monitor.changtian.bean.PreDataBean;
import com.monitor.changtian.bean.PreInfoDataBean;
import com.monitor.changtian.bean.ReshEvent;
import com.monitor.changtian.bean.SampleInfoByPointInfoBean;
import com.monitor.changtian.bean.SchemeFidsBean;
import com.monitor.changtian.presenter.AddAccusePresenter;
import com.monitor.changtian.view.AddAccuseView;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 增加质控信息界面
 */
@EActivity(R.layout.activity_add_accuse)
public class AddAccuseActivity extends BaseActvity implements AddAccuseView, AddCccuseAdapter.OnEditValueChangedListener_Con, BaseActvity.Rightlistener {

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    /**
     * 传递点位信息 因子信息
     */
    @Extra
    ArrayList<AccusePointsBean> accusePointsBeanList;

    @Extra
    ArrayList<String> numBerList;

    @Extra
    String maxdaynumbers;

    @Extra
    String smid;
    List<AccusePointsBean.FactorsBean> factorsBeanList = new ArrayList<>();
    Analysi_YzAdapter analysi_yzAdapter;
    AddCccuseAdapter addCccuseAdapter;
    AddAccusePresenter addAccusePresenter;
    AlertDialog analysis_dia, analysis_diass, analysis_biaozhun;
    List<DicDataBean> basicslist_quality = new ArrayList<>();
    Analysi_Yz_GoneAdapter analysi_yz_goneAdapter;
    SchemeFidsAdapter schemeFidsAdapter;

    //天数 //频次选择
    List<DayNum_FrequencyBean> DaysList = new ArrayList<>();
    List<DayNum_FrequencyBean> FrequencyList = new ArrayList<>();
    PreListDataAdapter dataAdapter;
    PreInfoDataadapter preInfoDataadapter;
    String dayst = "";
    String prest = "";

    @AfterViews
    void init() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
//
//        if (numBerList != null) {
//            msg("size:" + numBerList.size());
//        }

        initRightOnclikText("添加质控", "确定", this);
        analysi_yzAdapter = new Analysi_YzAdapter(R.layout.nopoint_select_item);
        schemeFidsAdapter = new SchemeFidsAdapter(R.layout.nopoint_select_item);
        dataAdapter = new PreListDataAdapter(R.layout.nopoint_select_sing);
        preInfoDataadapter = new PreInfoDataadapter(R.layout.nopoint_select_sing);
        analysi_yz_goneAdapter = new Analysi_Yz_GoneAdapter(R.layout.nopoint_select_item);
        addAccusePresenter = new AddAccusePresenter(this);
        addCccuseAdapter = new AddCccuseAdapter(R.layout.addccuse_item);
        addCccuseAdapter.setmEditListenter(this);
        rv_list.setLayoutManager(new GridLayoutManager(AddAccuseActivity.this, 2));
        rv_list.setAdapter(addCccuseAdapter);
        /**
         * 添加头布局
         */
        View view_foot = AddAccuseActivity.this.getLayoutInflater().inflate(R.layout.add_accuse_head, (ViewGroup) rv_list.getParent(), false);
        addCccuseAdapter.addHeaderView(view_foot);

        View view_foots = AddAccuseActivity.this.getLayoutInflater().inflate(R.layout.add_accuse_foot, (ViewGroup) rv_list.getParent(), false);
        addCccuseAdapter.addFooterView(view_foots);
        tv_bar_code = view_foots.findViewById(R.id.tv_bar_code);
        iv_bar_code = view_foots.findViewById(R.id.iv_bar_code);
        ll_code = view_foots.findViewById(R.id.ll_code);
        initView_head(view_foot);
//        msg(accusePointsBeanList.size() + "");
        initData();
    }

    public void initData() {

        /**
         * 查询质控类型
         */
        String data = "{id:\"\",typeCode:\"qualitycontrol\",DataValue:\"\",DataCode:\"1\",querystring:\"\"} ";
        addAccusePresenter.GetDevData(data);

    }

    boolean issubmit_x = true;

    @Override
    public void rightlistener() {

        if (addCccuseAdapter.getData().size() == 0) {
            msg("请选择检测因子!");
            return;
        }

//        if (tv_acc_type.getText().equals("质控样")) {
//
//            for (int i = 0; i < addCccuseAdapter.getData().size(); i++) {
//                if (addCccuseAdapter.getData().get(i).getValue().length() > 0) {
//                    issubmit_x = true;
//                } else {
//                    issubmit_x = false;
//                    break;
//                }
//            }
//
//            if (!issubmit_x) {
//                msg("请确保质控样的数值完整!");
//                return;
//            }
//        }


        if (tv_acc_type.getText().equals("质控样") || tv_acc_type.getText().equals("全程序空白")) {
            if (tv_bar_code.getText().toString().trim().length() == 0) {
                msg("条码信息不能为空!");
                return;
            }
        }
        AgainDialog("请仔细核对信息");
    }

    public void AgainTrue() {
        Submit();
    }

    public void Submit() {

        String days = "";
        String pres = "";

        if (dayst.length() > 0) {
            days = dayst;
        } else {
            days = "1";
        }
        if (prest.length() > 0) {
            pres = prest;
        } else {
            pres = "1";
        }

        List<PotinsBean.SubmitPointBean> submitPointBeans = new ArrayList<>();
        List<AccuseShowBean> accuseShowBeanList = new ArrayList<>();
        AccuseShowBean accuseShowBean = new AccuseShowBean();
        accuseShowBean.setPotionName(content);
        accuseShowBean.setPointsid(potionid);
        accuseShowBean.setQualitycontrolname(acc_typename);
        accuseShowBean.setIshow(false);
        accuseShowBean.setDaysNumber(days);
        accuseShowBean.setFrequency(pres);
        if (tv_acc_type.getText().equals("质控样")) {
            List<AccuseShowBean.FactorBean> factorBeans = new ArrayList<>();
            for (int i = 0; i < addCccuseAdapter.getData().size(); i++) {
                PotinsBean.SubmitPointBean submitPointBean = new PotinsBean.SubmitPointBean();
                submitPointBean.setFid(addCccuseAdapter.getData().get(i).getId());
                submitPointBean.setSetvalue(addCccuseAdapter.getData().get(i).getValue());
                submitPointBean.setPointsid(potionid);
                submitPointBean.setPointname(content);
                submitPointBean.setQualitycontrol(acc_typeid);
                submitPointBean.setQualitycontrolname(acc_typename);
                submitPointBeans.add(submitPointBean);
                AccuseShowBean.FactorBean bean = new AccuseShowBean.FactorBean();
                bean.setName(addCccuseAdapter.getData().get(i).getName());
                bean.setValue(addCccuseAdapter.getData().get(i).getValue());
                bean.setFid("");
                bean.setSetvalue(addCccuseAdapter.getData().get(i).getValue());
                bean.setQualitycontrol(acc_typeid);
                bean.setContactfnid(addCccuseAdapter.getData().get(i).getId());
                bean.setSamplenumber(tv_bar_code.getText().toString().trim());
                bean.setContentid("");
                bean.setDaysNumber("");
                bean.setBzwz(tv_prelist.getText().toString().trim());
                factorBeans.add(bean);
            }
            accuseShowBean.setDetails(factorBeans);
            accuseShowBeanList.add(accuseShowBean);
        } else if (tv_acc_type.getText().equals("全程序空白")) {
            List<AccuseShowBean.FactorBean> factorBeans = new ArrayList<>();
            for (int i = 0; i < addCccuseAdapter.getData().size(); i++) {
                PotinsBean.SubmitPointBean submitPointBean = new PotinsBean.SubmitPointBean();
                submitPointBean.setFid(addCccuseAdapter.getData().get(i).getId());
                submitPointBean.setSetvalue(addCccuseAdapter.getData().get(i).getValue());
                submitPointBean.setPointsid(potionid);
                submitPointBean.setPointname(content);
                submitPointBean.setQualitycontrol(acc_typeid);
                submitPointBean.setQualitycontrolname(acc_typename);
                submitPointBeans.add(submitPointBean);
                AccuseShowBean.FactorBean bean = new AccuseShowBean.FactorBean();
                bean.setName(addCccuseAdapter.getData().get(i).getName());
                bean.setValue(addCccuseAdapter.getData().get(i).getValue());
                bean.setFid(addCccuseAdapter.getData().get(i).getFid());
                bean.setSetvalue(addCccuseAdapter.getData().get(i).getValue());
                bean.setQualitycontrol(acc_typeid);
                bean.setContactfnid("");
                bean.setDaysNumber(days);
                bean.setBzwz("");
                bean.setSamplenumber(tv_bar_code.getText().toString().trim());
                bean.setContentid(addCccuseAdapter.getData().get(i).getId());
                factorBeans.add(bean);
            }
            accuseShowBean.setDetails(factorBeans);
            accuseShowBeanList.add(accuseShowBean);
        } else {
            List<AccuseShowBean.FactorBean> factorBeans = new ArrayList<>();
            for (int i = 0; i < addCccuseAdapter.getData().size(); i++) {
                PotinsBean.SubmitPointBean submitPointBean = new PotinsBean.SubmitPointBean();
                submitPointBean.setFid(addCccuseAdapter.getData().get(i).getId());
                submitPointBean.setSetvalue(addCccuseAdapter.getData().get(i).getValue());
                submitPointBean.setPointsid(potionid);
                submitPointBean.setPointname(content);
                submitPointBean.setQualitycontrol(acc_typeid);
                submitPointBean.setQualitycontrolname(acc_typename);
                submitPointBeans.add(submitPointBean);
                AccuseShowBean.FactorBean bean = new AccuseShowBean.FactorBean();
                bean.setName(addCccuseAdapter.getData().get(i).getName());
                bean.setValue(addCccuseAdapter.getData().get(i).getValue());
                bean.setFid(addCccuseAdapter.getData().get(i).getId());
                bean.setSetvalue(addCccuseAdapter.getData().get(i).getValue());
                bean.setQualitycontrol(acc_typeid);
                bean.setContactfnid("");
                bean.setDaysNumber("");
                bean.setSamplenumber("");
                bean.setBzwz("");
                bean.setContentid("");
                factorBeans.add(bean);
            }
            accuseShowBean.setDetails(factorBeans);
            accuseShowBeanList.add(accuseShowBean);
        }


        finish();
        EventBus.getDefault().post(new AccuseBeanEvent(submitPointBeans, accuseShowBeanList));

    }

    /**
     * 初始化头布局控件
     */
    TextView tv_potion, tv_acc_type, tv_yinzi, tv_acc_typeName, tv_day, tv_fre, tv_prelist, tv_bar_code;
    LinearLayout ll_fenlei, ll_baceinfo, ll_fre, ll_code;
    String potionid = "";
    String content = "";
    String acc_typeid = "";
    String acc_typename = "";

    ImageView iv_bar_code;


    String typeid = "";
    String prelistid = "";

    public void initView_head(View view_foot) {

        tv_prelist = view_foot.findViewById(R.id.tv_prelist);
        ll_fre = view_foot.findViewById(R.id.ll_fre);
        ll_baceinfo = view_foot.findViewById(R.id.ll_baceinfo);
        tv_potion = view_foot.findViewById(R.id.tv_potion);
        tv_acc_type = view_foot.findViewById(R.id.tv_acc_type);
        tv_yinzi = view_foot.findViewById(R.id.tv_yinzi);
        tv_acc_typeName = view_foot.findViewById(R.id.tv_acc_typeName);
        ll_fenlei = view_foot.findViewById(R.id.ll_fenlei);
        tv_day = view_foot.findViewById(R.id.tv_day);
        tv_fre = view_foot.findViewById(R.id.tv_fre);

        /**
         * 扫码
         */
        iv_bar_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZBarActivity_.intent(AddAccuseActivity.this).type("质控").start();
            }
        });
        /**
         * 选择点位信息
         */
        tv_potion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (tv_acc_type.getText().length() > 0) {

                    OptionsPickerView pvOptions = new OptionsPickerView.Builder(AddAccuseActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            //返回的分别是三个级别的选中位置
                            content = accusePointsBeanList.get(options1).getPickerViewText();
                            potionid = accusePointsBeanList.get(options1).getId();
                            tv_potion.setText(content);

                            /**
                             * 判断现场空白只针对于气体
                             */
                            if (tv_acc_type.getText().equals("现场空白")) {

                                if (accusePointsBeanList.get(options1).getTypeid().equals("43") ||
                                        accusePointsBeanList.get(options1).getTypeid().equals("44") ||
                                        accusePointsBeanList.get(options1).getTypeid().equals("45") ||
                                        accusePointsBeanList.get(options1).getTypeid().equals("46")) {
                                    content = accusePointsBeanList.get(options1).getPickerViewText();
                                    potionid = accusePointsBeanList.get(options1).getId();
                                    tv_potion.setText(content);
                                } else {

                                    msg("现场空白只局限于气体样品");
                                    content = "";
                                    tv_potion.setText("");
                                    detailsBeans = new ArrayList<>();
                                }
                            }

                            /**
                             *如果是气体 就隐藏分组选择
                             */
                            if (accusePointsBeanList.get(options1).getTypeid().equals("43") ||
                                    accusePointsBeanList.get(options1).getTypeid().equals("44") ||
                                    accusePointsBeanList.get(options1).getTypeid().equals("45") ||
                                    accusePointsBeanList.get(options1).getTypeid().equals("46")) {
                                ll_fenlei.setVisibility(View.GONE);
                            } else {
                                ll_fenlei.setVisibility(View.VISIBLE);
                            }
                            //天数数据
                            DaysList = new ArrayList<>();
                            //频次数据
                            FrequencyList = new ArrayList<>();

                            ///天数
                            int days = Integer.parseInt(accusePointsBeanList.get(options1).getDaysNumber());
                            for (int i = 0; i < days; i++) {
                                DayNum_FrequencyBean dayNum_frequencyBean = new DayNum_FrequencyBean();
                                dayNum_frequencyBean.setName("第" + (i + 1) + "天");
                                dayNum_frequencyBean.setNum((i + 1) + "");
                                DaysList.add(dayNum_frequencyBean);
                            }
                            //频次
                            int pinci = Integer.parseInt(accusePointsBeanList.get(options1).getFrequency());
                            for (int i = 0; i < pinci; i++) {
                                DayNum_FrequencyBean dayNum_frequencyBean = new DayNum_FrequencyBean();
                                dayNum_frequencyBean.setName("第" + (i + 1) + "次");
                                dayNum_frequencyBean.setNum((i + 1) + "");
                                FrequencyList.add(dayNum_frequencyBean);
                            }
                            /**
                             * 查询质控因子分组信息
                             */
                            ShowDialogtitle("请稍等......", AddAccuseActivity.this);
                            String datas = "{pointsid:\"" + potionid + "\",taskid:\"\",currentnumber:\"\",types:\"1\"}";
                            addAccusePresenter.GetSampleInfoByPointInfo(datas);

                            for (int i = 0; i < analysi_yzAdapter.getData().size(); i++) {
                                analysi_yzAdapter.getData().get(i).setChoice_save(false);
                                analysi_yzAdapter.getData().get(i).setChoice(false);
                            }


                            for (int i = 0; i < analysi_yz_goneAdapter.getData().size(); i++) {
                                analysi_yz_goneAdapter.getData().get(i).setSelected(false);
                                analysi_yz_goneAdapter.getData().get(i).setSelected(false);
                            }

                            tv_acc_typeName.setText("");
//                      tv_acc_type.setText("");
                            List<FaceItemBean> faceItemBeans = new ArrayList<>();
                            addCccuseAdapter.setNewData(faceItemBeans);
                            addCccuseAdapter.notifyDataSetChanged();

                            List<SampleInfoByPointInfoBean.DetailsBean.FactorsBean> s = new ArrayList<>();
                            analysi_yzAdapter.setNewData(s);
                        }
                    })
                            .setTitleText("选择点位信息")
                            .setDividerColor(Color.BLACK)
                            .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                            .setContentTextSize(20)
                            .setOutSideCancelable(true)// default is true
                            .build();
                    pvOptions.setPicker(accusePointsBeanList);//一级选择器
                    pvOptions.show();
                } else {
                    msg("请先选择质控类型");
                }
            }
        });
        /**
         * 天数
         */
        tv_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OptionsPickerView pvOptions = new OptionsPickerView.Builder(AddAccuseActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        tv_day.setText(DaysList.get(options1).getPickerViewText());
                        dayst = DaysList.get(options1).getNum();
                    }
                })
                        .setTitleText("选择质控天数")
                        .setDividerColor(Color.BLACK)
                        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                        .setContentTextSize(20)
                        .setOutSideCancelable(true)// default is true
                        .build();
                pvOptions.setPicker(DaysList);//一级选择器
                pvOptions.show();

            }
        });

        /**
         * 频次
         */
        tv_fre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(AddAccuseActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        tv_fre.setText(FrequencyList.get(options1).getPickerViewText());
                        prest = FrequencyList.get(options1).getNum();
                    }
                })
                        .setTitleText("选择质控频次")
                        .setDividerColor(Color.BLACK)
                        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                        .setContentTextSize(20)
                        .setOutSideCancelable(true)// default is true
                        .build();
                pvOptions.setPicker(FrequencyList);//一级选择器
                pvOptions.show();
            }
        });

        /**
         * 选择质控类型
         */
        tv_acc_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OptionsPickerView pvOptions = new OptionsPickerView.Builder(AddAccuseActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        acc_typename = basicslist_quality.get(options1).getPickerViewText();
                        acc_typeid = basicslist_quality.get(options1).getId() + "";

                        tv_acc_type.setText(acc_typename);
                        for (int i = 0; i < analysi_yzAdapter.getData().size(); i++) {
                            analysi_yzAdapter.getData().get(i).setChoice_save(false);
                            analysi_yzAdapter.getData().get(i).setChoice(false);
                        }
                        for (int i = 0; i < analysi_yz_goneAdapter.getData().size(); i++) {
                            analysi_yz_goneAdapter.getData().get(i).setSelected(false);
                            analysi_yz_goneAdapter.getData().get(i).setSelected(false);
                        }
                        List<FaceItemBean> faceItemBeans = new ArrayList<>();
                        addCccuseAdapter.setNewData(faceItemBeans);
                        addCccuseAdapter.notifyDataSetChanged();

                        if (acc_typename.equals("质控样")) {
                            ll_baceinfo.setVisibility(View.GONE);
                            ll_fre.setVisibility(View.VISIBLE);
                            ll_fenlei.setVisibility(View.GONE);
                            ll_code.setVisibility(View.VISIBLE);

                            ShowDialogtitle("请稍等...", AddAccuseActivity.this);
                            String data = "{Tables:\"T_SS_MS\",schemeid:\"" + smid + "\"}";
                            addAccusePresenter.GetPreData(data);
                        } else if (acc_typename.equals("全程序空白")) {

                            ll_code.setVisibility(View.VISIBLE);
                            ll_baceinfo.setVisibility(View.VISIBLE);
                            ll_fre.setVisibility(View.GONE);
                            ll_fenlei.setVisibility(View.GONE);
                            tv_potion.setVisibility(View.GONE);
                            tv_fre.setVisibility(View.GONE);
                            /**
                             * 调用查询接口
                             */
                            ShowDialogtitle("请稍等...", AddAccuseActivity.this);
                            String data = "{schemeid:\"" + smid + "\",pcid:\"\",fid:\"\",isdirect:\"0\"}";
                            addAccusePresenter.GetSchemeFids(data);
                            //天数数据
                            DaysList = new ArrayList<>();
                            ///天数

                            if(maxdaynumbers!=null){
                                int days = Integer.parseInt(maxdaynumbers);
                                for (int i = 0; i < days; i++) {
                                    DayNum_FrequencyBean dayNum_frequencyBean = new DayNum_FrequencyBean();
                                    dayNum_frequencyBean.setName("第" + (i + 1) + "天");
                                    dayNum_frequencyBean.setNum((i + 1) + "");
                                    DaysList.add(dayNum_frequencyBean);
                                }
                            }

                        } else {
                            ll_code.setVisibility(View.GONE);
                            ll_baceinfo.setVisibility(View.VISIBLE);
                            ll_fre.setVisibility(View.GONE);
                            tv_potion.setVisibility(View.VISIBLE);
                            tv_fre.setVisibility(View.VISIBLE);
                            ll_fenlei.setVisibility(View.VISIBLE);
                        }
                    }
                })
                        .setTitleText("选择质控类型")
                        .setDividerColor(Color.BLACK)
                        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                        .setContentTextSize(20)
                        .setOutSideCancelable(true)// default is true
                        .build();
                pvOptions.setPicker(basicslist_quality);//一级选择器
                pvOptions.show();

            }
        });

        /**
         * 选择因子分类信息
         */
        tv_acc_typeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (tv_acc_type.getText().toString().equals("现场空白")) {
                    msg("现场空白只局限于气体样品");
                } else {
                    if (detailsBeans.size() > 0) {
                        OptionsPickerView pvOptions = new OptionsPickerView.Builder(AddAccuseActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                tv_acc_typeName.setText(detailsBeans.get(options1).getPickerViewText());
                                analysi_yzAdapter.setNewData(detailsBeans.get(options1).getFactors());
                                List<FaceItemBean> faceItemBeans = new ArrayList<>();
                                addCccuseAdapter.setNewData(faceItemBeans);
                                addCccuseAdapter.notifyDataSetChanged();
                            }
                        })
                                .setTitleText("选择检测因子分类")
                                .setDividerColor(Color.BLACK)
                                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                                .setContentTextSize(20)
                                .setOutSideCancelable(true)// default is true
                                .build();
                        pvOptions.setPicker(detailsBeans);//一级选择器
                        pvOptions.show();

                    } else {
                        msg("暂无检测因子分类");
                    }
                }


            }
        });


        /**
         * 选择标准物质
         * tv_prelist
         *
         */
        tv_prelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dataAdapter.getData().size()>0){
                    ShowBIAOZHUN();
                }else {
                    msg("标准物质为空!");
                }


            }
        });


        /**
         * 选择因子信息
         */
        tv_yinzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (tv_acc_type.getText().length() == 0) {
                    msg("请选择质控类型");
                    return;
                }
                if (tv_acc_type.getText().equals("质控样")) {


                    if (tv_prelist.getText().length() > 0) {
                        ShowDialogtitle("请稍等...", AddAccuseActivity.this);
                        String data = "{\"col1\":\"" + prelistid + "\",\"id\":\"\",\"col3\":\"\",\"col4\":\"\",schemeid:\"" + smid + "\"}";
                        addAccusePresenter.OnGetPreInfoData(data);
                    }
//                    else {
//                        msg("请先选择标准物质");
//                    }

                } else if (tv_acc_type.getText().equals("全程序空白")) {
                    /**
                     * 选择全程序空白
                     */
                    SchemeFids();
                } else {
                    if (analysi_yzAdapter.getData().size() > 0) {
//                    /**
//                     *如果是气体 就隐藏分组选择
//                     */
//                    if (accusePointsBeanList.get(options1).getTypeid().equals("43") ||
//                            accusePointsBeanList.get(options1).getTypeid().equals("44") ||
//                            accusePointsBeanList.get(options1).getTypeid().equals("45") ||
//                            accusePointsBeanList.get(options1).getTypeid().equals("46")) {
//                        ll_fenlei.setVisibility(View.GONE);
//                    } else {
//                        ll_fenlei.setVisibility(View.VISIBLE);
//                    }
//

                        if (ll_fenlei.getVisibility() == View.GONE) {
                            Selector_Gone();
                        } else {
                            SelectorYz();
                        }


                    } else {
                        msg("请选择检测因子分类");
                    }
                }


            }
        });


    }

    public void ShowBIAOZHUN() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddAccuseActivity.this);
        View view = LayoutInflater.from(AddAccuseActivity.this).inflate(R.layout.analysis_item, null);
        builder.setView(view);
        analysis_biaozhun = builder.show();
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText("选择标准物质");
        rv_list.setLayoutManager(new GridLayoutManager(AddAccuseActivity.this, 3));
        rv_list.setAdapter(dataAdapter);
        dataAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                dataAdapter.setSelection(position);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idss = "";
                String text = "";

                for (int i = 0; i < dataAdapter.getData().size(); i++) {

                    if (dataAdapter.getData().get(i).isSelected() == true) {

                        prelistid = dataAdapter.getData().get(i).getId() + "";
                        text = dataAdapter.getData().get(i).getCol2() + "(" + dataAdapter.getData().get(i).getCol9() + ")";
                    }
                }
                idss = prelistid;
                tv_prelist.setText(text);
                analysis_biaozhun.dismiss();
            }
        });

        Button btn_false = view.findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analysis_biaozhun.dismiss();
            }
        });

    }


    public void SchemeFids() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddAccuseActivity.this);
        View view = LayoutInflater.from(AddAccuseActivity.this).inflate(R.layout.analysis_item, null);
        builder.setView(view);
        analysis_dia = builder.show();
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new GridLayoutManager(AddAccuseActivity.this, 3));
        rv_list.setAdapter(schemeFidsAdapter);

        schemeFidsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                schemeFidsAdapter.setSelection(position);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<FaceItemBean> faceItemBeans = new ArrayList<>();
                for (int i = 0; i < schemeFidsAdapter.getData().size(); i++) {
                    if (schemeFidsAdapter.getData().get(i).isSelected() == true) {
                        FaceItemBean faceItemBean = new FaceItemBean();
                        faceItemBean.setId(schemeFidsAdapter.getData().get(i).getContentid());
                        faceItemBean.setFid(schemeFidsAdapter.getData().get(i).getFid());
                        faceItemBean.setName(schemeFidsAdapter.getData().get(i).getFactorname());
                        faceItemBean.setValue("");
                        faceItemBean.setType("0");
                        faceItemBeans.add(faceItemBean);
                    }
                }
                addCccuseAdapter.setNewData(faceItemBeans);
                addCccuseAdapter.notifyDataSetChanged();
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


    public void PreSelector() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(AddAccuseActivity.this);
        View views = LayoutInflater.from(AddAccuseActivity.this).inflate(R.layout.analysis_item, null);
        builder.setView(views);
        analysis_diass = builder.show();
        TextView tv_title = views.findViewById(R.id.tv_title);
        tv_title.setText("选择检测因子");
        RecyclerView rv_list = views.findViewById(R.id.rv_list);
        Button btn_submit = views.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new GridLayoutManager(AddAccuseActivity.this, 1));
        rv_list.setAdapter(preInfoDataadapter);

        preInfoDataadapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                preInfoDataadapter.setSelection(position);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            List<FaceItemBean> faceItemBeans = new ArrayList<>();

            @Override
            public void onClick(View view) {
                for (int i = 0; i < preInfoDataadapter.getData().size(); i++) {
                    if (preInfoDataadapter.getData().get(i).isSelected() == true) {
                        FaceItemBean faceItemBean = new FaceItemBean();
                        faceItemBean.setId(preInfoDataadapter.getData().get(i).getId());
                        faceItemBean.setName(preInfoDataadapter.getData().get(i).getCol4() + "(" + preInfoDataadapter.getData().get(i).getCol5() + ")");
                        faceItemBean.setValue("");
                        faceItemBean.setFid("");
                        faceItemBean.setType("0");
                        faceItemBeans.add(faceItemBean);
                    }
                }
//                ViseLog.d(JSON.toJSONString(faceItemBeans));
//                msg(faceItemBeans.size() + "");
                addCccuseAdapter.setNewData(faceItemBeans);
                addCccuseAdapter.notifyDataSetChanged();
                analysis_diass.dismiss();
            }
        });

        Button btn_false = views.findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analysis_diass.dismiss();
            }
        });
    }


    public void Selector_Gone() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddAccuseActivity.this);
        View view = LayoutInflater.from(AddAccuseActivity.this).inflate(R.layout.analysis_item, null);
        builder.setView(view);
        analysis_dia = builder.show();
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new GridLayoutManager(AddAccuseActivity.this, 1));
        rv_list.setAdapter(analysi_yz_goneAdapter);
        analysi_yz_goneAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                analysi_yz_goneAdapter.setSelection(position);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            List<FaceItemBean> faceItemBeans = new ArrayList<>();

            @Override
            public void onClick(View view) {
                for (int i = 0; i < analysi_yz_goneAdapter.getData().size(); i++) {
                    if (analysi_yz_goneAdapter.getData().get(i).isSelected() == true) {
                        if (tv_acc_type.getText().equals("质控样")) {
                            FaceItemBean faceItemBean = new FaceItemBean();
                            faceItemBean.setId(analysi_yz_goneAdapter.getData().get(i).getFid());
                            faceItemBean.setName(analysi_yz_goneAdapter.getData().get(i).getFactorname());
                            faceItemBean.setValue("");
                            faceItemBean.setFid("");
                            faceItemBean.setType("1");
                            faceItemBeans.add(faceItemBean);
                        } else {
                            FaceItemBean faceItemBean = new FaceItemBean();
                            faceItemBean.setId(analysi_yz_goneAdapter.getData().get(i).getFid());
                            faceItemBean.setName(analysi_yz_goneAdapter.getData().get(i).getFactorname());
                            faceItemBean.setValue("");
                            faceItemBean.setFid("");
                            faceItemBean.setType("0");
                            faceItemBeans.add(faceItemBean);
                        }
                    }
                }

                addCccuseAdapter.setNewData(faceItemBeans);
                addCccuseAdapter.notifyDataSetChanged();
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
     * 选择因子信息
     */
    public void SelectorYz() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddAccuseActivity.this);
        View view = LayoutInflater.from(AddAccuseActivity.this).inflate(R.layout.analysis_item, null);
        builder.setView(view);
        analysis_dia = builder.show();
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new GridLayoutManager(AddAccuseActivity.this, 3));
        rv_list.setAdapter(analysi_yzAdapter);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<FaceItemBean> faceItemBeans = new ArrayList<>();
                for (int i = 0; i < analysi_yzAdapter.getData().size(); i++) {
                    if (analysi_yzAdapter.getData().get(i).isChoice()) {
                        analysi_yzAdapter.getData().get(i).setChoice_save(true);

                        if (tv_acc_type.getText().equals("质控样")) {
                            FaceItemBean faceItemBean = new FaceItemBean();
                            faceItemBean.setId(analysi_yzAdapter.getData().get(i).getFid());
                            faceItemBean.setName(analysi_yzAdapter.getData().get(i).getFactorname());
                            faceItemBean.setValue("");
                            faceItemBean.setType("1");
                            faceItemBean.setFid("");
                            faceItemBeans.add(faceItemBean);
                        } else {
                            FaceItemBean faceItemBean = new FaceItemBean();
                            faceItemBean.setId(analysi_yzAdapter.getData().get(i).getFid());
                            faceItemBean.setName(analysi_yzAdapter.getData().get(i).getFactorname());
                            faceItemBean.setValue("");
                            faceItemBean.setFid("");
                            faceItemBean.setType("0");
                            faceItemBeans.add(faceItemBean);
                        }
                    } else {
                        analysi_yzAdapter.getData().get(i).setChoice_save(false);
                    }
                }
                addCccuseAdapter.setNewData(faceItemBeans);
                addCccuseAdapter.notifyDataSetChanged();
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
    public void Onbasicslist_quality(List<DicDataBean> dicDataBeans) {
        basicslist_quality.clear();
        basicslist_quality.addAll(dicDataBeans);
    }

    List<SampleInfoByPointInfoBean.DetailsBean> detailsBeans = new ArrayList<>();

    @Override
    public void OnSampleInfoByPointInfo(SampleInfoByPointInfoBean sampleInfoByPointInfoBean) {

        DissDialog();
        List<SampleInfoByPointInfoBean.DetailsBean.FactorsBean> factorsBeans = new ArrayList<>();
        if (ll_fenlei.getVisibility() == View.GONE) {
            for (int i = 0; i < sampleInfoByPointInfoBean.getDetails().size(); i++) {
                factorsBeans.addAll(sampleInfoByPointInfoBean.getDetails().get(i).getFactors());
            }
            analysi_yzAdapter.setNewData(factorsBeans);
            analysi_yz_goneAdapter.setNewData(factorsBeans);
//            msg(analysi_yzAdapter.getData().size()+";");
        } else {

            detailsBeans = new ArrayList<>();
            detailsBeans.addAll(sampleInfoByPointInfoBean.getDetails());

        }


    }

    /**
     * 获取标准物质列表
     *
     * @param preDataBean
     */
    @Override
    public void OnGetPreData(PreDataBean preDataBean) {

        DissDialog();
        if (preDataBean != null) {
            if (preDataBean.getDataList().size() > 0) {
                dataAdapter.setNewData(preDataBean.getDataList());
            } else {
                msg("暂无数据");
            }
        }
    }

    /**
     * 获取标准物质检测因子列表
     *
     * @param preInfoDataBeans
     */
    @Override
    public void OnGetPreInfoData(List<PreInfoDataBean> preInfoDataBeans) {
        DissDialog();
        if (preInfoDataBeans.size() > 0) {
            preInfoDataadapter.setNewData(preInfoDataBeans);
            PreSelector();
        }
    }

    @Override
    public void OnSchemeFidsData(List<SchemeFidsBean> schemeFidsBeans) {


        DissDialog();
        if (schemeFidsBeans.size() > 0) {
            schemeFidsAdapter.setNewData(schemeFidsBeans);
        }

    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }


    @Override
    public void Value_con(Editable editable, int position) {

        ViseLog.d("++" + position);
//        FaceItemBean faceItemBean = addCccuseAdapter.getData().get(position);
//        if (editable.toString() != null) {
//            faceItemBean.setValue(editable.toString());
//        } else {
//            faceItemBean.setValue("");
//        }

    }


    Boolean isadd = false;

    @Subscribe
    public void AddAccEvents(AddAccEvent event) {
        if (event != null) {

            if (numBerList.size() > 0) {

                for (int i = 0; i < numBerList.size(); i++) {
                    if (event.getCodeNum().equals(numBerList.get(i))) {
                        msg("样品编码重复");
                        isadd = true;
                        ViseLog.d("111");
                        break;
                    } else {
                        isadd = false;
                    }
                }
                if (!isadd) {
                    tv_bar_code.setText(event.getCodeNum());
                }
            } else {

                tv_bar_code.setText(event.getCodeNum());
            }


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus7
    }
}

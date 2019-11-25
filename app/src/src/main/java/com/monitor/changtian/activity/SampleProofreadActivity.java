package com.monitor.changtian.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.task.SampleinfoActivity;
import com.monitor.changtian.adapter.GridImageAdapter;
import com.monitor.changtian.adapter.Sampleinfo_devAdapter;
import com.monitor.changtian.adapter.Sampleinfo_proAdapter;
import com.monitor.changtian.adapter.Sampleinfo_renAdapter;
import com.monitor.changtian.adapter.Sampleinfo_shijiAdapter;
import com.monitor.changtian.adapter.Sampleinfo_valueAdapter;
import com.monitor.changtian.adapter.Sampleinfo_value_QXAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.CloseEvent;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EventBus.UpdateEvent;
import com.monitor.changtian.bean.FieldsamplingDetailBean;
import com.monitor.changtian.bean.FieldsamplingDetailListBean;
import com.monitor.changtian.bean.FieldsamplingInfo;
import com.monitor.changtian.bean.GetfieldsamplingDetailListBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.monitor.changtian.presenter.SampleProofreadPresenter;
import com.monitor.changtian.presenter.TaskinfoPresenter;
import com.monitor.changtian.view.SampleProofreadView;
import com.monitor.changtian.view.TaskinfoView;
import com.monitor.changtian.widght.FullyGridLayoutManager;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.AUDIT;
import static com.monitor.changtian.constant.PublicConstant.PHOTOS_URL;
import static com.monitor.changtian.constant.PublicConstant.PROOFREAD;

@EActivity(R.layout.activity_sampleinfo)
public class SampleProofreadActivity extends BaseActvity implements TaskinfoView, SampleProofreadView {
    TaskinfoPresenter taskinfoPresenter;

    @Extra
    String sampid;
    @Extra
    String type;
    @Extra
    String stutas;
    String loginId = MyApplication.getInstance().getUser();
    @ViewById(R.id.rv_list_ren)//人员
            RecyclerView rv_list_ren;
    @ViewById(R.id.rv_list_pro)//分析项目
            RecyclerView rv_list_pro;
    @ViewById(R.id.rv_list_value)//现场出值
            RecyclerView rv_list_value;
    @ViewById(R.id.rv_list_shiji)//试剂
            RecyclerView rv_list_shiji;
    @ViewById(R.id.rv_list_dev) //设备
            RecyclerView rv_list_dev;

    @ViewById(R.id.ll_sample_ty)
    LinearLayout ll_sample_ty;
    @ViewById(R.id.et_mix)
    TextView et_mix;
    @ViewById(R.id.et_max)
    TextView et_max;
    @ViewById(R.id.tv_bar_code)
    TextView tv_bar_code;
    @ViewById(R.id.et_statuss)
    TextView et_statuss;
    @ViewById(R.id.tv_pack)
    TextView tv_pack;
    @ViewById(R.id.tv_unit)
    TextView tv_unit;
    @ViewById(R.id.tv_way)
    TextView tv_way;
    @ViewById(R.id.tv_zhuangk)
    TextView tv_zhuangk;
    @ViewById(R.id.tv_temp)
    TextView tv_temp;
    @ViewById(R.id.rv_list_qixiang)
    RecyclerView rv_list_qixiang;
    @ViewById(R.id.et_samplename)
    TextView et_samplename;
    @ViewById(R.id.ll_status)
    LinearLayout ll_status;
    @ViewById(R.id.tv_tr_sd)
    TextView tv_tr_sd;
    @ViewById(R.id.ll_sample_sd)
    LinearLayout ll_sample_sd;
    @ViewById(R.id.ll_sample_zd)
    LinearLayout ll_sample_zd;
    @ViewById(R.id.tv_tr_zd)
    TextView tv_tr_zd;
    @ViewById(R.id.ll_sample_color)
    LinearLayout ll_sample_color;
    @ViewById(R.id.tv_tr_color)
    TextView tv_tr_color;
    @ViewById(R.id.ll_sample_zb)
    LinearLayout ll_sample_zb;
    @ViewById(R.id.tv_tr_gzqk)
    TextView tv_tr_gzqk;
    @ViewById(R.id.ll_sample_tool)
    LinearLayout ll_sample_tool;
    @ViewById(R.id.tv_tr_tool)
    TextView tv_tr_tool;

    @ViewById(R.id.recycler_image)
    RecyclerView recycler_image;
    @ViewById(R.id.recycler_video)
    RecyclerView recycler_video;
    GridImageAdapter adapter_image, adapter_video;
    private List<LocalMedia> selectList_image = new ArrayList<>();
    private List<LocalMedia> selectList_video = new ArrayList<>();
    private List<LocalMedia> selectList_All = new ArrayList<>();

    Sampleinfo_renAdapter sampleinfo_renAdapter;
    Sampleinfo_proAdapter sampleinfo_proAdapter;
    Sampleinfo_valueAdapter sampleinfo_valueAdapter;
    Sampleinfo_shijiAdapter sampleinfo_shijiAdapter;
    Sampleinfo_devAdapter sampleinfo_devAdapter;
    SampleProofreadPresenter sampleProofreadPresenter;
    Sampleinfo_value_QXAdapter sampleinfo_value_qxAdapter;
    @ViewById(R.id.ll_proo)
    LinearLayout ll_proo;
    @ViewById(R.id.ll_audit)
    LinearLayout ll_audit;
    @ViewById(R.id.et_remark)
    EditText et_remark;

    @ViewById(R.id.ll_code)
    LinearLayout ll_code;


    @ViewById(R.id.tv_jd_name)
    TextView tv_jd_name;
    @ViewById(R.id.ll_sample_name)
    LinearLayout ll_sample_name;
    @AfterViews
    void init() {
        initImageBackText("详情");
        if (getIntent().getStringExtra("type") != null) {
            type = getIntent().getStringExtra("type");
        }
        if (getIntent().getStringExtra("sampid") != null) {
            sampid = getIntent().getStringExtra("sampid");
        }
        if (getIntent().getStringExtra("stutas") != null) {
            stutas = getIntent().getStringExtra("stutas");
        }

        taskinfoPresenter = new TaskinfoPresenter(this, this);
        sampleProofreadPresenter = new SampleProofreadPresenter(this, this);
        initData();
        if (type != null) {
            if (type.equals(PROOFREAD)) {
                if (stutas != null) {
                    if (stutas.equals("1")) {
                        ll_proo.setVisibility(View.VISIBLE);
                    }
                }
            } else if (type.equals(AUDIT)) {
                if (stutas != null) {
                    if (stutas.equals("2")) {
                        ll_audit.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    /**
     * 样品校对
     */
    @Click(R.id.stv_true)
    public void stv_true() {

        AgainDialog("确认校对样品信息吗?");

    }

    public void AgainTrue() {
        ShowDialogtitle("请稍等...", SampleProofreadActivity.this);
        String data = "{sampdetailid:\"" + sampid + "\",loginId:\"" + loginId + "\"}";
        sampleProofreadPresenter.proofreadingsamplingdetail(data);
    }

    /**
     * 审核样品
     */
    /**
     * 通过
     */
    String auditopinion = "";

    @Click(R.id.stv_audit_true)
    public void stv_audit_true() {

        if (et_remark.getText().toString().trim().length() == 0) {
            auditopinion = "";
        } else {
            auditopinion = et_remark.getText().toString().trim();
        }
        /**
         * 二次弹框确认
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认审核样品信息吗?");
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
                ShowDialogtitle("请稍等...", SampleProofreadActivity.this);
                String data = "{sampdetailid:\"" + sampid + "\",loginId:\"" + loginId + "\",auditopinion:\"" + auditopinion + "\",ispass:\"1\"}";
                sampleProofreadPresenter.auditsamplingdetail(data);
            }
        });
        builder.show();
    }

    /**
     * 不通过
     */
    @Click(R.id.stv_false)
    public void stv_false() {
        if (et_remark.getText().toString().trim().length() == 0) {
            msg("请输入审核意见!");
            return;
        } else {
            auditopinion = et_remark.getText().toString().trim();
        }

        /**
         * 二次弹框确认
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认提交采样信息吗?");
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
                ShowDialogtitle("请稍等...", SampleProofreadActivity.this);
                String data = "{sampdetailid:\"" + sampid + "\",loginId:\"" + loginId + "\",auditopinion:\"" + auditopinion + "\",ispass:\"0\"}";
                sampleProofreadPresenter.auditsamplingdetail(data);
            }
        });
        builder.show();

    }

    public void initData() {
        ShowDialogtitle("加载中...", SampleProofreadActivity.this);
        String data = "{sampid:\"\",sampdetailid:\"" + sampid + "\",onlynumber:\"\",pointsid:\"\"}";
        taskinfoPresenter.GetfieldsamplingDetail(data);
        initRecycleView();
    }

    public void initRecycleView() {
        /**
         * 人员
         */
        sampleinfo_renAdapter = new Sampleinfo_renAdapter(R.layout.cai_item);
        rv_list_ren.setLayoutManager(new GridLayoutManager(SampleProofreadActivity.this, 3));
        rv_list_ren.setAdapter(sampleinfo_renAdapter);
        /**
         * 分析项目
         */
        sampleinfo_proAdapter = new Sampleinfo_proAdapter(R.layout.cai_item);
        rv_list_pro.setLayoutManager(new GridLayoutManager(SampleProofreadActivity.this, 2));
        rv_list_pro.setAdapter(sampleinfo_proAdapter);

        /**
         * 现场出值
         */
        sampleinfo_valueAdapter = new Sampleinfo_valueAdapter(R.layout.cai_item);
        rv_list_value.setLayoutManager(new GridLayoutManager(SampleProofreadActivity.this, 2));
        rv_list_value.setAdapter(sampleinfo_valueAdapter);

        /**
         *  气象
         */
        sampleinfo_value_qxAdapter = new Sampleinfo_value_QXAdapter(R.layout.cai_item);
        rv_list_qixiang.setLayoutManager(new GridLayoutManager(SampleProofreadActivity.this, 2));
        rv_list_qixiang.setAdapter(sampleinfo_value_qxAdapter);
        /**
         * 试剂
         */
        sampleinfo_shijiAdapter = new Sampleinfo_shijiAdapter(R.layout.cai_item);
        rv_list_shiji.setLayoutManager(new GridLayoutManager(SampleProofreadActivity.this, 2));
        rv_list_shiji.setAdapter(sampleinfo_shijiAdapter);

        /**
         * 设备
         */
        sampleinfo_devAdapter = new Sampleinfo_devAdapter(R.layout.dev_item);
        rv_list_dev.setLayoutManager(new LinearLayoutManager(SampleProofreadActivity.this));
        rv_list_dev.setAdapter(sampleinfo_devAdapter);


    }

    public void initImage() {
        /**
         * 附件
         */
        FullyGridLayoutManager manager_image = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recycler_image.setLayoutManager(manager_image);
        adapter_image = new GridImageAdapter(this, "image");
        adapter_image.setList(selectList_image);
        adapter_image.setSelectMax(0);
        recycler_image.setAdapter(adapter_image);

        adapter_image.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList_image.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                // 预览图片
                PictureSelector.create(SampleProofreadActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList_image);
            }
        });

        FullyGridLayoutManager manager_video = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recycler_video.setLayoutManager(manager_video);
        adapter_video = new GridImageAdapter(this, "video");
        adapter_video.setList(selectList_video);
        adapter_video.setSelectMax(0);
        recycler_video.setAdapter(adapter_video);
        adapter_video.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList_video.get(position);
                String pictureType = media.getPictureType();
                PictureSelector.create(SampleProofreadActivity.this).externalPictureVideo(media.getPath());
            }
        });

    }

    @Override
    public void OnGetTasksInfomation(List<TasksInfomationBean> tasksInfomationBeans) {

    }

    @Override
    public void OnPack(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnUnit(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnStyle(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void onMessage(ResultBean message) {

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

        DissDialog();
        if (fieldsamplingDetailBeans != null) {
            sampleinfo_renAdapter.setNewData(fieldsamplingDetailBeans.get(0).getSampingusers());
            sampleinfo_proAdapter.setNewData(fieldsamplingDetailBeans.get(0).getNoscenefactors());



            List<FieldsamplingDetailBean.ScenefactorsBean> scenefactorsBeans = new ArrayList<>();
            List<FieldsamplingDetailBean.ScenefactorsBean> scenefactorsBeans_qixiang = new ArrayList<>();

            for (int i = 0; i < fieldsamplingDetailBeans.get(0).getScenefactors().size(); i++) {
                if (fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getFactorname().equals("温度")) {
                    FieldsamplingDetailBean.ScenefactorsBean scenefactorsBean = new FieldsamplingDetailBean.ScenefactorsBean();
                    scenefactorsBean.setFactorname(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getFactorname());
                    scenefactorsBean.setMeasuredvalue(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getMeasuredvalue());
                    scenefactorsBean.setUnit(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getUnit());
                    scenefactorsBeans_qixiang.add(scenefactorsBean);
                } else if (fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getFactorname().equals("湿度")) {
                    FieldsamplingDetailBean.ScenefactorsBean scenefactorsBean = new FieldsamplingDetailBean.ScenefactorsBean();
                    scenefactorsBean.setFactorname(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getFactorname());
                    scenefactorsBean.setMeasuredvalue(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getMeasuredvalue());
                    scenefactorsBean.setUnit(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getUnit());
                    scenefactorsBeans_qixiang.add(scenefactorsBean);
                } else if (fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getFactorname().equals("大气压")) {
                    FieldsamplingDetailBean.ScenefactorsBean scenefactorsBean = new FieldsamplingDetailBean.ScenefactorsBean();
                    scenefactorsBean.setFactorname(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getFactorname());
                    scenefactorsBean.setMeasuredvalue(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getMeasuredvalue());
                    scenefactorsBean.setUnit(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getUnit());
                    scenefactorsBeans_qixiang.add(scenefactorsBean);
                } else if (fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getFactorname().equals("风速")) {
                    FieldsamplingDetailBean.ScenefactorsBean scenefactorsBean = new FieldsamplingDetailBean.ScenefactorsBean();
                    scenefactorsBean.setFactorname(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getFactorname());
                    scenefactorsBean.setMeasuredvalue(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getMeasuredvalue());
                    scenefactorsBean.setUnit(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getUnit());
                    scenefactorsBeans_qixiang.add(scenefactorsBean);
                } else if (fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getFactorname().equals("风向")) {
                    FieldsamplingDetailBean.ScenefactorsBean scenefactorsBean = new FieldsamplingDetailBean.ScenefactorsBean();
                    scenefactorsBean.setFactorname(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getFactorname());
                    scenefactorsBean.setMeasuredvalue(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getMeasuredvalue());
                    scenefactorsBean.setUnit(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getUnit());
                    scenefactorsBeans_qixiang.add(scenefactorsBean);
                } else {
                    FieldsamplingDetailBean.ScenefactorsBean scenefactorsBean = new FieldsamplingDetailBean.ScenefactorsBean();
                    scenefactorsBean.setFactorname(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getFactorname());
                    scenefactorsBean.setMeasuredvalue(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getMeasuredvalue());
                    scenefactorsBean.setUnit(fieldsamplingDetailBeans.get(0).getScenefactors().get(i).getUnit());
                    scenefactorsBeans.add(scenefactorsBean);
                }

            }


            ViseLog.d("scenefactorsBeans_qixiang" + JSON.toJSONString(scenefactorsBeans_qixiang));

            sampleinfo_valueAdapter.setNewData(scenefactorsBeans);
            sampleinfo_value_qxAdapter.setNewData(scenefactorsBeans_qixiang);
            sampleinfo_shijiAdapter.setNewData(fieldsamplingDetailBeans.get(0).getReagent());
            sampleinfo_devAdapter.setNewData(fieldsamplingDetailBeans.get(0).getEquips());


            initView(fieldsamplingDetailBeans.get(0));


            selectList_image = new ArrayList<>();
            selectList_video = new ArrayList<>();
            for (int i = 0; i < fieldsamplingDetailBeans.get(0).getFujians().size(); i++) {
                LocalMedia s = new LocalMedia();
                s.setPath(PHOTOS_URL + fieldsamplingDetailBeans.get(0).getFujians().get(i).getFujian());

                if (fieldsamplingDetailBeans.get(0).getFujians().get(i).getFujian().indexOf(".txt") == -1) {
                    if (fieldsamplingDetailBeans.get(0).getFujians().get(i).getFujian().indexOf(".mp4") == -1) {
                        //不包含是图片
                        s.setPath(PHOTOS_URL + fieldsamplingDetailBeans.get(0).getFujians().get(i).getFujian());
                        selectList_image.add(s);
                    } else {
                        //是视频
                        s.setPath(PHOTOS_URL + fieldsamplingDetailBeans.get(0).getFujians().get(i).getFujian());
                        selectList_video.add(s);
                    }
                } else {


                }
            }
            initImage();
        }
    }

    @ViewById(R.id.ll_baoz)
    LinearLayout ll_baoz;
    @ViewById(R.id.ll_liang)
    LinearLayout ll_liang;
    @ViewById(R.id.ll_name)
    LinearLayout ll_name;

    public void initView(FieldsamplingDetailBean fieldsamplingDetailBeans) {
        et_mix.setText(fieldsamplingDetailBeans.getStarttime().substring(0, 16));
        et_max.setText(fieldsamplingDetailBeans.getEndtime().substring(0, 16));

        if (fieldsamplingDetailBeans.getOnlynumber().length() > 0) {
            ll_code.setVisibility(View.VISIBLE);
            tv_bar_code.setText(fieldsamplingDetailBeans.getOnlynumber());
        }

        if (fieldsamplingDetailBeans.getSampingstatus().length() > 0) {
            ll_status.setVisibility(View.VISIBLE);
            et_statuss.setText(fieldsamplingDetailBeans.getSampingstatus());
        }

        if (fieldsamplingDetailBeans.getSampingPackingname().length() > 0) {
            ll_baoz.setVisibility(View.VISIBLE);
            tv_pack.setText(fieldsamplingDetailBeans.getSampingPackingname());
        }

        if (fieldsamplingDetailBeans.getSamplingamount().length() > 0) {
            tv_unit.setText(fieldsamplingDetailBeans.getSamplingamount() + fieldsamplingDetailBeans.getSamplingunitname());
            ll_liang.setVisibility(View.VISIBLE);
        }

        if(fieldsamplingDetailBeans.getProofreadername().length()>0){
            tv_jd_name.setText(fieldsamplingDetailBeans.getProofreadername());
            ll_sample_name.setVisibility(View.VISIBLE);
        }

        if (fieldsamplingDetailBeans.getSamplestylename().length() > 0) {
            tv_way.setText(fieldsamplingDetailBeans.getSamplestylename());
            ll_sample_ty.setVisibility(View.VISIBLE);
        }

        if (fieldsamplingDetailBeans.getSampingname().length() > 0) {
            et_samplename.setText(fieldsamplingDetailBeans.getSampingname());
            ll_name.setVisibility(View.VISIBLE);
        }

        tv_zhuangk.setText(fieldsamplingDetailBeans.getWeathercondition());
        tv_temp.setText(fieldsamplingDetailBeans.getTemperature());

        if (fieldsamplingDetailBeans.getSoilsampletool().length() > 0) {
            tv_tr_tool.setText(fieldsamplingDetailBeans.getSoilsampletool());
            ll_sample_tool.setVisibility(View.VISIBLE);
        }
        if (fieldsamplingDetailBeans.getSoilvegetation().length() > 0) {
            tv_tr_gzqk.setText(fieldsamplingDetailBeans.getSoilvegetation());
            ll_sample_zb.setVisibility(View.VISIBLE);
        }

        if (fieldsamplingDetailBeans.getSoilcolor().length() > 0) {
            tv_tr_color.setText(fieldsamplingDetailBeans.getSoilcolor());
            ll_sample_color.setVisibility(View.VISIBLE);
        }

        if (fieldsamplingDetailBeans.getSoiltexturename().length() > 0) {
            tv_tr_zd.setText(fieldsamplingDetailBeans.getSoiltexturename());
            ll_sample_zd.setVisibility(View.VISIBLE);
        }
        if (fieldsamplingDetailBeans.getSoilhumidityname().length() > 0) {
            tv_tr_sd.setText(fieldsamplingDetailBeans.getSoilhumidityname());
            ll_sample_sd.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void OnList(List<GetfieldsamplingDetailListBean> getfieldsamplingDetailListBeans) {

    }

    @Override
    public void OnMessage(ResultBean resultBean) {
        DissDialog();
        if (resultBean.getResult().equals("1")) {
            EventBus.getDefault().post(new UpdateEvent("taskAudit"));
            EventBus.getDefault().post(new CloseEvent("Detection"));
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

    @Override
    public void OnSoilhumidity(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnSoiltexture(List<DicDataBean> dicDataBeans) {

    }
}

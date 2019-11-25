package com.monitor.changtian.activity.task;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.GridImageAdapter;
import com.monitor.changtian.adapter.Sampleinfo_devAdapter;
import com.monitor.changtian.adapter.Sampleinfo_proAdapter;
import com.monitor.changtian.adapter.Sampleinfo_renAdapter;
import com.monitor.changtian.adapter.Sampleinfo_shijiAdapter;
import com.monitor.changtian.adapter.Sampleinfo_valueAdapter;
import com.monitor.changtian.adapter.Sampleinfo_value_QXAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.FieldsamplingDetailBean;
import com.monitor.changtian.bean.FieldsamplingDetailListBean;
import com.monitor.changtian.bean.FieldsamplingInfo;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.monitor.changtian.presenter.TaskinfoPresenter;
import com.monitor.changtian.view.TaskinfoView;
import com.monitor.changtian.widght.FullyGridLayoutManager;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.PHOTOS_URL;

@EActivity(R.layout.activity_sampleinfo)
public class SampleinfoActivity extends BaseActvity implements TaskinfoView {
    TaskinfoPresenter taskinfoPresenter;
    @Extra
    String sampid;
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

    @ViewById(R.id.et_samplename)
    TextView et_samplename;
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

    @ViewById(R.id.ll_code)
    LinearLayout ll_code;
    @ViewById(R.id.ll_name)
    LinearLayout ll_name;
    @ViewById(R.id.ll_baoz)
    LinearLayout ll_baoz;
    @ViewById(R.id.ll_liang)
    LinearLayout ll_liang;

    @ViewById(R.id.recycler_image)
    RecyclerView recycler_image;
    @ViewById(R.id.recycler_video)
    RecyclerView recycler_video;

    @ViewById(R.id.rv_list_qixiang)
    RecyclerView rv_list_qixiang;
    GridImageAdapter adapter_image, adapter_video;
    private List<LocalMedia> selectList_image = new ArrayList<>();
    private List<LocalMedia> selectList_video = new ArrayList<>();
    private List<LocalMedia> selectList_All = new ArrayList<>();

    Sampleinfo_renAdapter sampleinfo_renAdapter;
    Sampleinfo_proAdapter sampleinfo_proAdapter;
    Sampleinfo_valueAdapter sampleinfo_valueAdapter;
    Sampleinfo_shijiAdapter sampleinfo_shijiAdapter;
    Sampleinfo_devAdapter sampleinfo_devAdapter;
    Sampleinfo_value_QXAdapter sampleinfo_value_qxAdapter;

    @ViewById(R.id.tv_jd_name)
    TextView tv_jd_name;
    @ViewById(R.id.ll_sample_name)
    LinearLayout ll_sample_name;


    @AfterViews
    void init() {
        initImageBackText("详情");
        taskinfoPresenter = new TaskinfoPresenter(this, this);
        initData();
    }

    public void initData() {
        ShowDialogtitle("加载中...", this);
        String data = "{sampid:\"\",sampdetailid:\"" + sampid + "\",onlynumber:\"\",pointsid:\"\"}";
        taskinfoPresenter.GetfieldsamplingDetail(data);
        initRecycleView();
    }

    public void initRecycleView() {
        /**
         * 人员
         */
        sampleinfo_renAdapter = new Sampleinfo_renAdapter(R.layout.cai_item);
        rv_list_ren.setLayoutManager(new GridLayoutManager(SampleinfoActivity.this, 3));
        rv_list_ren.setAdapter(sampleinfo_renAdapter);
        /**
         * 分析项目
         */
        sampleinfo_proAdapter = new Sampleinfo_proAdapter(R.layout.cai_item);
        rv_list_pro.setLayoutManager(new GridLayoutManager(SampleinfoActivity.this, 2));
        rv_list_pro.setAdapter(sampleinfo_proAdapter);

        /**
         * 现场出值
         */
        sampleinfo_valueAdapter = new Sampleinfo_valueAdapter(R.layout.cai_item);
        rv_list_value.setLayoutManager(new GridLayoutManager(SampleinfoActivity.this, 2));
        rv_list_value.setAdapter(sampleinfo_valueAdapter);

        /**
         *  气象
         */
        sampleinfo_value_qxAdapter = new Sampleinfo_value_QXAdapter(R.layout.cai_item);
        rv_list_qixiang.setLayoutManager(new GridLayoutManager(SampleinfoActivity.this, 2));
        rv_list_qixiang.setAdapter(sampleinfo_value_qxAdapter);
        /**
         * 试剂
         */
        sampleinfo_shijiAdapter = new Sampleinfo_shijiAdapter(R.layout.cai_item);
        rv_list_shiji.setLayoutManager(new GridLayoutManager(SampleinfoActivity.this, 2));
        rv_list_shiji.setAdapter(sampleinfo_shijiAdapter);

        /**
         * 设备
         */
        sampleinfo_devAdapter = new Sampleinfo_devAdapter(R.layout.dev_item);
        rv_list_dev.setLayoutManager(new LinearLayoutManager(SampleinfoActivity.this));
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
                PictureSelector.create(SampleinfoActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList_image);
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
                PictureSelector.create(SampleinfoActivity.this).externalPictureVideo(media.getPath());
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

            sampleinfo_renAdapter.setNewData(fieldsamplingDetailBeans.get(0).getSampingusers());
            sampleinfo_proAdapter.setNewData(fieldsamplingDetailBeans.get(0).getNoscenefactors());


            sampleinfo_shijiAdapter.setNewData(fieldsamplingDetailBeans.get(0).getReagent());
            sampleinfo_devAdapter.setNewData(fieldsamplingDetailBeans.get(0).getEquips());


            initView(fieldsamplingDetailBeans.get(0));


            selectList_image = new ArrayList<>();
            selectList_video = new ArrayList<>();
            for (int i = 0; i < fieldsamplingDetailBeans.get(0).getFujians().size(); i++) {
                LocalMedia s = new LocalMedia();

                if (fieldsamplingDetailBeans.get(0).getFujians().get(i).getFujian().indexOf(".txt") != -1) {

                } else {

                    if (fieldsamplingDetailBeans.get(0).getFujians().get(i).getFujian().indexOf(".mp4") == -1) {
                        //不包含是图片
                        s.setPath(PHOTOS_URL + fieldsamplingDetailBeans.get(0).getFujians().get(i).getFujian());
                        selectList_image.add(s);
                    } else {
                        //是视频
                        s.setPath(PHOTOS_URL + fieldsamplingDetailBeans.get(0).getFujians().get(i).getFujian());
                        selectList_video.add(s);
                    }
                }

            }
            initImage();
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


    public void initView(FieldsamplingDetailBean fieldsamplingDetailBeans) {
        et_mix.setText(fieldsamplingDetailBeans.getStarttime().substring(0, 16));
        et_max.setText(fieldsamplingDetailBeans.getEndtime().substring(0, 16));


//         ll_liang;


        if (fieldsamplingDetailBeans.getOnlynumber().length() > 0) {
            tv_bar_code.setText(fieldsamplingDetailBeans.getOnlynumber());
            ll_code.setVisibility(View.VISIBLE);
        }

        if (fieldsamplingDetailBeans.getSampingPackingname().length() > 0) {
            tv_pack.setText(fieldsamplingDetailBeans.getSampingPackingname());
            ll_baoz.setVisibility(View.VISIBLE);
        }
        if (fieldsamplingDetailBeans.getSampingname().length() > 0) {
            et_samplename.setText(fieldsamplingDetailBeans.getSampingname());
            ll_name.setVisibility(View.VISIBLE);
        }
        if (fieldsamplingDetailBeans.getSampingstatus().length() > 0) {
            ll_status.setVisibility(View.VISIBLE);
            et_statuss.setText(fieldsamplingDetailBeans.getSampingstatus());
        }
        if (fieldsamplingDetailBeans.getSamplingamount().length() > 0) {
            ll_liang.setVisibility(View.VISIBLE);
            tv_unit.setText(fieldsamplingDetailBeans.getSamplingamount() + fieldsamplingDetailBeans.getSamplingunitname());

        }

        if(fieldsamplingDetailBeans.getProofreadername().length()>0){
            tv_jd_name.setText(fieldsamplingDetailBeans.getProofreadername());
            ll_sample_name.setVisibility(View.VISIBLE);
        }


        if (fieldsamplingDetailBeans.getSamplestylename().length() > 0) {
            tv_way.setText(fieldsamplingDetailBeans.getSamplestylename());
            ll_sample_ty.setVisibility(View.VISIBLE);
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


}

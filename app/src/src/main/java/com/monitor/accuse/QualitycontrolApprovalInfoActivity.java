package com.monitor.accuse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.Quality_BZQX_Adapter;
import com.monitor.changtian.adapter.Quality_YSJL_Adapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.ProjectTestListBean;
import com.monitor.changtian.bean.QueryTestRecordBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TestProjectCycleListBean;
import com.monitor.changtian.presenter.QualitycontrolApprovalPresenter;
import com.monitor.changtian.view.QualitycontrolApprovalView;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * 质控详情界面
 */
@EActivity(R.layout.activity_qualitycontrol_approval_info)
public class QualitycontrolApprovalInfoActivity extends BaseActvity implements QualitycontrolApprovalView {

    QualitycontrolApprovalPresenter qualitycontrolApprovalPresenter;
    @Extra
    String bh;
    @Extra
    String samplenumber;

    @ViewById(R.id.tv1)
    TextView tv1;
    @ViewById(R.id.tv2)
    TextView tv2;
    @ViewById(R.id.tv3)
    TextView tv3;
    @ViewById(R.id.tv4)
    TextView tv4;
    @ViewById(R.id.tv5)
    TextView tv5;
    @ViewById(R.id.tv6)
    TextView tv6;
    @ViewById(R.id.tv7)
    TextView tv7;
    @ViewById(R.id.tv8)
    TextView tv8;
    @ViewById(R.id.tv9)
    TextView tv9;
    @ViewById(R.id.tv10)
    TextView tv10;
    @ViewById(R.id.tv11)
    TextView tv11;
    @ViewById(R.id.tv12)
    TextView tv12;
    @ViewById(R.id.tv13)
    TextView tv13;
    @ViewById(R.id.tv14)
    TextView tv14;
    @ViewById(R.id.tv15)
    TextView tv15;
    @ViewById(R.id.tv16)
    TextView tv16;
    /**
     * 标准曲线
     */
    @ViewById(R.id.rv_list)
    RecyclerView rv_list_bz;
    Quality_BZQX_Adapter quality_bzqx_adapter;

    /**
     * 原始记录
     */
    @ViewById(R.id.rv_list_ys)
    RecyclerView rv_list_ys;
    Quality_YSJL_Adapter quality_ysjl_adapter;


    @AfterViews
    void init() {
        initImageBackText("详情");
        qualitycontrolApprovalPresenter = new QualitycontrolApprovalPresenter(this);
        /**
         * 标准曲线
         */
        quality_bzqx_adapter = new Quality_BZQX_Adapter(R.layout.quality_bzqx_item, this);
        rv_list_bz.setLayoutManager(new LinearLayoutManager(this));
        rv_list_bz.setAdapter(quality_bzqx_adapter);
        quality_bzqx_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                ViseLog.d("" + position);
                if (quality_bzqx_adapter.getData().get(position).getIshow()) {
                    quality_bzqx_adapter.getData().get(position).setIshow(false);
                    quality_bzqx_adapter.notifyDataSetChanged();
                } else {
                    quality_bzqx_adapter.getData().get(position).setIshow(true);
                    quality_bzqx_adapter.notifyDataSetChanged();
                }
            }
        });

        /**
         * 原始记录
         */
        quality_ysjl_adapter = new Quality_YSJL_Adapter(R.layout.quality_bzqx_item, this);
        rv_list_ys.setLayoutManager(new LinearLayoutManager(this));
        rv_list_ys.setAdapter(quality_ysjl_adapter);
        quality_ysjl_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                ViseLog.d("" + position);
                if (quality_ysjl_adapter.getData().get(position).getIshow()) {
                    quality_ysjl_adapter.getData().get(position).setIshow(false);
                    quality_ysjl_adapter.notifyDataSetChanged();
                } else {
                    quality_ysjl_adapter.getData().get(position).setIshow(true);
                    quality_ysjl_adapter.notifyDataSetChanged();
                }
            }
        });

        initData();
    }

    public void initData() {
        String data = "{bh:\"" + bh + "\",sdate:\"\",edate:\"\",loginName:\"\",samplenumber:\"" + samplenumber + "\"}";
        qualitycontrolApprovalPresenter.QueryTestRecord(data);
    }

    @Override
    public void OnGetTestProjectCycleList(List<TestProjectCycleListBean> testProjectCycleListBeans) {

    }

    @Override
    public void OnProjectTestListBean(List<ProjectTestListBean> projectTestListBeans) {

    }

    @Override
    public void OnQueryTestRecordBeans(List<QueryTestRecordBean> queryTestRecordBeans) {
        if (queryTestRecordBeans.size() > 0) {

            /**
             * 添加公共参数
             */
            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getAmethod().length() > 0) {
                tv1.setText("分析方法:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getAmethod());
            } else {
                tv1.setText("分析方法: -");
            }

            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getDev().length() > 0) {
                tv2.setText("仪器型号:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getDev());
            } else {
                tv2.setText("仪器型号: -");
            }


            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getDevnum().length() > 0) {
                tv3.setText("仪器编号:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getDevnum());
            } else {
                tv3.setText("仪器编号: -");
            }

            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getColumn().length() > 0) {
                tv4.setText("色谱柱:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getColumn());
            } else {
                tv4.setText("色谱柱: -");
            }

            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getDetector().length() > 0) {
                tv5.setText("检测器:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getDetector());
            } else {
                tv5.setText("检测器: -");
            }

            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getThickness().length() > 0) {
                tv6.setText("标准液浓度:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getThickness());
            } else {
                tv6.setText("标准液浓度: -");
            }
            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getQhsTemp().length() > 0) {
                tv7.setText("气化室温度:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getQhsTemp());
            } else {
                tv7.setText("气化室温度: -");
            }
            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getZxTemp().length() > 0) {
                tv8.setText("柱箱温度:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getZxTemp());
            } else {
                tv8.setText("柱箱温度: -");
            }
            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getJcqTemp().length() > 0) {
                tv9.setText("检测器温度:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getJcqTemp());
            } else {
                tv9.setText("检测器温度: -");
            }

            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getPress_h2().length() > 0) {
                tv10.setText("氢气压力:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getPress_h2());
            } else {
                tv10.setText("氢气压力: -");
            }

            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getRoomTemp().length() > 0) {
                tv11.setText("室温:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getRoomTemp());
            } else {
                tv11.setText("室温: -");
            }

            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getRoomHum().length() > 0) {
                tv12.setText("湿度:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getRoomHum());
            } else {
                tv12.setText("湿度: -");
            }
            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getPress_load().length() > 0) {
                tv13.setText("载气压力:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getPress_load());
            } else {
                tv13.setText("载气压力: -");
            }

            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getJyl().length() > 0) {
                tv14.setText("进样量:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getJyl());
            } else {
                tv14.setText("进样量: -");
            }
            if (queryTestRecordBeans.get(0).getTdata().getCommon_para().getPress_air().length() > 0) {
                tv15.setText("空气压力:" + queryTestRecordBeans.get(0).getTdata().getCommon_para().getPress_air());
            } else {
                tv15.setText("空气压力: -");
            }

            /**
             * 添加检验记录
             */
            if (queryTestRecordBeans.get(0).getProcess().length() > 0) {
                tv16.setText(queryTestRecordBeans.get(0).getProcess());
            } else {
                tv16.setText("");
            }
            /**
             * 添加标准曲线数据
             */
            if (queryTestRecordBeans.get(0).getTdata().getStd_curve().size() > 0) {
                quality_bzqx_adapter.setNewData(queryTestRecordBeans.get(0).getTdata().getStd_curve());
                for (int i = 0; i < quality_bzqx_adapter.getData().size(); i++) {
                    quality_bzqx_adapter.getData().get(i).setIshow(false);
                }
            }
            /**
             * 添加原始数据记录
             */
            if (queryTestRecordBeans.get(0).getTdata().getTest_result().size() > 0) {
                quality_ysjl_adapter.setNewData(queryTestRecordBeans.get(0).getTdata().getTest_result());

                for (int i = 0; i < quality_ysjl_adapter.getData().size(); i++) {
                    quality_ysjl_adapter.getData().get(i).setIshow(false);
                }
            }
        }
    }

    @Override
    public void OnMessage(ResultBean resultBean) {

    }

    @Override
    public void OnError(String s) {

    }
}


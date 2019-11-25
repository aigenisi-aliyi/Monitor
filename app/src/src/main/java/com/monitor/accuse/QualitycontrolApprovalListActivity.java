package com.monitor.accuse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.QualitycontrolAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.EventBus.QualityEvent;
import com.monitor.changtian.bean.EventBus.UpdateEvent;
import com.monitor.changtian.bean.ProjectTestListBean;
import com.monitor.changtian.bean.QualitySubmitBean;
import com.monitor.changtian.bean.QueryTestRecordBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TestProjectCycleListBean;
import com.monitor.changtian.presenter.QualitycontrolApprovalPresenter;
import com.monitor.changtian.view.QualitycontrolApprovalView;
import com.monitor.taskallocation.Task_AddActivity;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 审核列表信息
 */
@EActivity(R.layout.activity_qualitycontrol_approval_list)
public class QualitycontrolApprovalListActivity extends BaseActvity implements BaseActvity.Rightlistener, QualitycontrolApprovalView {

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    QualitycontrolApprovalPresenter qualitycontrolApprovalPresenter;
    QualitycontrolAdapter qualitycontrolAdapter;
    @Extra
    String tpcid;


    String tpcids = "";

    @AfterViews
    void init() {
        initRightOnclikText("质控审核列表", "提交", this);
        qualitycontrolApprovalPresenter = new QualitycontrolApprovalPresenter(this);

        if (getIntent().getStringExtra("tpcid") != null) {
            tpcids = getIntent().getStringExtra("tpcid");
        } else {
            tpcids = tpcid;
        }

        initData();
        qualitycontrolAdapter = new QualitycontrolAdapter(R.layout.qualitycontrol_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(qualitycontrolAdapter);
        qualitycontrolAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.ll_look:

                        /**
                         * 跳转详情界面
                         */
                        QualitycontrolApprovalInfoActivity_.intent(QualitycontrolApprovalListActivity.this)
                                .bh(qualitycontrolAdapter.getData().get(position).getTestno())
                                .samplenumber(qualitycontrolAdapter.getData().get(position).getNewsamplecode())
                                .start();
                        break;
                    /**
                     * 通过
                     */
                    case R.id.stv_true:
                        qualitycontrolAdapter.getData().get(position).setISTrue("0");
                        qualitycontrolAdapter.notifyDataSetChanged();
                        break;
                    /**
                     * 不通过
                     */
                    case R.id.stv_true1:

                        /**
                         * 如果不通过选择不通过的原因
                         */
                        ShowDialog(position);
                        break;
                }

            }
        });
    }


    /**
     * 确定
     */

    boolean isshows = true;

    String datalist = "";

    @Override
    public void rightlistener() {

        if (qualitycontrolAdapter.getData().size() > 0) {

            for (int i = 0; i < qualitycontrolAdapter.getData().size(); i++) {

                if (qualitycontrolAdapter.getData().get(i).getISTrue().equals("-1")) {
                    isshows = false;
                    break;
                }
            }

            if (!isshows) {
                msg("请审核完所有数据在进行提交!");
                return;
            }

            QualitySubmitBean qualitySubmitBean = new QualitySubmitBean();
            qualitySubmitBean.setLoginId(MyApplication.getInstance().getUser());
            qualitySubmitBean.setTpcid(tpcid);
            List<QualitySubmitBean.DetailsBean> detailsBeanList = new ArrayList<>();
            for (int i = 0; i < qualitycontrolAdapter.getData().size(); i++) {
                QualitySubmitBean.DetailsBean detailsBean = new QualitySubmitBean.DetailsBean();
                detailsBean.setCol2("");
                detailsBean.setIspass("1");
                detailsBean.setRemark("");
                detailsBean.setTestno(qualitycontrolAdapter.getData().get(i).getTestno());
                detailsBean.setSamplenumber(qualitycontrolAdapter.getData().get(i).getNewsamplecode());
                detailsBeanList.add(detailsBean);
            }
            qualitySubmitBean.setDetails(detailsBeanList);
            ViseLog.d("lll:" + JSON.toJSONString(qualitySubmitBean));
            datalist = JSON.toJSONString(qualitySubmitBean);
            AgainDialog("确认提交当前的所有审核记录吗？");
        } else {
            msg("当前暂无需要审核的数据");
        }


    }

    public void AgainTrue() {
        if (datalist.length() > 0) {
            ShowDialogtitle("请稍等...", QualitycontrolApprovalListActivity.this);
            qualitycontrolApprovalPresenter.qcApproveprojectTest(datalist);
        }

    }

    AlertDialog dev_dia;
    /**
     * 弹出dialog
     */
    String col2 = "";
    String remk = "";

    public void ShowDialog(final int potion) {

        /**
         * 二次弹框确认
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认当前记录审核为: (不通过) 吗?");
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

                qualitycontrolAdapter.getData().get(potion).setISTrue("1");
                qualitycontrolAdapter.notifyDataSetChanged();
                final AlertDialog.Builder builder = new AlertDialog.Builder(QualitycontrolApprovalListActivity.this, R.style.BDAlertDialog);
                builder.setCancelable(false);
                View view = LayoutInflater.from(QualitycontrolApprovalListActivity.this).inflate(R.layout.no_flase_item, null);
                builder.setView(view);
                dev_dia = builder.show();

                final EditText et_remark = view.findViewById(R.id.et_remark);
                RadioGroup tv_data = view.findViewById(R.id.tv_data);
                tv_data.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.rb_normal:
                                col2 = "0";
                                break;

                            case R.id.rb_abnormal:
                                col2 = "1";
                                break;
                        }
                    }
                });
                final RadioButton rb_normal = view.findViewById(R.id.rb_normal);
                final RadioButton rb_abnormal = view.findViewById(R.id.rb_abnormal);

                SuperTextView stv_false = view.findViewById(R.id.stv_false);
                stv_false.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        qualitycontrolAdapter.getData().get(potion).setISTrue("-1");
                        qualitycontrolAdapter.notifyDataSetChanged();
                        dev_dia.dismiss();
                    }
                });

                SuperTextView stv_submit = view.findViewById(R.id.stv_submit);

                stv_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (rb_normal.isChecked() || rb_abnormal.isChecked()) {
                        } else {
                            msg("请选择类型");
                            return;
                        }
                        if (et_remark.getText().toString().length() > 0) {
                            remk = et_remark.getText().toString();
                        } else {
                            remk = "";
                        }

                        QualitySubmitBean qualitySubmitBean = new QualitySubmitBean();
                        qualitySubmitBean.setLoginId(MyApplication.getInstance().getUser());
                        qualitySubmitBean.setTpcid(tpcid);
                        List<QualitySubmitBean.DetailsBean> detailsBeanList = new ArrayList<>();
                        for (int i = 0; i < qualitycontrolAdapter.getData().size(); i++) {
                            if (!qualitycontrolAdapter.getData().get(i).getISTrue().equals("-1")) {
                                QualitySubmitBean.DetailsBean detailsBean = new QualitySubmitBean.DetailsBean();
                                if (qualitycontrolAdapter.getData().get(i).getISTrue().equals("0")) {
                                    detailsBean.setCol2("");
                                    detailsBean.setIspass("1");
                                    detailsBean.setRemark("");
                                } else {
                                    detailsBean.setIspass("2");
                                    detailsBean.setCol2(col2);
                                    detailsBean.setRemark(remk);
                                }
                                detailsBean.setTestno(qualitycontrolAdapter.getData().get(i).getTestno());
                                detailsBean.setSamplenumber(qualitycontrolAdapter.getData().get(i).getNewsamplecode());
                                detailsBeanList.add(detailsBean);
                            }
                        }
                        qualitySubmitBean.setDetails(detailsBeanList);
                        dev_dia.dismiss();
                        ShowDialogtitle("请稍等...", QualitycontrolApprovalListActivity.this);
                        String data = JSON.toJSONString(qualitySubmitBean);
                        qualitycontrolApprovalPresenter.qcApproveprojectTest(data);
                    }
                });


            }
        });
        builder.show();


    }


    public void initData() {
        String data = "{tpcid:\"" + tpcids + "\"}";
        qualitycontrolApprovalPresenter.GetprojectTestList(data);
    }

    @Override
    public void OnGetTestProjectCycleList(List<TestProjectCycleListBean> testProjectCycleListBeans) {

    }

    @Override
    public void OnProjectTestListBean(List<ProjectTestListBean> projectTestListBeans) {

        if (projectTestListBeans != null) {
            if (projectTestListBeans.size() > 0) {
                qualitycontrolAdapter.setNewData(projectTestListBeans);

                for (int i = 0; i < qualitycontrolAdapter.getData().size(); i++) {
                    qualitycontrolAdapter.getData().get(i).setISTrue("-1");
                }
            } else {
                qualitycontrolAdapter.setNewData(projectTestListBeans);
                AddEmptyView(qualitycontrolAdapter, rv_list);
                setEmptylistener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initData();
                    }
                });
            }
        }
    }

    @Override
    public void OnQueryTestRecordBeans(List<QueryTestRecordBean> queryTestRecordBeans) {

    }

    @Override
    public void OnMessage(ResultBean resultBean) {

        DissDialog();
        if (resultBean != null) {
            if (resultBean.getResult().equals("1")) {
                msg("成功");
                EventBus.getDefault().post(new QualityEvent("更新"));
                finish();
            } else {
                msg(resultBean.getErrormessage());
            }
        }
    }

    @Override
    public void OnError(String s) {

        DissDialog();
    }

}

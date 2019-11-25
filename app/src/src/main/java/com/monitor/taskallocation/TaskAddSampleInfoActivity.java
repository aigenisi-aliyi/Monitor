package com.monitor.taskallocation;

import android.graphics.Paint;
import android.widget.TextView;

import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.Task_SapmleBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_task_add_sample_info)
public class TaskAddSampleInfoActivity extends BaseActvity {

    @Extra
    Task_SapmleBean task_sapmleBean;
    @ViewById(R.id.tv_potion)
    TextView tv_potion;
    @ViewById(R.id.tv_code)
    TextView tv_code;
    @ViewById(R.id.tv_sampleName)
    TextView tv_sampleName;
    @ViewById(R.id.tv_time)
    TextView tv_time;
    @ViewById(R.id.tv_status)
    TextView tv_status;
    @ViewById(R.id.tv_pack)
    TextView tv_pack;
    @ViewById(R.id.tv_personName)
    TextView tv_personName;
    @ViewById(R.id.tv_phone)
    TextView tv_phone;
    @ViewById(R.id.tv_unit)
    TextView tv_unit;
    @ViewById(R.id.tv_analysis)
    TextView tv_analysis;


    @AfterViews
    void init() {

        initImageBackText("样品详情");
        tv_phone.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        if (task_sapmleBean != null) {
            tv_potion.setText(task_sapmleBean.getCategoryname());
            tv_code.setText(task_sapmleBean.getOnlynumber());
            tv_sampleName.setText(task_sapmleBean.getSampingname());
            tv_time.setText(task_sapmleBean.getSampingtime());
            tv_status.setText(task_sapmleBean.getSampingstatus());
            tv_pack.setText(task_sapmleBean.getSampingPackingname());
            tv_personName.setText(task_sapmleBean.getSampinguser());
            tv_phone.setText(task_sapmleBean.getSampinguserphone());
            tv_unit.setText(task_sapmleBean.getSamplingamount() + "\t" + task_sapmleBean.getSamplingunitname());
            tv_analysis.setText(task_sapmleBean.getAnalysisitemnames());
        }
    }
    @Click(R.id.tv_phone)
    public void tv_phone(){
        if (tv_phone.getText().toString().trim() != null) {
            call(tv_phone.getText().toString().trim());
        }
    }


}

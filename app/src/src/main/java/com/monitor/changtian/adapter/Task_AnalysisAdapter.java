package com.monitor.changtian.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.vise.log.ViseLog;

/**
 * Created by ken on 2018/8/9.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class Task_AnalysisAdapter extends BaseQuickAdapter<DetectionSchemeInfoBean.LeftcateogoryBean.FactorsBean, BaseViewHolder> {
    public Task_AnalysisAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, DetectionSchemeInfoBean.LeftcateogoryBean.FactorsBean item) {

        final CheckBox cb_doctor = helper.getView(R.id.cb_doctor);
        helper.addOnClickListener(R.id.cb_doctor);
        if (item.getFactorname() != null) {
            cb_doctor.setText(item.getFactorname());
        }
        cb_doctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 通过这个方法，来监听当前的checkbox是否被选中

                if (isChecked) {
                    Task_AnalysisAdapter.this.getData().get(helper.getLayoutPosition()).setChoice(true);
                } else {
                    ViseLog.d("取消选中");
                    Task_AnalysisAdapter.this.getData().get(helper.getLayoutPosition()).setChoice(false);
                }
            }
        });

        if (Task_AnalysisAdapter.this.getData().get(helper.getLayoutPosition()).isChoice_save()) {
            cb_doctor.setChecked(true);
        } else {
            cb_doctor.setChecked(false);
        }

    }
}

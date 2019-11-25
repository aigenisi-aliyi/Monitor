package com.monitor.changtian.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SampleInfoByPointInfoBean;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.vise.log.ViseLog;

/**
 * Created by ken on 2018/7/12.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class AnalysisAdapter extends BaseQuickAdapter<SampleInfoByPointInfoBean.DetailsBean, BaseViewHolder> {
    public AnalysisAdapter(int layoutResId) {
        super(layoutResId);
    }

    private int selected = -1;

    @Override
    protected void convert(final BaseViewHolder helper, SampleInfoByPointInfoBean.DetailsBean item) {
        final CheckBox cb_doctor = helper.getView(R.id.cb_doctor);
        if (item.getName() != null) {
            cb_doctor.setText(item.getName());
        }

        cb_doctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 通过这个方法，来监听当前的checkbox是否被选中

                if (isChecked) {
                    ViseLog.d("选中");
                    AnalysisAdapter.this.getData().get(helper.getLayoutPosition()).setSelected(true);
                } else {
                    ViseLog.d("取消选中");
                    AnalysisAdapter.this.getData().get(helper.getLayoutPosition()).setSelected(false);
                }
            }
        });

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < item.getFactors().size(); i++) {
            stringBuffer.append(item.getFactors().get(i).getFactorname() + ",");
        }
        helper.setText(R.id.tv_contet, stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1));

        if (AnalysisAdapter.this.getData().get(helper.getLayoutPosition()).isSelected()) {
            cb_doctor.setChecked(true);
        } else {
            cb_doctor.setChecked(false);
        }


        helper.addOnClickListener(R.id.ll_check);
        helper.addOnClickListener(R.id.cb_doctor);
//        if (selected == helper.getAdapterPosition()) {
//            cb_doctor.setChecked(true);
//            AnalysisAdapter.this.getData().get(helper.getLayoutPosition()).setSelected(true);
//        }
//        else {
//            cb_doctor.setChecked(false);
//            AnalysisAdapter.this.getData().get(helper.getLayoutPosition()).setSelected(false);
//        }
//        StringBuffer stringBuffer = new StringBuffer();
//        for (int i = 0; i < item.getFactors().size(); i++) {
//            stringBuffer.append(item.getFactors().get(i).getFactorname() + ",");
//        }
//        helper.setText(R.id.tv_contet, stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1));

    }

    public void setSelection(int position) {
        this.selected = position;
        notifyDataSetChanged();
    }
}

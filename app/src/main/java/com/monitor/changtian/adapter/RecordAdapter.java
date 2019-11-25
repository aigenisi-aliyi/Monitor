package com.monitor.changtian.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SampleInfoDataBean;

import static com.monitor.changtian.constant.PublicConstant.TYPE_taskpro;

/**
 * Created by ken on 2018/6/1.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class RecordAdapter extends BaseQuickAdapter<SampleInfoDataBean, BaseViewHolder> {
    private String type;

    public RecordAdapter(int layoutResId, String type) {
        super(layoutResId);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, SampleInfoDataBean item) {

        TextView tv_submit = helper.getView(R.id.tv_submit);

        if (item != null) {

            helper.setText(R.id.tv_proname, item.getProjectName());
            helper.setText(R.id.tv_time, item.getSampleDate());
            helper.setText(R.id.tv_name_pop, item.getUserName());
        }
        if (type.equals(TYPE_taskpro)) {
            tv_submit.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.ll_record);
        helper.addOnClickListener(R.id.tv_submit);
    }
}
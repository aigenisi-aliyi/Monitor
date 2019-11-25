package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.FieldsamplingDetailBean;

/**
 * Created by ken on 2018/7/13.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class Sampleinfo_shijiAdapter extends BaseQuickAdapter<FieldsamplingDetailBean.ReagentBean, BaseViewHolder> {
    public Sampleinfo_shijiAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FieldsamplingDetailBean.ReagentBean item) {

        String name = "", value = "";
        if (item != null) {
            if (item.getNames() != null) {
                name = item.getNames();
            }
            if (item.getDosage() != null) {
                value = item.getDosage();
            }
        }

        helper.setText(R.id.tv_name, name + "\t\t" + value + "\t" + item.getUnit());
    }
}


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

public class Sampleinfo_valueAdapter extends BaseQuickAdapter<FieldsamplingDetailBean.ScenefactorsBean, BaseViewHolder> {
    public Sampleinfo_valueAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FieldsamplingDetailBean.ScenefactorsBean item) {

        String name = "", value = "";
        if (item != null) {
            if (item.getFactorname() != null) {
                name = item.getFactorname();
            }
            if (item.getMeasuredvalue() != null) {
                value = item.getMeasuredvalue();
            }
        }

        helper.setText(R.id.tv_name, name + "\t\t" + value + "\t" + item.getUnit());
    }
}


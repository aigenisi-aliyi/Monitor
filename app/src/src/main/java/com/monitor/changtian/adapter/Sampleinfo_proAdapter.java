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

public class Sampleinfo_proAdapter extends BaseQuickAdapter<FieldsamplingDetailBean.NoscenefactorsBean, BaseViewHolder> {
    public Sampleinfo_proAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FieldsamplingDetailBean.NoscenefactorsBean item) {
        if (item != null) {
            if (item.getFactorname() != null) {
                helper.setText(R.id.tv_name, item.getFactorname());
            }

        }
    }
}

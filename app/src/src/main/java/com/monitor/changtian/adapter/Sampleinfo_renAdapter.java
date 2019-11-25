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

public class Sampleinfo_renAdapter extends BaseQuickAdapter<FieldsamplingDetailBean.SampingusersBean, BaseViewHolder> {
    public Sampleinfo_renAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FieldsamplingDetailBean.SampingusersBean item) {

        if (item != null) {
            if (item.getUserName() != null) {
                helper.setText(R.id.tv_name, item.getUserName());
            }

        }
    }
}

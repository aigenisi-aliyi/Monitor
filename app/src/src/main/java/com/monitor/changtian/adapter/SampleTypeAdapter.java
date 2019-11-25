package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SampleType;

/**
 * Created by ken on 2018/6/1.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SampleTypeAdapter extends BaseQuickAdapter<SampleType, BaseViewHolder> {
    public SampleTypeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SampleType item) {

        helper.setText(R.id.tv_dialog_title, item.getDataValue());
        helper.addOnClickListener(R.id.tv_dialog_title);
    }
}

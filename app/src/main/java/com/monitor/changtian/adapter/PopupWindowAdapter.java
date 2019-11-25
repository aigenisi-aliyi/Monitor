package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SampleInfoDataBean;

/**
 * Created by ken on 2018/5/17.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class PopupWindowAdapter extends BaseQuickAdapter<SampleInfoDataBean, BaseViewHolder> {
    public PopupWindowAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SampleInfoDataBean item) {

        if (item.getProjectName() != null) {
            helper.setText(R.id.tv_title, item.getProjectName());
        }
        helper.addOnClickListener(R.id.tv_title);
    }
}

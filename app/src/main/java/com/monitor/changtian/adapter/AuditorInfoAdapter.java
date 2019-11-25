package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SampleInfoCompreData;

/**
 * Created by ken on 2018/6/1.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class AuditorInfoAdapter extends BaseQuickAdapter<SampleInfoCompreData.SampleDetailsBean.SampleDetailItemsBean, BaseViewHolder> {
    public AuditorInfoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SampleInfoCompreData.SampleDetailsBean.SampleDetailItemsBean item) {

        helper.setText(R.id.tv_au_info_title, item.getItemsInfo());
        helper.setText(R.id.tv_au_info_value, item.getItemvalue());
    }
}
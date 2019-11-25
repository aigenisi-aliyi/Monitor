package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SampleDetailsAndItemsData;

/**
 * Created by ken on 2018/6/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class HomeRecordItemsAdapter extends BaseQuickAdapter<SampleDetailsAndItemsData.SampleDetailItemsBean, BaseViewHolder> {
    public HomeRecordItemsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SampleDetailsAndItemsData.SampleDetailItemsBean item) {

        helper.setText(R.id.tv_name, item.getItemsInfo());
        helper.setText(R.id.tv_value, item.getItemvalue());

    }
}

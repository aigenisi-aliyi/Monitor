package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.PersonsDataBean;

/**
 * Created by ken on 2018/6/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskPersonAdapter extends BaseQuickAdapter<PersonsDataBean, BaseViewHolder> {
    public TaskPersonAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonsDataBean item) {

        if (item.getUsername() != null) {
            helper.setText(R.id.tv_per_name, item.getUsername());
        }

        if (item.getExplainInfo() != null) {
            helper.setText(R.id.tv_per_info, item.getExplainInfo());
        }
        if (item.getSamplingTimes() != null) {

            helper.setText(R.id.tv_per_num, item.getSamplingTimes());
        }
    }
}

package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.TasksInfoDetailBean;

/**
 * Created by ken on 2018/7/5.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class taskinfo_caiAdapter extends BaseQuickAdapter<TasksInfoDetailBean.SamplinguserBean, BaseViewHolder> {
    public taskinfo_caiAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TasksInfoDetailBean.SamplinguserBean item) {

        if (item.getUsername() != null) {
            helper.setText(R.id.tv_name, item.getUsername());
        }
    }
}

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

public class Task_derAdapter extends BaseQuickAdapter<TasksInfoDetailBean.EquipsBean, BaseViewHolder> {
    public Task_derAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TasksInfoDetailBean.EquipsBean item) {

        if (item.getCategoryname() != null) {
            helper.setText(R.id.tv_title, "设备:");
            helper.setText(R.id.tv_content, "\t\t"+ item.getCategoryname() + "\t\t"+"数量:" + item.getTotalnumber());
        }

    }
}
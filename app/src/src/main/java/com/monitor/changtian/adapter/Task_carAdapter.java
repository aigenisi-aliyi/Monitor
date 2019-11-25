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

public class Task_carAdapter extends BaseQuickAdapter<TasksInfoDetailBean.CarsBean, BaseViewHolder> {
    public Task_carAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TasksInfoDetailBean.CarsBean item) {

        if (item.getVlicense() != null) {
            helper.setText(R.id.tv_title, "车牌:");
            helper.setText(R.id.tv_content, item.getVlicense());
        }

    }
}
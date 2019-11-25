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

public class Taskinfo_testAdapter extends BaseQuickAdapter<TasksInfoDetailBean.FactorsBean, BaseViewHolder> {
    public Taskinfo_testAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TasksInfoDetailBean.FactorsBean item) {


        if (item.getFactorname() != null) {
            helper.setText(R.id.tv_factor, item.getFactorname());
        }
        if(item.getUsers().get(0).getUsername()!=null){
            helper.setText(R.id.tv_name, item.getUsers().get(0).getUsername());
        }

    }
}

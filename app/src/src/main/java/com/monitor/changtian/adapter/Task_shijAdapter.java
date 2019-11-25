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

public class Task_shijAdapter extends BaseQuickAdapter<TasksInfoDetailBean.ConsumablesBean, BaseViewHolder> {
    public Task_shijAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, TasksInfoDetailBean.ConsumablesBean item) {

        if (item.getCategoryname() != null) {
            helper.setText(R.id.tv_title, "名称:");
            if(item.getConsumablesid().length()>0){
                helper.setText(R.id.tv_content, item.getConsumablesName() + "\t\t"+"数量:"  + item.getTotalnumber());
            }else{
                helper.setText(R.id.tv_content, item.getCategoryname() + "\t\t"+"数量:"  + item.getTotalnumber());
            }
        }
    }
}

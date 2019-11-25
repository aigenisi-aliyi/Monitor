package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.TaskListBean;

/**
 * Created by ken on 2018/5/10.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskAdapter extends BaseQuickAdapter<TaskListBean, BaseViewHolder> {
    public TaskAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskListBean item) {
        if (item != null) {
            helper.setText(R.id.tv_proname, item.getSubject());
            helper.setText(R.id.tv_name, item.getUsername());
            helper.setText(R.id.tv_time, "开始时间:" + item.getStarttime());
//            helper.setText(R.id.tv_type,"分类:"+item.getLeftcategoryname());
        }
        helper.addOnClickListener(R.id.tv_info);
        helper.addOnClickListener(R.id.tv_add);
        helper.addOnClickListener(R.id.ll_infolook);
    }
}
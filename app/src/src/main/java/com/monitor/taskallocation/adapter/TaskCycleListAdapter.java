package com.monitor.taskallocation.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.TasksListInfoBean;

/**
 * Created by Administrator on 2018/10/16.
 */

public class TaskCycleListAdapter extends BaseQuickAdapter<TasksListInfoBean, BaseViewHolder> {
    public TaskCycleListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TasksListInfoBean item) {

        helper.setText(R.id.tv_subject, item.getSubject());
//        helper.setText(R.id.starttime, "开始:" + item.getStarttime().substring(0, 10));
//        helper.setText(R.id.endtime, "结束:" + item.getEndtime().substring(0, 10));
        helper.addOnClickListener(R.id.ll_items);
        helper.addOnClickListener(R.id.iv_addtask);
        ImageView iv_image2 = helper.getView(R.id.iv_image2);
        if (item.getTaskstatus() != null) {
            if (item.getTaskstatus().equals("2")) {
                iv_image2.setVisibility(View.VISIBLE);
                iv_image2.setImageResource(R.drawable.pause);
            } else if (item.getTaskstatus().equals("5")) {
                iv_image2.setVisibility(View.VISIBLE);
                iv_image2.setImageResource(R.drawable.over);
            } else {
                iv_image2.setVisibility(View.GONE);
            }
        }
    }
}

package com.monitor.changtian.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.MessageBean;

/**
 * Created by ken on 2018/5/14.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TasksAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {
    public TasksAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {

        TextView tv_title = helper.getView(R.id.tv_title);
        tv_title.setText(item.getUserName() + item.getContent());
        helper.setText(R.id.tv_time, item.getStime());
    }
}

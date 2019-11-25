package com.monitor.taskallocation.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.Task_SapmleBean;

/**
 * Created by ken on 2018/8/10.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskaddSampleAdapter extends BaseQuickAdapter<Task_SapmleBean, BaseViewHolder> {
    public TaskaddSampleAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Task_SapmleBean item) {
        if (item.getCategoryname().length() > 0) {
            helper.setText(R.id.tv_subject, item.getCategoryname());
        } else {
            helper.setText(R.id.tv_subject, "");
        }
        if (item.getAnalysisitemnames().length() > 0) {
            helper.setText(R.id.starttime, item.getAnalysisitemnames() + "\t" + "数量:" + item.getAnalysisitems());
        } else {
            helper.setText(R.id.starttime, "");

        }

        if (item.getSampingtime().length() > 0) {
            helper.setText(R.id.endtime, "送样:" + item.getSampingtime().substring(0, 10));
        } else {
            helper.setText(R.id.endtime, "");
        }
        helper.addOnClickListener(R.id.ll_items);
    }
}

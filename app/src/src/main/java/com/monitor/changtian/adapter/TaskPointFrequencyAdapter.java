package com.monitor.changtian.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.task.SampleListInfoActivity_;
import com.monitor.changtian.activity.task.TaskPointsFrequencyListActivity;
import com.monitor.changtian.bean.TaskPointsFrequencyBean;

/**
 * Created by Administrator on 2018/10/17.
 */

public class TaskPointFrequencyAdapter extends BaseQuickAdapter<TaskPointsFrequencyBean, BaseViewHolder> {
    private Context mContext;

    public TaskPointFrequencyAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
        this.mContext = mContext;
    }


    private String zq_status;

    public String getZq_status() {
        return zq_status;
    }

    public void setZq_status(String zq_status) {
        this.zq_status = zq_status;
    }

    @Override
    protected void convert(BaseViewHolder helper, final TaskPointsFrequencyBean item) {

        if (item.getName().length() > 0) {
            helper.setText(R.id.tv_subject, item.getName());
        }
        helper.addOnClickListener(R.id.ll_items);
        helper.addOnClickListener(R.id.ll_option_look);
        LinearLayout ll_option_look = helper.getView(R.id.ll_option_look);
        ll_option_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SampleListInfoActivity_.intent(mContext)
                        .status(getZq_status())
                        .pointsid(item.getPotinsName())
                        .taskid(item.getTaskid())
                        .IsEnd(item.getIsEnd())
                        .ishadden(item.getIshadden())
                        .potions_index(item.getPotins_index())
                        .currentnumber(item.getOptions()).start();
            }
        });
    }
}

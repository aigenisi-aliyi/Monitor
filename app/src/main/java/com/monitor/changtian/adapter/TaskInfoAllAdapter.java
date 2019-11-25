package com.monitor.changtian.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.TaskInfoAllBean;

/**
 * Created by ken on 2018/8/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskInfoAllAdapter extends BaseQuickAdapter<TaskInfoAllBean, BaseViewHolder> {
    public TaskInfoAllAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskInfoAllBean item) {

        LinearLayout ll_vis = helper.getView(R.id.ll_vis);
        TextView tv_contractname = helper.getView(R.id.tv_contractname);
        tv_contractname.setVisibility(View.GONE);
        ll_vis.setVisibility(View.GONE);
        if (item.getSchemename() != null) {
            helper.setText(R.id.tv_detetionname, item.getSchemename());
        }
        if (item.getStarttime() != null && item.getStarttime().length() > 0) {
            helper.setText(R.id.tv_signDate, item.getStarttime().substring(0, 10));
        }
        ImageView iv_image = helper.getView(R.id.iv_image);
        if (item.getIsUrgent().equals("0")) {
            iv_image.setVisibility(View.GONE);
        }
        if (item.getIsUrgent().equals("1")) {
            iv_image.setVisibility(View.VISIBLE);
        }

        ImageView iv_image2 = helper.getView(R.id.iv_image2);
        if (item.getIsstoppass() != null) {
            if (item.getIsstoppass().equals("1")) {
                iv_image2.setVisibility(View.VISIBLE);
                iv_image2.setImageResource(R.drawable.pass);
            } else if (item.getIsstoppass().equals("0")) {
                iv_image2.setVisibility(View.VISIBLE);
                iv_image2.setImageResource(R.drawable.nopass);
            } else {
                iv_image2.setVisibility(View.GONE);
            }
        }
        helper.addOnClickListener(R.id.ll_look);
    }
}

package com.monitor.changtian.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.ProjectcontractBean;

/**
 * Created by ken on 2018/8/2.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class AccuseAdapter extends BaseQuickAdapter<ProjectcontractBean, BaseViewHolder> {

    public AccuseAdapter(int layoutResId) {
        super(layoutResId);

    }
    @Override
    protected void convert(BaseViewHolder helper, ProjectcontractBean item) {

        if (item.getProjectnames() != null) {
            helper.setText(R.id.tv_detetionname, item.getProjectnames());
        }
        if (item.getCustomernames() != null) {
            helper.setText(R.id.tv_contractname, item.getCustomernames());
        }
        if (item.getCreatetime() != null && item.getCreatetime().length() > 0) {
            helper.setText(R.id.tv_signDate, item.getCreatetime().substring(0, 10));
        }
        if (item.getUsername() != null) {
            helper.setText(R.id.username, item.getUsername());
        }

        ImageView iv_image = helper.getView(R.id.iv_image);

        if (item.getIsUrgent().length() > 0) {
            if (item.getIsUrgent().equals("0")) {
                iv_image.setVisibility(View.GONE);
            }
            if (item.getIsUrgent().equals("1")) {
                iv_image.setVisibility(View.VISIBLE);
            }
        } else {
            iv_image.setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.ll_look);
    }
}

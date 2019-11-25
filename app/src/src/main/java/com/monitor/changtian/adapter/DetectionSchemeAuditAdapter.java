package com.monitor.changtian.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.DetectionSchemeBean;

/**
 * Created by ken on 2018/7/25.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class DetectionSchemeAuditAdapter extends BaseQuickAdapter<DetectionSchemeBean, BaseViewHolder> {
    public DetectionSchemeAuditAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DetectionSchemeBean item) {

        if (item.getProjectname() != null) {
            helper.setText(R.id.tv_detetionname, item.getSchemeName());
        }
        if (item.getCustomername() != null) {
            helper.setText(R.id.tv_contractname, item.getCustomername());
        }
        if (item.getCreatetime() != null && item.getCreatetime().length() > 0) {
            helper.setText(R.id.tv_signDate, item.getCreatetime());
        }
        if (item.getUsername() != null) {
            helper.setText(R.id.username, item.getUsername());
        }

        ImageView iv_image = helper.getView(R.id.iv_image);
        ImageView iv_image2 = helper.getView(R.id.iv_image2);

        if (item.getIsUrgent().equals("0")) {
            iv_image.setVisibility(View.GONE);
        }
        if (item.getIsUrgent().equals("1")) {
            iv_image.setVisibility(View.VISIBLE);
        }
        helper.addOnClickListener(R.id.ll_look);
        if (item.getSchemeStatus() != null) {
            if (item.getSchemeStatus().equals("审核已通过")) {
                iv_image2.setVisibility(View.VISIBLE);
                iv_image2.setImageResource(R.drawable.pass);
            } else if (item.getSchemeStatus().equals("审核未通过")) {
                iv_image2.setVisibility(View.VISIBLE);
                iv_image2.setImageResource(R.drawable.nopass);
            } else {
                iv_image2.setVisibility(View.GONE);
            }
        }

    }
}

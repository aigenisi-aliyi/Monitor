package com.monitor.changtian.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.GetSchemeQuotationInfoBean;

/**
 * Created by ken on 2018/7/30.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class FirsttrialAdapter extends BaseQuickAdapter<GetSchemeQuotationInfoBean, BaseViewHolder> {
    public FirsttrialAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetSchemeQuotationInfoBean item) {

        if (item.getSchemename() != null) {
            helper.setText(R.id.tv_detetionname, item.getSchemename());
        }
        if (item.getRemark() != null) {
            helper.setText(R.id.tv_remark, item.getRemark());
        }
        if (item.getUsername() != null) {
            helper.setText(R.id.tv_contractname, item.getUsername());
        }
        if (item.getCreatetime() != null) {
            helper.setText(R.id.tv_signDate, item.getCreatetime());
        }
        if (item.getSumtotal() != null) {
            helper.setText(R.id.tv_money, item.getSumtotal());
        }

        TextView tv_money1 = helper.getView(R.id.tv_money1);
        if (tv_money1 != null) {
            tv_money1.setText(item.getDiscountnumber());
        }


        ImageView iv_image2 = helper.getView(R.id.iv_image2);
        if (item.getSqstatus() != null) {
            if (item.getSqstatus().equals("2")) {
                iv_image2.setVisibility(View.VISIBLE);
                iv_image2.setImageResource(R.drawable.pass);
            } else if (item.getSqstatus().equals("1")) {
                iv_image2.setVisibility(View.VISIBLE);
                iv_image2.setImageResource(R.drawable.nopass);
            } else if (item.getSqstatus().equals("4")) {
                iv_image2.setVisibility(View.VISIBLE);
                iv_image2.setImageResource(R.drawable.pass);
            } else if (item.getSqstatus().equals("3")) {
                iv_image2.setVisibility(View.VISIBLE);
                iv_image2.setImageResource(R.drawable.nopass);
            } else {
                iv_image2.setVisibility(View.GONE);
            }
        }


        helper.addOnClickListener(R.id.ll_look);
    }
}

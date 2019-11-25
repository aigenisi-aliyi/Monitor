package com.monitor.sample.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.GetsamplemanageInfoBean;

/**
 * Created by ken on 2018/8/10.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class InspectionDAdapter extends BaseQuickAdapter<GetsamplemanageInfoBean, BaseViewHolder> {
    public InspectionDAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetsamplemanageInfoBean item) {

        TextView endtime = helper.getView(R.id.endtime);
        endtime.setVisibility(View.GONE);
        if (item.getSamplenumber() != null) {
            helper.setText(R.id.tv_subject, item.getSamplenumber());
        }
        if (item.getOperationtime() != null) {
            helper.setText(R.id.starttime, item.getOperationtime());
        }
    }
}

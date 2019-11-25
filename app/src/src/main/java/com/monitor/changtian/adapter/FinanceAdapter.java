package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.FinanceListBean;
import com.monitor.changtian.R;

/**
 * Created by ken on 2018/6/26.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class FinanceAdapter extends BaseQuickAdapter<FinanceListBean, BaseViewHolder> {
    public FinanceAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FinanceListBean item) {

        helper.setText(R.id.tv_proname, item.getNames());
        helper.setText(R.id.tv_time, item.getRiqi());
        helper.setText(R.id.tv_name, item.getProjectItemname());

        helper.addOnClickListener(R.id.ll_info);
        helper.addOnClickListener(R.id.tv_look);
    }
}

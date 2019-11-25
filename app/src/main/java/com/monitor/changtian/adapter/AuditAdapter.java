package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.MessageBean;

/**
 * Created by ken on 2018/5/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class AuditAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {
    public AuditAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {

        helper.setText(R.id.tv_time, item.getStime());
        helper.setText(R.id.tv_count, item.getContent());
        helper.setText(R.id.tv_name, item.getUserName());

        helper.addOnClickListener(R.id.ll_audits);
    }
}
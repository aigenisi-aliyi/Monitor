package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.AllUserInfo;

/**
 * Created by ken on 2018/6/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class AllUserInfoAdapter extends BaseQuickAdapter<AllUserInfo, BaseViewHolder> {
    public AllUserInfoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllUserInfo item) {

        if (item != null) {
            helper.setText(R.id.tv_dialog_title, item.getUserName());
        }
        helper.addOnClickListener(R.id.tv_dialog_title);
    }
}
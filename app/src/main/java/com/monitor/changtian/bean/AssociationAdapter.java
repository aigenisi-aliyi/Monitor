package com.monitor.changtian.bean;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;

/**
 * Created by Administrator on 2018/10/19.
 */

public class AssociationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public AssociationAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        if (item != null) {
            helper.setText(R.id.tv_codeStr, item);
        }
        helper.addOnLongClickListener(R.id.tv_codeStr);
    }
}

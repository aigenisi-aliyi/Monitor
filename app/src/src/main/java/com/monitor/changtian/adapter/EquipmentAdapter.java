package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.DicDataBean;

/**
 * Created by ken on 2018/7/9.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class EquipmentAdapter extends BaseQuickAdapter<DicDataBean, BaseViewHolder> {
    public EquipmentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DicDataBean item) {

        if (item.getDataValue() != null) {
            helper.setText(R.id.tv_name, item.getDataValue());
        }
        helper.addOnClickListener(R.id.ll_onclik);
    }
}

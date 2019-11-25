package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.EquipInOutStockDataBean;

/**
 * Created by ken on 2018/8/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ReceiveConAdapter extends BaseQuickAdapter<EquipInOutStockDataBean, BaseViewHolder> {
    public ReceiveConAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, EquipInOutStockDataBean item) {
        if (item.getNames() != null) {
            helper.setText(R.id.tv_name, item.getNames());
        }
    }
}


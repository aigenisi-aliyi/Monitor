package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.EquipmentDataBean;

/**
 * Created by ken on 2018/7/9.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ConsumableinfoAdapter extends BaseQuickAdapter<EquipmentDataBean, BaseViewHolder> {
    public ConsumableinfoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, EquipmentDataBean item) {

        if (item.getNames() != null) {
            helper.setText(R.id.tv_name, item.getNames());
        }

    }
}
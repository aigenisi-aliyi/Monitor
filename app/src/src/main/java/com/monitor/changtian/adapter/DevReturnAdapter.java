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

public class DevReturnAdapter extends BaseQuickAdapter<EquipInOutStockDataBean, BaseViewHolder> {

    public DevReturnAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, EquipInOutStockDataBean item) {

        if (item != null) {
            if (item.getNames().length() > 0) {
                helper.setText(R.id.tv_name, item.getNames());
            } else {
                helper.setText(R.id.tv_name, "");
            }

            if (item.getUseddate().length() > 0) {
                helper.setText(R.id.tv_time, item.getUseddate());
            } else {
                helper.setText(R.id.tv_time, "");
            }

            if (item.getUsername().length() > 0) {
                helper.setText(R.id.tv_person,"领用人:"+ item.getUsername());
            } else {
                helper.setText(R.id.tv_person, "");
            }
        }

        helper.addOnClickListener(R.id.ll_look);

    }
}

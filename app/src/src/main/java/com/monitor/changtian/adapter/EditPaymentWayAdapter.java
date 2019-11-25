package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.BuyWayBean;

/**
 * Created by ken on 2018/6/26.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class EditPaymentWayAdapter extends BaseQuickAdapter<BuyWayBean, BaseViewHolder> {
    public EditPaymentWayAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BuyWayBean item) {

        helper.setText(R.id.tv_value, item.getDataValue());

    }


}

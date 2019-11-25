package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.GetqualitycontrolBean;

/**
 * Created by ken on 2018/8/3.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class AwaitAdapter extends BaseQuickAdapter<GetqualitycontrolBean, BaseViewHolder> {
    public AwaitAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetqualitycontrolBean item) {

        if (item.getPointsaddress() != null) {
            helper.setText(R.id.tv_address, item.getPointsaddress());
        }
        if (item.getQualitycontrolname() != null) {
            helper.setText(R.id.tv_quality, item.getQualitycontrolname());
        }
//        if (item.getNumbers() != null) {
//            helper.setText(R.id.tv_number, item.getNumbers());
//        }
    }
}

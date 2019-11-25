package com.monitor.changtian.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SchemeDeviceBean;
import com.monitor.changtian.widght.AmountView;
import com.vise.log.ViseLog;

/**
 * Created by ken on 2018/7/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SchemeDeviceAdapter extends BaseQuickAdapter<SchemeDeviceBean, BaseViewHolder> {
    public SchemeDeviceAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, SchemeDeviceBean item) {

        helper.setText(R.id.tv_name, item.getCategoryname());
        AmountView amount_view = helper.getView(R.id.amount_view);
        amount_view.setGoods_storage(9999);

        try {
            if (item.getSumnumber_s() != null) {
                amount_view.setValue(Integer.parseInt(item.getSumnumber_s()));
            } else {
                amount_view.setValue(Integer.parseInt(item.getSumnumber()));
            }

        } catch (
                Exception e) {

        }
        amount_view.setOnAmountChangeListener(new AmountView.OnAmountChangeListener()

        {
            @Override
            public void onAmountChange(View view, int amount) {
                SchemeDeviceAdapter.this.getData().get(helper.getLayoutPosition()).setSumnumber_s(amount + "");
            }
        });
    }
}

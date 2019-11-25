package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.clj.fastble.data.BleDevice;
import com.monitor.changtian.R;

/**
 * Created by Administrator on 2018/11/6.
 */

public class MeteorologicalAdapter extends BaseQuickAdapter<BleDevice, BaseViewHolder> {
    public MeteorologicalAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BleDevice item) {

        helper.setText(R.id.tv_content, item.getName());        //蓝牙名称
        helper.setText(R.id.tv_macinfo, item.getMac());         //蓝牙MAC地址
        helper.addOnClickListener(R.id.ll_look);                //蓝牙列表框
    }
}

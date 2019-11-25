package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.QueryTestRecordBean;

/**
 * Created by Administrator on 2018/12/19.
 */

public class Quality_YSJL_Item_Adapter extends BaseQuickAdapter<QueryTestRecordBean.TdataBean.TestResultBean.FactorsBean, BaseViewHolder> {
    public Quality_YSJL_Item_Adapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryTestRecordBean.TdataBean.TestResultBean.FactorsBean item) {

        if (item.getFname().length() > 0) {
            helper.setText(R.id.tv_001, item.getFname());
        } else {
            helper.setText(R.id.tv_001, "");
        }
        if (item.getRdata().getArea().length() > 0) {
            helper.setText(R.id.tv_002, item.getRdata().getArea());
        } else {
            helper.setText(R.id.tv_002, "");
        }
        if (item.getRdata().getThickness().length() > 0) {
            helper.setText(R.id.tv_003, item.getRdata().getThickness());
        } else {
            helper.setText(R.id.tv_003, "");
        }

    }
}

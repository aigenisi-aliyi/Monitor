package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.QueryTestRecordBean;

/**
 * Created by Administrator on 2018/12/19.
 */

public class Quality_BZQX_Item_Adapter extends BaseQuickAdapter<QueryTestRecordBean.TdataBean.StdCurveBean.CdataBean, BaseViewHolder> {

    public Quality_BZQX_Item_Adapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryTestRecordBean.TdataBean.StdCurveBean.CdataBean item) {

        if (item.getDnum().length() > 0) {
            helper.setText(R.id.tv_001, item.getDnum());
        }else{
            helper.setText(R.id.tv_001, "");
        }
        if (item.getValues().length() > 0) {
            helper.setText(R.id.tv_002, item.getValues());
        }
        else{
            helper.setText(R.id.tv_002, "");
        }
        if (item.getAreas().length() > 0) {
            helper.setText(R.id.tv_003, item.getAreas());
        }
        else{
            helper.setText(R.id.tv_003, "");
        }

    }
}

package com.monitor.changtian.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.AccuseShowBean;

/**
 * Created by Administrator on 2018/10/16.
 */

public class AccuseSubmitItemAdapter extends BaseQuickAdapter<AccuseShowBean.FactorBean, BaseViewHolder> {
    public AccuseSubmitItemAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccuseShowBean.FactorBean item) {

        helper.setText(R.id.tv_name, item.getName());
        TextView tv_name = helper.getView(R.id.tv_name);

        if (item.getValue() != null) {
            if (item.getValue().length() > 0) {
                tv_name.setText(item.getName() + "(" + item.getValue() + ")");
            } else {
                tv_name.setText(item.getName());
            }
        } else {
            tv_name.setText(item.getName());
        }

    }
}

package com.monitor.changtian.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.AccuseShowBean;
import com.monitor.changtian.bean.GetqualitycontrolBean;

/**
 * Created by Administrator on 2018/10/22.
 */

public class AccuseAllinfoItemAdapter extends BaseQuickAdapter<GetqualitycontrolBean.FactorsBean, BaseViewHolder> {
    public AccuseAllinfoItemAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetqualitycontrolBean.FactorsBean item) {

        TextView tv_name = helper.getView(R.id.tv_name);

        if (item.getSetvalue().length() > 0) {
            tv_name.setText(item.getFactorname() + "(" + item.getSetvalue() + ")");
        } else {
            tv_name.setText(item.getFactorname());
        }
    }
}


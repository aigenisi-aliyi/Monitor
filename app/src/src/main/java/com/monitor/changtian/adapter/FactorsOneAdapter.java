package com.monitor.changtian.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;

/**
 * Created by ken on 2018/7/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class FactorsOneAdapter extends BaseQuickAdapter<DetectionSchemeInfoBean.LeftcateogoryBean.FactorsBean, BaseViewHolder> {
    public Context mContext;

    public FactorsOneAdapter(Context mContext, int layoutResId) {
        super(layoutResId);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, DetectionSchemeInfoBean.LeftcateogoryBean.FactorsBean item) {

        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_content = helper.getView(R.id.tv_content);
        tv_title.setText("检测因子:" + item.getFactorname());
        tv_content.setText("判定依据:" + item.getDecisiontablenames() + item.getDecisionlevel() + "\n" +
                "检测方法名称:" + item.getMethodname() + "\n" +
                "检测频次:" + item.getDaysNumber() + "天\t\t(每天" + item.getFrequency() + "次)\t");
    }
}

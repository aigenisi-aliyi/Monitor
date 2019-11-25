package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.TestProjectCycleListBean;

/**
 * Created by Administrator on 2018/12/19.
 */

public class QualitycontrolApprovalAdapter extends BaseQuickAdapter<TestProjectCycleListBean, BaseViewHolder> {
    public QualitycontrolApprovalAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestProjectCycleListBean item) {

        if (item.getProjectname().length() > 0) {
            helper.setText(R.id.tv_detetionname, item.getProjectname());
        } else {
            helper.setText(R.id.tv_detetionname, "");
        }
        if (item.getTaskNumber().length() > 0) {
            helper.setText(R.id.tv_contractname, "第" + item.getTaskNumber() + "周期");
        } else {
            helper.setText(R.id.tv_contractname, "");
        }
        helper.addOnClickListener(R.id.ll_look);
    }
}

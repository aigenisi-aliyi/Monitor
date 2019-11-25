package com.monitor.changtian.adapter;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.ProjectcontractBean;

/**
 * Created by ken on 2018/6/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class FinanceMainAdapter extends BaseQuickAdapter<ProjectcontractBean, BaseViewHolder> {
    public FinanceMainAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectcontractBean item) {

        LinearLayout ll_finish = helper.getView(R.id.ll_finish_money);
        LinearLayout ll_look = helper.getView(R.id.ll_look);


        helper.setText(R.id.tv_contractcode, "No: "+item.getContractcode());
        if (item.getContractstatus().equals("1")) {
            helper.setText(R.id.tv_contractstatus, "待付款");
            helper.setText(R.id.tv_AdvanceCharge, "￥" + item.getAdvanceCharge());
        } else if (item.getContractstatus().equals("2")) {
            helper.setText(R.id.tv_contractstatus, "待付尾款");
            helper.setText(R.id.tv_moneyss, "待付尾款:");
            helper.setText(R.id.tv_AdvanceCharge, "￥" + item.getTailmoney());
        } else if (item.getContractstatus().equals("3")) {
            helper.setText(R.id.tv_contractstatus, "已结款");
            ll_finish.setVisibility(View.GONE);
            ll_look.setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.tv_contractname, item.getContractname());
        helper.setText(R.id.tv_schemename, item.getSchemename());
        helper.setText(R.id.tv_signDate, "签订:" + item.getSignDate());
        if(item.getCompletetime().length()>0){
            helper.setText(R.id.tv_latestpaydate, "截至:" +item.getCompletetime().substring(0,10));
        }

        helper.setText(R.id.tv_totalmoney, "￥" + item.getTotalmoney());

        helper.addOnClickListener(R.id.ll_infos);
        helper.addOnClickListener(R.id.tv_look_info);
    }
}

package com.monitor.taskallocation.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.FactorsOneAdapter;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;

/**
 * Created by ken on 2018/7/16.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskCorrelationinfoOneAdapter extends BaseQuickAdapter<DetectionSchemeInfoBean.LeftcateogoryBean, BaseViewHolder> {

    public Context mContext;
    TaskCorrelationinfoTwoAdapter taskCorrelationinfoTwoAdapter;
    FactorsOneAdapter factorsOneAdapter;

    public TaskCorrelationinfoOneAdapter(Context mContext, int layoutResId) {
        super(layoutResId);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, DetectionSchemeInfoBean.LeftcateogoryBean item) {

        RecyclerView rv_list = helper.getView(R.id.rv_list);
        if (item.getCategoryname() != null) {
            helper.setText(R.id.tv_type, item.getCategoryname());
        }
        if (item.getCriterionname() != null) {
            helper.setText(R.id.tv_criterionname, item.getCriterionname());
        }



        try {
            /**
             * 现场采样
             */
            if (item.getPoints() != null && item.getPoints().size() > 0) {

                taskCorrelationinfoTwoAdapter = new TaskCorrelationinfoTwoAdapter(mContext, R.layout.taskcorrelationinfo_two);
                rv_list.setLayoutManager(new LinearLayoutManager(mContext));
                rv_list.setAdapter(taskCorrelationinfoTwoAdapter);
                taskCorrelationinfoTwoAdapter.setNewData(item.getPoints());
            }
            /**
             * 客户送样
             */
            else if (item.getFactors().size() > 0 && item.getFactors() != null) {

                factorsOneAdapter = new FactorsOneAdapter(mContext, R.layout.taskcorrelationinfo_three);
                rv_list.setLayoutManager(new LinearLayoutManager(mContext));
                rv_list.setAdapter(factorsOneAdapter);
                factorsOneAdapter.setNewData(item.getFactors());
            }
        } catch (Exception e) {
//           LogAndToastUtil.toast(mContext,"数据异常");
        }


    }


}

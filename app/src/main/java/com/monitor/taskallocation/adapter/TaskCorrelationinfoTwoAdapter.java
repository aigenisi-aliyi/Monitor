package com.monitor.taskallocation.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;

/**
 * Created by ken on 2018/7/16.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskCorrelationinfoTwoAdapter extends BaseQuickAdapter<DetectionSchemeInfoBean.LeftcateogoryBean.PointsBean, BaseViewHolder> {

    public Context mContext;

    public TaskCorrelationinfoTwoAdapter(Context mContext, int layoutResId) {
        super(layoutResId);
        this.mContext = mContext;
    }

    TaskCorrelationinfoThreeAdapter taskCorrelationinfoThreeAdapter;

    @Override
    protected void convert(BaseViewHolder helper, DetectionSchemeInfoBean.LeftcateogoryBean.PointsBean item) {


        if (item.getAddress() != null) {
            helper.setText(R.id.tv_title, "点位名称:" + item.getAddress());
        } else {

        }

        RecyclerView rv_list = helper.getView(R.id.rv_list);
        taskCorrelationinfoThreeAdapter = new TaskCorrelationinfoThreeAdapter(mContext, R.layout.taskcorrelationinfo_three);
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.setAdapter(taskCorrelationinfoThreeAdapter);
        taskCorrelationinfoThreeAdapter.setNewData(item.getFactors());

        TextView tv_reamks = helper.getView(R.id.tv_reamks);
        if(item.getRemark()!=null){
            if (item.getRemark().length() > 0) {
                tv_reamks.setVisibility(View.VISIBLE);
                tv_reamks.setText("(" + item.getRemark() + ")");
            }else{
                tv_reamks.setVisibility(View.GONE);
            }
        }
    }


}

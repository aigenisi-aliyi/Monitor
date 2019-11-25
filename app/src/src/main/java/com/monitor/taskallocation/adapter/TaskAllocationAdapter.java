package com.monitor.taskallocation.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.ProjectcontractBean;

/**
 * Created by ken on 2018/7/3.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskAllocationAdapter extends BaseQuickAdapter<ProjectcontractBean, BaseViewHolder> {


    public TaskAllocationAdapter(int layoutResId) {
        super(layoutResId);

    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectcontractBean item) {


        helper.setText(R.id.tv_projectnames, item.getSchemename());
        helper.setText(R.id.tv_contractname, item.getContractname());
        helper.setText(R.id.tv_signDate, "签订日期:" + item.getSignDate());
        helper.setText(R.id.tv_contractcode, "No:" + item.getContractcode());
        helper.addOnClickListener(R.id.ll_infos);
        ImageView iv_image = helper.getView(R.id.iv_image);
        ImageView iv_image2 = helper.getView(R.id.iv_image2);


        if(item.getSamplemodename().equals("客户送样")){
            iv_image2.setVisibility(View.GONE);
        }else{
            if (item.getTaskstatus().equals("1")) {
                iv_image2.setImageResource(R.drawable.undis);
                iv_image2.setVisibility(View.VISIBLE);
            }  else {
                iv_image2.setVisibility(View.GONE);
            }

//            else if (item.getTaskstatus().equals("2")) {
//                iv_image2.setImageResource(R.drawable.ondis);
//                iv_image2.setVisibility(View.VISIBLE);
//            } else if (item.getTaskstatus().equals("3")) {
//                iv_image2.setImageResource(R.drawable.overdis);
//                iv_image2.setVisibility(View.VISIBLE);
//            }
        }

        if (item.getIsUrgent().equals("0")) {
            iv_image.setVisibility(View.GONE);
        }
        if (item.getIsUrgent().equals("1")) {
            iv_image.setVisibility(View.VISIBLE);
        }

    }
}

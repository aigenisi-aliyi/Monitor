package com.monitor.changtian.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.TaskListBean;

/**
 * Created by ken on 2018/8/7.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskPointAdapter extends BaseQuickAdapter<TaskListBean.PointsBean, BaseViewHolder> {
    public TaskPointAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskListBean.PointsBean item) {

        if (item.getAddress() != null) {
            helper.setText(R.id.tv_devname, "点位名称:" + item.getAddress());
        } else {
            helper.setText(R.id.tv_devname, "");
        }
        TextView tv_reamks = helper.getView(R.id.tv_reamks);
        if(item.getRemark()!=null){
            if(item.getRemark().length()>0){
                tv_reamks.setVisibility(View.VISIBLE);
                tv_reamks.setText("("+item.getRemark()+")");
            }else{
                tv_reamks.setVisibility(View.GONE);
            }
        }
        if (item.getCategoryname() != null) {

            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < item.getFactors().size(); i++) {
                stringBuffer.append(item.getFactors().get(i).getFactorname() + ",");
            }


            helper.setText(R.id.tv_dev_con, "检测项目:" + item.getCategoryname() + "\n"
                    + "检测频次:" + item.getDaysNumber() + "天" + "(1天" + item.getFrequency() + "次)"
                    + "\n" + "检测因子:" + stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1));
        } else {
            helper.setText(R.id.tv_dev_con, "");
        }

    }
}

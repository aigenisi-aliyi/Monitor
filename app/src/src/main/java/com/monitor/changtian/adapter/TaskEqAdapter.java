package com.monitor.changtian.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.TaskListBean;

/**
 * Created by Administrator on 2018/11/1.
 */

public class TaskEqAdapter extends BaseQuickAdapter<TaskListBean.EquipsBean, BaseViewHolder> {
    public TaskEqAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskListBean.EquipsBean item) {

        TextView tv_name = helper.getView(R.id.tv_name);

        if (item.getCategoryname().length() > 0) {
            tv_name.setTextColor(getColor("#757575"));
            tv_name.setText(item.getCategoryname() + "(" + item.getTotalnumber() + ")");
        }
    }

    private int getColor(String colorStr) {
        if (TextUtils.isEmpty(colorStr)) return Color.BLACK;
        if (!colorStr.startsWith("#")) colorStr = "#" + colorStr;
        return Color.parseColor(colorStr);
    }
}

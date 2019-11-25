package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.TaskEquipmentDataBean;

/**
 * Created by ken on 2018/6/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskEquAdapter extends BaseQuickAdapter<TaskEquipmentDataBean, BaseViewHolder> {

    public TaskEquAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskEquipmentDataBean item) {

        if (item.getNames() != null) {
            helper.setText(R.id.tv_equ_name, item.getNames());
        }
    }
}

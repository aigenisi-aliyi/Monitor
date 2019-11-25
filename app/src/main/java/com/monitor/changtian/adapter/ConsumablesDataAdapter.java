package com.monitor.changtian.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.GetConsumablesDataBean;
import com.vise.log.ViseLog;

/**
 * Created by ken on 2018/8/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ConsumablesDataAdapter extends BaseQuickAdapter<GetConsumablesDataBean, BaseViewHolder> {
    public ConsumablesDataAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, GetConsumablesDataBean item) {
        final CheckBox cb_doctor = helper.getView(R.id.cb_doctor);
        helper.addOnClickListener(R.id.cb_doctor);
        if (item.getNames() != null) {
            cb_doctor.setText(item.getNames());
        }
        cb_doctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 通过这个方法，来监听当前的checkbox是否被选中

                if (isChecked) {
                    ConsumablesDataAdapter.this.getData().get(helper.getLayoutPosition()).setChoice(true);
                } else {
                    ViseLog.d("取消选中");
                    ConsumablesDataAdapter.this.getData().get(helper.getLayoutPosition()).setChoice(false);
                }
            }
        });

        if (ConsumablesDataAdapter.this.getData().get(helper.getLayoutPosition()).isChoice_save()) {
            cb_doctor.setChecked(true);
        } else {
            cb_doctor.setChecked(false);
        }
    }
}

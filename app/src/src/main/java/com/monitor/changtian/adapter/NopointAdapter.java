package com.monitor.changtian.adapter;

import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.NoPointsTaskDataBean;

/**
 * Created by ken on 2018/7/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class NopointAdapter extends BaseQuickAdapter<NoPointsTaskDataBean, BaseViewHolder> {
    public NopointAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, NoPointsTaskDataBean item) {
        final CheckBox cb_doctor = helper.getView(R.id.cb_doctor);
        helper.addOnClickListener(R.id.cb_doctor);
        if (item.getAddress() != null) {
            cb_doctor.setText(item.getAddress());
        }

//        cb_doctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // 通过这个方法，来监听当前的checkbox是否被选中
//
//
//                if (isChecked) {
//                    ViseLog.d("选中");
//                    NopointAdapter.this.getData().get(helper.getLayoutPosition()).setChoice(true);
//                } else {
//                    ViseLog.d("取消选中");
//                    NopointAdapter.this.getData().get(helper.getLayoutPosition()).setChoice(false);
//                }
//            }
//        });


        if (NopointAdapter.this.getData().get(helper.getLayoutPosition()).isChoice()) {
            cb_doctor.setChecked(true);
        } else {
            cb_doctor.setChecked(false);
        }
    }
}

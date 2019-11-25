package com.monitor.changtian.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.CarDataListBean;
import com.vise.log.ViseLog;

/**
 * Created by ken on 2018/7/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class CarInfoAdapter extends BaseQuickAdapter<CarDataListBean, BaseViewHolder> {


    public CarInfoAdapter(int layoutResId ) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, CarDataListBean item) {


        LinearLayout ll_check = helper.getView(R.id.ll_check);
        final CheckBox cb_doctor = helper.getView(R.id.cb_doctor);

        helper.addOnClickListener(R.id.cb_doctor);

        if (item.getVlicense() != null) {
            helper.setText(R.id.tv_vlicense, item.getVlicense());
        }

        if (item.getVitemName() != null) {
            helper.setText(R.id.tv_vitemName, item.getVitemName());
        }
        cb_doctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 通过这个方法，来监听当前的checkbox是否被选中
                if (isChecked) {
                    ViseLog.d("选中");
                    CarInfoAdapter.this.getData().get(helper.getLayoutPosition()).setChoice(true);
                } else {
                    ViseLog.d("取消选中");
                    CarInfoAdapter.this.getData().get(helper.getLayoutPosition()).setChoice(false);
                }
            }
        });
        if (CarInfoAdapter.this.getData().get(helper.getLayoutPosition()).isChoice_save()) {
            cb_doctor.setChecked(true);
        } else {
            cb_doctor.setChecked(false);
        }
    }
}

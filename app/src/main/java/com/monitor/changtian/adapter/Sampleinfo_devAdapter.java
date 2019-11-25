package com.monitor.changtian.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.FieldsamplingDetailBean;

/**
 * Created by ken on 2018/7/13.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class Sampleinfo_devAdapter extends BaseQuickAdapter<FieldsamplingDetailBean.EquipsBean, BaseViewHolder> {
    public Sampleinfo_devAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FieldsamplingDetailBean.EquipsBean item) {
        if (item.getNames() != null) {
            helper.setText(R.id.tv_devname, item.getNames() + "(" + item.getEquipmentNumber() + ")");
        }
        TextView tv_dev_con = helper.getView(R.id.tv_dev_con);
        tv_dev_con.setVisibility(View.GONE);
//        if (item.getCalibrations() != null && item.getCalibrations().size() > 0) {
//            helper.setText(R.id.tv_dev_con, "校准人:" + item.getCalibrations().get(0).getOperationusername() + "\n" +
//                    "校准设备:" + item.getCalibrations().get(0).getCalnames() + "(" + item.getCalibrations().get(0).getCalEquipmentNumber() + ")" + "\n"
//                    + "校准前值:" + item.getCalibrations().get(0).getPremeasurement() + "\n"
//                    + "校准后值:" + item.getCalibrations().get(0).getPostmeasurement()
//            );
//        } else {
//            helper.setText(R.id.tv_dev_con, "");
//        }

    }
}


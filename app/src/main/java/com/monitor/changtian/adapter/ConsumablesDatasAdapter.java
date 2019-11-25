package com.monitor.changtian.adapter;

import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.PreInfoDataBean;

/**
 * Created by Administrator on 2018/12/17.
 */

public class ConsumablesDatasAdapter extends BaseQuickAdapter<EquipmentDataBean, BaseViewHolder> {

    private int selected = -1;

    public ConsumablesDatasAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, EquipmentDataBean item) {
        final CheckBox cb_doctor = helper.getView(R.id.cb_doctor);
        cb_doctor.setText(item.getNames());
        helper.addOnClickListener(R.id.ll_check);
        helper.addOnClickListener(R.id.cb_doctor);
        if (selected == helper.getAdapterPosition()) {
            cb_doctor.setChecked(true);
            ConsumablesDatasAdapter.this.getData().get(helper.getLayoutPosition()).setSelected(true);
        } else {
            cb_doctor.setChecked(false);
            ConsumablesDatasAdapter.this.getData().get(helper.getLayoutPosition()).setSelected(false);
        }
    }

    public void setSelection(int position) {
        if (selected == position) {
            this.selected = -1;
            notifyDataSetChanged();
        } else {
            this.selected = position;
            notifyDataSetChanged();
        }

    }
}

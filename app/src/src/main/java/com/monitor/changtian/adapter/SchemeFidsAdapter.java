package com.monitor.changtian.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SchemeFidsBean;
import com.vise.log.ViseLog;

/**
 * Created by Administrator on 2018/12/17.
 */

public class SchemeFidsAdapter extends BaseQuickAdapter<SchemeFidsBean, BaseViewHolder> {
    private int selected = -1;

    public SchemeFidsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, SchemeFidsBean item) {

        final CheckBox cb_doctor = helper.getView(R.id.cb_doctor);
        helper.addOnClickListener(R.id.cb_doctor);
        if (item.getFactorname() != null) {
            cb_doctor.setText(item.getFactorname());
        }
        helper.addOnClickListener(R.id.ll_check);
        helper.addOnClickListener(R.id.cb_doctor);
        if (selected == helper.getAdapterPosition()) {
            cb_doctor.setChecked(true);
            SchemeFidsAdapter.this.getData().get(helper.getLayoutPosition()).setSelected(true);
        } else {
            cb_doctor.setChecked(false);
            SchemeFidsAdapter.this.getData().get(helper.getLayoutPosition()).setSelected(false);
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

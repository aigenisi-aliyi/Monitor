package com.monitor.changtian.adapter;

import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.PreDataBean;
import com.monitor.changtian.bean.PreInfoDataBean;

/**
 * Created by Administrator on 2018/12/14.
 */

public class PreInfoDataadapter extends BaseQuickAdapter<PreInfoDataBean, BaseViewHolder> {
    private int selected = -1;

    public PreInfoDataadapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PreInfoDataBean item) {
        final CheckBox cb_doctor = helper.getView(R.id.cb_doctor);
        cb_doctor.setText(item.getCol4() + "(" + item.getCol5() + ")");
        helper.addOnClickListener(R.id.ll_check);
        helper.addOnClickListener(R.id.cb_doctor);
        if (selected == helper.getAdapterPosition()) {
            cb_doctor.setChecked(true);
            PreInfoDataadapter.this.getData().get(helper.getLayoutPosition()).setSelected(true);
        } else {
            cb_doctor.setChecked(false);
            PreInfoDataadapter.this.getData().get(helper.getLayoutPosition()).setSelected(false);
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

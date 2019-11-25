package com.monitor.changtian.adapter;

import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.PreDataBean;

/**
 * Created by Administrator on 2018/12/14.
 */

public class PreListDataAdapter extends BaseQuickAdapter<PreDataBean.DataListBean, BaseViewHolder> {
    private int selected = -1;
    public PreListDataAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PreDataBean.DataListBean item) {
        final CheckBox cb_doctor = helper.getView(R.id.cb_doctor);
        cb_doctor.setText(item.getCol2()+"("+ item.getCol9()+")");
        helper.addOnClickListener(R.id.ll_check);
        helper.addOnClickListener(R.id.cb_doctor);
        if (selected == helper.getAdapterPosition()) {
            cb_doctor.setChecked(true);
            PreListDataAdapter.this.getData().get(helper.getLayoutPosition()).setSelected(true);
        } else {
            cb_doctor.setChecked(false);
            PreListDataAdapter.this.getData().get(helper.getLayoutPosition()).setSelected(false);
        }
    }

    public void setSelection(int position) {
        if(selected==position)
        {
            this.selected = -1;
            notifyDataSetChanged();
        } else{

            this.selected = position;
            notifyDataSetChanged();
        }

    }
}

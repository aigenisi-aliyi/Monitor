package com.monitor.changtian.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SampleInfoByPointInfoBean;
import com.vise.log.ViseLog;

/**
 * Created by Administrator on 2018/11/23.
 */

public class Analysi_Yz_GoneAdapter extends BaseQuickAdapter<SampleInfoByPointInfoBean.DetailsBean.FactorsBean, BaseViewHolder> {
    public Analysi_Yz_GoneAdapter(int layoutResId) {
        super(layoutResId);
    }
    private int selected = -1;
    @Override
    protected void convert(final BaseViewHolder helper, SampleInfoByPointInfoBean.DetailsBean.FactorsBean item) {

        final CheckBox cb_doctor = helper.getView(R.id.cb_doctor);
        if(item.getFactorname()!=null){
            cb_doctor.setText(item.getFactorname());
        }
        helper.addOnClickListener(R.id.ll_check);
        helper.addOnClickListener(R.id.cb_doctor);
        if (selected == helper.getAdapterPosition()) {
            cb_doctor.setChecked(true);
            Analysi_Yz_GoneAdapter.this.getData().get(helper.getLayoutPosition()).setSelected(true);
        } else {
            cb_doctor.setChecked(false);
            Analysi_Yz_GoneAdapter.this.getData().get(helper.getLayoutPosition()).setSelected(false);
        }
    }

    public void setSelection(int position) {
        this.selected = position;
        notifyDataSetChanged();
    }

}

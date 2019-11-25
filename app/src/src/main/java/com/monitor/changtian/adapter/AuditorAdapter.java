package com.monitor.changtian.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SampleInfoCompreData;

/**
 * Created by ken on 2018/5/30.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class AuditorAdapter extends BaseQuickAdapter<SampleInfoCompreData.SampleDetailsBean, BaseViewHolder> {
    public AuditorAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SampleInfoCompreData.SampleDetailsBean item) {

        TextView tv_au_edit = helper.getView(R.id.tv_au_edit);

        if (item != null) {
            helper.setText(R.id.tv_au_num, item.getSampleNum());

            helper.setText(R.id.tv_au_name, item.getUsername());
            helper.setText(R.id.tv_au_time, item.getCreatetime());
            if (item.getUserid().equals("操作")) {
                tv_au_edit.setText("操作");
            } else {
                tv_au_edit.setText("查看详情");

            }

            helper.addOnClickListener(R.id.tv_au_edit);

        }

    }
}
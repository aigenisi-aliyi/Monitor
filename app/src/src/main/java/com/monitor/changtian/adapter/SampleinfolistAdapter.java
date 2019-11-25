package com.monitor.changtian.adapter;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.FieldsamplingInfo;

/**
 * Created by ken on 2018/7/13.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SampleinfolistAdapter extends BaseQuickAdapter<FieldsamplingInfo, BaseViewHolder> {
    public SampleinfolistAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, FieldsamplingInfo item) {


        LinearLayout ll_isShow = helper.getView(R.id.ll_isShow);
        if (item.getIsEnd().equals("1")) {
            ll_isShow.setVisibility(View.GONE);
        }
        else{
            ll_isShow.setVisibility(View.VISIBLE);
        }
//
//        if (item.getLeftcategoryname() != null) {
//            helper.setText(R.id.tv_type, item.getLeftcategoryname());
//        }
//        if (item.getAddress() != null) {
//            helper.setText(R.id.tv_address, item.getAddress());
//        }
//        if (item.getCurrentnumber() != null) {
//            helper.setText(R.id.tv_name, "第\t" + item.getCurrentnumber() + "\t频次");
//        }


        helper.setText(R.id.tv_type, "样品记录");
        helper.addOnClickListener(R.id.stv_false);
        helper.addOnClickListener(R.id.stv_true);
        helper.addOnClickListener(R.id.ll_infolook);

    }
}

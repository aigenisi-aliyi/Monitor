package com.monitor.changtian.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.GetfieldsamplingDetailListBean;

/**
 * Created by ken on 2018/8/14.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SampleProofreadAdapter extends BaseQuickAdapter<GetfieldsamplingDetailListBean, BaseViewHolder> {
    public SampleProofreadAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetfieldsamplingDetailListBean item) {

        if (item != null) {
            if (item.getOnlynumber() != null) {
                helper.setText(R.id.tv_number, "No:" + item.getOnlynumber());
            }
            if (item.getSampletypename() != null) {
                helper.setText(R.id.tv_type, item.getSampletypename());
            }
            if (item.getAnalysisitems() != null) {
                helper.setText(R.id.tv_F_project, item.getStarttime() + "-" + item.getEndtime());
            }
            ImageView iv_image2 = helper.getView(R.id.iv_image2);

            if (item.getStatevalue() != null) {
                if (item.getStatevalue().equals("1")) {
                    iv_image2.setVisibility(View.VISIBLE);
                    iv_image2.setImageResource(R.drawable.tobecollated);
                } else if (item.getStatevalue().equals("2")) {
                    iv_image2.setVisibility(View.VISIBLE);
                    iv_image2.setImageResource(R.drawable.collated);
                }else if (item.getStatevalue().equals("3")) {
                    iv_image2.setVisibility(View.VISIBLE);
                    iv_image2.setImageResource(R.drawable.audover);
                }
                else if (item.getStatevalue().equals("4")) {
                    iv_image2.setVisibility(View.VISIBLE);
                    iv_image2.setImageResource(R.drawable.audpause);
                }
                else {
                    iv_image2.setVisibility(View.GONE);
                }

            }
        }


        helper.addOnClickListener(R.id.ll_infolook);
    }
}

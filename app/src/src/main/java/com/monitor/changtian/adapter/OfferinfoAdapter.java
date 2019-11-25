package com.monitor.changtian.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.GetSchemePriceDetailListBean;

/**
 * Created by ken on 2018/7/30.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class OfferinfoAdapter extends BaseQuickAdapter<GetSchemePriceDetailListBean, BaseViewHolder> {
    public OfferinfoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetSchemePriceDetailListBean item) {

        TextView tv_pinci = helper.getView(R.id.tv_pinci);
        tv_pinci.setVisibility(View.VISIBLE);
        if (item.getTypesname() != null) {
            if (item.getTypesname().equals("分析费")) {
                helper.setText(R.id.tv_title, item.getFactorname());
            } else {
                helper.setText(R.id.tv_title, item.getTypesname());
            }
        }else{
            helper.setText(R.id.tv_title, item.getFactorname());
        }
        if (item.getSubtotal() != null) {
            helper.setText(R.id.tv_money, item.getSubtotal());
        }
        if (item.getRemark() != null) {
            helper.setText(R.id.tv_pinci, item.getRemark());
        }

     /*   if (item != null) {

            if (item.getTypesname() != null) {

                if (item.getTypesname().equals("分析费")) {

                    if (item.getFactorname() != null) {
                        helper.setText(R.id.tv_title, item.getFactorname());
                    }
                    if (item.getSubtotal() != null) {
                        helper.setText(R.id.tv_money, item.getSubtotal());
                    }
                    tv_pinci.setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_pinci, item.getDaysnumber() + "天\n" + "1天" + item.getTimes() + "次");
                } else {

                    tv_pinci.setVisibility(View.VISIBLE);
                    if (item.getTypes().equals("20")) {
                        if (item.getSourcename().length() > 0) {
                            helper.setText(R.id.tv_title, item.getSourcename() + "\t" + item.getRemark() + "个");
                        }
                        if (item.getSubtotal() != null) {
                            helper.setText(R.id.tv_money, item.getSubtotal());
                        }

                    } else {

                        if (item.getFactorname() != null && item.getFactorname().length() > 0) {
                            helper.setText(R.id.tv_title, item.getFactorname() + "\n" + item.getTypesname());
                        } else {
                            helper.setText(R.id.tv_title, item.getTypesname());
                        }
                        if (item.getSubtotal() != null) {
                            helper.setText(R.id.tv_money, item.getSubtotal());
                        }


//                        if(item.getFactorname().equals("车辆费")){
//                            helper.setText(R.id.tv_pinci, item.getDaysnumber() + "天\n" );
//                        }
//                        if(item.getFactorname().equals("差旅费")){
//                            helper.setText(R.id.tv_pinci, item.getDaysnumber() + "天\n" );
//                        }
//                        else{
//                            helper.setText(R.id.tv_pinci, item.getDaysnumber() + "天\n" + "1天" + item.getTimes()+"次");
//                        }

                    }

                    if (item.getTypes().equals("18")) {
                        helper.setText(R.id.tv_pinci, item.getDaysnumber() + "天");
                    } else if (item.getTypes().equals("19")) {
                        helper.setText(R.id.tv_pinci, item.getDaysnumber() + "天\n"+item.getTimes()+"人");
                    } else if (item.getTypes().equals("10")) {
                        helper.setText(R.id.tv_pinci, "");
                    }else {
                        helper.setText(R.id.tv_pinci, item.getDaysnumber() + "天\n" + "1天" + item.getTimes()+"次");
                    }

                }
                helper.setText(R.id.tv_pinci,"150元×1天×2人×4次");


            }

        }
*/

    }
}

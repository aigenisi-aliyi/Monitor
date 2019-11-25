package com.monitor.changtian.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.WeatherinfoListBean;

/**
 * Created by ken on 2018/4/24.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class WeatherListAdapter extends BaseQuickAdapter<WeatherinfoListBean, BaseViewHolder> {
    public WeatherListAdapter() {
        super(R.layout.weatherlist_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeatherinfoListBean item) {


        TextView tv_week = helper.getView(R.id.tv_week);
        TextView tv_type = helper.getView(R.id.tv_type);

//        if (item != null) {
//            tv_week.setText(item.getDatatime().substring(3, 6));
//            tv_type.setText(item.getType());
//        }
//        if(!item.getIstoday()){
//            tv_week.setTextColor(color(R.color.gray));
//            tv_type.setTextColor(color(R.color.gray));
//        }else{
//            tv_week.setTextColor(color(R.color.blak));
//            tv_type.setTextColor(color(R.color.blak));
//        }
    }

    private int color(int value) {
        return mContext.getResources().getColor(value);
    }
}

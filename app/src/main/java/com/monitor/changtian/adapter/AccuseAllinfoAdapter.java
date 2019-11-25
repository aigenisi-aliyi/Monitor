package com.monitor.changtian.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.AccuseShowBean;
import com.monitor.changtian.bean.GetqualitycontrolBean;

/**
 * Created by Administrator on 2018/10/22.
 */

public class AccuseAllinfoAdapter extends BaseQuickAdapter<GetqualitycontrolBean, BaseViewHolder> {
    public Context mContext;

    public AccuseAllinfoAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
        this.mContext = mContext;
    }

    boolean ishow = false;
    RecyclerView rv_list;
    LinearLayout ll_show;
    AccuseAllinfoItemAdapter accuseAllinfoItemAdapter;
    //    List<AccuseShowBean.FactorBean> trues = new ArrayList<>();
    ImageView iv_look;

    @Override
    protected void convert(final BaseViewHolder helper, GetqualitycontrolBean item) {


        TextView tv_potionname = helper.getView(R.id.tv_potionname);
        TextView tv_type = helper.getView(R.id.tv_type);
        iv_look = helper.getView(R.id.iv_look);
        rv_list = helper.getView(R.id.rv_list);
        accuseAllinfoItemAdapter = new AccuseAllinfoItemAdapter(R.layout.accusesub_item);
        rv_list.setLayoutManager(new GridLayoutManager(mContext, 2));
        rv_list.setAdapter(accuseAllinfoItemAdapter);
        accuseAllinfoItemAdapter.setNewData(item.getFactors());
        ll_show = helper.getView(R.id.ll_show);
        if (item.getPointsaddress().length() > 0) {
            tv_potionname.setText(item.getPointsaddress());
        } else {
            tv_potionname.setText("");
        }

        if (item.getQualitycontrolname().length() > 0) {
            tv_type.setText(item.getQualitycontrolname());
        } else {
            tv_type.setText("");
        }

        if (item.getIshow()) {
            ll_show.setVisibility(View.VISIBLE);
            iv_look.setImageResource(R.drawable.fold_down);
        } else {
            ll_show.setVisibility(View.GONE);
            iv_look.setImageResource(R.drawable.fold_right);
        }
        helper.addOnClickListener(R.id.tv_oncliks);


        /**
         * 显示部分信息
         */

        TextView tv_quename = helper.getView(R.id.tv_quename);
        LinearLayout ll_infos = helper.getView(R.id.ll_infos);
        if (item.getQualitycontrolname().equals("全程序空白")) {
            ll_infos.setVisibility(View.VISIBLE);
            tv_quename.setVisibility(View.GONE);
            helper.setText(R.id.tv_num, "样品码:" + item.getFactors().get(0).getSamplenumber());
        } else if (item.getQualitycontrolname().equals("质控样")) {
            ll_infos.setVisibility(View.VISIBLE);
            tv_quename.setVisibility(View.GONE);
            helper.setText(R.id.tv_num, "样品码:" + item.getFactors().get(0).getSamplenumber());
        } else {
            ll_infos.setVisibility(View.GONE);
        }


    }

}


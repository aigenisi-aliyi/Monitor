package com.monitor.changtian.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.accuse.AddAccuseActivity;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.AccuseShowBean;
import com.monitor.changtian.bean.PotinsBean;
import com.vise.log.ViseLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2018/9/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * へ　　　　／|
 * 　　/＼7　　∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　 │　　　　　ヽ　　 /　　〉
 * 　Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */

public class AccuseSubmitAdapter extends BaseQuickAdapter<AccuseShowBean, BaseViewHolder> {
    public Context mContext;

    public AccuseSubmitAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
        this.mContext = mContext;
    }

    boolean ishow = false;
    RecyclerView rv_list;
    LinearLayout ll_show;
    AccuseSubmitItemAdapter accuseSubmitItemAdapter;
    ImageView iv_look;

    @Override
    protected void convert(final BaseViewHolder helper, AccuseShowBean item) {


        TextView tv_potionname = helper.getView(R.id.tv_potionname);
        TextView tv_type = helper.getView(R.id.tv_type);
        iv_look = helper.getView(R.id.iv_look);
        rv_list = helper.getView(R.id.rv_list);
//        trues.addAll(item.getSubmitPointBeanList());
        accuseSubmitItemAdapter = new AccuseSubmitItemAdapter(R.layout.accusesub_item);
        rv_list.setLayoutManager(new GridLayoutManager(mContext, 2));
        rv_list.setAdapter(accuseSubmitItemAdapter);
        accuseSubmitItemAdapter.setNewData(item.getDetails());

        ll_show = helper.getView(R.id.ll_show);
        if (item.getPotionName().length() > 0) {
            tv_potionname.setText(item.getPotionName());
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
            helper.setText(R.id.tv_num, "样品码:" + item.getDetails().get(0).getSamplenumber());
        } else if (item.getQualitycontrolname().equals("质控样")) {
            ll_infos.setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_num, "样品码:" + item.getDetails().get(0).getSamplenumber());
            tv_quename.setText("标准物质:" + item.getDetails().get(0).getBzwz());
        } else {
            ll_infos.setVisibility(View.GONE);
        }

    }

}

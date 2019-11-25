package com.monitor.changtian.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.ProjectTestListBean;

/**
 * Created by Administrator on 2018/12/19.
 */


public class QualitycontrolAdapter extends BaseQuickAdapter<ProjectTestListBean, BaseViewHolder> {

    public QualitycontrolAdapter(int layoutResId) {
        super(layoutResId);
    }

    LinearLayout ll_caozuo, ll_shenhe;

    @Override
    protected void convert(BaseViewHolder helper, ProjectTestListBean item) {

        ll_caozuo = helper.getView(R.id.ll_caozuo);
        ll_shenhe = helper.getView(R.id.ll_shenhe);
        if (item.getISTrue().equals("-1")) {
            ll_caozuo.setVisibility(View.VISIBLE);
            ll_shenhe.setVisibility(View.GONE);
        } else {
            ll_caozuo.setVisibility(View.GONE);
            ll_shenhe.setVisibility(View.VISIBLE);
        }

        helper.setText(R.id.tv0, item.getTestno());
        helper.setText(R.id.tv1, "样品类型:" + item.getControlname());
        helper.setText(R.id.tv2, "实验样品码:" + item.getNewsamplecode());
        helper.setText(R.id.tv3, "原样样品码:" + item.getOldsamplecode());
        helper.setText(R.id.tv4, "平行样品码:" + item.getParallelsample());
        TextView tv4 = helper.getView(R.id.tv4);
        if (item.getControlname().equals("平行样")) {
            tv4.setVisibility(View.VISIBLE);
        } else {
            tv4.setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_1, "检测因子:" + item.getFactornames());

        helper.addOnClickListener(R.id.ll_look);
        helper.addOnClickListener(R.id.stv_true);
        helper.addOnClickListener(R.id.stv_true1);

    }
}

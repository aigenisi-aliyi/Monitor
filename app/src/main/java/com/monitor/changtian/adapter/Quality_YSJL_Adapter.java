package com.monitor.changtian.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.QueryTestRecordBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/19.
 */

public class Quality_YSJL_Adapter extends BaseQuickAdapter<QueryTestRecordBean.TdataBean.TestResultBean, BaseViewHolder> {

    Context context;
    RecyclerView recyclerView;
    Quality_YSJL_Item_Adapter quality_ysjl_item_adapter;
    LinearLayout ll_show;

    ImageView iv_look;

    public Quality_YSJL_Adapter(int layoutResId, Context context) {
        super(layoutResId);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryTestRecordBean.TdataBean.TestResultBean item) {
        TextView tv0 = helper.getView(R.id.tv0);
        recyclerView = helper.getView(R.id.rv_list_item);
        tv0.setText("样品编号:" + item.getSmid() + "\t\t\t\t\t" + "定容/标况体积:" + item.getVolume());
        ll_show = helper.getView(R.id.ll_show);
        iv_look = helper.getView(R.id.iv_look);

        /**
         * 隐藏折叠
         */
        if (item.getIshow()) {
            ll_show.setVisibility(View.VISIBLE);
            iv_look.setImageResource(R.drawable.fold_down);
        } else {
            ll_show.setVisibility(View.GONE);
            iv_look.setImageResource(R.drawable.fold_right);
        }
        helper.addOnClickListener(R.id.tv0);

        quality_ysjl_item_adapter = new Quality_YSJL_Item_Adapter(R.layout.quality_bzqx_item_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(quality_ysjl_item_adapter);

        List<QueryTestRecordBean.TdataBean.TestResultBean.FactorsBean> factorsBeans = new ArrayList<>();
        QueryTestRecordBean.TdataBean.TestResultBean.FactorsBean factorsBean = new QueryTestRecordBean.TdataBean.TestResultBean.FactorsBean();
        factorsBean.setFname("因子名称");
        QueryTestRecordBean.TdataBean.TestResultBean.FactorsBean.RdataBean rdataBean = new QueryTestRecordBean.TdataBean.TestResultBean.FactorsBean.RdataBean();
        rdataBean.setArea("峰面积");
        rdataBean.setThickness("浓度");
        factorsBean.setRdata(rdataBean);
        factorsBeans.add(factorsBean);
        factorsBeans.addAll(item.getFactors());
        quality_ysjl_item_adapter.setNewData(factorsBeans);

    }
}

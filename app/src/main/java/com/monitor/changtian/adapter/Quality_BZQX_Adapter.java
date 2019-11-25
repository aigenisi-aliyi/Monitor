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

public class Quality_BZQX_Adapter extends BaseQuickAdapter<QueryTestRecordBean.TdataBean.StdCurveBean, BaseViewHolder> {

    Context context;

    public Quality_BZQX_Adapter(int layoutResId, Context context) {
        super(layoutResId);
        this.context = context;
    }

    Quality_BZQX_Item_Adapter quality_bzqx_item_adapter;
    RecyclerView recyclerView;
    LinearLayout ll_show;
    ImageView iv_look;

    @Override
    protected void convert(BaseViewHolder helper, QueryTestRecordBean.TdataBean.StdCurveBean item) {

        TextView tv0 = helper.getView(R.id.tv0);
        recyclerView = helper.getView(R.id.rv_list_item);
        ll_show = helper.getView(R.id.ll_show);
        iv_look = helper.getView(R.id.iv_look);
        if (item.getFname().length() > 0) {
            tv0.setText(item.getFname() + "\t\t" + "a=" + item.getM() + "\t\t" + "b=" + item.getB() + "\t\t" + "r=" + item.getR());
        } else {
            tv0.setText("");
        }

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

        quality_bzqx_item_adapter = new Quality_BZQX_Item_Adapter(R.layout.quality_bzqx_item_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(quality_bzqx_item_adapter);

        List<QueryTestRecordBean.TdataBean.StdCurveBean.CdataBean> cdataBeanList = new ArrayList<>();
        QueryTestRecordBean.TdataBean.StdCurveBean.CdataBean cdataBean = new QueryTestRecordBean.TdataBean.StdCurveBean.CdataBean();
        cdataBean.setDnum("序号");
        cdataBean.setValues("含量ug");
        cdataBean.setAreas("峰面积");
        cdataBeanList.add(cdataBean);
        cdataBeanList.addAll(item.getCdata());
        quality_bzqx_item_adapter.setNewData(cdataBeanList);
    }
}

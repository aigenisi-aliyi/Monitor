package com.monitor.changtian.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.EditRecordActivity_;
import com.monitor.changtian.bean.SampleDetailsAndItemsData;

import java.util.ArrayList;

/**
 * Created by ken on 2018/5/9.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class HomeRecordAdapter extends BaseQuickAdapter<SampleDetailsAndItemsData, BaseViewHolder> {
    Context context;

    public HomeRecordAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SampleDetailsAndItemsData item) {

//        helper.setText(R.id.tv_title, item.getSampleName());
//        helper.setText(R.id.tv_num, item.getSampleNum());
//        helper.setText(R.id.tv_status, item.getSampleStatus());
//        helper.setText(R.id.tv_electrical, item.getElectrical());
//        helper.setText(R.id.tv_nums, item.getSampleCount());
//        helper.setText(R.id.tv_Oxygen, item.getOxygen());
//        helper.setText(R.id.tv_ph, item.getPH());
//        helper.setText(R.id.tv_flows, item.getFlows());
//        helper.setText(R.id.tv_watertmp, item.getWatertmp());

        RecyclerView recyclerView = helper.getView(R.id.rv_list);
        HomeRecordItemsAdapter adapter = new HomeRecordItemsAdapter(R.layout.homerecorditem_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        View viewhead = LayoutInflater.from(context).inflate(R.layout.records_head, (ViewGroup) recyclerView.getParent(), false);
        adapter.addHeaderView(viewhead);

        adapter.notifyDataSetChanged();
        TextView tv_num = viewhead.findViewById(R.id.tv_num);
        tv_num.setText(item.getSampleNum());
        TextView tv_edit = viewhead.findViewById(R.id.tv_edit);
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<SampleDetailsAndItemsData.SampleDetailItemsBean> sampleDetailItemsBeans = new ArrayList();
                sampleDetailItemsBeans.addAll(item.getSampleDetailItems());

                ArrayList<SampleDetailsAndItemsData.PhotosBean> photosBeans = new ArrayList();
                photosBeans.addAll(item.getPhotos());
                EditRecordActivity_.intent(context).SampleId(item.getSampleId()).photosBeans(photosBeans).detailid(item.getDetailid()).SampleNum(item.getSampleNum()).sampleDetailItemsBeans(sampleDetailItemsBeans).start();
            }
        });

        View viewhead1 = LayoutInflater.from(context).inflate(R.layout.records_foot, (ViewGroup) recyclerView.getParent(), false);
        adapter.addFooterView(viewhead1);
        adapter.addData(item.getSampleDetailItems());
    }
}

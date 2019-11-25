package com.monitor.changtian.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.vise.log.ViseLog;

/**
 * Created by ken on 2018/7/12.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class DeviceAdapter extends BaseQuickAdapter<TasksInfomationBean.EquipsBean, BaseViewHolder> {


    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public DeviceAdapter(Context context, int layoutResId) {
        super(layoutResId);
        this.context = context;
    }

    Context context;

    @Override
    protected void convert(BaseViewHolder helper, TasksInfomationBean.EquipsBean item) {

        Button stv_add_record = helper.getView(R.id.stv_add_record);
        Button stv_add_record1 = helper.getView(R.id.stv_add_record1);

        LinearLayout ll_caozuo = helper.getView(R.id.ll_caozuo);

        if(type.equals("48")||type.equals("52")||type.equals("53")){
            ll_caozuo.setVisibility(View.VISIBLE);
        }else{
            ll_caozuo.setVisibility(View.GONE);
        }

//        //判断按钮是否可以点击
        if (item.getBeforemeasurement() == null) {
            stv_add_record.setEnabled(true);
            stv_add_record.setBackgroundResource(R.drawable.dev_bg_c);
            stv_add_record1.setEnabled(false);
            stv_add_record1.setBackgroundResource(R.drawable.dev_bg_gray);
        } else {
            stv_add_record.setEnabled(false);
            stv_add_record.setBackgroundResource(R.drawable.dev_bg_gray);
            stv_add_record1.setEnabled(true);
            stv_add_record1.setBackgroundResource(R.drawable.dev_bg_c);
        }

        if (item.getAftermeasurement() != null) {

            stv_add_record1.setEnabled(false);
            stv_add_record1.setBackgroundResource(R.drawable.dev_bg_gray);

        } else {

        }


        if (item.getNames() != null && item.getEquipmentNumber() != null) {
            helper.setText(R.id.tv_name, item.getNames() + "(" + item.getEquipmentNumber() + ")");
        }

        helper.addOnClickListener(R.id.stv_add_record);
        helper.addOnClickListener(R.id.stv_add_record1);
        helper.addOnClickListener(R.id.ll_look);
    }
}
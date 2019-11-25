package com.monitor.taskallocation.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.vise.log.ViseLog;

/**
 * Created by ken on 2018/7/16.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskCorrelationinfoThreeAdapter extends BaseQuickAdapter<DetectionSchemeInfoBean.LeftcateogoryBean.PointsBean.FactorsBean, BaseViewHolder> {

    public Context mContext;

    public TaskCorrelationinfoThreeAdapter(Context mContext, int layoutResId) {
        super(layoutResId);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, DetectionSchemeInfoBean.LeftcateogoryBean.PointsBean.FactorsBean item) {

        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_content = helper.getView(R.id.tv_content);
        tv_title.setText("检测因子:" + item.getFactorname() + "\n" +
                "判定依据:" + item.getDecisiontablenames() + item.getDecisionlevel() + "\n" +
                "检测方法名称:" + item.getMethodname() + "\n" +
                "检测频次:" + item.getDaysNumber() + "天\t\t(每天" + item.getFrequency() + "次)\t");

        StringBuffer stringBuffer = new StringBuffer();

        if (item.getEquipments().size() > 0) {
            for (int i = 0; i < item.getEquipments().size(); i++) {
                stringBuffer.append(item.getEquipments().get(i).getNames() + ",");
            }

        } else {
            stringBuffer.append("");
        }


        StringBuffer stringBuffer_shiji = new StringBuffer();
        if (item.getConsumables().size() > 0) {
            for (int i = 0; i < item.getConsumables().size(); i++) {
                if(item.getConsumables().get(i).getNames().equals("保护剂")){
                    stringBuffer_shiji.append(item.getConsumables().get(i).getConsumablesnames() + ",");
                }else{
                    stringBuffer_shiji.append(item.getConsumables().get(i).getNames() + ",");
                }
            }
        } else {
            stringBuffer_shiji.append("");
        }
        ViseLog.d(stringBuffer.toString() + "\n" + stringBuffer_shiji.toString());

        String strDEV = "", strHAO = "";

        if (stringBuffer.toString().length() > 0) {
            strDEV = "设备:" + stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1)+"\n";
        } else {
            strDEV = "";
        }
        if (stringBuffer_shiji.toString().length() > 0) {
            strHAO = "耗材:" + stringBuffer_shiji.toString().substring(0, stringBuffer_shiji.toString().length() - 1);
        } else {
            strHAO = "";
        }

        tv_content.setText(strDEV+strHAO);
//        tv_content.setText(stringBuffer.toString() + "\n" + stringBuffer_shiji.toString());

        tv_content.setTextColor(color(R.color.color3));

    }

    private int color(int value) {
        return mContext.getResources().getColor(value);
    }

}


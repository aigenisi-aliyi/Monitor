package com.monitor.changtian.adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.FactorsExperimenterDataBean;
import com.monitor.changtian.R;
import com.vise.log.ViseLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2018/7/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ExperimentAdapter extends BaseQuickAdapter<FactorsExperimenterDataBean, BaseViewHolder> {

    public Context context;
    ArrayList<FactorsExperimenterDataBean> factorsExperimenterDataBeans;

    public ExperimentAdapter(ArrayList<FactorsExperimenterDataBean> factorsExperimenterDataBeans, int layoutResId, Context context) {
        super(layoutResId);
        this.context = context;
        this.factorsExperimenterDataBeans = factorsExperimenterDataBeans;
    }

    LinearLayout ll_selectors;

    TextView tv_name;


    @Override
    protected void convert(final BaseViewHolder helper, final FactorsExperimenterDataBean item) {

        if (factorsExperimenterDataBeans.size() == 0) {
            if (item.getIsshow()) {
                List<FactorsExperimenterDataBean.UsersBeanSave> saveList = new ArrayList<>();
                if(item.getUsers()!=null&&item.getUsers().size()>0){
                    FactorsExperimenterDataBean.UsersBeanSave save = new FactorsExperimenterDataBean.UsersBeanSave();
                    save.setId(item.getUsers().get(0).getId());
                    save.setUserName(item.getUsers().get(0).getUserName());
                    saveList.add(save);
                    ViseLog.d(saveList.size());
                    ExperimentAdapter.this.getData().get(helper.getLayoutPosition()).setUser_save(saveList);
                    helper.setText(R.id.tv_name, item.getUser_save().get(0).getUserName());
                }
            } else {
                helper.setText(R.id.tv_name, item.getUser_save().get(0).getUserName());
            }
        } else {
            helper.setText(R.id.tv_name, item.getUser_save().get(0).getUserName());
        }


        helper.setText(R.id.tv_ex_name, item.getFactorname());
        ll_selectors = helper.getView(R.id.ll_selectors);
        tv_name = helper.getView(R.id.tv_name);
        helper.addOnClickListener(R.id.tv_name);


    }

}

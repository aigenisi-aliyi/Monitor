package com.monitor.changtian.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.TaskListBean;
import com.monitor.changtian.bean.TaskPointsFrequencyBean;
import com.vise.log.ViseLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/17.
 */

public class TaskPointsAdapter extends BaseQuickAdapter<TaskListBean.PointsBean, BaseViewHolder> {
    TaskPointFrequencyAdapter taskPointFrequencyAdapter;
    RecyclerView rv_list;
    private Context mContext;
    private String taskid;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public TaskPointsAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
        this.mContext = mContext;
    }



    private String zq_status;

    public String getZq_status() {
        return zq_status;
    }

    public void setZq_status(String zq_status) {
        this.zq_status = zq_status;
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskListBean.PointsBean item) {

        LinearLayout ll_items = helper.getView(R.id.ll_items);
        if (item.getAddress().length() > 0) {
            helper.setText(R.id.tv_subject, item.getAddress());
        }
        if (getZq_status() == null) {
//            if (item.getIshadden().equals("0")) {
//                ll_items.setVisibility(View.VISIBLE);
//            } else {
//                ll_items.setVisibility(View.GONE);
//            }
        }

        rv_list = helper.getView(R.id.rv_list);
        helper.addOnClickListener(R.id.ll_items);
        ImageView iv_look = helper.getView(R.id.iv_look);
        taskPointFrequencyAdapter = new TaskPointFrequencyAdapter(R.layout.task_points_item_item, mContext);
        taskPointFrequencyAdapter.setZq_status(getZq_status());
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.setAdapter(taskPointFrequencyAdapter);

//      传递频次的数据
        if (item.getFrequencys().size() > 0 && item.getFrequencys() != null) {
            initData(helper.getAdapterPosition(), item.getFrequencys(), item);
            ViseLog.d("ss" + helper.getLayoutPosition());
        } else {
            initData(helper.getAdapterPosition(), item.getFrequencys(), item);
//            ViseLog.d("ss"+helper.getLayoutPosition());
        }

        if (item.getShow()) {
            rv_list.setVisibility(View.VISIBLE);
            iv_look.setImageResource(R.drawable.fold_down);
        } else {
            rv_list.setVisibility(View.GONE);
            iv_look.setImageResource(R.drawable.fold_right);
        }

    }

    public void initData(int potion, List<TaskListBean.PointsBean.FrequencysBeanBean> frequencysBeanBeanList, TaskListBean.PointsBean item) {


        List<TaskPointsFrequencyBean> taskPointsFrequencyBeans = new ArrayList<>();
        for (int i = 0; i < frequencysBeanBeanList.size(); i++) {
            TaskPointsFrequencyBean taskPointsFrequencyBean = new TaskPointsFrequencyBean();
            taskPointsFrequencyBean.setPotins_index(potion);
            taskPointsFrequencyBean.setName("第" + (frequencysBeanBeanList.get(i).getFrequencyname()) + "频次");
            taskPointsFrequencyBean.setIsEnd(frequencysBeanBeanList.get(i).getIsEnd());
            taskPointsFrequencyBean.setPotinsName(item.getPointsid());
            taskPointsFrequencyBean.setOptions(frequencysBeanBeanList.get(i).getFrequencyname() + "");
            taskPointsFrequencyBean.setTaskid(getTaskid());
            taskPointsFrequencyBean.setIshadden(item.getIshadden());
            taskPointsFrequencyBeans.add(taskPointsFrequencyBean);

        }
        taskPointFrequencyAdapter.setNewData(taskPointsFrequencyBeans);
    }
}

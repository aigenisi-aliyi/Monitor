package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.ProjectinfoBean;

/**
 * Created by ken on 2018/4/25.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ProjectinfoAdapter extends BaseQuickAdapter<ProjectinfoBean, BaseViewHolder> {
    public ProjectinfoAdapter() {
        super(R.layout.projectinfo_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectinfoBean item) {

        helper.setText(R.id.tv_title, item.getProjectinfo_name());

        helper.addOnClickListener(R.id.tv_title);
    }
}

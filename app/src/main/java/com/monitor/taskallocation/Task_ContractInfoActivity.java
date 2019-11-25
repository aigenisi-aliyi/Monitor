package com.monitor.taskallocation;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.TasksListInfoBean;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.ProjectcontractBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.Task_SapmleBean;
import com.monitor.changtian.bean.TasksInfoDetailBean;
import com.monitor.changtian.presenter.TaskContractInfoPresenter;
import com.monitor.changtian.view.TaskContractInfoView;
import com.monitor.taskallocation.adapter.TaskContractInfoAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_task__contract_info)
public class Task_ContractInfoActivity extends BaseActvity implements TaskContractInfoView {

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    TaskContractInfoAdapter taskContractInfoAdapter;
    ImageView iv_addtask;
    @Extra
    ProjectcontractBean projectcontractBean;
    TaskContractInfoPresenter taskContractInfoPresenter;
    TextView tv_code, tv_contractname, tv_projectnames, tv_taskinfo, tv_signDate, tv_introduction;
    String pinci = "";

    @AfterViews
    void init() {
        initImageBackText("任务详情");
        taskContractInfoPresenter = new TaskContractInfoPresenter(this, this);
        taskContractInfoAdapter = new TaskContractInfoAdapter(R.layout.ts_contractinfo_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(taskContractInfoAdapter);
        initData();
        View view1 = Task_ContractInfoActivity.this.getLayoutInflater().inflate(R.layout.task_contractinfo_item, (ViewGroup) rv_list.getParent(), false);
        taskContractInfoAdapter.addHeaderView(view1);
        initView(view1);
        iv_addtask = view1.findViewById(R.id.iv_addtask);
        taskContractInfoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TaskinfoActivity_.intent(Task_ContractInfoActivity.this).taskid(taskContractInfoAdapter.getData().get(position).getId()).start();
            }
        });
        /**
         * 跳转到添加任务界面
         */
        iv_addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task_AddActivity_.intent(Task_ContractInfoActivity.this).frequency(pinci).conid(projectcontractBean.getId()).start();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    public void initView(View view1) {

        tv_taskinfo = view1.findViewById(R.id.tv_taskinfo);
        tv_code = view1.findViewById(R.id.tv_code);
        tv_contractname = view1.findViewById(R.id.tv_contractname);
        tv_projectnames = view1.findViewById(R.id.tv_projectnames);
        tv_signDate = view1.findViewById(R.id.tv_signDate);
        tv_introduction = view1.findViewById(R.id.tv_introduction);
        tv_code.setText(projectcontractBean.getContractcode() + "");
        tv_taskinfo.setText(projectcontractBean.getSchemename());
        tv_contractname.setText(projectcontractBean.getContractname() + "");
        tv_projectnames.setText(projectcontractBean.getProjectnames() + "");
        tv_signDate.setText("签订日期:" + projectcontractBean.getSignDate() + "");
        if (projectcontractBean.getIntroduction() != null) {
            tv_introduction.setText(projectcontractBean.getIntroduction());
        }
        tv_taskinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Task_ContractInfoActivity.this, TaskCorrelationinfoActivity.class).putExtra("schid", projectcontractBean.getSchemeid()));
            }
        });
    }

    public void initData() {
        String data = "{id:\"\",pagenumber:\"\",numbers:\"\",conid:\"" + projectcontractBean.getId() + "\"} ";
        taskContractInfoPresenter.GetTasksInfo(data);
    }

    @Override
    public void OnTasksListInfo(List<TasksListInfoBean> tasksListInfoBeans) {

        taskContractInfoAdapter.setNewData(tasksListInfoBeans);
    }

    @Override
    public void OnTasksInfoDetail(List<TasksInfoDetailBean> tasksInfoDetailBeans) {

    }

    @Override
    public void OnGetDetectionSchemeInfo(List<DetectionSchemeInfoBean> list) {

    }

    @Override
    public void OnAddDetectionSchemeAudit(ResultBean resultBean) {

    }

    @Override
    public void onData(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnGetsampleInformation(List<Task_SapmleBean> dicDataBeans) {

    }

    @Override
    public void OnError(String s) {

    }
}

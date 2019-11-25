package com.monitor.taskallocation;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.TasksListInfoBean;
import com.monitor.changtian.adapter.Task_carAdapter;
import com.monitor.changtian.adapter.Task_derAdapter;
import com.monitor.changtian.adapter.Task_shijAdapter;
import com.monitor.changtian.adapter.Taskinfo_testAdapter;
import com.monitor.changtian.adapter.taskinfo_caiAdapter;
import com.monitor.changtian.adapter.taskinfo_fuAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.Task_SapmleBean;
import com.monitor.changtian.bean.TasksInfoDetailBean;
import com.monitor.changtian.presenter.TaskContractInfoPresenter;
import com.monitor.changtian.view.TaskContractInfoView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_taskinfo)
public class TaskinfoActivity extends BaseActvity implements TaskContractInfoView, BaseActvity.Rightlistener {

    @Extra
    String taskid;
    @Extra
    String taskStutas;
    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    String loginId = MyApplication.getInstance().getUser();

    TaskContractInfoPresenter taskContractInfoPresenter;
    Taskinfo_testAdapter taskinfo_testAdapter;
    taskinfo_caiAdapter taskinfo_caiAdapter;
    taskinfo_fuAdapter taskinfo_fuAdapter;
    Task_carAdapter task_carAdapter;
    Task_derAdapter task_derAdapter;
    Task_shijAdapter task_shijAdapter;
    TextView tv_address, et_mix, et_max, tv_reamk;

    @AfterViews
    void init() {
        if (taskStutas.equals("5")) {
            initImageBackText("任务详情");
        } else {
            initRightOnclikText("任务详情", "终止任务", this);
        }

        taskContractInfoPresenter = new TaskContractInfoPresenter(this, this);
        String data = "{taskid:\"" + taskid + "\"} ";
        taskContractInfoPresenter.GetTasksInfoDetail(data);
        taskinfo_testAdapter = new Taskinfo_testAdapter(R.layout.factor_item);
        rv_list.setLayoutManager(new LinearLayoutManager(TaskinfoActivity.this));
        rv_list.setAdapter(taskinfo_testAdapter);
        /**
         * 添加时间头布局
         */
        View view1 = TaskinfoActivity.this.getLayoutInflater().inflate(R.layout.task_time_item, (ViewGroup) rv_list.getParent(), false);
        taskinfo_testAdapter.addHeaderView(view1);
        tv_address = view1.findViewById(R.id.tv_address);
        /**
         * 实验人员
         */
        RecyclerView rv_list = view1.findViewById(R.id.rv_list);
        taskinfo_testAdapter = new Taskinfo_testAdapter(R.layout.factor_item);
        rv_list.setLayoutManager(new LinearLayoutManager(TaskinfoActivity.this));
        rv_list.setAdapter(taskinfo_testAdapter);
        et_mix = view1.findViewById(R.id.et_mix);
        tv_reamk = view1.findViewById(R.id.tv_reamk);
        et_max = view1.findViewById(R.id.et_max);

        /**
         * 采样人员
         */
        RecyclerView rv_list_c = view1.findViewById(R.id.rv_list_c);
        taskinfo_caiAdapter = new taskinfo_caiAdapter(R.layout.cai_item);
        rv_list_c.setLayoutManager(new GridLayoutManager(TaskinfoActivity.this, 3));
        rv_list_c.setAdapter(taskinfo_caiAdapter);

        /**
         * 辅助人员
         */
        RecyclerView rv_list_f = view1.findViewById(R.id.rv_list_f);
        taskinfo_fuAdapter = new taskinfo_fuAdapter(R.layout.cai_item);
        rv_list_f.setLayoutManager(new GridLayoutManager(TaskinfoActivity.this, 3));
        rv_list_f.setAdapter(taskinfo_fuAdapter);

        /**
         * 车辆信息
         */
        RecyclerView rv_list_car = view1.findViewById(R.id.rv_list_car);
        task_carAdapter = new Task_carAdapter(R.layout.car_item);
        rv_list_car.setLayoutManager(new GridLayoutManager(TaskinfoActivity.this, 2));
        rv_list_car.setAdapter(task_carAdapter);

        /**
         * 设备信息
         */
        RecyclerView rv_list_der = view1.findViewById(R.id.rv_list_der);
        task_derAdapter = new Task_derAdapter(R.layout.car_item);
        rv_list_der.setLayoutManager(new GridLayoutManager(TaskinfoActivity.this, 1));
        rv_list_der.setAdapter(task_derAdapter);
        /**
         * 耗材信息
         */

        RecyclerView rv_list_shiji = view1.findViewById(R.id.rv_list_shiji);
        task_shijAdapter = new Task_shijAdapter(R.layout.car_item);
        rv_list_shiji.setLayoutManager(new GridLayoutManager(TaskinfoActivity.this, 1));
        rv_list_shiji.setAdapter(task_shijAdapter);

    }


    @Override
    public void rightlistener() {
        AgainDialog("确认终止当前任务吗？");
    }

    public void AgainTrue() {

        final EditText inputET = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请输入终止原因").setView(inputET)
                .setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                String input = inputET.getText().toString();
                if (input.length() > 0) {
                    ShowDialogtitle("请稍等...", TaskinfoActivity.this);
                    String data2 = "{taskid:\"" + taskid + "\",loginId:\"" + loginId + "\",reason:\"" + input + "\"}";
                    taskContractInfoPresenter.AddTaskEnd(data2);
                } else {
                    msg("终止原因不能为空!");
                }

            }
        });
        builder.show();

    }

    @Override
    public void OnTasksListInfo(List<TasksListInfoBean> tasksListInfoBeans) {

    }

    @Override
    public void OnTasksInfoDetail(List<TasksInfoDetailBean> tasksInfoDetailBeans) {
        if (tasksInfoDetailBeans != null) {
            taskinfo_testAdapter.setNewData(tasksInfoDetailBeans.get(0).getFactors());
            tv_address.setText(tasksInfoDetailBeans.get(0).getAddress());
            et_mix.setText(tasksInfoDetailBeans.get(0).getStarttime());
            et_max.setText(tasksInfoDetailBeans.get(0).getEndtime());
            tv_reamk.setText(tasksInfoDetailBeans.get(0).getRemark());
            taskinfo_caiAdapter.setNewData(tasksInfoDetailBeans.get(0).getSamplinguser());
            taskinfo_fuAdapter.setNewData(tasksInfoDetailBeans.get(0).getAssistuser());
            task_carAdapter.setNewData(tasksInfoDetailBeans.get(0).getCars());
            task_derAdapter.setNewData(tasksInfoDetailBeans.get(0).getEquips());
            task_shijAdapter.setNewData(tasksInfoDetailBeans.get(0).getConsumables());
        }

    }

    @Override
    public void OnGetDetectionSchemeInfo(List<DetectionSchemeInfoBean> list) {

    }

    @Override
    public void OnAddDetectionSchemeAudit(ResultBean resultBean) {
        DissDialog();
        if (resultBean.getResult().equals("1")) {
            msg("成功");
            finish();
        } else {
            msg(resultBean.getErrormessage());
        }
    }

    @Override
    public void onData(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnGetsampleInformation(List<Task_SapmleBean> dicDataBeans) {

    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }


}

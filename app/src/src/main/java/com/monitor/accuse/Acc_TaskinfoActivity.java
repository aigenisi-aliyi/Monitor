package com.monitor.accuse;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.TasksListInfoBean;
import com.monitor.changtian.activity.task.SampleListInfoActivity_;
import com.monitor.changtian.adapter.SampleinfolistAdapter;
import com.monitor.changtian.adapter.TaskPointsAdapter;
import com.monitor.changtian.adapter.Task_carAdapter;
import com.monitor.changtian.adapter.Task_derAdapter;
import com.monitor.changtian.adapter.Task_shijAdapter;
import com.monitor.changtian.adapter.Taskinfo_testAdapter;
import com.monitor.changtian.adapter.taskinfo_caiAdapter;
import com.monitor.changtian.adapter.taskinfo_fuAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.FieldsamplingDetailBean;
import com.monitor.changtian.bean.FieldsamplingDetailListBean;
import com.monitor.changtian.bean.FieldsamplingInfo;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TaskListBean;
import com.monitor.changtian.bean.Task_SapmleBean;
import com.monitor.changtian.bean.TasksInfoDetailBean;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.monitor.changtian.presenter.Acc_TaskPresenter;
import com.monitor.changtian.presenter.TaskContractInfoPresenter;
import com.monitor.changtian.presenter.TaskPresenter;
import com.monitor.changtian.presenter.TaskinfoPresenter;
import com.monitor.changtian.view.Acc_TaskView;
import com.monitor.changtian.view.TaskContractInfoView;
import com.monitor.changtian.view.TaskinfoView;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_acc__taskinfo)
public class Acc_TaskinfoActivity extends BaseActvity implements TaskinfoView, Acc_TaskView, TaskContractInfoView {

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
    SampleinfolistAdapter sampleinfolistAdapter;
    LinearLayout ll_isShow, ll_looks;
    ImageView iv_look;
    TaskinfoPresenter taskinfoPresenter;
    TaskPointsAdapter taskPointsAdapter;

    Acc_TaskPresenter acc_taskPresenter;

    @AfterViews
    void init() {
        initImageBackText("任务详情");
        taskinfoPresenter = new TaskinfoPresenter(this, this);
        taskContractInfoPresenter = new TaskContractInfoPresenter(this, this);
        acc_taskPresenter = new Acc_TaskPresenter(this);

        String data = "{taskid:\"" + taskid + "\"} ";
        taskContractInfoPresenter.GetTasksInfoDetail(data);
        String data1 = "{fsid:\"\",IsEnd:\"\",pointsid:\"\",taskid:\"" + taskid + "\",sdate:\"\",edate:\"\"}";
        taskinfoPresenter.GetfieldsamplingInfo(data1);

        String datacc_task = "{id:\"" + taskid + "\",pagenumber:\"\",numbers:\"\",conid:\"\",sampingloginId:\"\",taskstatus:\"1,2,3,5,7\"}";
        acc_taskPresenter.GetTaskList(datacc_task);

        sampleinfolistAdapter = new SampleinfolistAdapter(R.layout.acc_samplelist_item);
        taskinfo_testAdapter = new Taskinfo_testAdapter(R.layout.factor_item);
        taskPointsAdapter = new TaskPointsAdapter(R.layout.task_points_item, this);
        rv_list.setLayoutManager(new LinearLayoutManager(Acc_TaskinfoActivity.this));
        rv_list.setAdapter(taskinfo_testAdapter);
        /**
         * 添加头布局
         */
        View view1 = Acc_TaskinfoActivity.this.getLayoutInflater().inflate(R.layout.acc_task_head, (ViewGroup) rv_list.getParent(), false);
        taskinfo_testAdapter.addHeaderView(view1);
        tv_address = view1.findViewById(R.id.tv_address);
        /**
         * 实验人员
         */
        RecyclerView rv_list = view1.findViewById(R.id.rv_list);
        taskinfo_testAdapter = new Taskinfo_testAdapter(R.layout.factor_item);
        rv_list.setLayoutManager(new LinearLayoutManager(Acc_TaskinfoActivity.this));
        rv_list.setAdapter(taskinfo_testAdapter);
        et_mix = view1.findViewById(R.id.et_mix);
        tv_reamk = view1.findViewById(R.id.tv_reamk);
        et_max = view1.findViewById(R.id.et_max);

        /**
         * 采样人员
         */
        RecyclerView rv_list_c = view1.findViewById(R.id.rv_list_c);
        taskinfo_caiAdapter = new taskinfo_caiAdapter(R.layout.cai_item);
        rv_list_c.setLayoutManager(new GridLayoutManager(Acc_TaskinfoActivity.this, 3));
        rv_list_c.setAdapter(taskinfo_caiAdapter);

        /**
         * 辅助人员
         */
        RecyclerView rv_list_f = view1.findViewById(R.id.rv_list_f);
        taskinfo_fuAdapter = new taskinfo_fuAdapter(R.layout.cai_item);
        rv_list_f.setLayoutManager(new GridLayoutManager(Acc_TaskinfoActivity.this, 3));
        rv_list_f.setAdapter(taskinfo_fuAdapter);

        /**
         * 车辆信息
         */
        RecyclerView rv_list_car = view1.findViewById(R.id.rv_list_car);
        task_carAdapter = new Task_carAdapter(R.layout.car_item);
        rv_list_car.setLayoutManager(new GridLayoutManager(Acc_TaskinfoActivity.this, 2));
        rv_list_car.setAdapter(task_carAdapter);

        /**
         * 设备信息
         */
        RecyclerView rv_list_der = view1.findViewById(R.id.rv_list_der);
        task_derAdapter = new Task_derAdapter(R.layout.car_item);
        rv_list_der.setLayoutManager(new GridLayoutManager(Acc_TaskinfoActivity.this, 1));
        rv_list_der.setAdapter(task_derAdapter);
        /**
         * 耗材信息
         */

        RecyclerView rv_list_shiji = view1.findViewById(R.id.rv_list_shiji);
        task_shijAdapter = new Task_shijAdapter(R.layout.car_item);
        rv_list_shiji.setLayoutManager(new GridLayoutManager(Acc_TaskinfoActivity.this, 1));
        rv_list_shiji.setAdapter(task_shijAdapter);

        initShowVi(view1);
    }

    Boolean isvi = true;
    RecyclerView rv_list_sample;

    public void initShowVi(View view) {
        ll_isShow = view.findViewById(R.id.ll_isShow);
        ll_looks = view.findViewById(R.id.ll_looks);
        iv_look = view.findViewById(R.id.iv_look);
        ll_isShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isvi) {
                    isvi = true;

                    iv_look.setImageResource(R.drawable.fold_down);
                    ll_looks.setVisibility(View.VISIBLE);

                } else {
                    isvi = false;
                    iv_look.setImageResource(R.drawable.fold_right);
                    ll_looks.setVisibility(View.GONE);
                }

            }
        });

        /**
         * 展示采样信息
         */
        rv_list_sample = view.findViewById(R.id.rv_list_sample);
        rv_list_sample.setLayoutManager(new LinearLayoutManager(Acc_TaskinfoActivity.this));
        rv_list_sample.setAdapter(taskPointsAdapter);
        taskPointsAdapter.setTaskid(taskid);
        taskPointsAdapter.setZq_status("zq");
        /**
         * 跳转对应点位列表
         */
        taskPointsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


                if (taskPointsAdapter.getData().get(position).getFrequency() != null) {

                    if (taskPointsAdapter.getData().get(position).getShow()) {
                        taskPointsAdapter.getData().get(position).setShow(false);
//                    accuseSubmitAdapter.LL_GONE();
                        taskPointsAdapter.notifyDataSetChanged();

                    } else {
                        taskPointsAdapter.getData().get(position).setShow(true);
//                    taskPointsAdapter.LL_VISIBLE();
                        taskPointsAdapter.notifyDataSetChanged();
                    }
                } else {
                    msg("当前方案暂无频次");
                }


            }
        });


    }


    @Override
    public void OnTasksListInfo(List<TasksListInfoBean> tasksListInfoBeans) {

    }

    @Override
    public void OnTasksInfoDetail(List<TasksInfoDetailBean> tasksInfoDetailBeans) {
        if (tasksInfoDetailBeans != null) {
//            taskinfo_testAdapter.setNewData(tasksInfoDetailBeans.get(0).getFactors());

//            taskPointsAdapter.setNewData(tasksInfoDetailBeans.get(0).get);
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

    }

    @Override
    public void onData(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnGetsampleInformation(List<Task_SapmleBean> dicDataBeans) {

    }

    @Override
    public void OnGetTasksInfomation(List<TasksInfomationBean> tasksInfomationBeans) {

    }

    @Override
    public void OnPack(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnUnit(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnStyle(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void onMessage(ResultBean message) {

    }

    @Override
    public void OnFieldsamplingInfo(List<FieldsamplingInfo> fieldsamplingInfos) {
        sampleinfolistAdapter.setNewData(fieldsamplingInfos);
    }

    @Override
    public void OnEndFieldsampling(ResultBean message) {

    }

    @Override
    public void OnGetfieldsamplingDetailList(List<FieldsamplingDetailListBean> fieldsamplingDetailListBeans) {

    }

    @Override
    public void OnGetfieldsamplingDetail(List<FieldsamplingDetailBean> fieldsamplingDetailBeans) {

    }

    @Override
    public void OnAcc_TaskList(List<TaskListBean> taskListBeans) {

        if (taskListBeans != null) {
            taskPointsAdapter.setNewData(taskListBeans.get(0).getPoints());
        }
    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }

    @Override
    public void OnSoilhumidity(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnSoiltexture(List<DicDataBean> dicDataBeans) {

    }


}

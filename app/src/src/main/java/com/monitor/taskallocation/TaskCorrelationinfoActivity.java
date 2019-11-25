package com.monitor.taskallocation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.SupportMapFragment;

import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.TasksListInfoBean;
import com.monitor.changtian.adapter.GridImageAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.CloseEvent;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EventBus.UpdateEvent;
import com.monitor.changtian.bean.ProjectcontractBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TaskInfoAllBean;
import com.monitor.changtian.bean.Task_SapmleBean;
import com.monitor.changtian.bean.TasksInfoDetailBean;
import com.monitor.changtian.presenter.FinanceMainsPresenter;
import com.monitor.changtian.presenter.TaskContractInfoPresenter;
import com.monitor.changtian.presenter.TaskInfoAllPresenter;
import com.monitor.changtian.view.FinanceMainView;
import com.monitor.changtian.view.TaskContractInfoView;
import com.monitor.changtian.view.TaskInfoAllView;
import com.monitor.changtian.widght.FullyGridLayoutManager;
import com.monitor.taskallocation.adapter.TaskContractInfoAdapter;
import com.monitor.taskallocation.adapter.TaskCorrelationinfoOneAdapter;
import com.monitor.taskallocation.adapter.TaskCycleListAdapter;
import com.monitor.taskallocation.adapter.TaskaddSampleAdapter;
import com.monitor.taskallocation.task_list.TaskCycleListActivity_;
import com.vise.log.ViseLog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.Contractstatus_DAIFUWEI_YIWANJIE;
import static com.monitor.changtian.constant.PublicConstant.Latitude_name;
import static com.monitor.changtian.constant.PublicConstant.PHOTOS_URL;
import static com.monitor.changtian.constant.PublicConstant.PageNum;
import static com.monitor.changtian.constant.PublicConstant.latitude_value;
import static com.monitor.changtian.constant.PublicConstant.longitude_value;

public class TaskCorrelationinfoActivity extends BaseActvity implements TaskInfoAllView, FinanceMainView, TaskContractInfoView {


    RecyclerView rv_list;
    TaskContractInfoPresenter taskContractInfoPresenter;
    TaskCycleListAdapter taskContractInfoAdapter;

    TaskCorrelationinfoOneAdapter taskCorrelationinfoOneAdapter;
    private BaiduMap mBaiduMap;
    LinearLayout ll_map;
    TextView task_info_name, tv_task_phone, tv_schnames, tv_task_person1, tv_introduction, tv_task_time, tv_task_samplemodename, tv_task_isretentionname, tv_task_typesname;
    TaskaddSampleAdapter taskaddSampleAdapter;
    FinanceMainsPresenter financeMainsPresenter;
    String loginId = MyApplication.getInstance().getUser();

    TaskInfoAllPresenter taskInfoAllPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_correlationinfo);

        initImageBackText("任务相关详情");
        rv_list = findViewById(R.id.rv_list);
        taskInfoAllPresenter = new TaskInfoAllPresenter(this, this);
        taskContractInfoPresenter = new TaskContractInfoPresenter(this, this);
        financeMainsPresenter = new FinanceMainsPresenter(this, this);

        taskaddSampleAdapter = new TaskaddSampleAdapter(R.layout.ts_contractinfo_item);
        taskContractInfoAdapter = new TaskCycleListAdapter(R.layout.ts_cycle_contractinfo_item);
        taskCorrelationinfoOneAdapter = new TaskCorrelationinfoOneAdapter(this, R.layout.taskcorrelationinfo_one);
        View view1 = TaskCorrelationinfoActivity.this.getLayoutInflater().inflate(R.layout.taskcorrelationinfo_head, (ViewGroup) rv_list.getParent(), false);
        taskCorrelationinfoOneAdapter.addHeaderView(view1);
        addFoot();
        rv_list.setLayoutManager(new LinearLayoutManager(TaskCorrelationinfoActivity.this));
        rv_list.setAdapter(taskCorrelationinfoOneAdapter);
        initViews(view1);
        initMap(savedInstanceState);

        //加载图片
        if (getIntent().getStringArrayListExtra("photos") != null) {

            ArrayList<String> photoslist = new ArrayList<>();
            photoslist = getIntent().getStringArrayListExtra("photos");
            if (photoslist.size() > 0) {
                ll_photos.setVisibility(View.VISIBLE);
                selectList_image = new ArrayList<>();
                selectList_video = new ArrayList<>();
                for (int i = 0; i < photoslist.size(); i++) {
                    LocalMedia s = new LocalMedia();
                    s.setPath(PHOTOS_URL + photoslist.get(i));
                    if (photoslist.get(i).indexOf(".mp4") == -1) {
                        //不包含是图片
                        selectList_image.add(s);
                    } else {
                        //是视频
                        selectList_video.add(s);
                    }
                }
                initImage();
            }
        }
    }

    public void addFoot() {

        if (getIntent().getStringExtra("status") != null) {

            switch (getIntent().getStringExtra("status")) {
                case "0":
                    if (getIntent().getStringExtra("Samplemodename") != null) {
                        if (getIntent().getStringExtra("Samplemodename").equals("现场采样")) {
                            View view_foot0 = TaskCorrelationinfoActivity.this.getLayoutInflater().inflate(R.layout.task_contractinfo_item, (ViewGroup) rv_list.getParent(), false);
                            taskCorrelationinfoOneAdapter.addFooterView(view_foot0);
                            initView_foot(view_foot0);
                        } else {
                            View view_foot0 = TaskCorrelationinfoActivity.this.getLayoutInflater().inflate(R.layout.task_contraction_addsapmle, (ViewGroup) rv_list.getParent(), false);
                            taskCorrelationinfoOneAdapter.addFooterView(view_foot0);
                            initView_AddFoot(view_foot0);
                        }
                    } else {
                        //推送
                        String data = "{id:\"\",projectid:\"\",schemeid:\"" + getIntent().getStringExtra("schid")
                                + "\",querystring:\"\",contractstatus:\"" + Contractstatus_DAIFUWEI_YIWANJIE + "\",sdate:\"\",edate:\"\",smoney:\"\",emoney:\"\",pagenumber:\"" + page + "\",numbers:\"" + PageNum + "\"} ";
                        financeMainsPresenter.Getprojectcontract(data);
                    }
                    break;
                case "1":
                    View view_foot1 = TaskCorrelationinfoActivity.this.getLayoutInflater().inflate(R.layout.task_contractinfo_item, (ViewGroup) rv_list.getParent(), false);
                    taskCorrelationinfoOneAdapter.addFooterView(view_foot1);
                    initView_foot(view_foot1);
                    break;
                case "2":

                    if (getIntent().getStringExtra("coid") != null) {
                        String data = "{id:\"" + getIntent().getStringExtra("coid") + "\",conid:\"\",sampingloginId:\"\",isstopandaudit:\"1\",taskstatus:\"\"}";
                        taskInfoAllPresenter.GetTasksInfoAudit(data);
                    }
                    View view_foot2 = TaskCorrelationinfoActivity.this.getLayoutInflater().inflate(R.layout.task_contractinfo_audit_item, (ViewGroup) rv_list.getParent(), false);
                    taskCorrelationinfoOneAdapter.addFooterView(view_foot2);
                    initView_foot_Audit(view_foot2);
                    break;
            }
        }
    }

    ImageView iv_addtask;
    /**
     * 审核任务
     */
    EditText et_remark;
    SuperTextView stv_true, stv_false;
    TextView tv_fj;
    LinearLayout ll_caozuo, ll_photos;
    RecyclerView recycler_image, recycler_video;
    GridImageAdapter adapter_image, adapter_video;


    public void initView_foot_Audit(View view) {

        recycler_image = view.findViewById(R.id.recycler_image);
        recycler_video = view.findViewById(R.id.recycler_video);
        ll_photos = view.findViewById(R.id.ll_photos);
        ll_caozuo = view.findViewById(R.id.ll_caozuo);
        tv_fj = view.findViewById(R.id.tv_fj);
        et_remark = view.findViewById(R.id.et_remark);
        stv_true = view.findViewById(R.id.stv_true);
        stv_false = view.findViewById(R.id.stv_false);


        if (getIntent().getStringExtra("isstoppass") != null) {
            //需要审核
            if (getIntent().getStringExtra("isstoppass").equals("1")) {
                ll_caozuo.setVisibility(View.VISIBLE);
            }
            //不需要审核
            else {
                ll_caozuo.setVisibility(View.GONE);
            }
        }


        if (getIntent().getStringExtra("yijian") != null) {
            tv_fj.setText(getIntent().getStringExtra("yijian"));
        }

        stv_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogs("1", et_remark.getText().toString().trim());
            }
        });
        stv_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_remark.getText().toString().trim().length() == 0) {
                    msg("审核意见不能为空!");
                    return;
                }
                Dialogs("0", et_remark.getText().toString().trim());
            }
        });

    }


    /**
     * 提交
     *
     * @param type
     * @param manager
     */
    public void Dialogs(final String type, final String manager) {

        /**
         * 二次弹框确认
         */
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认执行本次操作吗?");
        builder.setMessage("      请仔细核对相关信息!");
        //    设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (type.equals("0")) {
                    ShowDialogtitle("请稍等...", TaskCorrelationinfoActivity.this);
                    String data = "{taskid:\"" + getIntent().getStringExtra("taskid") + "\",loginId:\"" + loginId + "\",AuditOpinion:\"" + manager + "\",Ispass:\"0\"}";
                    taskContractInfoPresenter.AddManagerTaskAudit(data);
                } else if (type.equals("1")) {
                    ShowDialogtitle("请稍等...", TaskCorrelationinfoActivity.this);
                    String data = "{taskid:\"" + getIntent().getStringExtra("taskid") + "\",loginId:\"" + loginId + "\",AuditOpinion:\"" + manager + "\",Ispass:\"1\"}";
                    taskContractInfoPresenter.AddManagerTaskAudit(data);
                }


            }
        });
        builder.show();

    }

    RecyclerView rv_list_sample;

    int addsample = 0;

    /**
     * 添加样品信息
     */
    public void initView_AddFoot(View view) {
        iv_addtask = view.findViewById(R.id.iv_addtask);
        rv_list_sample = view.findViewById(R.id.rv_list_sample);
        rv_list_sample.setLayoutManager(new LinearLayoutManager(TaskCorrelationinfoActivity.this));
        rv_list_sample.setAdapter(taskaddSampleAdapter);
        taskaddSampleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TaskAddSampleInfoActivity_.intent(TaskCorrelationinfoActivity.this).task_sapmleBean(taskaddSampleAdapter.getData().get(position)).start();
            }
        });
//        ts_contractinfo_item
        /**
         * 跳转到样品添加界面
         */
        iv_addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mTrue.size() > 0) {
                    ArrayList<DetectionSchemeInfoBean.LeftcateogoryBean> leftcateogoryBeans = new ArrayList<>();
                    leftcateogoryBeans.addAll(mTrue);
                    Task_AddSapmleActivity_.intent(TaskCorrelationinfoActivity.this).schemeid(getIntent().getStringExtra("schid")).leftcateogoryBean(leftcateogoryBeans).start();
                } else {
                    msg("数据不正常");
                }

            }
        });
    }

    /**
     * 分配任务
     *
     * @param view
     */
    public void initView_foot(View view) {

        RecyclerView rv_lists = view.findViewById(R.id.rv_list);
        rv_lists.setLayoutManager(new LinearLayoutManager(this));
        rv_lists.setAdapter(taskContractInfoAdapter);
        taskContractInfoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.iv_addtask:
                        /**
                         * 添加任务界面
                         */
                        Task_AddActivity_.intent(TaskCorrelationinfoActivity.this).Taskcount(Taskcount).schemename(tv_schnames.getText().toString()).frequency("").currenttasknumber(position + 1 + "").schid(getIntent().getStringExtra("schid")).conid(getIntent().getStringExtra("coid")).start();
                        break;
                    /**
                     * 查看任务相关信息
                     */
                    case R.id.ll_items:
                        TaskCycleListActivity_.intent(TaskCorrelationinfoActivity.this).currenttasknumber(position + 1 + "").conid(getIntent().getStringExtra("coid")).start();
                        break;
                }


                ///   TaskinfoActivity_.intent(TaskCorrelationinfoActivity.this).taskStutas(taskContractInfoAdapter.getData().get(position).getTaskstatus()).taskid(taskContractInfoAdapter.getData().get(position).getId()).start();
            }
        });
        iv_addtask = view.findViewById(R.id.iv_addtask);
        iv_addtask.setVisibility(View.GONE);
//        /**
//         * 跳转到添加任务界面
//         */
//        iv_addtask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Task_AddActivity_.intent(TaskCorrelationinfoActivity.this).frequency("").schid(getIntent().getStringExtra("schid")).conid(getIntent().getStringExtra("coid")).start();
//            }
//        });


    }

    Boolean isvi = true;
    ImageView iv_look;
    LinearLayout ll_isShow;

    private TextureMapView mMapView = null;

    public void initViews(View view) {

        //获取地图控件引用
        mMapView = view.findViewById(R.id.bmapView);


        ll_isShow = view.findViewById(R.id.ll_isShow);
        iv_look = view.findViewById(R.id.iv_look);
//        map = view.findViewById(R.id.map);
        tv_task_person1 = view.findViewById(R.id.tv_task_person1);
        tv_task_phone = view.findViewById(R.id.tv_task_phone);
        task_info_name = view.findViewById(R.id.task_info_name);
        tv_task_time = view.findViewById(R.id.tv_task_time);
        tv_task_samplemodename = view.findViewById(R.id.tv_task_samplemodename);
        tv_task_isretentionname = view.findViewById(R.id.tv_task_isretentionname);
        tv_task_typesname = view.findViewById(R.id.tv_task_typesname);
        ll_map = view.findViewById(R.id.ll_map);
        tv_introduction = view.findViewById(R.id.tv_introduction);
        tv_schnames = view.findViewById(R.id.tv_schnames);

        tv_task_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_task_phone.getText().toString().trim() != null) {
                    call(tv_task_phone.getText().toString().trim());
                }
            }
        });


//        if (isvi) {
//            iv_look.setImageResource(R.drawable.fold_right);
//            taskCorrelationinfoOneAdapter.setNewData(mFalse);
//        } else {
//            iv_look.setImageResource(R.drawable.fold_down);
//            taskCorrelationinfoOneAdapter.setNewData(mTrue);
//        }
        ll_isShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isvi) {
                    isvi = true;
                    iv_look.setImageResource(R.drawable.fold_right);
                    taskCorrelationinfoOneAdapter.setNewData(mFalse);
                } else {
                    isvi = false;
                    iv_look.setImageResource(R.drawable.fold_down);

                    taskCorrelationinfoOneAdapter.setNewData(mTrue);
                }

            }
        });
    }


    public void initData() {
        ShowDialogtitle("加载中...", this);
        String schemeid = getIntent().getStringExtra("schid");
//        String schemeid = "282fd403-9fb0-492f-b693-2826590e1563";
        String data = "{schemeid:\"" + schemeid + "\"}";
        taskContractInfoPresenter.GetDetectionSchemeInfo(data);


//        switch (getIntent().getStringExtra("status")) {
//            case "0":
//
//                if (getIntent().getStringExtra("Samplemodename") != null) {
//                    if (getIntent().getStringExtra("Samplemodename").equals("现场采样")) {
//                        String datas = "{id:\"\",conid:\"" + getIntent().getStringExtra("coid") + "\"} ";
//                        taskContractInfoPresenter.GetTasksInfo(datas);
//                    } else {
//                        String datas = "{onlynumber:\"\",sampingname:\"\",schemeid:\"" + getIntent().getStringExtra("schid") + "\",siid:\"\",categoryid:\"\",sdate:\"\",edate:\"\"} ";
//                        taskContractInfoPresenter.GetsampleInformation(datas);
//                    }
//                }
//
//                break;
//            case "1":
//                String datas1 = "{id:\"\",conid:\"" + getIntent().getStringExtra("coid") + "\"} ";
//                taskContractInfoPresenter.GetTasksInfo(datas1);
//                break;
//            case "2":
//                break;
//        }

//        if (getIntent().getStringExtra("status") != null) {
//            if (!getIntent().getStringExtra("status").equals("审批")) {
//                String datas = "{id:\"" + getIntent().getStringExtra("coid") + "\",conid:\"\"} ";
//                taskContractInfoPresenter.GetTasksInfo(datas);
//            }
//        } else {
//
//        }
    }


    /**
     * 初始化地图
     *
     * @param savedInstanceState
     */
    public void initMap(Bundle savedInstanceState) {

//        map.onCreate(savedInstanceState);
//        //初始化地图控制器对象
//        if (aMap == null) {
//            aMap = map.getMap();
//        }
        mBaiduMap = mMapView.getMap();

    }


    @Override
    public void onResume() {
        super.onResume();
//        map.onResume();
        mMapView.onResume();

        initData();


        switch (getIntent().getStringExtra("status")) {
            case "0":

                if (getIntent().getStringExtra("Samplemodename") != null) {
                    if (getIntent().getStringExtra("Samplemodename").equals("现场采样")) {
//                        String datas = "{id:\"\",conid:\"" + getIntent().getStringExtra("coid") + "\"} ";
//                        taskContractInfoPresenter.GetTasksInfo(datas);
                    } else {
                        String datas = "{onlynumber:\"\",sampingname:\"\",schemeid:\"" + getIntent().getStringExtra("schid") + "\",siid:\"\",categoryid:\"\",sdate:\"\",edate:\"\"} ";
                        taskContractInfoPresenter.GetsampleInformation(datas);
                    }
                }
                break;
            case "1":
//                String datas1 = "{id:\"\",conid:\"" + getIntent().getStringExtra("coid") + "\"} ";
//                taskContractInfoPresenter.GetTasksInfo(datas1);
                break;
            case "2":
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
//        map.onPause();
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        map.onSaveInstanceState(outState);
    }

    private List<LocalMedia> selectList_image = new ArrayList<>();
    private List<LocalMedia> selectList_video = new ArrayList<>();


    public void initImage() {
        /**
         * 附件
         */
        FullyGridLayoutManager manager_image = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recycler_image.setLayoutManager(manager_image);
        adapter_image = new GridImageAdapter(this, "image");
        adapter_image.setList(selectList_image);
        adapter_image.setSelectMax(0);
        recycler_image.setAdapter(adapter_image);

        adapter_image.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList_image.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                // 预览图片
                PictureSelector.create(TaskCorrelationinfoActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList_image);
            }
        });

        FullyGridLayoutManager manager_video = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recycler_video.setLayoutManager(manager_video);
        adapter_video = new GridImageAdapter(this, "video");
        adapter_video.setList(selectList_video);
        adapter_video.setSelectMax(0);
        recycler_video.setAdapter(adapter_video);
        adapter_video.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList_video.get(position);
                String pictureType = media.getPictureType();
                PictureSelector.create(TaskCorrelationinfoActivity.this).externalPictureVideo(media.getPath());
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        map.onDestroy();
        mMapView.onDestroy();

    }

    List<DetectionSchemeInfoBean.LeftcateogoryBean> mTrue = new ArrayList<>();
    List<DetectionSchemeInfoBean.LeftcateogoryBean> mFalse = new ArrayList<>();

    @Override
    public void OnTasksListInfo(List<TasksListInfoBean> tasksListInfoBeans) {
        DissDialog();
        if (tasksListInfoBeans.size() > 0) {
            if (getIntent().getStringExtra("status") != null) {

                switch (getIntent().getStringExtra("status")) {
                    case "0":
                        break;
                    case "1":
                        iv_addtask.setVisibility(View.VISIBLE);
                        break;
                    case "2":
                        break;
                }
            }
            /**
             * 根据任务次数判断当前是否还可以添加任务
             */
//            int nums = 0;
//            for (int i = 0; i < tasksListInfoBeans.size(); i++) {
//                ViseLog.d("lsls:" + tasksListInfoBeans.get(i).getTaskstatus());
//                if (!tasksListInfoBeans.get(i).getTaskstatus().equals("2") &&
//                        !tasksListInfoBeans.get(i).getTaskstatus().equals("5")) {
//                    nums++;
//                }
//            }
//            ViseLog.d("lsls:" + nums + "\n" + tasknum);
//            if (tasknum == nums) {
//                iv_addtask.setVisibility(View.GONE);
//            } else {
//                iv_addtask.setVisibility(View.VISIBLE);
//            }
        }


    }

    @Override
    public void OnTasksInfoDetail(List<TasksInfoDetailBean> tasksInfoDetailBeans) {

    }

    String issamplemodename = "";

    int tasknum = 0;

    String Taskcount = "";

    @Override
    public void OnGetDetectionSchemeInfo(List<DetectionSchemeInfoBean> list) {
        DissDialog();
        String jj = "", w = "";
        if (list != null && list.size() > 0) {
            if (list.get(0).getTaskNumber().length() > 0) {
                tasknum = Integer.parseInt(list.get(0).getTaskNumber());

                List<TasksListInfoBean> tasksListInfoBeanList = new ArrayList<>();
                for (int i = 0; i < tasknum; i++) {
                    TasksListInfoBean tasksListInfoBean = new TasksListInfoBean();
                    tasksListInfoBean.setSubject("第" + (i + 1) + "周期");
                    tasksListInfoBeanList.add(tasksListInfoBean);
                }
                taskContractInfoAdapter.setNewData(tasksListInfoBeanList);
            }

            /**
             * 已分配任务总数
             */
            Taskcount = list.get(0).getTaskcount();

            issamplemodename = list.get(0).getSamplemodename();
            task_info_name.setText(list.get(0).getProjectname());
            tv_task_time.setText(list.get(0).getCustomername());  //客户名称
            tv_task_person1.setText(list.get(0).getContacts());//联系人
            tv_task_phone.setText(list.get(0).getTelephone());//联系电话
            tv_task_samplemodename.setText(list.get(0).getSamplemodename());
            tv_task_isretentionname.setText(list.get(0).getIsretentionname());
            tv_task_typesname.setText(list.get(0).getTypesname());
            tv_schnames.setText(list.get(0).getSchemeName());
            tv_introduction.setText(list.get(0).getIntroduction());
            if (list.get(0).getLeftcateogory().size() > 0 && list.get(0).getLeftcateogory() != null) {

                mTrue = new ArrayList<>();
                mFalse = new ArrayList<>();
                mTrue.addAll(list.get(0).getLeftcateogory());


                for (int i = 0; i < list.get(0).getLeftcateogory().size(); i++) {

                    addsample = list.get(0).getLeftcateogory().size();


                    if (list.get(0).getLeftcateogory().get(i).getPoints() != null && list.get(0).getLeftcateogory().get(i).getPoints().size() > 0) {

                        ll_map.setVisibility(View.VISIBLE);

                        try {
                            for (int j = 0; j < list.get(0).getLeftcateogory().get(i).getPoints().size(); j++) {

                                jj = list.get(0).getLeftcateogory().get(i).getPoints().get(j).getLongitude();
                                w = list.get(0).getLeftcateogory().get(i).getPoints().get(j).getLatitude();

                                if (jj.length() > 0 && w.length() > 0) {


//                                    View view = LayoutInflater.from(this).inflate(R.layout.mark_view, null);
//
//                                    TextView tv_num_price=view.findViewById(R.id.tv_num_price);
//                                    tv_num_price.setText(list.get(0).getLeftcateogory().get(i).getPoints().get(j).getAddress());
                                    LatLng point = new LatLng(Double.parseDouble(w)
                                            , Double.parseDouble(jj));
                                    BitmapDescriptor bitmap = BitmapDescriptorFactory
                                            .fromResource(R.drawable.ic_delete_photo);
                                    OverlayOptions option = new MarkerOptions()
                                            .position(point)
                                            .icon(bitmap);
                                    mBaiduMap.addOverlay(option);
                                    MapStatus mMapStatus = new MapStatus.Builder()
                                            .target(point)
                                            .zoom(18)
                                            .build();
                                    MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                                    //改变地图状态
                                    mBaiduMap.setMapStatus(mMapStatusUpdate);
                                } else {

                                    LatLng cenpt = new LatLng(latitude_value, longitude_value);
                                    BitmapDescriptor bitmap = BitmapDescriptorFactory
                                            .fromResource(R.drawable.ic_delete_photo);
                                    OverlayOptions option = new MarkerOptions()
                                            .position(cenpt)
                                            .icon(bitmap);
                                    mBaiduMap.addOverlay(option);
                                    MapStatus mMapStatus = new MapStatus.Builder()
                                            .target(cenpt)
                                            .zoom(18)
                                            .build();
                                    MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                                    //改变地图状态
                                    mBaiduMap.setMapStatus(mMapStatusUpdate);

                                }

                            }
                        } catch (Exception e) {
                            LatLng cenpt = new LatLng(latitude_value, longitude_value);
                            BitmapDescriptor bitmap = BitmapDescriptorFactory
                                    .fromResource(R.drawable.ic_delete_photo);
                            OverlayOptions option = new MarkerOptions()
                                    .position(cenpt)
                                    .icon(bitmap);
                            mBaiduMap.addOverlay(option);
                            MapStatus mMapStatus = new MapStatus.Builder()
                                    .target(cenpt)
                                    .zoom(18)
                                    .build();
                            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                            //改变地图状态
                            mBaiduMap.setMapStatus(mMapStatusUpdate);
                        }

                    } else if (list.get(0).getLeftcateogory().get(i).getFactors() != null && list.get(0).getLeftcateogory().get(i).getFactors().size() > 0) {

                        /**
                         * 如果是客户送样不存在现场点位信息
                         */
                    }
                }
            }
        }


        switch (getIntent().getStringExtra("status")) {
            case "0":

                if (getIntent().getStringExtra("Samplemodename") != null) {
                    if (getIntent().getStringExtra("Samplemodename").equals("现场采样")) {
//                        String datas = "{id:\"\",conid:\"" + getIntent().getStringExtra("coid") + "\"} ";
//                        taskContractInfoPresenter.GetTasksInfo(datas);
                    } else {
                        String datas = "{onlynumber:\"\",sampingname:\"\",schemeid:\"" + getIntent().getStringExtra("schid") + "\",siid:\"\",categoryid:\"\",sdate:\"\",edate:\"\"} ";
                        taskContractInfoPresenter.GetsampleInformation(datas);
                    }
                }

                break;
            case "1":
                String datas1 = "{id:\"\",pagenumber:\"\",numbers:\"\",conid:\"" + getIntent().getStringExtra("coid") + "\"} ";
                taskContractInfoPresenter.GetTasksInfo(datas1);
                break;
            case "2":
                break;
        }


    }

    @Override
    public void OnAddDetectionSchemeAudit(ResultBean resultBean) {

        DissDialog();
        if (resultBean.getResult().equals("1")) {
            EventBus.getDefault().post(new UpdateEvent("Audittask"));
            EventBus.getDefault().post(new CloseEvent("Detection"));
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
        if (dicDataBeans.size() > 0) {
//            iv_addtask.setVisibility(View.GONE);

            /**
             * 根据任务次数判断当前是否还可以添加任务
             */
//            if (tasknum == dicDataBeans.size()) {
//                iv_addtask.setVisibility(View.GONE);
//            }
            taskaddSampleAdapter.setNewData(dicDataBeans);
        } else {

        }
    }

    @Override
    public void onFinance(List<ProjectcontractBean> list) {

        if (list.get(0).getSamplemodename().equals("现场采样")) {
            View view_foot0 = TaskCorrelationinfoActivity.this.getLayoutInflater().inflate(R.layout.task_contractinfo_item, (ViewGroup) rv_list.getParent(), false);
            taskCorrelationinfoOneAdapter.addFooterView(view_foot0);
            initView_foot(view_foot0);
        } else {
            View view_foot0 = TaskCorrelationinfoActivity.this.getLayoutInflater().inflate(R.layout.task_contraction_addsapmle, (ViewGroup) rv_list.getParent(), false);
            taskCorrelationinfoOneAdapter.addFooterView(view_foot0);
            initView_AddFoot(view_foot0);
        }
    }

    @Override
    public void OnGetTasksInfoAudit(List<TaskInfoAllBean> taskInfoAllBeans) {

        tv_fj.setText(taskInfoAllBeans.get(0).getStopreason());
        List<String> photoslist = new ArrayList<>();
        for (int i = 0; i < taskInfoAllBeans.get(0).getFujians().size(); i++) {
            photoslist.add(taskInfoAllBeans.get(0).getFujians().get(i).getFujian());
        }
        if (photoslist.size() > 0) {
            ll_photos.setVisibility(View.VISIBLE);
            selectList_image = new ArrayList<>();
            selectList_video = new ArrayList<>();
            for (int i = 0; i < photoslist.size(); i++) {
                LocalMedia s = new LocalMedia();
                s.setPath(PHOTOS_URL + photoslist.get(i));
                if (photoslist.get(i).indexOf(".mp4") == -1) {
                    //不包含是图片
                    selectList_image.add(s);
                } else {
                    //是视频
                    selectList_video.add(s);
                }
            }
            initImage();
        }

    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }


}

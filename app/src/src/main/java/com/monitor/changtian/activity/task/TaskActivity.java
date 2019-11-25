package com.monitor.changtian.activity.task;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.AddSampleInfoActivity_;
import com.monitor.changtian.activity.SignatureActivity_;
import com.monitor.changtian.adapter.ReceiveConAdapter;
import com.monitor.changtian.adapter.ReceiveDevAdapter;
import com.monitor.changtian.adapter.SampleinfolistAdapter;
import com.monitor.changtian.adapter.TaskEqAdapter;
import com.monitor.changtian.adapter.TaskPointAdapter;
import com.monitor.changtian.adapter.TaskPointsAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.CloseEvent;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EquipInOutStockDataBean;
import com.monitor.changtian.bean.EventBus.TaskEvent;
import com.monitor.changtian.bean.FieldsamplingDetailBean;
import com.monitor.changtian.bean.FieldsamplingDetailListBean;
import com.monitor.changtian.bean.FieldsamplingInfo;
import com.monitor.changtian.bean.GetConsumablesDataBean;
import com.monitor.changtian.bean.JumpEvent;
import com.monitor.changtian.bean.JumpFrequencyEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TaskAllPointsFreQuencyBean;
import com.monitor.changtian.bean.TaskListBean;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.AddPhotosPresenter;
import com.monitor.changtian.presenter.ReceivePresenter;
import com.monitor.changtian.presenter.TaskPresenter;
import com.monitor.changtian.presenter.TaskinfoPresenter;
import com.monitor.changtian.view.AddPhotosView;
import com.monitor.changtian.view.ReceiveView;
import com.monitor.changtian.view.TaskinfoView;
import com.vise.log.ViseLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.Latitude_name;
import static com.monitor.changtian.constant.PublicConstant.latitude_value;
import static com.monitor.changtian.constant.PublicConstant.longitude_value;


public class TaskActivity extends BaseActvity implements TaskinfoView, AddPhotosView, SubmitView<List<TaskListBean>>, ReceiveView {


    private BaiduMap mBaiduMap;
    private TextureMapView mMapView = null;
    //  全局变量 任务id 后从Extra获取taskid数据
    String taskid = "";
    TaskListBean taskBean = null;
    TextView task_info_name,
            tv_task_time,
            tv_task_etime,
            tv_task_pro,
            tv_task_count,
            tv_task_address, tv_addrecord, tv_abnormal;
    LinearLayout ll_task_onclik;
    TaskinfoPresenter taskinfoPresenter;
    SampleinfolistAdapter sampleinfolistAdapter;
    RecyclerView rv_list;
    ReceivePresenter receivePresenter;
    ReceiveDevAdapter receiveDevAdapter;
    ReceiveConAdapter receiveConAdapter;
    TaskPresenter taskPresenter;
    String fsid = "";
    AddPhotosPresenter addPhotosPresenter;

    TaskEqAdapter taskEqAdapter;
    TaskPointsAdapter taskPointsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        //注册 EvetnBus；
//        EventBus Android 发布/订阅事件总线，可简化 Activities, Fragments, Threads, Services 等组件间的消息传递，
//        可替代 Intent, Handler, BroadCast ，接口等传统方案，更快，代码更小
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
        RegisterEventBus();
        initImageBackText("任务详情");

        //  设备列表id 绑定 RecyclerView 类型变量
        rv_list = findViewById(R.id.rv_list);

        //  初始化变量
        addPhotosPresenter = new AddPhotosPresenter(this, this);
        taskPresenter = new TaskPresenter(this, this);
        taskinfoPresenter = new TaskinfoPresenter(this, this);
        receivePresenter = new ReceivePresenter(this, this);
        ShowDialogtitle("加载中...", TaskActivity.this);

        // 获取任务的数据(任务信息 设备 耗材)
        initData();

        //设备
        receiveDevAdapter = new ReceiveDevAdapter(R.layout.cai_item);
        //耗材
        receiveConAdapter = new ReceiveConAdapter(R.layout.cai_item);

        taskEqAdapter = new TaskEqAdapter(R.layout.cai_item);
        taskPointsAdapter = new TaskPointsAdapter(R.layout.task_points_item, this);
        taskPointAdapter = new TaskPointAdapter(R.layout.dev_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(taskPointsAdapter);
//        View view = this.getLayoutInflater().inflate(R.layout.sampleinlist_head, (ViewGroup) rv_list.getParent(), false);
//        taskPointsAdapter.addHeaderView(view);

        initViews();
//        if (getIntent().getStringExtra("taskid") == null) {
//
//        }

        initMap(savedInstanceState);

        /**
         * 跳转对应点位列表 看各频次
         */
        taskPointsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (taskPointsAdapter.getData().get(position).getFrequency() != null) {
                    if (taskPointsAdapter.getData().get(position).getShow()) {
                        taskPointsAdapter.getData().get(position).setShow(false);
                        taskPointsAdapter.notifyDataSetChanged();
                    } else {
                        taskPointsAdapter.getData().get(position).setShow(true);
                        taskPointsAdapter.notifyDataSetChanged();
                    }
                } else {
                    msg("当前方案暂无频次");
                }
            }
        });
    }

    //        注册EventBus
    //        EventBus Android 发布/订阅事件总线，可简化 Activities, Fragments, Threads, Services 等组件间的消息传递，
    //        可替代 Intent, Handler, BroadCast ，接口等传统方案，更快，代码更小
    public void RegisterEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    // 获取  1.任务信息 2.任务设备信息 3.任务耗材信息
    public void initData() {

        if (getIntent().getStringExtra("taskid") != null) {
            String data = "{id:\"" + getIntent().getStringExtra("taskid") + "\",conid:\"\",pagenumber:\"\",numbers:\"\",sampingloginId:\"" + MyApplication.getInstance().getUser() + "\",taskstatus:\"1,3\"}";
            taskPresenter.GetTaskList(data);
        } else {

            if (taskid.length() > 0) {
                String data = "{fsid:\"\",IsEnd:\"\",pointsid:\"\",taskid:\"" + taskid + "\",sdate:\"\",edate:\"\"}";
                taskinfoPresenter.GetfieldsamplingInfo(data);
                /**
                 * 查询领用的设备 types "1" 表示已领用 , "0" 表示已归还 isreturn 是否归还 "0"表示"否" "1"表示"是"
                 */
                String data_dev = "{id:\"\",equipid:\"\",types:\"1\",optionuser:\"\",taskid:\"" + taskid + "\",optionLoginName:\"admin\",isreturn:\"0,1\"}";
                receivePresenter.GetEquipInOutStockData(data_dev);
                /**
                 * 查询领用的耗材
                 */
                String data_con = "{id:\"\",conid:\"\",types:\"1\",optionuser:\"\",taskid:\"" + taskid + "\",optionuserLoginName:\"admin\",isreturn:\"0,1\"}";
                receivePresenter.GetConsumablesInOutStockOptionData(data_con);
            }
        }
    }


    //反注册EventBus
    public void UnRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    /**
     * 初始化地图
     *
     * @param savedInstanceState
     */
    public void initMap(Bundle savedInstanceState) {
        mBaiduMap = mMapView.getMap();
    }

//    @Subscribe(sticky = true)
//    public void taskBean(TaskEvent event) {
//        if (event != null) {
//            taskid = event.getTaskid();
//            shemid = event.getTaskBean().getSchemeid();
//            taskBean = event.getTaskBean();
//            ViseLog.d(taskBean.getPoints().size() + "");
//        } else {
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        if (taskid.length() > 0) {
            String data = "{fsid:\"\",IsEnd:\"\",pointsid:\"\",taskid:\"" + taskid + "\",sdate:\"\",edate:\"\"}";
            taskinfoPresenter.GetfieldsamplingInfo(data);
            /**
             * 查询领用的设备
             */
            String data_dev = "{id:\"\",equipid:\"\",types:\"1\",optionuser:\"\",taskid:\"" + taskid + "\",optionLoginName:\"admin\",isreturn:\"0,1\"}";
            receivePresenter.GetEquipInOutStockData(data_dev);
            /**
             * 查询领用的耗材
             */
            String data_con = "{id:\"\",conid:\"\",types:\"1\",optionuser:\"\",taskid:\"" + taskid + "\",optionuserLoginName:\"admin\",isreturn:\"0,1\"}";
            receivePresenter.GetConsumablesInOutStockOptionData(data_con);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        UnRegisterEventBus();
        mMapView.onDestroy();
    }

    /**
     * 头布局
     */
    TaskPointAdapter taskPointAdapter;
    RecyclerView rv_list_point;
    LinearLayout ll_point;
    TextView tv_receiveDev, tv_receiveCon; //领取设备和耗材
    RecyclerView rv_list_dev, rv_list_Con;

    Boolean isvi = true;
    ImageView iv_look;
    LinearLayout ll_looks;
    List<TaskListBean.PointsBean> mTrue = new ArrayList<>();
    List<TaskListBean.PointsBean> mFalse = new ArrayList<>();
    View viewhead = null;
    TextView tv_photos, tv_submit;
    private List<LocalMedia> sy_image = new ArrayList<>();

    RecyclerView rv_listy_eq;

    TextView tv_task_address2, tv_task_person2, tv_task_phone2;

    public void initViews() {

        /**
         *  现场方
          */
        tv_task_address2 = findViewById(R.id.tv_task_address2);
        tv_task_person2 = findViewById(R.id.tv_task_person2);
        tv_task_phone2 = findViewById(R.id.tv_task_phone2);

        iv_look = findViewById(R.id.iv_look);
        ll_looks = findViewById(R.id.ll_looks);
        //获取地图控件引用
        mMapView = findViewById(R.id.bmapView);
        ll_point = findViewById(R.id.ll_point);
        ll_task_onclik = findViewById(R.id.ll_task_onclik);
        task_info_name = findViewById(R.id.task_info_name);
        tv_task_count = findViewById(R.id.tv_task_count);
        tv_task_time = findViewById(R.id.tv_task_time);
        tv_task_etime = findViewById(R.id.tv_task_etime);
        tv_task_address = findViewById(R.id.tv_task_address);
        tv_task_pro = findViewById(R.id.tv_task_pro);
        rv_list_point = findViewById(R.id.rv_list_potion);
        rv_list_point.setLayoutManager(new LinearLayoutManager(this));
        rv_list_point.setAdapter(taskPointAdapter);
        // 现场示意图id
        tv_photos = findViewById(R.id.tv_photos);
        // 提交id
        tv_submit = findViewById(R.id.tv_submit);

        // 设备id 绑定
        rv_listy_eq = findViewById(R.id.rv_listy_eq);
        rv_listy_eq.setLayoutManager(new GridLayoutManager(TaskActivity.this, 2));
        rv_listy_eq.setAdapter(taskEqAdapter);


        if (taskBean != null) {
            initView(taskBean);
            if (taskBean.getPoints().size() > 0) {
                mTrue = new ArrayList<>();
                mFalse = new ArrayList<>();
                mTrue.addAll(taskBean.getPoints());
            } else {
                ll_point.setVisibility(View.GONE);
            }
        }


        if (isvi) {
            iv_look.setImageResource(R.drawable.fold_right);
            taskPointAdapter.setNewData(mFalse);
        } else {
            iv_look.setImageResource(R.drawable.fold_down);
            taskPointAdapter.setNewData(mTrue);
        }

        /**
         *  展开合并点位信息
         */
        ll_looks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isvi) {
                    isvi = true;
                    iv_look.setImageResource(R.drawable.fold_right);
                    taskPointAdapter.setNewData(mFalse);
                } else {
                    isvi = false;
                    iv_look.setImageResource(R.drawable.fold_down);
                    taskPointAdapter.setNewData(mTrue);
                }

            }
        });
        /**
         * 上传现场示意图 打开相册 选择图片
         */
        tv_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureSelector.create(TaskActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .theme(R.style.picture_default_style)
                        .maxSelectNum(1)
                        .minSelectNum(1)
                        .selectionMode(true ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                        .previewImage(true)
                        .previewVideo(true)
                        .enablePreviewAudio(true) // 是否可播放音频
                        .isCamera(true)
                        .glideOverride(160, 160)
                        .previewEggs(true)
                        .compress(true)
                        .selectionMedia(sy_image)
                        .forResult(777);
            }
        });
        /**
         *  提交现场示意图
         */
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sy_image.size() > 0) {
                    ShowDialogtitle("请稍等...", TaskActivity.this);
                    addPhotosPresenter.AddfujianInfo(MyApplication.getInstance().getUser(), "2", "", shemid, new File(sy_image.get(0).getPath()));
                } else {
                    msg("请选择图片");
                }
            }
        });
        /**
         * 添加采样信息
         */
        tv_addrecord = findViewById(R.id.tv_addrecord);
        tv_addrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSampleInfoActivity_.intent(TaskActivity.this).taskid(taskid).start();
            }
        });
        /**
         * 异常反馈界面
         */
        tv_abnormal = findViewById(R.id.tv_abnormal);
        tv_abnormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbnormalActivity_.intent(TaskActivity.this).taskid(taskid).name(taskBean.getSubject()).time(taskBean.getStarttime()).start();
            }
        });
        /**
         * 领取设备
         */
        tv_receiveDev = findViewById(R.id.tv_receiveDev);
        tv_receiveDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReceiveDevActivity_.intent(TaskActivity.this).taskId(taskid).type("dev").start();
            }
        });
        rv_list_dev = findViewById(R.id.rv_list_dev);
        rv_list_dev.setLayoutManager(new GridLayoutManager(TaskActivity.this, 2));
        rv_list_dev.setAdapter(receiveDevAdapter);
        /**
         * 归还设备
         */
        receiveDevAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                initShowDialogs();
            }
        });


        /**
         * 领取耗材
         */
        tv_receiveCon = findViewById(R.id.tv_receiveCon);
        tv_receiveCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReceiveDevActivity_.intent(TaskActivity.this).taskId(taskid).type("con").start();
            }
        });
        rv_list_Con = findViewById(R.id.rv_list_Con);
        rv_list_Con.setLayoutManager(new GridLayoutManager(TaskActivity.this, 2));
        rv_list_Con.setAdapter(receiveConAdapter);


        /**
         * 内容点击
         */
        // 点击任务内容 Toast 弹出 全部任务内容
        tv_task_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_task_count.getText().toString().length() > 0) {
                    msg(tv_task_count.getText().toString());
                }
            }
        });
        // 点击任务名称 Toast 弹出 全部任务名称
        task_info_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (task_info_name.getText().toString().length() > 0) {
                    msg(task_info_name.getText().toString());
                }
            }
        });
        // 点击任务地址 Toast 弹出全部 任务地址
        tv_task_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_task_address.getText().toString().length() > 0) {
                    msg(tv_task_address.getText().toString());
                }
            }
        });

    }

    public void initShowDialogs() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.BDAlertDialog);
        final AlertDialog dia = builder.show();
        builder.setCancelable(false);
        View view = LayoutInflater.from(this).inflate(R.layout.dev_showdialog, null);

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dia.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dia.dismiss();

            }
        });
        builder.setView(view);
        builder.show();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 777:
                    sy_image = PictureSelector.obtainMultipleResult(data);
                    tv_photos.setText(sy_image.get(0).getPath());
                    break;
            }
        }
    }

    /**
     * 底布局
     */
    public void AddFootView() {
//        View view_foot = this.getLayoutInflater().inflate(R.layout.task_foot, (ViewGroup) rv_list_pre.getParent(), false);
//        taskPersonAdapter.addFooterView(view_foot);
//        RecyclerView rv_list_car = view_foot.findViewById(R.id.rv_list_car);
//        taskCarsAdapter = new TaskCarsAdapter(R.layout.task_car_item, this);
//        rv_list_car.setLayoutManager(new LinearLayoutManager(this));
//        rv_list_car.setAdapter(taskCarsAdapter);
//        map = view_foot.findViewById(R.id.map);
//        taskCarsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//
//                if (taskCarsAdapter.getData().get(position).getImgurl().length() > 0) {
//                    List<LocalMedia> selectList_image = new ArrayList<>();
//                    LocalMedia s = new LocalMedia();
//                    s.setPath(PHOTOS_URL + taskCarsAdapter.getData().get(position).getImgurl());
//                    selectList_image.add(s);
//                    PictureSelector.create(TaskActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(0, selectList_image);
//                } else {
//                    msg("暂无图片!");
//                }
//            }
//        });
//
//        RecyclerView rv_list_Eq = view_foot.findViewById(R.id.rv_list_Eq);
//        taskEquAdapter = new TaskEquAdapter(R.layout.task_equ_item);
//        rv_list_Eq.setLayoutManager(new LinearLayoutManager(this));
//        rv_list_Eq.setAdapter(taskEquAdapter);
    }

    /**
     * 加载地图
     */


    public void initView(TaskListBean taskBean) {
        tv_task_count.setText(taskBean.getRemark());
        tv_task_address.setText(taskBean.getAddress());
        task_info_name.setText(taskBean.getSubject());
        tv_task_time.setText(taskBean.getCtime().substring(0, 10));
        tv_task_pro.setText(taskBean.getSchemename());
        tv_task_etime.setText(taskBean.getEndtime().substring(0, 10));


        if (taskBean.getAddress2().length() > 0) {
            tv_task_address2.setText(taskBean.getAddress2());
        } else {
            tv_task_address2.setText("");
        }

        if (taskBean.getContacts2().length() > 0) {
            tv_task_person2.setText(taskBean.getContacts2());
        } else {
            tv_task_person2.setText("");
        }

        if (taskBean.getPhone2().length() > 0) {
            tv_task_phone2.setText(taskBean.getPhone2());
        } else {
            tv_task_phone2.setText("");
        }
        tv_task_phone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_task_phone2.getText().toString().trim() != null) {
                    call(tv_task_phone2.getText().toString().trim());
                }
            }
        });
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

    String shemid = "";

    //  GetTaskList 回调函数 带过来任务的相关数据 包含任务所有设备 耗材
    @Override
    public void onData(List<TaskListBean> taskListBeans) {
        taskid = taskListBeans.get(0).getId();
        taskBean = taskListBeans.get(0);
        shemid = taskListBeans.get(0).getSchemeid();
        taskPointsAdapter.setTaskid(taskListBeans.get(0).getId());
        taskPointsAdapter.setNewData(taskListBeans.get(0).getPoints());
        taskEqAdapter.setNewData(taskListBeans.get(0).getEquips());
        for (int i = 0; i < taskBean.getPoints().size(); i++) {
            if (taskBean.getPoints().get(i).getLatitude().length() > 0 || taskBean.getPoints().get(i).getLongitude().length() > 0) {
                LatLng point = new LatLng(Double.parseDouble(taskBean.getPoints().get(i).getLatitude())
                        , Double.parseDouble(taskBean.getPoints().get(i).getLongitude()));
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

        if (GETSTATUS.equals("更新数据")) {
            EventBus.getDefault().post(new JumpFrequencyEvent(taskListBeans.get(0).getPoints()));
        }


        if (taskid.length() > 0) {
            String data = "{fsid:\"\",IsEnd:\"\",pointsid:\"\",taskid:\"" + taskid + "\",sdate:\"\",edate:\"\"}";
            taskinfoPresenter.GetfieldsamplingInfo(data);
            /**
             * 查询领用的设备
             */
            String data_dev = "{id:\"\",equipid:\"\",types:\"1\",optionuser:\"\",taskid:\"" + taskid + "\",optionLoginName:\"" + MyApplication.getInstance().getUser() + "\",isreturn:\"0,1\"}";
            receivePresenter.GetEquipInOutStockData(data_dev);
            /**
             * 查询领用的耗材
             */
            String data_con = "{id:\"\",conid:\"\",types:\"1\",optionuser:\"\",taskid:\"" + taskid + "\",optionuserLoginName:\"" + MyApplication.getInstance().getUser() + "\",isreturn:\"0,1\"}";
            receivePresenter.GetConsumablesInOutStockOptionData(data_con);
        }


        initViews();
    }

    @Override
    public void onMessage(ResultBean message) {

    }


    @Override
    public void OnFieldsamplingInfo(List<FieldsamplingInfo> fieldsamplingInfos) {
        DissDialog();
//        sampleinfolistAdapter.setNewData(fieldsamplingInfos);
//        if (fieldsamplingInfos.size() > 0) {
//            tv_addrecord.setVisibility(View.GONE);
//        }

    }

    @Override
    public void OnEndFieldsampling(ResultBean message) {
        DissDialog();
        if (message != null) {
            if (message.getResult().equals("1")) {
                initData();
                /**
                 * 如果添加成功,然后弹出dialog提示添加签名的界面。
                 */
                /**
                 * 二次弹框确认
                 */
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("是否要录入现场负责人签字");
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SignatureActivity_.intent(TaskActivity.this).fsid(fsid).start();
                    }
                });
                builder.show();


            }
        } else {
            msg(message.getErrormessage());
        }

    }

    @Override
    public void OnGetfieldsamplingDetailList(List<FieldsamplingDetailListBean> fieldsamplingDetailListBeans) {

    }

    @Override
    public void OnGetfieldsamplingDetail(List<FieldsamplingDetailBean> fieldsamplingDetailBeans) {

    }

    @Override
    public void OnQueryDevList(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {
        if (equipInOutStockDataBeans != null) {
            receiveDevAdapter.setNewData(equipInOutStockDataBeans);
        }
    }

    @Override
    public void OnQueryConList(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {
        if (equipInOutStockDataBeans != null) {
            receiveConAdapter.setNewData(equipInOutStockDataBeans);
        }
    }

    @Override
    public void OnQuerConsumablesDataBean(List<GetConsumablesDataBean> consumablesDataBeans) {

    }

    @Override
    public void OnReceiveSubmit(ResultBean resultBean) {

    }


    // 回调-提交现场示意图
    @Override
    public void OnAddPhotos(ResultBean resultBean) {
        DissDialog();
        if (resultBean.getResult().equals("1")) {
            msg("成功");
            tv_photos.setText("");
        } else {
            msg(resultBean.getErrormessage());
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

    String GETSTATUS = "";

    @Subscribe
    public void JumpEvents(JumpEvent event) {

        if (event != null) {

            if (event.getStatus().equals("更新数据")) {
                GETSTATUS = "更新数据";
//                msg("哈哈哈");
                String data = "{id:\"" + getIntent().getStringExtra("taskid") + "\",pagenumber:\"\",numbers:\"\",conid:\"\",sampingloginId:\"" + MyApplication.getInstance().getUser() + "\",taskstatus:\"1,3\"}";
                taskPresenter.GetTaskList(data);
            }

        }

    }


}

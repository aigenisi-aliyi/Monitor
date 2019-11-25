package com.monitor.accuse;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
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
import com.monitor.changtian.R;
import com.monitor.changtian.TasksListInfoBean;
import com.monitor.changtian.adapter.AccuseAllinfoAdapter;
import com.monitor.changtian.adapter.AccuseSubmitAdapter;
import com.monitor.changtian.adapter.AwaitAdapter;
import com.monitor.changtian.adapter.PointZkAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.AccusePointsBean;
import com.monitor.changtian.bean.AccuseShowBean;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.GetqualitycontrolBean;
import com.monitor.changtian.bean.ReshEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SubmitPointBean;
import com.monitor.changtian.bean.Task_SapmleBean;
import com.monitor.changtian.bean.TasksInfoDetailBean;
import com.monitor.changtian.presenter.AccusePresenter;
import com.monitor.changtian.presenter.QueryBasicsPresenter;
import com.monitor.changtian.presenter.TaskContractInfoPresenter;
import com.monitor.changtian.view.AccuseView;
import com.monitor.changtian.view.QueryBasicsView;
import com.monitor.changtian.view.TaskContractInfoView;
import com.monitor.taskallocation.TaskCorrelationinfoActivity;
import com.monitor.taskallocation.adapter.TaskContractInfoAdapter;
import com.monitor.taskallocation.adapter.TaskCorrelationinfoOneAdapter;
import com.monitor.taskallocation.adapter.TaskCycleListAdapter;
import com.monitor.taskallocation.task_list.TaskCycleListActivity_;
import com.vise.log.ViseLog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.Latitude_name;
import static com.monitor.changtian.constant.PublicConstant.latitude_value;
import static com.monitor.changtian.constant.PublicConstant.longitude_value;

/**
 * 质控详情
 */
public class AccuseAllinfoActivity extends BaseActvity implements AccuseView, PointZkAdapter.OnEditValueChangedListener_number, TaskContractInfoView, QueryBasicsView {

    QueryBasicsPresenter queryBasicsPresenter;
    private TextureMapView mMapView = null;
    RecyclerView rv_list;
    TaskContractInfoPresenter taskContractInfoPresenter;
    TaskCorrelationinfoOneAdapter taskCorrelationinfoOneAdapter;
    TaskCycleListAdapter taskContractInfoAdapter;
    private BaiduMap mBaiduMap;
    LinearLayout ll_map;
    TextView task_info_name, tv_task_phone, tv_task_person1, tv_introduction, tv_schnames, tv_task_time, tv_task_samplemodename, tv_task_isretentionname, tv_task_typesname;
    String schemeid = "";
    PointZkAdapter pointZkAdapter;
    AccusePresenter accusePresenter;
    AwaitAdapter awaitAdapter;
    AccuseAllinfoAdapter accuseSubmitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection_audit);
        initImageBackText("详情");
        schemeid = getIntent().getStringExtra("schid");
        id = getIntent().getStringExtra("id");
        rv_list = findViewById(R.id.rv_list);
        accusePresenter = new AccusePresenter(this, this);
        queryBasicsPresenter = new QueryBasicsPresenter(this, this);
        taskContractInfoPresenter = new TaskContractInfoPresenter(this, this);
        initData(schemeid);
        accuseSubmitAdapter = new AccuseAllinfoAdapter(R.layout.accuse_zhikong_item, AccuseAllinfoActivity.this);
        awaitAdapter = new AwaitAdapter(R.layout.awaitaccuse_item);
        taskCorrelationinfoOneAdapter = new TaskCorrelationinfoOneAdapter(this, R.layout.taskcorrelationinfo_one);
        pointZkAdapter = new PointZkAdapter(this, R.layout.accuseinfo_qualitycontrol);
        taskContractInfoAdapter = new TaskCycleListAdapter(R.layout.zq_cycle_contractinfo_item);
        View view1 = AccuseAllinfoActivity.this.getLayoutInflater().inflate(R.layout.taskcorrelationinfo_head, (ViewGroup) rv_list.getParent(), false);
        taskCorrelationinfoOneAdapter.addHeaderView(view1);


        View view_foot1 = AccuseAllinfoActivity.this.getLayoutInflater().inflate(R.layout.acc_task_foot, (ViewGroup) rv_list.getParent(), false);
        taskCorrelationinfoOneAdapter.addFooterView(view_foot1);
        initView_foot(view_foot1);
        rv_list.setLayoutManager(new LinearLayoutManager(AccuseAllinfoActivity.this));
        rv_list.setAdapter(taskCorrelationinfoOneAdapter);
        initViews(view1);
        initMap(savedInstanceState);
    }

    /**
     * 添加任务列表展示
     */
    public void initView_foot(View view_foot1) {

        RecyclerView rv_lists = view_foot1.findViewById(R.id.rv_list);
        rv_lists.setLayoutManager(new LinearLayoutManager(this));
        rv_lists.setAdapter(taskContractInfoAdapter);
        taskContractInfoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                TaskinfoActivity_.intent(AccuseAllinfoActivity.this).taskStutas(taskContractInfoAdapter.getData().get(position).getTaskstatus()).taskid(taskContractInfoAdapter.getData().get(position).getId()).start();


                TaskCycleListActivity_.intent(AccuseAllinfoActivity.this).status("0").currenttasknumber(position + 1 + "").conid(getIntent().getStringExtra("id")).start();
//
            }
        });
    }

    String id = "";
    List<DicDataBean> basicslist_quality = new ArrayList<>();
    List<DetectionSchemeInfoBean.LeftcateogoryBean> mTrue = new ArrayList<>();
    List<DetectionSchemeInfoBean.LeftcateogoryBean> mFalse = new ArrayList<>();
    Boolean isvi = true;
    Boolean isvi_acc = true;
    ImageView iv_look, iv_accuse;
    RecyclerView rv_list_acc;
    LinearLayout ll_acc, ll_isShow;


    public void initViews(View view) {
        ll_isShow = view.findViewById(R.id.ll_isShow);
        tv_task_person1 = view.findViewById(R.id.tv_task_person1);
        iv_accuse = view.findViewById(R.id.iv_accuse);
        ll_acc = view.findViewById(R.id.ll_acc);
        ll_acc.setVisibility(View.VISIBLE);
        rv_list_acc = view.findViewById(R.id.rv_list_acc);
        iv_look = view.findViewById(R.id.iv_look);
        //获取地图控件引用
        mMapView = view.findViewById(R.id.bmapView);
        task_info_name = view.findViewById(R.id.task_info_name);
        tv_task_time = view.findViewById(R.id.tv_task_time);
        tv_task_phone = view.findViewById(R.id.tv_task_phone);
        tv_task_samplemodename = view.findViewById(R.id.tv_task_samplemodename);
        tv_task_isretentionname = view.findViewById(R.id.tv_task_isretentionname);
        tv_task_typesname = view.findViewById(R.id.tv_task_typesname);
        tv_schnames = view.findViewById(R.id.tv_schnames);
        tv_introduction = view.findViewById(R.id.tv_introduction);
        ll_map = view.findViewById(R.id.ll_map);
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

        /**
         * 质控详情
         */
        rv_list_acc.setLayoutManager(new LinearLayoutManager(this));
        rv_list_acc.setAdapter(accuseSubmitAdapter);

        ll_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isvi_acc) {
                    isvi_acc = true;
                    iv_accuse.setImageResource(R.drawable.fold_right);
                    rv_list_acc.setVisibility(View.GONE);
                } else {
                    isvi_acc = false;
                    iv_accuse.setImageResource(R.drawable.fold_down);
                    rv_list_acc.setVisibility(View.VISIBLE);
                }

            }
        });
        accuseSubmitAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                ViseLog.d("" + position);
                if (accuseSubmitAdapter.getData().get(position).getIshow()) {
                    accuseSubmitAdapter.getData().get(position).setIshow(false);
                    accuseSubmitAdapter.notifyDataSetChanged();

                } else {
                    accuseSubmitAdapter.getData().get(position).setIshow(true);
                    accuseSubmitAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    public void initData(String schemeid) {
        ShowDialogtitle("加载中...", this);
        String data = "{schemeid:\"" + schemeid + "\"}";
        taskContractInfoPresenter.GetDetectionSchemeInfo(data);
        String datas1 = "{id:\"\",pagenumber:\"\",numbers:\"\",conid:\"" + getIntent().getStringExtra("id") + "\"} ";
        taskContractInfoPresenter.GetTasksInfo(datas1);
        String dataqu = "{cqcid:\"\",pcid:\"\",schemeid:\"" + schemeid + "\",pointsid:\"\",contractstatus:\"\"} ";
        accusePresenter.Getqualitycontrol(dataqu);

    }

    /**
     * 初始化地图
     *
     * @param savedInstanceState
     */
    public void initMap(Bundle savedInstanceState) {

        mBaiduMap = mMapView.getMap();

    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void OnTasksListInfo(List<TasksListInfoBean> tasksListInfoBeans) {
//        taskContractInfoAdapter.setNewData(tasksListInfoBeans);
    }

    @Override
    public void OnTasksInfoDetail(List<TasksInfoDetailBean> tasksInfoDetailBeans) {

    }

    List<AccusePointsBean> accusePointsBeanList = new ArrayList<>();
    int tasknum = 0;

    @Override
    public void OnGetDetectionSchemeInfo(List<DetectionSchemeInfoBean> list) {

        DissDialog();
        String jj = "", w = "";

        try {

            if (list != null && list.size() > 0) {
                tasknum = Integer.parseInt(list.get(0).getTaskNumber());
                List<TasksListInfoBean> tasksListInfoBeanList = new ArrayList<>();
                for (int i = 0; i < tasknum; i++) {
                    TasksListInfoBean tasksListInfoBean = new TasksListInfoBean();
                    tasksListInfoBean.setSubject("第" + (i + 1) + "周期");
                    tasksListInfoBeanList.add(tasksListInfoBean);
                }
                taskContractInfoAdapter.setNewData(tasksListInfoBeanList);

                tv_task_phone.setText(list.get(0).getTelephone());
                task_info_name.setText(list.get(0).getProjectname());
                tv_task_time.setText(list.get(0).getCustomername());
                tv_task_samplemodename.setText(list.get(0).getSamplemodename());
                tv_task_isretentionname.setText(list.get(0).getIsretentionname());
                tv_task_typesname.setText(list.get(0).getTypesname());
                tv_schnames.setText(list.get(0).getSchemeName());
                tv_introduction.setText(list.get(0).getIntroduction());
                tv_task_person1.setText(list.get(0).getContacts());
                if (list.get(0).getLeftcateogory().size() > 0 && list.get(0).getLeftcateogory() != null) {
                    mTrue = new ArrayList<>();
                    mFalse = new ArrayList<>();
                    mTrue.addAll(list.get(0).getLeftcateogory());

                    for (int i = 0; i < list.get(0).getLeftcateogory().size(); i++) {
                        if (list.get(0).getLeftcateogory().get(i).getPoints() != null && list.get(0).getLeftcateogory().get(i).getPoints().size() > 0) {
                            ll_map.setVisibility(View.VISIBLE);

                            try {

                                for (int j = 0; j < list.get(0).getLeftcateogory().get(i).getPoints().size(); j++) {
                                    /**
                                     * 添加点位信息
                                     */
                                    jj = list.get(0).getLeftcateogory().get(i).getPoints().get(j).getLongitude();
                                    w = list.get(0).getLeftcateogory().get(i).getPoints().get(j).getLatitude();

                                    if (jj.length() > 0 && w.length() > 0) {
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
        } catch (Exception e) {

        }


    }

    @Override
    public void OnAddDetectionSchemeAudit(ResultBean resultBean) {
    }

    @Override
    public void onData(List<DicDataBean> dicDataBeans) {

        basicslist_quality.clear();
        basicslist_quality.addAll(dicDataBeans);
    }

    @Override
    public void OnGetsampleInformation(List<Task_SapmleBean> dicDataBeans) {

    }

    @Override
    public void OnAllPerson(List<AllUserInfo> userInfos) {


    }

    @Override
    public void OnAllDevice(List<EquipmentDataBean> equipmentDataBeans) {

    }

    @Override
    public void Value_numbers(Editable editable, int position) {
        SubmitPointBean submitPointBean = pointZkAdapter.getData().get(position);
        if (editable.toString() != null) {
            submitPointBean.setNumbers(editable.toString());
        } else {
            submitPointBean.setNumbers("");
        }
    }

    List<GetqualitycontrolBean> qualits = new ArrayList<>();

    @Override
    public void Getqualitycontrol(List<GetqualitycontrolBean> getqualitycontrolBeans) {


        List<GetqualitycontrolBean> getqualitycontrolBeanArrayList = new ArrayList<>();
        for (int i = 0; i < getqualitycontrolBeans.size(); i++) {
            GetqualitycontrolBean queBean = new GetqualitycontrolBean();
            queBean.setPointsaddress(getqualitycontrolBeans.get(i).getPointsaddress());
            queBean.setIshow(false);
            queBean.setQualitycontrolname(getqualitycontrolBeans.get(i).getQualitycontrolname());
            queBean.setFactors(getqualitycontrolBeans.get(i).getFactors());
            getqualitycontrolBeanArrayList.add(queBean);
        }
        accuseSubmitAdapter.setNewData(getqualitycontrolBeanArrayList);

    }


    @Override
    public void Addqualitycontrol(ResultBean resultBean) {
        DissDialog();
        if (resultBean.getResult().equals("1")) {
            EventBus.getDefault().post(new ReshEvent("更新"));
            msg("成功");
            finish();
        } else {
            msg(resultBean.getErrormessage());
        }

    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }

    @Override
    public void SubmitVoice(ResultBean resultBean) {

    }
}
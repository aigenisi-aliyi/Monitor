package com.monitor.accuse;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.allen.library.SuperTextView;
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
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.TasksListInfoBean;
import com.monitor.changtian.adapter.AccuseDicDataAdapter;
import com.monitor.changtian.adapter.AccuseSubmitAdapter;
import com.monitor.changtian.adapter.AccuseinfoAdapter;
import com.monitor.changtian.adapter.PointInfoAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.AccusePointsBean;
import com.monitor.changtian.bean.AccuseShowBean;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.CloseEvent;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.EventBus.AccuseBeanEvent;
import com.monitor.changtian.bean.GetqualitycontrolBean;
import com.monitor.changtian.bean.PotinsBean;
import com.monitor.changtian.bean.ReshEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.Task_SapmleBean;
import com.monitor.changtian.bean.TasksInfoDetailBean;
import com.monitor.changtian.presenter.AccusePresenter;
import com.monitor.changtian.presenter.QueryBasicsPresenter;
import com.monitor.changtian.presenter.TaskContractInfoPresenter;
import com.monitor.changtian.view.AccuseView;
import com.monitor.changtian.view.QueryBasicsView;
import com.monitor.changtian.view.TaskContractInfoView;
import com.monitor.taskallocation.adapter.TaskCorrelationinfoOneAdapter;
import com.vise.log.ViseLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.Latitude_name;
import static com.monitor.changtian.constant.PublicConstant.latitude_value;
import static com.monitor.changtian.constant.PublicConstant.longitude_value;

public class AccuseInfoActivity extends BaseActvity implements AccuseView, TaskContractInfoView, QueryBasicsView {

    QueryBasicsPresenter queryBasicsPresenter;
    public TextureMapView map;
    RecyclerView rv_list;
    TaskContractInfoPresenter taskContractInfoPresenter;
    TaskCorrelationinfoOneAdapter taskCorrelationinfoOneAdapter;
    private BaiduMap mBaiduMap;
    private TextureMapView mMapView = null;
    LinearLayout ll_map;
    TextView task_info_name, tv_task_phone, tv_introduction, tv_task_person1,
            tv_schnames, tv_task_time, tv_task_samplemodename, tv_task_isretentionname, tv_task_typesname;
    String schemeid = "";
    TextView tv_dev_person;
    RecyclerView rv_list_zhikong;
    AccuseinfoAdapter accuseinfoAdapter;

    AccusePresenter accusePresenter;
    View viewfoot;
    AccuseDicDataAdapter accuseDicDataAdapter;
//    PointInfoAdapter pointInfoAdapter;


    AccuseSubmitAdapter accuseSubmitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection_audit);
        initImageBackText("方案详情");
        //注册EvetnBus；
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        schemeid = getIntent().getStringExtra("schid");
        id = getIntent().getStringExtra("id");
        rv_list = findViewById(R.id.rv_list);
        accusePresenter = new AccusePresenter(this, this);
        queryBasicsPresenter = new QueryBasicsPresenter(this, this);
        taskContractInfoPresenter = new TaskContractInfoPresenter(this, this);
        initData(schemeid);
        accuseSubmitAdapter = new AccuseSubmitAdapter(R.layout.accuse_zhikong_item, AccuseInfoActivity.this);
        taskCorrelationinfoOneAdapter = new TaskCorrelationinfoOneAdapter(this, R.layout.taskcorrelationinfo_one);
        accuseDicDataAdapter = new AccuseDicDataAdapter(R.layout.nopoint_select_item);
        View view1 = AccuseInfoActivity.this.getLayoutInflater().inflate(R.layout.taskcorrelationinfo_head, (ViewGroup) rv_list.getParent(), false);
        taskCorrelationinfoOneAdapter.addHeaderView(view1);
        accuseinfoAdapter = new AccuseinfoAdapter(R.layout.nopoint_select_item);
        View view_foot = AccuseInfoActivity.this.getLayoutInflater().inflate(R.layout.accuseinfo_foot, (ViewGroup) rv_list.getParent(), false);
        taskCorrelationinfoOneAdapter.addFooterView(view_foot);
        viewfoot = view_foot;
        init_foot(viewfoot);
        rv_list.setLayoutManager(new LinearLayoutManager(AccuseInfoActivity.this));
        rv_list.setAdapter(taskCorrelationinfoOneAdapter);
        initViews(view1);
        initMap();
    }

    String id = "";
    String loginId = MyApplication.getInstance().getUser();
    String controls = "";

    ImageView tv_add_Accuse;
    SuperTextView stv_true;
    TextView tv_info;


    public void init_foot(View view_foot) {
        tv_info = view_foot.findViewById(R.id.tv_info);
        stv_true = view_foot.findViewById(R.id.stv_true);
        tv_add_Accuse = view_foot.findViewById(R.id.tv_add_Accuse);
        /**
         * 跳转到添加质控详情界面
         */
        tv_add_Accuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accusePointsBeanList.size() > 0) {

                    ArrayList<String> strnum = new ArrayList<>();

                    for (int i = 0; i < accuseSubmitAdapter.getData().size(); i++) {
                        strnum.add(accuseSubmitAdapter.getData().get(i).getDetails().get(0).getSamplenumber());
                    }

                    ViseLog.d("ll:"+maxdaynumberss);

                    AddAccuseActivity_.intent(AccuseInfoActivity.this).maxdaynumbers(maxdaynumberss).numBerList(strnum).smid(smid).accusePointsBeanList(accusePointsBeanList).start();
                } else {
                    msg("当前点位都不可质控");
                }
            }
        });
        rv_list_zhikong = view_foot.findViewById(R.id.rv_list);
        rv_list_zhikong.setLayoutManager(new LinearLayoutManager(AccuseInfoActivity.this));
        rv_list_zhikong.setAdapter(accuseSubmitAdapter);
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
        stv_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (accuseSubmitAdapter.getData().size() > 0) {
                    AgainDialog("确认提交质控信息吗?");
                } else {
                    msg("请先添加质控信息!");
                }
            }
        });
    }

    List<DicDataBean> basicslist_quality = new ArrayList<>();

    /**
     * 确定
     */
    public void AgainTrue() {
        ViseLog.d("lslsl:" + JSON.toJSONString(accuseSubmitAdapter.getData()));
        ShowDialogtitle("请稍等...", this);
        controls = JSON.toJSONString(accuseSubmitAdapter.getData());
        String data = "{pcid:\"" + id + "\",loginId:\"" + loginId + "\",controls:" + controls + "}";
        accusePresenter.Addqualitycontrol(data);


    }

    /**
     * 保存之前的数据
     */
    List<AccuseShowBean> newLIST = new ArrayList<>();
    List<PotinsBean.SubmitPointBean> submitPointBeanList = new ArrayList<>();


    /**
     * 接受回传的值
     */
    @Subscribe
    public void AccuseEvent(AccuseBeanEvent event) {
        if (event != null) {

            /**
             * 提交数据
             */
            submitPointBeanList.addAll(event.getPointBeanList());
            newLIST.addAll(event.getAccuseShowBeans());
            ViseLog.d("lslsl:" + JSON.toJSONString(newLIST));
            accuseSubmitAdapter.setNewData(newLIST);
            tv_info.setVisibility(View.VISIBLE);

            float pressent = (float) newLIST.size() / count * 100;//i 和 mNumber都是int型数

            DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            String p = decimalFormat.format(pressent);//format 返回的是字符串


            ViseLog.d("count:" + count + "---" + p + "----" + pressent);
            tv_info.setText("当前质控占比为:" + p + "%");
        }

    }


    List<DetectionSchemeInfoBean.LeftcateogoryBean> mTrue = new ArrayList<>();
    List<DetectionSchemeInfoBean.LeftcateogoryBean> mFalse = new ArrayList<>();

    Boolean isvi = true;
    ImageView iv_look;
    LinearLayout ll_isShow;

    public void initViews(View view) {

        //获取地图控件引用
        mMapView = view.findViewById(R.id.bmapView);

        ll_isShow = view.findViewById(R.id.ll_isShow);
        tv_task_person1 = view.findViewById(R.id.tv_task_person1);
        iv_look = view.findViewById(R.id.iv_look);
//        map = view.findViewById(R.id.map);
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
        if (isvi) {
            iv_look.setImageResource(R.drawable.fold_right);
            taskCorrelationinfoOneAdapter.setNewData(mFalse);
        } else {
            iv_look.setImageResource(R.drawable.fold_down);
            taskCorrelationinfoOneAdapter.setNewData(mTrue);
        }
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

    public void initData(String schemeid) {
        ShowDialogtitle("加载中...", this);
        String data = "{schemeid:\"" + schemeid + "\"}";
        taskContractInfoPresenter.GetDetectionSchemeInfo(data);

    }

    /**
     * 初始化地图
     *
     */
    public void initMap() {
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
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Override
    public void OnTasksListInfo(List<TasksListInfoBean> tasksListInfoBeans) {

    }

    @Override
    public void OnTasksInfoDetail(List<TasksInfoDetailBean> tasksInfoDetailBeans) {

    }

    ArrayList<AccusePointsBean> accusePointsBeanList = new ArrayList<>();

    String smid = "";
    int count = 0;

    String maxdaynumberss = "";

    @Override
    public void OnGetDetectionSchemeInfo(List<DetectionSchemeInfoBean> list) {

        DissDialog();
        String jj = "", w = "";
        if (list != null && list.size() > 0) {
            tv_task_phone.setText(list.get(0).getTelephone());
            task_info_name.setText(list.get(0).getProjectname());
            tv_task_time.setText(list.get(0).getCustomername());
            tv_task_samplemodename.setText(list.get(0).getSamplemodename());
            tv_task_isretentionname.setText(list.get(0).getIsretentionname());
            tv_task_typesname.setText(list.get(0).getTypesname());
            tv_schnames.setText(list.get(0).getSchemeName());
            tv_task_person1.setText(list.get(0).getContacts());
            tv_introduction.setText(list.get(0).getIntroduction());

            smid = list.get(0).getSchemeid();
            maxdaynumberss = list.get(0).getMaxdaynumbe();
            accusePointsBeanList = new ArrayList<>();
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
                                count += list.get(0).getLeftcateogory().get(i).getPoints().get(j).getSamplecount();
                                jj = list.get(0).getLeftcateogory().get(i).getPoints().get(j).getLongitude();
                                w = list.get(0).getLeftcateogory().get(i).getPoints().get(j).getLatitude();


                                if (list.get(0).getLeftcateogory().get(i).getCategoryid().equals("48") ||
                                        list.get(0).getLeftcateogory().get(i).getCategoryid().equals("52") ||
                                        list.get(0).getLeftcateogory().get(i).getCategoryid().equals("53")) {

                                } else {
                                    AccusePointsBean accusePointsBean = new AccusePointsBean();
                                    List<AccusePointsBean.FactorsBean> factorsBeanList = new ArrayList<>();

                                    accusePointsBean.setTypeid(list.get(0).getLeftcateogory().get(i).getCategoryid());
                                    accusePointsBean.setId(list.get(0).getLeftcateogory().get(i).getPoints().get(j).getPointsid());
                                    accusePointsBean.setName(list.get(0).getLeftcateogory().get(i).getPoints().get(j).getAddress());
                                    accusePointsBean.setDaysNumber(list.get(0).getLeftcateogory().get(i).getPoints().get(j).getDaysNumber()); //天数
                                    accusePointsBean.setFrequency(list.get(0).getLeftcateogory().get(i).getPoints().get(j).getFrequency());//频次
                                    for (int x = 0; x < list.get(0).getLeftcateogory().get(i).getPoints().get(j).getFactors().size(); x++) {
                                        AccusePointsBean.FactorsBean factorsBean = new AccusePointsBean.FactorsBean();
                                        factorsBean.setFactorsid(list.get(0).getLeftcateogory().get(i).getPoints().get(j).getFactors().get(x).getFid());
                                        factorsBean.setFactorsName(list.get(0).getLeftcateogory().get(i).getPoints().get(j).getFactors().get(x).getFactorname());
                                        factorsBeanList.add(factorsBean);
                                    }
                                    accusePointsBean.setFactorsBeans(factorsBeanList);
                                    accusePointsBeanList.add(accusePointsBean);
                                }


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

//                ViseLog.d("sss:" + JSON.toJSONString(accusePointsBeanList));
            }
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
    public void Getqualitycontrol(List<GetqualitycontrolBean> getqualitycontrolBeans) {

    }

    @Override
    public void Addqualitycontrol(ResultBean resultBean) {
        DissDialog();
        if (resultBean.getResult().equals("1")) {
            EventBus.getDefault().post(new ReshEvent("更新"));
            EventBus.getDefault().post(new CloseEvent("Detection"));
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


}

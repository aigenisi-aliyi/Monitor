package com.monitor.detectionschemeaudit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;

import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.TasksListInfoBean;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.CloseEvent;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.ReshEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.Task_SapmleBean;
import com.monitor.changtian.bean.TasksInfoDetailBean;
import com.monitor.changtian.presenter.QueryBasicsPresenter;
import com.monitor.changtian.presenter.TaskContractInfoPresenter;
import com.monitor.changtian.view.QueryBasicsView;
import com.monitor.changtian.view.TaskContractInfoView;
import com.monitor.taskallocation.adapter.TaskCorrelationinfoOneAdapter;
import com.vise.log.ViseLog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.Latitude_name;
import static com.monitor.changtian.constant.PublicConstant.latitude_value;
import static com.monitor.changtian.constant.PublicConstant.longitude_value;

public class DetectionAuditActivity extends BaseActvity implements TaskContractInfoView, QueryBasicsView {

    private BaiduMap mBaiduMap;
    private TextureMapView mMapView = null;
    QueryBasicsPresenter queryBasicsPresenter;

    RecyclerView rv_list;
    TaskContractInfoPresenter taskContractInfoPresenter;
    TaskCorrelationinfoOneAdapter taskCorrelationinfoOneAdapter;
    private AMap aMap;
    LinearLayout ll_map, ll_audit, ll_reamk;
    TextView task_info_name, tv_auditOpinion, tv_task_phone, tv_task_person1, tv_introduction, tv_dev_person, tv_schnames, tv_task_time, tv_task_samplemodename, tv_task_isretentionname, tv_task_typesname;
    SuperTextView stv_false, stv_true;
    EditText et_remark;
    List<AllUserInfo> userInfoList = new ArrayList<>();
    String schemeid = "";
    String types = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection_audit);
        initImageBackText("方案详情");

        schemeid = getIntent().getStringExtra("schid");
        types = getIntent().getStringExtra("type");
        rv_list = findViewById(R.id.rv_list);
        queryBasicsPresenter = new QueryBasicsPresenter(this, this);
        taskContractInfoPresenter = new TaskContractInfoPresenter(this, this);
        initData(schemeid);
        taskCorrelationinfoOneAdapter = new TaskCorrelationinfoOneAdapter(this, R.layout.taskcorrelationinfo_one);
        View view1 = DetectionAuditActivity.this.getLayoutInflater().inflate(R.layout.taskcorrelationinfo_head, (ViewGroup) rv_list.getParent(), false);
        taskCorrelationinfoOneAdapter.addHeaderView(view1);
        View view_foot = DetectionAuditActivity.this.getLayoutInflater().inflate(R.layout.detection_foot, (ViewGroup) rv_list.getParent(), false);
        taskCorrelationinfoOneAdapter.addFooterView(view_foot);
        initFootView(view_foot);
        rv_list.setLayoutManager(new LinearLayoutManager(DetectionAuditActivity.this));
        rv_list.setAdapter(taskCorrelationinfoOneAdapter);
        initViews(view1);
        initMap(savedInstanceState);
    }

    String str_per = "", remakS = "";

    public void initFootView(View view_foot) {

        ll_audit = view_foot.findViewById(R.id.ll_audit);
        ll_reamk = view_foot.findViewById(R.id.ll_reamk);
        tv_auditOpinion = view_foot.findViewById(R.id.tv_auditOpinion);
        et_remark = view_foot.findViewById(R.id.et_remark);
        if (types.equals("0")) {
            ll_reamk.setVisibility(View.GONE);
        }
        if (types.equals("1")) {
            ll_audit.setVisibility(View.GONE);
        }

        /**
         * 负责人选择
         */
        tv_dev_person = view_foot.findViewById(R.id.tv_dev_person);
        stv_false = view_foot.findViewById(R.id.stv_false);
        stv_true = view_foot.findViewById(R.id.stv_true);
//        tv_dev_person.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                hintKbTwo();
//                OptionsPickerView pvOptions = new OptionsPickerView.Builder(DetectionAuditActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
//                    @Override
//                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                        //返回的分别是三个级别的选中位置
//                        String content = userInfoList.get(options1).getPickerViewText();
//                        str_per = userInfoList.get(options1).getId() + "";
//                        tv_dev_person.setText(content);
//                    }
//                })
//                        .setTitleText("选择项目负责人")
//                        .setDividerColor(Color.BLACK)
//                        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
//                        .setContentTextSize(20)
//                        .setOutSideCancelable(true)// default is true
//                        .build();
//                pvOptions.setPicker(userInfoList);//一级选择器
//                pvOptions.show();
//            }
//        });
        /**
         * 不通过
         */
        stv_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_remark.getText().toString().trim().length() == 0) {
                    msg("审核意见不能为空!");
                    return;
                } else {
                    remakS = et_remark.getText().toString().trim();
                }
                Dialogs("0", remakS, "");
            }
        });
        /**
         * 通过
         */
        stv_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_remark.getText().toString().trim().length() > 0) {
                    remakS = et_remark.getText().toString().trim();
                } else {
                    remakS = "";
                }
//                if (tv_dev_person.getText().toString().trim().length() == 0) {
//                    msg("请选择项目负责人!");
//                    return;
//                }
                Dialogs("1", remakS, str_per);
            }
        });


    }

    /**
     * 提交
     *
     * @param type
     * @param manager
     */
    public void Dialogs(final String type, final String remak, final String manager) {

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
                    ShowDialogtitle("请稍等...", DetectionAuditActivity.this);
                    String data = "{schemeid:\"" + schemeid + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\",AuditOpinion:\"" + remak + "\",Ispass:\"0\",manager:\"" + manager + "\"}";
                    taskContractInfoPresenter.AddDetectionSchemeAudit(data);
                } else if (type.equals("1")) {
                    ShowDialogtitle("请稍等...", DetectionAuditActivity.this);
                    String data = "{schemeid:\"" + schemeid + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\",AuditOpinion:\"" + remak + "\",Ispass:\"1\",manager:\"" + manager + "\"}";
                    taskContractInfoPresenter.AddDetectionSchemeAudit(data);
                }

            }
        });
        builder.show();

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
        iv_look = view.findViewById(R.id.iv_look);
//        map = view.findViewById(R.id.map);
        task_info_name = view.findViewById(R.id.task_info_name);
        tv_task_time = view.findViewById(R.id.tv_task_time);
        tv_task_phone = view.findViewById(R.id.tv_task_phone);
        tv_task_person1 = view.findViewById(R.id.tv_task_person1);
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

        ShowDialogtitle("加载中...", DetectionAuditActivity.this);
        String data = "{schemeid:\"" + schemeid + "\"}";
        taskContractInfoPresenter.GetDetectionSchemeInfo(data);
        if (types.equals("0")) {
            String Person_data = "{loginName:\"\",querystring:\"\",rolename:\"\"}";
            queryBasicsPresenter.GetAllUserInfo(Person_data);
        }

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

    }

    @Override
    public void OnTasksInfoDetail(List<TasksInfoDetailBean> tasksInfoDetailBeans) {

    }

    @Override
    public void OnGetDetectionSchemeInfo(List<DetectionSchemeInfoBean> list) {
        DissDialog();
        String jj = "", w = "";
        if (list != null && list.size() > 0) {

            tv_auditOpinion.setText(list.get(0).getAuditOpinion());
            tv_task_phone.setText(list.get(0).getContactNumber());
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


                    if (list.get(0).getLeftcateogory().get(i).getPoints() != null && list.get(0).getLeftcateogory().get(i).getPoints().size() > 0) {

                        ll_map.setVisibility(View.VISIBLE);

                        try {

                            for (int j = 0; j < list.get(0).getLeftcateogory().get(i).getPoints().size(); j++) {

                                jj = list.get(0).getLeftcateogory().get(i).getPoints().get(j).getLongitude();
                                w = list.get(0).getLeftcateogory().get(i).getPoints().get(j).getLatitude();

                                if (jj.length() > 0 && w.length() > 0) {
                                    com.baidu.mapapi.model.LatLng point = new com.baidu.mapapi.model.LatLng(Double.parseDouble(w)
                                            , Double.parseDouble(jj));
                                    BitmapDescriptor bitmap = com.baidu.mapapi.map.BitmapDescriptorFactory
                                            .fromResource(R.drawable.ic_delete_photo);
                                    OverlayOptions option = new com.baidu.mapapi.map.MarkerOptions()
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
                                    com.baidu.mapapi.model.LatLng cenpt = new com.baidu.mapapi.model.LatLng(latitude_value, longitude_value);
                                    BitmapDescriptor bitmap = com.baidu.mapapi.map.BitmapDescriptorFactory
                                            .fromResource(R.drawable.ic_delete_photo);
                                    OverlayOptions option = new com.baidu.mapapi.map.MarkerOptions()
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
                            com.baidu.mapapi.model.LatLng cenpt = new com.baidu.mapapi.model.LatLng(latitude_value, longitude_value);
                            BitmapDescriptor bitmap = com.baidu.mapapi.map.BitmapDescriptorFactory
                                    .fromResource(R.drawable.ic_delete_photo);
                            OverlayOptions option = new com.baidu.mapapi.map.MarkerOptions()
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


    }

    @Override
    public void OnAddDetectionSchemeAudit(ResultBean resultBean) {


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
    public void onData(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnGetsampleInformation(List<Task_SapmleBean> dicDataBeans) {

    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }

    @Override
    public void OnAllPerson(List<AllUserInfo> userInfos) {

        userInfoList.clear();
        userInfoList.addAll(userInfos);
    }

    @Override
    public void OnAllDevice(List<EquipmentDataBean> equipmentDataBeans) {

    }
}

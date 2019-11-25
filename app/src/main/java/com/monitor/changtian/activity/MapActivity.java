package com.monitor.changtian.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TaskBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.TaskPresenter;
import com.vise.log.ViseLog;

import java.util.ArrayList;
import java.util.List;


public class MapActivity extends BaseActvity implements SubmitView<List<TaskBean>>, AMap.OnMarkerClickListener {

    public TextureMapView map;
    private AMap aMap;

    TaskPresenter taskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initImageBackText("地图");
        initMap(savedInstanceState);
        taskPresenter = new TaskPresenter(this, this);
        initData();

        //设置 Marker 点的 点击事件。
        aMap.setOnMarkerClickListener(this);
    }

    /**
     * 加载数据
     */
    public void initData() {
        String data = "{loginName:\"admin\"}";
        taskPresenter.GetPosTask(data);
    }

    /**
     * 初始化地图
     *
     * @param savedInstanceState
     */
    public void initMap(Bundle savedInstanceState) {

        map = (TextureMapView) findViewById(R.id.map);
        map.onCreate(savedInstanceState);
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = map.getMap();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    Marker marker;

    String subject = "";

    String time = "";
    String content = "";
    String subname = "";
    String source = "";
    String ClientTel = "";
    List<TaskBean> beans = new ArrayList<>();

    @Override
    public void onData(List<TaskBean> taskBeans) {

        String j = "", w = "";
        if (taskBeans != null) {
            beans.clear();
            beans.addAll(taskBeans);
            for (int i = 0; i < taskBeans.size(); i++) {

                String[] standard = taskBeans.get(i).getCoordinate().split(",");
                if (standard[0].toString().length() != 0 && standard[1].toString().length() != 0) {
                    j = standard[0].toString();
                    w = standard[1].toString();
                }

                subject = taskBeans.get(i).getSubject();
                time = taskBeans.get(i).getCtime();
                content = taskBeans.get(i).getContent();
                subname = taskBeans.get(i).getSubname();
                source = taskBeans.get(i).getSource();
                ClientTel = taskBeans.get(i).getClientTel();
                ViseLog.d("1111");
                LatLng latLng = new LatLng(Double.parseDouble(w), Double.parseDouble(j));
                aMap.addMarker(new MarkerOptions().position(latLng)
                        .title("" + i)
                        .snippet("王武")
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_delete_photo)));
            }

            //创建执行人员位置
            //设置中心点和缩放比例
            aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(Double.parseDouble(w), Double.parseDouble(j))));
            aMap.moveCamera(CameraUpdateFactory.zoomTo(14));

        }

    }

    @Override
    public void onMessage(ResultBean t) {

    }

    @Override
    public void OnError(String s) {

    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        if (marker.isInfoWindowShown()) {
            marker.setInfoWindowEnable(false);//这个是隐藏infowindow窗口的方法
        } else {
            marker.setInfoWindowEnable(true);//这个是隐藏infowindow窗口的方法
        }
        final MarkerOptions moo = marker.getOptions();
        int postion = Integer.parseInt(moo.getTitle());
        Event(moo, postion);
        return false;
    }


    private void Event(MarkerOptions mo, final int postion) {
        final Marker markern = aMap.addMarker(mo);
        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            View infoWindow = null;

            @Override
            public View getInfoWindow(Marker marker) {

                if (infoWindow == null) {
                    infoWindow = LayoutInflater.from(MapActivity.this).inflate(R.layout.map_realtime_layout, null);
                }
                render(markern, infoWindow);

                return infoWindow;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }

            public void render(Marker marker, View view) {

                subject = beans.get(postion).getSubject();
                time = beans.get(postion).getCtime();
                content = beans.get(postion).getContent();
                subname = beans.get(postion).getSubname();
                source = beans.get(postion).getSource();
                ClientTel = beans.get(postion).getClientTel();

                TextView tv_map_pro_name = view.findViewById(R.id.tv_map_pro_name);
                tv_map_pro_name.setText(subject);


                TextView tv_map_pro_time = view.findViewById(R.id.tv_map_pro_time);
                tv_map_pro_time.setText(time);

                TextView tv_map_content = view.findViewById(R.id.tv_map_content);
                tv_map_content.setText(content);

                TextView tv_map_pro = view.findViewById(R.id.tv_map_pro);
                tv_map_pro.setText(subname);
                TextView tv_map_operation = view.findViewById(R.id.tv_map_operation);
                tv_map_operation.setText(source);

                TextView tv_phone = view.findViewById(R.id.tv_phone);
                tv_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ClientTel));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                marker.showInfoWindow();
                marker.setFlat(true);
            }
        });
    }
}

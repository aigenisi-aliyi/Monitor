package com.monitor.changtian.activity.task.ZhongHeTai;

import android.text.Editable;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.Add_Sample_qixiang_valueAdapter;
import com.monitor.changtian.adapter.ConsumableAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.FieldsamplingDetailBean;
import com.monitor.changtian.bean.FieldsamplingDetailListBean;
import com.monitor.changtian.bean.FieldsamplingInfo;
import com.monitor.changtian.bean.PreDataBean;
import com.monitor.changtian.bean.PreInfoDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SampleInfoByPointInfoBean;
import com.monitor.changtian.bean.SampleTypeBean;
import com.monitor.changtian.bean.SchemeFidsBean;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.monitor.changtian.view.AddAccuseView;
import com.monitor.changtian.view.SampleCodeView;
import com.monitor.changtian.view.SampleTypeView;
import com.monitor.changtian.view.TaskinfoView;
import org.androidannotations.annotations.EActivity;
import java.util.List;

@EActivity(R.layout.addnoiseinfo)
public class AddNoiseInfoActivity extends BaseActvity implements AddAccuseView,  BaseActvity.Rightlistener, WeatherSearch.OnWeatherSearchListener, TaskinfoView, Add_Sample_qixiang_valueAdapter.OnEditValueChangedListener_qx {

    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int i) {

    }

    @Override
    public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i) {

    }

    @Override
    public void Value_qixiang(Editable editable, int position) {

    }

    @Override
    public void rightlistener() {

    }

    @Override
    public void Onbasicslist_quality(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnSampleInfoByPointInfo(SampleInfoByPointInfoBean sampleInfoByPointInfoBean) {

    }

    @Override
    public void OnGetPreData(PreDataBean preDataBean) {

    }

    @Override
    public void OnGetPreInfoData(List<PreInfoDataBean> preInfoDataBeans) {

    }

    @Override
    public void OnSchemeFidsData(List<SchemeFidsBean> schemeFidsBeans) {

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
    public void OnError(String s) {

    }

    @Override
    public void OnSoilhumidity(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnSoiltexture(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

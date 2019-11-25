package com.monitor.changtian.view;

import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.PreDataBean;
import com.monitor.changtian.bean.PreInfoDataBean;
import com.monitor.changtian.bean.SampleInfoByPointInfoBean;
import com.monitor.changtian.bean.SchemeFidsBean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/15.
 */

public interface AddAccuseView {

    void Onbasicslist_quality(List<DicDataBean> dicDataBeans);

    void OnSampleInfoByPointInfo(SampleInfoByPointInfoBean sampleInfoByPointInfoBean);

    void OnGetPreData(PreDataBean preDataBean);

    void OnGetPreInfoData(List<PreInfoDataBean> preInfoDataBeans);

    void OnSchemeFidsData(List<SchemeFidsBean> schemeFidsBeans);

    void OnError(String s);
}

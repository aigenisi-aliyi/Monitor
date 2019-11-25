package com.monitor.changtian.view;

import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SampleCategoryItemsDataBean;
import com.monitor.changtian.bean.SampleInfoDataBean;
import com.monitor.changtian.bean.SampleType;

import java.util.List;

/**
 * Created by ken on 2018/6/1.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface AddView {

    void onTypeData(List<SampleType> sampleType);

    void GetSampleInfoData(List<SampleInfoDataBean> sampleInfoDataBeans);

    void GetSampleCategoryItemsData(List<SampleCategoryItemsDataBean> sampleCategoryItemsDataBeans);

    void onMessage(ResultBean message);
}

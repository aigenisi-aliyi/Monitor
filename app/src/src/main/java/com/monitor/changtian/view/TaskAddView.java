package com.monitor.changtian.view;

import com.monitor.changtian.FactorsExperimenterDataBean;
import com.monitor.changtian.SchemeConsumablesBean;
import com.monitor.changtian.bean.CarDataListBean;
import com.monitor.changtian.bean.NoPointsTaskDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SamplingAssistDataBean;
import com.monitor.changtian.bean.SchemeDeviceBean;

import java.util.List;

/**
 * Created by ken on 2018/7/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface TaskAddView {
    /**
     * 返回点位信息
     *
     * @param noPointsTaskDataBeans
     */
    void OnNoPointsTaskData(List<NoPointsTaskDataBean> noPointsTaskDataBeans);

    /**
     * 返回采样人员和辅助人员
     */
    void OnSamplingAssistData(SamplingAssistDataBean samplingAssistDataBean);

    /**
     * 返回实验人员；
     */
    void OnFactorsExperimenterData(List<FactorsExperimenterDataBean> factorsExperimenterDataBeans);

    /**
     * 返回车辆信息
     */
    void OnCarDataList(List<CarDataListBean> carDataListBeans);

    /**
     * 返回设备信息
     */
    void OnSchemeDevice(List<SchemeDeviceBean> schemeDeviceBeans);

    /**
     * 返回耗材信息
     */

    void OnSchemeConsumables(List<SchemeConsumablesBean> schemeConsumablesBeans);


    /**
     * 提交任务
     */
    void onSubmit(ResultBean resultBean);


    void OnError(String s);
}

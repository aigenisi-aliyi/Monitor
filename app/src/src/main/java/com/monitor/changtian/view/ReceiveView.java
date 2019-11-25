package com.monitor.changtian.view;

import com.monitor.changtian.bean.CalibrateDevListBean;
import com.monitor.changtian.bean.EquipInOutStockDataBean;
import com.monitor.changtian.bean.GetConsumablesDataBean;
import com.monitor.changtian.bean.ResultBean;

import java.util.List;

/**
 * Created by ken on 2018/8/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface ReceiveView {
    /**
     * 查询领用设备
     */
    void OnQueryDevList(List<EquipInOutStockDataBean> equipInOutStockDataBeans);

    /**
     * 查询领用耗材
     */
    void OnQueryConList(List<EquipInOutStockDataBean> equipInOutStockDataBeans);

    /**
     * 查询相关耗材
     */
    void OnQuerConsumablesDataBean(List<GetConsumablesDataBean> consumablesDataBeans);

    /**
     * 提交领用
     */
    void OnReceiveSubmit(ResultBean resultBean);

    /**
     * 查询校准设备
     */
//    void OnReceiveCalibrateDev(List<CalibrateDevListBean> calibrateDevListBean);

    void OnError(String s);
}

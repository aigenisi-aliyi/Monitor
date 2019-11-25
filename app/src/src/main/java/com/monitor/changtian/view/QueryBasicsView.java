package com.monitor.changtian.view;

import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.ResultBean;

import java.util.List;

/**
 * Created by ken on 2018/7/12.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface QueryBasicsView {
    /**
     * 返回人员信息
     */
    void OnAllPerson(List<AllUserInfo> userInfos);
    /**
     * 返回设备信息
     */
    void OnAllDevice(List<EquipmentDataBean> equipmentDataBeans);

    void OnError(String s);

    void SubmitVoice(ResultBean resultBean);

}

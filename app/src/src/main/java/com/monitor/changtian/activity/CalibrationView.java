package com.monitor.changtian.activity;

import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.EquipStateBean;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.view.ReceiveView;

import java.util.List;

public interface CalibrationView extends ReceiveView {
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

    void BarCodeDevState(List<EquipStateBean> equipStateBeans);

}


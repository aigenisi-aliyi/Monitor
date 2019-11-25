package com.monitor.changtian.activity;

import com.monitor.changtian.bean.EquipTypeBean;

public interface ZBarActivityView {

    /**
     * 调用设备类型接口成功，根据二维码返回设备类型
     */
    public void onSuccess(EquipTypeBean bean,boolean isReture);
    /**
     * 调用设备类型接口失败
     */
    public void onFailed(String s);
}

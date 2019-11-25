package com.monitor.changtian.view;

import com.monitor.changtian.bean.GetqualitycontrolBean;
import com.monitor.changtian.bean.ResultBean;

import java.util.List;

/**
 * Created by ken on 2018/8/2.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface AccuseView {
    void Getqualitycontrol(List<GetqualitycontrolBean> getqualitycontrolBeans);

    void Addqualitycontrol(ResultBean resultBean);

    void OnError(String s);
}

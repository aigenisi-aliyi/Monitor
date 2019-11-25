package com.monitor.changtian.view;

import com.monitor.changtian.bean.GetfieldsamplingDetailListBean;
import com.monitor.changtian.bean.ResultBean;

import java.util.List;

/**
 * Created by ken on 2018/8/14.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface SampleProofreadView {
    /**
     * 返回列表
     */
    void OnList(List<GetfieldsamplingDetailListBean> getfieldsamplingDetailListBeans);

    void OnMessage(ResultBean resultBean);

    void OnError(String s);

}

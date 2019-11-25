package com.monitor.changtian.view;

import com.monitor.changtian.bean.DetectionSchemeBean;

import java.util.List;

/**
 * Created by ken on 2018/8/30.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface DetectionSchemeView {
    void OnDetectionSchemeList(List<DetectionSchemeBean> detectionSchemeBeans);

    void OnError(String s);
}

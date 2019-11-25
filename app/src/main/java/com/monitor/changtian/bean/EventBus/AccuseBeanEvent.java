package com.monitor.changtian.bean.EventBus;

import com.monitor.changtian.bean.AccuseShowBean;
import com.monitor.changtian.bean.PotinsBean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/16.
 */

public class AccuseBeanEvent {

    private List<PotinsBean.SubmitPointBean> pointBeanList;

    private List<AccuseShowBean> accuseShowBeans;


    public AccuseBeanEvent(List<PotinsBean.SubmitPointBean> pointBeanList, List<AccuseShowBean> accuseShowBeans) {
        this.pointBeanList = pointBeanList;
        this.accuseShowBeans = accuseShowBeans;
    }

    public List<AccuseShowBean> getAccuseShowBeans() {
        return accuseShowBeans;
    }

    public void setAccuseShowBeans(List<AccuseShowBean> accuseShowBeans) {
        this.accuseShowBeans = accuseShowBeans;
    }


    public List<PotinsBean.SubmitPointBean> getPointBeanList() {
        return pointBeanList;
    }

    public void setPointBeanList(List<PotinsBean.SubmitPointBean> pointBeanList) {
        this.pointBeanList = pointBeanList;
    }
}

package com.monitor.changtian.bean.EventBus;

import com.monitor.changtian.FactorsExperimenterDataBean;

import java.util.ArrayList;

/**
 * Created by ken on 2018/7/5.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ExperimentEvent {

    public ExperimentEvent(ArrayList<FactorsExperimenterDataBean> factorsExperimenterDataBeanArrayList) {
        this.factorsExperimenterDataBeanArrayList = factorsExperimenterDataBeanArrayList;
    }

    public ArrayList<FactorsExperimenterDataBean> factorsExperimenterDataBeanArrayList;

    public ArrayList<FactorsExperimenterDataBean> getFactorsExperimenterDataBeanArrayList() {
        return factorsExperimenterDataBeanArrayList;
    }

    public void setFactorsExperimenterDataBeanArrayList(ArrayList<FactorsExperimenterDataBean> factorsExperimenterDataBeanArrayList) {
        this.factorsExperimenterDataBeanArrayList = factorsExperimenterDataBeanArrayList;
    }
}

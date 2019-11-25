package com.monitor.changtian.view;

import com.monitor.changtian.bean.StatisticsMoneyQueryBean;
import com.monitor.changtian.bean.StatisticsQueryBean;

import java.util.List;

/**
 * Created by ken on 2018/6/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface FinanceStatisticsView {

    void OnStatisticsQueryBean(StatisticsQueryBean statisticsQueryBean);

    void StatisticsMoneyQueryBean(List<StatisticsMoneyQueryBean> statisticsMoneyQueryBeans);

    void onError(String s);
}

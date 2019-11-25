package com.monitor.changtian.view;

import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.FieldsamplingDetailBean;
import com.monitor.changtian.bean.FieldsamplingDetailListBean;
import com.monitor.changtian.bean.FieldsamplingInfo;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TasksInfomationBean;

import java.util.List;

/**
 * Created by ken on 2018/6/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface TaskinfoView {


    void OnGetTasksInfomation(List<TasksInfomationBean> tasksInfomationBeans);

    void OnPack(List<DicDataBean> dicDataBeans);

    void OnUnit(List<DicDataBean> dicDataBeans);

    void OnStyle(List<DicDataBean> dicDataBeans);

    void onMessage(ResultBean message);

    void OnFieldsamplingInfo(List<FieldsamplingInfo> fieldsamplingInfos);

    void OnEndFieldsampling(ResultBean message);

    void OnGetfieldsamplingDetailList(List<FieldsamplingDetailListBean> fieldsamplingDetailListBeans);

    void OnGetfieldsamplingDetail(List<FieldsamplingDetailBean> fieldsamplingDetailBeans);


    void OnError(String s);


    /**
     * 返回土壤湿度
     */

    void OnSoilhumidity(List<DicDataBean> dicDataBeans);

    /**
     * 返回土壤之地
     */

    void OnSoiltexture(List<DicDataBean> dicDataBeans);

}


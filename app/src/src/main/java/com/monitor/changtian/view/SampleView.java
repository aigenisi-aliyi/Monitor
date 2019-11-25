package com.monitor.changtian.view;

import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.GetsamplemanageInfoBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.RoomListBean;
import com.monitor.changtian.bean.SampleInfoByAssignmentRecordBean;
import com.monitor.changtian.bean.SampleStatusBean;

import java.util.List;

/**
 * Created by ken on 2018/8/10.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface SampleView {
    void OnGetsamplemanageInfo(List<GetsamplemanageInfoBean> getsamplemanageInfoBeans);

    void OnMessage(ResultBean resultBean);

    /**
     * 返回人员信息
     */
    void OnAllPerson(List<AllUserInfo> userInfos);

    void OnSampleStatus(SampleStatusBean sampleStatusBeans);

    void OnRoomList(List<RoomListBean> roomListBeans);

    void OnGetSampleInfoByAssignmentRecord(SampleInfoByAssignmentRecordBean sampleInfoByAssignmentRecordBean);

    void OnError(String s);
}

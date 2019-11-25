package com.monitor.changtian.bean.EventBus;

import com.monitor.changtian.bean.TaskInfoAllBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/9/17.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * へ　　　　／|
 * 　　/＼7　　∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　 │　　　　　ヽ　　 /　　〉
 * 　Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */

public class PhotoListEvent implements Serializable{

    public PhotoListEvent(List<TaskInfoAllBean.FujiansBean> fujians) {
        this.fujians = fujians;
    }

    public List<TaskInfoAllBean.FujiansBean> getFujians() {
        return fujians;
    }

    public void setFujians(List<TaskInfoAllBean.FujiansBean> fujians) {
        this.fujians = fujians;
    }

    private List<TaskInfoAllBean.FujiansBean> fujians;


}

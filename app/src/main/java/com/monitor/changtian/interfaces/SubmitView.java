package com.monitor.changtian.interfaces;

import com.monitor.changtian.bean.ResultBean;

/**
 * Created by ken on 2018/5/18.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:    提交
 */

public interface SubmitView<T> {


    //查询数据
    void onData(T t);

    //提交数据
    void onMessage(ResultBean t);

    //错误信息
    void OnError(String s);
}


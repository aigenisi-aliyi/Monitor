package com.monitor.changtian.view;

import com.monitor.changtian.bean.ResultBean;

/**
 * Created by ken on 2018/6/26.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface BaseEditView<T> {

    /**
     * 查询
     *
     * @param t
     */
    void Getinfo(T t);

    /**
     * 增加
     */

    void Addinfo(ResultBean resultBean);

    /**
     * 删除
     */
    void Remoinfo(ResultBean resultBean);



}

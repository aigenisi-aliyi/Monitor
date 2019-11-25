package com.monitor.changtian.view;

import com.monitor.changtian.bean.GetSchemePriceDetailListBean;
import com.monitor.changtian.bean.GetSchemeQuotationInfoBean;
import com.monitor.changtian.bean.ResultBean;

import java.util.List;

/**
 * Created by ken on 2018/7/30.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public interface OfferView {

    /**
     * 返回方案列表
     */
    void OnOfferList(List<GetSchemeQuotationInfoBean> getSchemeQuotationInfoBeans);

    /**
     * 审核方案
     */
    void OnOfferTrial(ResultBean resultBean);

    /**
     * 查询详情
     */
    void OnOffertrialinfo(List<GetSchemePriceDetailListBean> getSchemePriceDetailListBeans);

    void OnError(String s);
}

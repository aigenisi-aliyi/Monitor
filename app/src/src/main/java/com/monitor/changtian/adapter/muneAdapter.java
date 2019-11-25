package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.ArticleBean;

/**
 * Created by ken on 2018/4/10.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class muneAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {

    public muneAdapter() {
        super(R.layout.main_item_mune);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {

        helper.setText(R.id.item_text, item.getTitle());

    }
}

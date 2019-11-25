package com.monitor.changtian.adapter;

import android.text.Editable;
import android.text.TextWatcher;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SampleCategoryItemsDataBean;
import com.monitor.changtian.view.ClearEditText;

/**
 * Created by ken on 2018/6/1.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SampleCategoryItemAdapter extends BaseQuickAdapter<SampleCategoryItemsDataBean, BaseViewHolder> {


    public SampleCategoryItemAdapter(int layoutResId ) {
        super(layoutResId);

    }

    private OnEditValueChangedListener mEditListenter;

    public void setmEditListenter(OnEditValueChangedListener listenter) {
        mEditListenter = listenter;
    }

    @Override
    protected void convert(final BaseViewHolder helper, SampleCategoryItemsDataBean item) {

        ClearEditText edit_query = helper.getView(R.id.edit_query);
        if (item != null) {
            helper.setText(R.id.tv_title, item.getItemsname());
            edit_query.setHint("请输入" + item.getItemsname());



            if (item.getItemvalue() != null) {
                edit_query.setText(item.getItemvalue());
            } else {
                edit_query.setText("");
            }
        }

        edit_query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mEditListenter.Value(s, helper.getLayoutPosition());
            }
        });
    }


    public interface OnEditValueChangedListener {
        void Value(Editable editable, int position);  //获取的单位
    }
}

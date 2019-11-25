package com.monitor.changtian.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.TasksInfomationBean;

/**
 * Created by ken on 2018/7/12.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class ConsumableAdapter extends BaseQuickAdapter<TasksInfomationBean.ConsumablesBean, BaseViewHolder> {
    public ConsumableAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, TasksInfomationBean.ConsumablesBean item) {

        TextView tv_unit = helper.getView(R.id.tv_unit);
        tv_unit.setVisibility(View.VISIBLE);
        EditText et_value = helper.getView(R.id.et_value);
        if (item.getNames() != null) {
            helper.setText(R.id.tv_name, item.getNames());
            et_value.setHint("请输入" + item.getNames());
        }
        if (item.getValue() != null) {
            et_value.setText(item.getValue());
        } else {
            et_value.setText("");
        }
        if (item.getUnit() != null) {
            helper.setText(R.id.tv_unit, item.getUnit());
        }


        et_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mEditListenter.Value_con(s, helper.getLayoutPosition());
            }
        });
    }


    public interface OnEditValueChangedListener_Con {
        void Value_con(Editable editable, int position);  //获取的单位
    }

    private OnEditValueChangedListener_Con mEditListenter;

    public void setmEditListenter(OnEditValueChangedListener_Con listenter) {
        mEditListenter = listenter;
    }
}
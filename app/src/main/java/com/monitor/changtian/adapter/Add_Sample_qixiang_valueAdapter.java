package com.monitor.changtian.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.TasksInfomationBean;

/**
 * Created by Administrator on 2018/10/18.
 */

public class Add_Sample_qixiang_valueAdapter extends BaseQuickAdapter<TasksInfomationBean.PointsBean.ScenefactorsBean, BaseViewHolder> {
    public Add_Sample_qixiang_valueAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, TasksInfomationBean.PointsBean.ScenefactorsBean item) {

        EditText et_value = helper.getView(R.id.et_value);
//        et_value.setFocusable(false);
//        et_value.setFocusableInTouchMode(false);
        if (item.getFactorname() != null) {
            helper.setText(R.id.tv_name, item.getFactorname());
            et_value.setHint(item.getFactorname());
        }
        if (item.getValue() != null) {
            et_value.setText(item.getValue());
        } else {
            et_value.setText("");
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
                mEditListenter_qx.Value_qixiang(s, helper.getLayoutPosition());
            }
        });
    }

    public interface OnEditValueChangedListener_qx {
        void Value_qixiang(Editable editable, int position);  //获取的单位
    }

    private OnEditValueChangedListener_qx mEditListenter_qx;

    public void setmEditListenter(OnEditValueChangedListener_qx listenter) {
        mEditListenter_qx = listenter;
    }
}

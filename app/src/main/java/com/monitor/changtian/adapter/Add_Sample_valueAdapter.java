package com.monitor.changtian.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
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

public class Add_Sample_valueAdapter extends BaseQuickAdapter<TasksInfomationBean.PointsBean.ScenefactorsBean, BaseViewHolder> {
    public Add_Sample_valueAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final TasksInfomationBean.PointsBean.ScenefactorsBean item) {

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

        TextView tv_result = helper.getView(R.id.tv_result);
        if (tv_result != null) {
            if (item.getXzz_value_result() != null) {
                tv_result.setText(item.getXzz_value_result());
            } else {
                tv_result.setText("");
            }
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
                mEditListenter.Value(s, helper.getLayoutPosition());
            }
        });

        Button bt_look_yq = helper.getView(R.id.bt_look_yq);
        if (bt_look_yq != null) {
            bt_look_yq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onYQClickListener.onClick(v, item.getFactorname());
                }
            });
        }
        Button bt_read_yq = helper.getView(R.id.bt_read_yq);
        if (bt_read_yq != null) {
            bt_read_yq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onYQClickListener.onClick(v, item.getFactorname());
                }
            });
        }
    }

    public interface OnEditValueChangedListener {
        void Value(Editable editable, int position);  //获取的单位
    }

    public interface OnYQClickListener {
        void onClick(View view, String name_yq);  //点击事件,传递点击按钮本身,以及该条目的名称,后续以名称判断文件
    }


    private OnEditValueChangedListener mEditListenter;

    public void setmEditListenter(OnEditValueChangedListener listenter) {
        mEditListenter = listenter;
    }

    private OnYQClickListener onYQClickListener;

    public void setOnYQClickListener(OnYQClickListener onYQClickListener) {
        this.onYQClickListener = onYQClickListener;
    }
}

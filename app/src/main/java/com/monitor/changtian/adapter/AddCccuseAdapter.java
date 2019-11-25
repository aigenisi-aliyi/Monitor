package com.monitor.changtian.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.FaceItemBean;

/**
 * Created by Administrator on 2018/10/15.
 */

public class AddCccuseAdapter extends BaseQuickAdapter<FaceItemBean, BaseViewHolder> {

    private int fancye;

    public int getFancye() {
        return fancye;
    }

    public void setFancye(int fancye) {
        this.fancye = fancye;
    }

    public OnEditValueChangedListener_Con getmEditListenter() {
        return mEditListenter;
    }

    public AddCccuseAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, FaceItemBean item) {

        TextView tv_name = helper.getView(R.id.tv_name);
        EditText et_value = helper.getView(R.id.et_value);

        if (item.getName() != null) {
            tv_name.setText(item.getName());
        }
        if (item.getType().equals("1")) {
            et_value.setVisibility(View.GONE);
        } else {
            et_value.setVisibility(View.GONE);
        }
        // 填充数值
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
                mEditListenter.Value_con(s, helper.getLayoutPosition() - 1);
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

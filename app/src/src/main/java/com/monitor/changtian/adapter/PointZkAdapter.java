package com.monitor.changtian.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SubmitPointBean;

/**
 * Created by ken on 2018/8/2.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class PointZkAdapter extends BaseQuickAdapter<SubmitPointBean, BaseViewHolder> {
    public PointZkAdapter(Context mContext, int layoutResId) {
        super(layoutResId);
        this.mContext = mContext;
    }

    RecyclerView rv_list;
    Context mContext;

    @Override
    protected void convert(final BaseViewHolder helper, SubmitPointBean item) {

        if (item.getPointname().length() > 0) {
            helper.setText(R.id.tv_name, item.getPointname());
        } else {
            helper.setText(R.id.tv_name, "");
        }
        rv_list = helper.getView(R.id.rv_list);
//        if (item.getQualitycontrolname().length() > 0) {
//            helper.setText(R.id.tv_quality_type, item.getQualitycontrolname());
//        } else {
//            helper.setText(R.id.tv_quality_type,"");
//        }
//
//
//        EditText et_quality_number = helper.getView(R.id.et_quality_number);
//        if (item.getNumbers().length() > 0) {
//            et_quality_number.setText(item.getNumbers());
//        } else {
//            et_quality_number.setText("");
//        }
//
//        et_quality_number.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                mEditListenter.Value_numbers(s, helper.getLayoutPosition());
//            }
//        });
        helper.addOnClickListener(R.id.tv_quality_type);
    }

    public void initRecycleView(BaseQuickAdapter baseQuickAdapter) {
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.setAdapter(baseQuickAdapter);
        notifyDataSetChanged();
    }


    public interface OnEditValueChangedListener_number {
        void Value_numbers(Editable editable, int position);  //获取的单位
    }

    private OnEditValueChangedListener_number mEditListenter;

    public void setmEditListenter(OnEditValueChangedListener_number listenter) {
        mEditListenter = listenter;
    }
}

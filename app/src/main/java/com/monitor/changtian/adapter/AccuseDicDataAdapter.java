package com.monitor.changtian.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.DicDataBean;
import com.vise.log.ViseLog;

/**
 * Created by ken on 2018/9/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * へ　　　　／|
 * 　　/＼7　　∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　 │　　　　　ヽ　　 /　　〉
 * 　Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */

public class AccuseDicDataAdapter extends BaseQuickAdapter<DicDataBean, BaseViewHolder> {
    public AccuseDicDataAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, DicDataBean item) {

        final CheckBox cb_doctor = helper.getView(R.id.cb_doctor);
        helper.addOnClickListener(R.id.cb_doctor);
        if (item.getDataValue() != null) {
            cb_doctor.setText(item.getDataValue());
        }
        cb_doctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 通过这个方法，来监听当前的checkbox是否被选中
                if (isChecked) {
                    ViseLog.d("选中");
                    AccuseDicDataAdapter.this.getData().get(helper.getLayoutPosition()).setChoice(true);
                } else {
                    ViseLog.d("取消选中");
                    AccuseDicDataAdapter.this.getData().get(helper.getLayoutPosition()).setChoice(false);
                }
            }
        });


        if (AccuseDicDataAdapter.this.getData().get(helper.getLayoutPosition()).isChoice_save()) {
            cb_doctor.setChecked(true);
        } else {
            cb_doctor.setChecked(false);
        }
    }
}

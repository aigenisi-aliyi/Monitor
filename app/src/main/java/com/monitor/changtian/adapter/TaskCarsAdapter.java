package com.monitor.changtian.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.TaskCarsDataBean;

import static com.monitor.changtian.constant.PublicConstant.PHOTOS_URL;

/**
 * Created by ken on 2018/6/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TaskCarsAdapter extends BaseQuickAdapter<TaskCarsDataBean, BaseViewHolder> {
    private Context context;

    public TaskCarsAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskCarsDataBean item) {

        ImageView image_task = helper.getView(R.id.image_task);
        if (item.getVlicense() != null) {
            helper.setText(R.id.tv_car_num, item.getVlicense());
        }
        if (item.getImgurl().length() > 0) {
            Glide.with(context)
                    .load(PHOTOS_URL + item.getImgurl())
                    .into(image_task);
        } else {
            Glide.with(context)
                    .load(R.drawable.nopng11)
                    .into(image_task);
        }

        helper.addOnClickListener(R.id.image_task);
    }
}

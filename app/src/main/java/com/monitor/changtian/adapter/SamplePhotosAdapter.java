package com.monitor.changtian.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.SampleInfoCompreData;

import static com.monitor.changtian.constant.PublicConstant.PHOTOS_URL;

/**
 * Created by ken on 2018/6/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SamplePhotosAdapter extends BaseQuickAdapter<SampleInfoCompreData.SamplePhotosBean, BaseViewHolder> {
    private Context mContext;

    public SamplePhotosAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
        this.mContext = mContext;

    }


    @Override
    protected void convert(BaseViewHolder helper, SampleInfoCompreData.SamplePhotosBean item) {

        ImageView iv_face_photograph_pic = helper.getView(R.id.iv_face_photograph_pic);
        if (item != null) {

            Glide.with(mContext).load(PHOTOS_URL + item.getFujian()).into(iv_face_photograph_pic);

        }

        helper.addOnClickListener(R.id.iv_face_photograph_pic);

    }
}
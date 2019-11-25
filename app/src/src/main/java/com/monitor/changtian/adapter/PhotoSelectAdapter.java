package com.monitor.changtian.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.PhotoModel;
import com.monitor.changtian.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by ken on 2018/5/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class PhotoSelectAdapter extends BaseQuickAdapter<PhotoModel, BaseViewHolder> {


    ArrayList<PhotoModel> mData = new ArrayList();

    Context mContext;
    String uri = "";

    public PhotoSelectAdapter(Context mContext) {
        super(R.layout.widget_image);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoModel item) {

        ImageView iv_image = helper.getView(R.id.iv_image);
        Button bt_del = helper.getView(R.id.bt_del);
        if (helper.getLayoutPosition() == PhotoSelectAdapter.this.getData().size()) {
            iv_image.setVisibility(View.VISIBLE);
            iv_image.setImageResource(R.mipmap.ic_launcher);
            Glide.with(mContext).load(R.mipmap.ic_launcher).into(iv_image);
            uri = "";
        } else {
            iv_image.setVisibility(View.VISIBLE);
            PhotoModel photoData = mData.get(helper.getLayoutPosition());
            if (StringUtils.isUrl(photoData.getOriginalPath())) {
                uri = photoData.getOriginalPath();
            } else {
                uri = "file:///" + photoData.getOriginalPath();
            }
            Glide.with(mContext).load(uri).into(iv_image);
        }
    }
}

package com.monitor.changtian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.PhotoModel;
import com.monitor.changtian.utils.StringUtils;

import java.util.ArrayList;

/**
 * name : lishuai
 * time : 2017/11/1
 * title:
 */

public class MyGrideViewAdapter extends android.widget.BaseAdapter {


    ArrayList<PhotoModel> mData = new ArrayList();

    ArrayList<ViewHolder> holderList = new ArrayList<ViewHolder>();

    private LayoutInflater mInflater = null;
    private Context context;


    public MyGrideViewAdapter(Context context, ArrayList<PhotoModel> mData) {
        //根据context上下文加载布局，这里的是Demo17Activity本身，即this
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = mData;
    }


    @Override
    public int getCount() {
        return mData.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.widget_image, null);
            holder = new ViewHolder();
            holder.image = convertView.findViewById(R.id.iv_photo);
            holder.iv_delete = convertView.findViewById(R.id.iv_delete);
            holderList.add(holder);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == getCount() - 1) {
//                if (getCount() == (PHOTO_MAX_COUNT + 1)) {
//                    holder.image.setVisibility(View.INVISIBLE);
//                } else {
            holder.iv_delete.setVisibility(View.GONE);
            holder.image.setVisibility(View.VISIBLE);
            holder.image.setImageResource(R.mipmap.ic_launcher);
            holder.uri = "";
            Glide.with(context).load(R.drawable.add_road_book_take_img_photo).into(holder.image);
//                }
        } else {
            holder.iv_delete.setVisibility(View.VISIBLE);
            holder.image.setVisibility(View.VISIBLE);
            PhotoModel photoData = mData.get(position);
            if (StringUtils.isUrl(photoData.getOriginalPath())) {
                holder.uri = photoData.getOriginalPath();
            } else {
                holder.uri = "file:///" + photoData.getOriginalPath();
            }
            for (ViewHolder item : holderList) {
                if (item.uri.equals(holder.uri)) {
                    Glide.with(context).load(holder.uri ).into(holder.image);
                }
            }
        }

        //单张删除处理
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mData.remove(position);
                notifyDataSetChanged();
            }
        });


        return convertView;
    }

    class ViewHolder {
        ImageView image;
        ImageView iv_delete;
        String uri = "";
    }
}

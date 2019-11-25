package com.monitor.changtian.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 * name : lishuai
 * time : 2017/10/24
 * title:
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<String> mlist;
    private Context mContext;
    private List<ImageView> imageViews;
    public ViewPagerAdapter(List<ImageView> list) {
        super();
        imageViews = list;
        // TODO Auto-generated constructor stub
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // 因为实际只有几个页面但是我们要无限循环，所以取模计算出当前的是第几个页面
        int i = position % imageViews.size();
        // 预防负值
        position = Math.abs(i);
        ImageView imageView = imageViews.get(position);
        ViewParent parent = imageView.getParent();
        // remove掉View之前已经加到一个父控件中，否则报异常
        if (parent != null) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(imageView);
        }
        container.addView(imageView);
        return imageView;




    }
}

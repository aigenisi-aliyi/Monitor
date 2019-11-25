package com.monitor.changtian.adapter;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jauker.widget.BadgeView;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.IconBean;

/**
 * Created by ken on 2018/10/11.
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
 * 首页图标
 */
public class IconAdapter extends BaseQuickAdapter<IconBean, BaseViewHolder> {
    private Context mContext;
    BadgeView badgeView;
    public IconAdapter(int layoutResId, Context mContext) {
        super(layoutResId);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, IconBean item) {
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        TextView tv_title = helper.getView(R.id.tv_title);
        if (item.getName() != null) {
            helper.setText(R.id.tv_title, item.getName());
        }
        badgeView = new BadgeView(mContext);
        badgeView.setTargetView(iv_icon);
        badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.TOP);
        iv_icon.setImageResource(item.getIcon_url());
        if (item.getStatus().equals("0")) {
            tv_title.setTextColor(color(R.color.text_gey));
        } else {
            tv_title.setTextColor(color(R.color.black_deep));
            badgeView.setText("0");
        }
        helper.addOnClickListener(R.id.ll_look);




    }

    private int color(int value) {
        return mContext.getResources().getColor(value);
    }
}

package com.monitor.changtian.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.MessageListBean;

/**
 * Created by ken on 2018/9/25.
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

public class MessageListAdapter extends BaseQuickAdapter<MessageListBean, BaseViewHolder> {
    public MessageListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageListBean item) {

        if (item.getMtypes().equals("1")) {

            helper.setText(R.id.tv_person, "方案审核");
        } else if (item.getMtypes().equals("2")) {
            helper.setText(R.id.tv_person, "报价单初审");
        } else if (item.getMtypes().equals("3")) {
            helper.setText(R.id.tv_person, "报价单复审");
        } else if (item.getMtypes().equals("4")) {
            helper.setText(R.id.tv_person, "报价复审结果");
        } else if (item.getMtypes().equals("5")) {
            helper.setText(R.id.tv_person, "质控信息");
        } else if (item.getMtypes().equals("6")) {
            helper.setText(R.id.tv_person, "分配任务信息");
        } else if (item.getMtypes().equals("7")) {
            helper.setText(R.id.tv_person, "新任务消息");
        } else if (item.getMtypes().equals("8")) {
            helper.setText(R.id.tv_person, "新消息");
        }else if (item.getMtypes().equals("23")) {
            helper.setText(R.id.tv_person, "财务消息");
        }
        else {
            helper.setText(R.id.tv_person, "新消息");
        }

        if (item.getContent().length() > 0) {
            helper.setText(R.id.tv_content, item.getContent());
        } else {
            helper.setText(R.id.tv_content, "");
        }

        if (item.getStime().length() > 0) {
            helper.setText(R.id.tv_time, item.getStime());
        } else {
            helper.setText(R.id.tv_time, "");
        }
    }
}

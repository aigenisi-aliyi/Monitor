package com.monitor.changtian.presenter;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.MessageListBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.MessageListView;
import com.vise.log.ViseLog;

import java.util.List;

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

public class MessageListPresenter {

    private MessageListView messageListView;
    private Context mContext;

    public MessageListPresenter(MessageListView messageListView, Context mContext) {
        this.messageListView = messageListView;
        this.mContext = mContext;
    }

    public void GetMsgLists(String data) {

        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetMsgLists(data)
                .compose(Transformer.<List<MessageListBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<MessageListBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        messageListView.OnError(s);
                        ViseLog.d(s);
                    }

                    @Override
                    protected void onSuccess(List<MessageListBean> messageListBeans) {
                        if (messageListBeans != null) {
                            messageListView.OnMessageList(messageListBeans);
                        }
                    }
                });
    }


}

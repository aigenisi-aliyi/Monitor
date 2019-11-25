package com.monitor.changtian.presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.monitor.changtian.interfaces.SubmitView;

/**
 * Created by ken on 2018/5/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class BasePresenter  {

    public Dialog loading_dialog;
    public Context mContext;
    public SubmitView submitView;

    public BasePresenter(SubmitView submitView, Context mContext) {
        this.submitView = submitView;
        loading_dialog = new AlertDialog.Builder(mContext).setMessage("请稍等...").create();
        this.mContext = mContext;
    }
}

package com.monitor.changtian.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


import com.monitor.changtian.MyApplication;

import java.util.HashMap;
import java.util.Stack;


public class LogAndToastUtil {
	// 等待加载进度条对话框的 集合
	public static HashMap<Class<?>, Stack<ProgressDialog>> dicWait;

	/**
	 * Toast辅助
	 */
	static class ToastRunable implements Runnable {
		private Context c;
		private String s;
		private Object[] args;

		public ToastRunable(Context c, String s, Object... args) {
			this.c = c;
			this.s = s;
			this.args = args;
		}

		@Override
		public void run() {
			toast(c, s, args);
		}
	}

	/**
	 * 弹出提示 使用静态成员变量保证toast不延时
	 */
	public static Toast toast;
	public static Context toastContext;

	public static void toast(Context c, String s, Object... args) {
		toastShowLengthTime(c, s, Toast.LENGTH_SHORT, args);
	}
 
	public static void toast(Context c, View view) {
		if (!c.equals(toastContext)) {
			toastContext = c;
			toast = Toast.makeText(c, "", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
		}
		toast.setView(view);
		toast.show();
	}

	//自定义设置toas弹出的时间（duration）
	public static void toastShowLengthTime(Context c, String s, int duration, Object... args) {
		s = String.format(s, args);
		if (!c.equals(toastContext)) {
			toastContext = c;
			toast = Toast.makeText(c, "", duration);
			toast.setGravity(Gravity.CENTER, 0, 0);
		}

		toast.setText(s);
		toast.show();
	}

	/**
	 * 清除Tosst 避免A的Tosst出现在B中
	 */
	public static void clearToast() {
		if (toast != null) {
			toast.cancel();
			toast = null;
		}
		if (toastContext != null)
			toastContext = null;

	}

	/**
	 * 弹出提示
	 */
	public static void toastOnUi(Activity activity, String s, Object... args) {
		activity.runOnUiThread(new ToastRunable(activity, s, args));
	}

	/**
	 * 打印日志
	 */
	public static void log(String s, Object... args) {
		if(!MyApplication.openLog){
			return;
		}
		if (args != null && args.length > 0) {
			s = String.format(s, args);
		}

		Log.i("测试", s);
	}

	public static ProgressDialog showWait(Context context, String message) {
		return showWait(context, message, true);
	}

	public static ProgressDialog showWaitNoCanceledOutside(Context context, String message) {
		return showWait(context, message, false);
	}

	public static ProgressDialog showWait(Context context, String message, boolean canceledOutside) {

		ProgressDialog pd = new ProgressDialog(context);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCanceledOnTouchOutside(canceledOutside);
		pd.setMessage(message);
		pd.setIndeterminate(true);
		pd.setCancelable(true);
		pd.show();

		if (dicWait == null) {
			dicWait = new HashMap<Class<?>, Stack<ProgressDialog>>();
		}
		Stack<ProgressDialog> stack = dicWait.get(context.getClass());
		if (stack == null) {
			stack = new Stack<ProgressDialog>();
			dicWait.put(context.getClass(), stack);
		}
		stack.push(pd);
		return pd;
	}

	public static void clearWait(Context context) {
		if (context == null) {
			dicWait.clear();
		} else {
			Stack<ProgressDialog> stack = dicWait.get(context.getClass());
			if (stack != null) {
				stack.clear();
			}
			dicWait.remove(context.getClass());
		}
	}

	public static void cancelWaitOnUi(Activity activity) {
		activity.runOnUiThread(new CancelWaitRunnalbe(activity));
	}

	/**
	 * CancelWait 辅助类
	 */
	static class CancelWaitRunnalbe implements Runnable {
		private Context c;

		public CancelWaitRunnalbe(Context c) {
			this.c = c;
		}

		public void run() {
			cancelWait(c);
		}
	}

	public static void cancelWait(Context context) {
		try {
			if(dicWait == null){
				return;
			}
			Stack<ProgressDialog> stack = dicWait.get(context.getClass());
			if (stack != null && stack.size() > 0) {
				ProgressDialog pd = stack.pop();
				if (pd.isShowing()) {
					pd.cancel();
				}
				if (stack.size() == 0 && context != null) {
					dicWait.remove(context.getClass());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

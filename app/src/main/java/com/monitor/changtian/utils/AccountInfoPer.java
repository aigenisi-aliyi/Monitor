package com.monitor.changtian.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.ResultBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Administrator on 2019/1/3.
 */

public class AccountInfoPer {

    public static void loginOut(Context ctx) {
        File dir = ctx.getFilesDir();
        String[] fileNameList = dir.list();
        for (String item : fileNameList) {
            File file = new File(dir, item);
            if (file.exists() && !file.isDirectory()) {
                file.delete();
            }
        }
        AccountInfoPer.setNeedPush(ctx, true);
    }

    private static final String ACCOUNT = "ResultBean";

    public static void saveAccount(Context ctx, ResultBean data) {
        File file = new File(ctx.getFilesDir(), ACCOUNT);
        if (file.exists()) {
            file.delete();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ctx.openFileOutput(ACCOUNT, Context.MODE_PRIVATE));
            oos.writeObject(data);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultBean loadAccount(Context ctx) {
        ResultBean data = null;
        File file = new File(ctx.getFilesDir(), ACCOUNT);
        if (file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(ctx.openFileInput(ACCOUNT));
                data = (ResultBean) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (data == null) {
            data = new ResultBean();
//            data.setId(-1l);
        }

        return data;
    }

    public static boolean isLogin(Context ctx) {
        File file = new File(ctx.getFilesDir(), ACCOUNT);
        return file.exists();
    }


    // TODO 添加单个item接口
    static class DataCache<T> {

        public final static String FILDER_GLOBAL = "FILDER_GLOBAL";

        public void save(Context ctx, ArrayList<T> data, String name) {
            save(ctx, data, name, "");
        }

        public void saveGlobal(Context ctx, ArrayList<T> data, String name) {
            save(ctx, data, name, FILDER_GLOBAL);
        }

        public void delete(Context ctx, String name) {
            File file = new File(ctx.getFilesDir(), name);
            if (file.exists()) {
                file.delete();
            }
        }

        private void save(Context ctx, ArrayList<T> data, String name, String folder) {
            if (ctx == null) {
                return;
            }

            File file;
            if (!folder.isEmpty()) {
                File fileDir = new File(ctx.getFilesDir(), folder);
                if (!fileDir.exists() || !fileDir.isDirectory()) {
                    fileDir.mkdir();
                }
                file = new File(fileDir, name);
            } else {
                file = new File(ctx.getFilesDir(), name);
            }

            if (file.exists()) {
                file.delete();
            }

            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(data);
                oos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public ArrayList<T> load(Context ctx, String name) {
            return load(ctx, name, "");
        }

        public ArrayList<T> loadGlobal(Context ctx, String name) {
            return load(ctx, name, FILDER_GLOBAL);
        }

        private ArrayList<T> load(Context ctx, String name, String folder) {
            ArrayList<T> data = null;
            File file;
            if (!folder.isEmpty()) {
                File fileDir = new File(ctx.getFilesDir(), folder);
                if (!fileDir.exists() || !fileDir.isDirectory()) {
                    fileDir.mkdir();
                }
                file = new File(fileDir, name);
            } else {
                file = new File(ctx.getFilesDir(), name);
            }

            if (file.exists()) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                    data = (ArrayList<T>) ois.readObject();
                    ois.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (data == null) {
                data = new ArrayList<T>();
            }
            return data;
        }
    }

    private static String FILE_PUSH = "FILE_PUSH";
    private static String KEY_NEED_PUSH = "KEY_NEED_PUSH";

    public static boolean getNeedPush(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(FILE_PUSH, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_NEED_PUSH, true);
    }

    public static void setNeedPush(Context ctx, boolean push) {
        SharedPreferences sp = ctx.getSharedPreferences(FILE_PUSH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_NEED_PUSH, push);
        editor.commit();
    }

    private static final String GLOBAL_SETTING = "GLOBAL_SETTING";
    private static final String GLOBAL_SETTING_BACKGROUND = "GLOBAL_SETTING_BACKGROUND";

    public static void setCheckLoginBackground(Context ctx) {
        Calendar calendar = Calendar.getInstance();
        SharedPreferences.Editor editor = ctx.getSharedPreferences(GLOBAL_SETTING, Context.MODE_PRIVATE).edit();
        editor.putLong(GLOBAL_SETTING_BACKGROUND, calendar.getTimeInMillis());
        editor.commit();
    }

    // 距离上次检查24小时后再检查
    public static boolean needCheckLoginBackground(Context ctx) {
        long last = ctx.getSharedPreferences(GLOBAL_SETTING, Context.MODE_PRIVATE)
                .getLong(GLOBAL_SETTING_BACKGROUND, 0);
        return (Calendar.getInstance().getTimeInMillis() - last) > 1000 * 3600 * 24;
    }
}

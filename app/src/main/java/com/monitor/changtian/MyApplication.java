package com.monitor.changtian;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.view.WindowManager;

import com.allen.library.RxHttpUtils;
import com.allen.library.config.OkHttpConfig;
import com.allen.library.cookie.store.SPCookieStore;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mobstat.StatService;
import com.bumptech.glide.request.target.ViewTarget;
import com.monitor.changtian.activity.LoginActivity_;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.EventBus.FinishEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.constant.PublicConstant;
import com.monitor.changtian.utils.AccountInfo;
import com.monitor.changtian.utils.AccountInfoPer;
import com.vise.log.ViseLog;
import com.vise.log.inner.LogcatTree;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import me.jessyan.autosize.utils.LogUtils;
import okhttp3.OkHttpClient;

import static com.monitor.changtian.constant.PublicConstant.SERVICE_URL;

/**
 * Created by ken on 2018/4/23.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class MyApplication extends Application {

    public static boolean openLog = true;//打开log日志
    public static MyApplication ME;

    @Override
    public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_tag);
        ME = this;
        initViseLog();
        initHttp();
        StatService.autoTrace(ME);

        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        closeAndroidPDialog();

        LogUtils.setDebug(openLog);
    }


    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public static MyApplication getInstance() {
        return ME;
    }


    private void initViseLog() {
        ViseLog.getLogConfig()
                .configAllowLog(true)//是否输出日志
                .configShowBorders(true)//是否排版显示
                .configTagPrefix("LoggerHpatient")//设置标签前缀
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")//个性化设置标签，默认显示包名
                .configLevel(Log.VERBOSE);//设置日志最小输出级别，默认Log.VERBOSE
        ViseLog.plant(new LogcatTree());//添加打印日志信息到Logcat的树
    }

    private void initHttp() {

        OkHttpClient okHttpClient = new OkHttpConfig
                .Builder(this)
                //全局的请求头信息
                .setHeaders(null)
                //开启缓存策略(默认false)
                //1、在有网络的时候，先去读缓存，缓存时间到了，再去访问网络获取数据；
                //2、在没有网络的时候，去读缓存中的数据。
                .setCache(false)
                //全局持久话cookie,保存到内存（new MemoryCookieStore()）或者保存到本地（new SPCookieStore(this)）
                //不设置的话，默认不对cookie做处理
                .setCookieType(new SPCookieStore(this))
                //可以添加自己的拦截器(比如使用自己熟悉三方的缓存库等等)
                //.setAddInterceptor(null)
                //全局ssl证书认证
                //1、信任所有证书,不安全有风险（默认信任所有证书）
                //.setSslSocketFactory()
                //2、使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(cerInputStream)
                //3、使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(bksInputStream,"123456",cerInputStream)
                //全局超时配置
                .setReadTimeout(30)
                //全局超时配置
                .setWriteTimeout(30)
                //全局超时配置
                .setConnectTimeout(30)
                //全局是否打开请求log日志
                .setDebug(false)
                .build();
        RxHttpUtils
                .getInstance()
                .init(this)
                .config()
                //配置全局baseUrl
                .setBaseUrl(SERVICE_URL)
                //开启全局配置
                .setOkClient(okHttpClient);

    }


    /**
     * 保存用户名
     *
     * @param User
     */
    public void setUser(String User) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("User", User);
        edit.commit();
    }

    /**
     * 读取用户名
     */
    //读取UserCode
    public String getUser() {

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        return preferences.getString("User", "");
    }

    /**
     * 保存用户id
     */
    public void setUserCode(String userCode) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        preferences.edit().putString("userCode", userCode).commit();
    }

    public String getUserCode() {
        // TODO Auto-generated method stub
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        return preferences.getString("userCode", "");
    }

    /**
     * 保存密码
     *
     * @param pwd
     */
    //保存密码信息
    public void setPwd(String pwd) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        preferences.edit().putString("pwd", pwd).commit();
    }

    /**
     * 读取密码
     *
     * @return
     */

    //读取密码
    public String getPWd() {
        // TODO Auto-generated method stub
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        return preferences.getString("pwd", "");
    }

    /**
     * 读取token
     */
    public void setToken(String token) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        preferences.edit().putString("token", token).commit();
    }

    /**
     * 获取token
     */
    public String getToken() {
        // TODO Auto-generated method stub
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        return preferences.getString("token", "");
    }


    /**
     * 保存用户角色权限
     */
    public void setUserPermission(String permission) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        preferences.edit().putString("permission", permission).commit();
    }

    /**
     * 获取用户角色权限
     */
    //读取密码
    public String getUserPermission() {
        // TODO Auto-generated method stub
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        return preferences.getString("permission", "");
    }

    //读取码表
    public Map<String, String> MsgList() {
        return PublicConstant.map;
    }


    //读取用户信息
    public AllUserInfo getAllUserInfo() {
        AllUserInfo dataBean = AccountInfo.loadAccount(this);
        return dataBean;
    }

    ///读取权限信息

    public ResultBean getResultInfo(){
        ResultBean dataBean = AccountInfoPer.loadAccount(this);
        return dataBean;
    }


    //    安卓9.0安装后 会有一个安全提醒的弹框  需要设置去掉
    private void closeAndroidPDialog(){
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

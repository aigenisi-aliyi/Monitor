package com.monitor.changtian.constant;

import com.monitor.changtian.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ken on 2018/4/10.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class PublicConstant {
    public static final String TOKEN = "e815f4cc6404f4327c6636e6474a6cc1";
    public static final String UID = "101";
    //刷新延时时间
    public static int delayMillis = 1000;
    public static final String TAG_PAGE_HOME = "首页";
    public static final String TAG_PAGE_CITY = "任务";
    public static final String TAG_PAGE_PUBLISH = " ";
    public static final
    String TAG_PAGE_MESSAGE = "消息";
    public static final String TAG_PAGE_PERSON = "我的";
    public static final String WEATHER = "https://www.sojson.com/open/api/weather/json.shtml?city=西安";
    /**
     * 内网
     */
//    public static final String SERVICE_URL = "http://192.168.1.233:8003/WebService/";
//    public static final String PHOTOS_URL = "http://192.168.1.233:8003";

//    public static final String SERVICE_URL = "http://192.168.1.201:8002/WebService/";
//    public static final String PHOTOS_URL = "http://192.168.1.201:8002";
//    public static final String RABBIT_Release_adress = "192.168.1.130";
//    public static final String RABBIT_Release_user = "guest";
//    public static final double latitude_value = 34.3669410000;
//    public static final double longitude_value = 108.8543730000;
//    public static final String Latitude_name = "陕西中测";
//    public static final String NAME_APP = "中测";
//    public static final int LOGO_APP = R.mipmap.logo111;
    /**
     * 中测外网
     */
//    public static final String RABBIT_Release_adress = "111.26.161.164";
//    public static final String RABBIT_Release_user = "ct_admin";
//    public static final String SERVICE_URL = "http://39.107.103.60:8080/WebService/";
//    public static final String PHOTOS_URL = "http://39.107.103.60:8080";
//    public static final double latitude_value = 34.3669410000;
//    public static final double longitude_value = 108.8543730000;
//    public static final String Latitude_name = "陕西中测";
//    public static final String NAME_APP = "中测";
//    public static final int LOGO_APP = R.mipmap.logo111;


    /**
     * 中测内网
     */
    public static final String RABBIT_Release_adress = "111.26.161.164";
    public static final String RABBIT_Release_user = "ct_admin";
    public static final String SERVICE_URL = "http://192.168.0.141/WebService/";
    public static final String PHOTOS_URL = "http://192.168.0.141";
    public static final double latitude_value = 34.3669410000;
    public static final double longitude_value = 108.8543730000;
    public static final String Latitude_name = "陕西中测";
    public static final String NAME_APP = "中测";
    public static final int LOGO_APP = R.mipmap.logo111;
    /**
     * 中测测试外网
     */
//    public static final String RABBIT_Release_adress = "111.26.161.164";
//    public static final String RABBIT_Release_user = "ct_admin";
//    public static final String SERVICE_URL = "http://62.234.207.139:81/WebService/";
//    public static final String PHOTOS_URL = "http://62.234.207.139:81";
//    public static final double latitude_value = 34.3669410000;
//    public static final double longitude_value = 108.8543730000;
//    public static final String Latitude_name = "陕西中测";
//    public static final String NAME_APP = "中测";
//    public static final int LOGO_APP = R.mipmap.logo111;


//  手机apk release 版本 打包密码 123456

    public static final String TYPE_history = "0";
    public static final String TYPE_taskpro = "1";
    public static final String SIGN_PHOTOS_PATH = "/sdcard/zhongce/sign/";
    public static final String PageNum = "8";
    public static final String Contractstatus_ZUOFEI = "0";
    public static final String Contractstatus_DAIFU = "1";
    public static final String Contractstatus_DAIFUWEI = "2";
    public static final String Contractstatus_YIWANJIE = "3";
    public static final String Contractstatus_DAIFUWEI_YIWANJIE = "2,3";
    public static final String PROOFREAD = "1";
    public static final String AUDIT = "2";
//  public static final double latitude_value = 34.259432;
//  public static final double longitude_value = 108.94702;
//  public static final String Latitude_name = "钟楼";
    public static Map<String, String> map = new HashMap<String, String>() {
        {
            //公共 代码
            put("跟踪率", "跟 踪 率");
        }
    };

}

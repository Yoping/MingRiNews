package com.yubin.news.application;

/**
 * @author:YUBIN
 * @create at:2018/7/18.
 * @description:一些 APP内通用的码值
 */

public class MyCode {

    /**
     * 网络请求码
     */
    public final static int GET_MESSAGE_SUCCESS = 1;
    public final static int GET_MESSAGE_Fail = -1;
    public final static int GET_MESSAGE_TOKEN_ERROR = -888;
    public final static int GET_MESSAGE_USER_TOKEN_EXPIRED = -14;
    public final static String GET_MESSAGE_TOKEN_ERROR_STRING = "{\"code\":-888,";
    public final static String GET_MESSAGE_USER_TOKEN_EXPIRED_STRING = "{\"code\":-14,";
    public final static String GET_MESSAGE_TOKEN_NO_PERMISSION = "非常抱歉，您没有权限怎么做";
    public final static int GET_MESSAGE_LOGIN_NOT_BIND_PHONE = -666;

    /**
     * activity跳转请求码
     */
    public final static int REQUEST_CODE_NEW_USER = 1001;
    public final static int REQUEST_CODE_MY_USER_LOGIN = 1002;
    public final static int REQUEST_CODE_USERINFO_CAMERA = 1003;
    public final static int REQUEST_CODE_USERINFO_PHOTO = 1004;
    public final static int REQUEST_CODE_USERINFO_CRPP_PHOTO = 1005;
    public final static int REQUEST_CODE_PERMISSION = 1006;
    public final static int REQUEST_CODE_F_MY_TO_SETTING = 1007;
    public final static int REQUEST_CODE_F_MY_TO_USERINFO = 1008;
    public final static int REQUEST_CODE_CARE_TO_AUTHORLIST = 1009;
    public final static int REQUEST_CODE_USERINFO_TO_CHANGE_PHONE = 1010;


    public final static int REQUEST_CODE_FLASH_DETAIL_TO_LOGIN = 1011;
    public final static int REQUEST_CODE_NEWS_DETAIL_TO_LOGIN = 1012;
    public final static int REQUEST_CODE_FLASH_LIST_TO_LOGIN = 1013;

    public final static int REQUEST_CODE_MY_F_TO_DAILY_SIGN = 1014;
    public final static int REQUEST_WALLET_ADDRESS_TO_CAMERA_SCANNER = 1015;
    public final static int REQUEST_WALLET_ADDRESS_TO_PHOTO_SCANNER = 1016;
    public final static int REQUEST_ADDRESS_MANAGER_TO_ADD_WALLET_ADDRESS = 1017;
    public final static int REQUEST_ADDRESS_MANAGER_TO_ADD_LIVE_ADDRESS = 1018;

    public final static int REQUEST_CODE_CARE_NEWS_LIST_TO_LOGIN = 1019;


    /**
     * activity跳转结果码
     */
    public final static int RESULT_CODE_LOGIN_SUCCESS = 1101;
    public final static int RESULT_CODE_REGIST_SUCCESS = 1102;
    public final static int RESULT_CODE_CHANGE_PHONE_SUCCESS = 1103;
    public final static int RESULT_CODE_SIGNED_SUCCESS = 1104;
    public final static int RESULT_CODE_LOGOUT_SUCCESS = 1105;

    /**
     * 分享平台类型码
     */
    public final static int SHARE_CODE_WECHAT = 1901;
    public final static int SHARE_CODE_FRIENDS_CIRCLE = 1902;

    /**
     * 分享内容类型码
     */
    public final static int SHARE_CONTENT_CODE_TYPE_IMAGE = 1910;
    public final static int SHARE_CONTENT_CODE_TYPE_URL = 1911;
    public final static int SHARE_CONTENT_CODE_TYPE_LOCAL_IMAGE = 1912;


}

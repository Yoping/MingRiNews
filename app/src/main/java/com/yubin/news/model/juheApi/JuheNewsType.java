package com.yubin.news.model.juheApi;

/**
 * Created by YUBIN on 2017/5/5.
 */

public class JuheNewsType {

    public static int TOTAL_TYPE_NUMBER=10;
    public static String getTypePinying(int index){
        String typeName="top";
        switch (index){
            case 0:
                typeName="top";
                break;
            case 1:
                typeName="shehui";
                break;
            case 2:
                typeName="guonei";
                break;
            case 3:
                typeName="guoji";
                break;
            case 4:
                typeName="yule";
                break;
            case 5:
                typeName="tiyu";
                break;
            case 6:
                typeName="junshi";
                break;
            case 7:
                typeName="keji";
                break;
            case 8:
                typeName="caijing";
                break;
            case 9:
                typeName="shishang";
                break;

        }
        return typeName;
    }

    public static String getTypeChinese(int index){
        String typeName="头条";
        switch (index){
            case 0:
                typeName="头条";
                break;
            case 1:
                typeName="社会";
                break;
            case 2:
                typeName="国内";
                break;
            case 3:
                typeName="国际";
                break;
            case 4:
                typeName="娱乐";
                break;
            case 5:
                typeName="体育";
                break;
            case 6:
                typeName="军事";
                break;
            case 7:
                typeName="科技";
                break;
            case 8:
                typeName="财经";
                break;
            case 9:
                typeName="时尚";
                break;

        }
        return typeName;
    }

}

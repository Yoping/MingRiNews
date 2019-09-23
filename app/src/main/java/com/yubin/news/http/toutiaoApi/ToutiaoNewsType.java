package com.yubin.news.http.toutiaoApi;

/**
 * Created by YUBIN on 2017/5/5.
 */

public class ToutiaoNewsType {

//    public static int TOTAL_TYPE_NUMBER=13;
//    public static String[] typeName={"news_hot","news_society","news_tech","news_edu","news_entertainment","image_ppmm","movie","news_comic","news_story","essay_joke","news_food","news_travel","rumor"};
//    public static String[] typeNameCN={"热点","社会","科技","教育","娱乐","美女","电影","动漫","故事","段子","美食","旅游","辟谣"};
    public static int TOTAL_TYPE_NUMBER=10;
    public static String[] typeName={"news_society","news_tech","news_edu","news_entertainment","movie","news_comic","news_story","news_food","news_travel","rumor"};
    public static String[] typeNameCN={"社会","科技","教育","娱乐","电影","动漫","故事","美食","旅游","辟谣"};
    public static String getTypeEnglish(int index){
       if(index<0||index>typeName.length){
           return typeName[0];
       }
        return typeName[index];
    }
    public static String getTypeChinese(int index){
        if(index<0||index>typeName.length){
            return typeName[0];
        }
        return typeNameCN[index];
    }

}

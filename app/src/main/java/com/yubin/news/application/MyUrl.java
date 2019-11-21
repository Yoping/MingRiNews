package com.yubin.news.application;

/**
 * Created by YUBIN on 2018/7/4.
 */
public class MyUrl {

    /**
     * 域名
     */
    public final static String baseUrl="http://api.etaicj.com";//正式环境

//    public final static String baseUrl = "http://cs.api.etaicj.com";//测试环境

    //apk下载地址
    public final static String downloadApkUrl = baseUrl + "/InvitingFriends/loader";
    //获取access token
    public final static String getAccessToken = baseUrl + "/BuildToken/getAccessToken";

    /**
     * *******************************************************快讯模块*********************************************
     */
    //获取快讯列表
    public final static String newsFlashList = baseUrl + "/Home/getNewsFlashList";//旧接口
    public final static String newsFlashListNew = baseUrl + "/Home/getNewsFlashLists";//新接口
    //获取快讯详情
    public final static String newsFlashDetail = baseUrl + "/Home/getLiveInfo";
    //快讯利空、利好点赞
    public final static String putFlashIsLike = baseUrl + "/GetNews/putNewsIslike";


    /**
     * *********************************************************登录注册相关***********************************************************
     */
    //获取短信验证码
    public final static String getVerifiCode = baseUrl + "/Mobile/post";
    //登录
    public final static String login = baseUrl + "/Com/loginByPhone";
    //判断这个微信用户时候绑定了手机，绑定了则直接登录
    public final static String judgeIfBindPhoneOrLogin = baseUrl + "/Com/loginByWechat";
    //第三方平台绑定手机并登录
    public final static String bindPhoneAndLogin = baseUrl + "/Com/wechatBindPhone";
    //退出登录
    public final static String logout = baseUrl + "/Com/logout";


    /**
     * ****************************************************************个人信息相关*******************************************************
     */
    //获取用户个人信息
    public final static String getUserInfo = baseUrl + "/UserCenter/getUserInfo";
    //更新个人信息
    public final static String updateUserInfo = baseUrl + "/UserCenter/updateUserInfoByAndroid";
    //更改用户手机号码
    public final static String changeUserPhone = baseUrl + "/UserCenter/updatePhoneByAndroid";
    //上传用户头像
    public final static String uploadUserAvatar = baseUrl + "/UserCenter/uploadUserAvatarsByAndroid";


    /**
     * ***********************************************************主页模块*****************************************************
     */

    //获取新闻分类
    public final static String newsTitleList = baseUrl + "/ThemeSettings/getCategoryLabels";
    //获取资讯新闻列表
    public final static String newsList = baseUrl + "/Home/getNewsList";//旧接口
    //    public final static String newsListNew = baseUrl + "/Home/getNewsLists";//新接口
    public final static String newsListNew = baseUrl + "/Home/getNewsListAll";//新接口,加了置顶功能
    //获取个人关注新闻列表
    public final static String newsListOfCare = baseUrl + "/Home/getFocusPeopleArticle";
    //获取搜索新闻列表
    public final static String searchNewsList = baseUrl + "/Search/index";
    //获取搜索热词列表
    public final static String searchHotKeywordList = baseUrl + "/Search/getHotQueryWord";
    //获取Banner图信息
    public final static String bannerInfo = baseUrl + "/ThemeSettings/getBannerInfo";


    /**
     * ********************************************************新闻详情模块*****************************************************
     */
    //获取新闻详情的内容
    public final static String newsDetail = baseUrl + "/Home/getNewsInfo";
    //获取最新评论列表
    public final static String newsCommentList = baseUrl + "/CommentsInfo/getCommentsList";
    // 获取文章相关推荐列表
    public final static String newsRecommandList = baseUrl + "/Home/getRecommendPost";
    //获取热门评论列表
    public final static String newsHotCommentList = baseUrl + "/CommentsInfo/getHotCommentList";
    //收藏文章
    public final static String collectNews = baseUrl + "/Collection/favoritePost";
    //取消文章收藏
    public final static String cancelCollectNews = baseUrl + "/Collection/deleFavoritePost";
    //关注作者
    public final static String careAuthor = baseUrl + "/Collection/addFollowUser";
    //取消作者关注
    public final static String cancelCareAuthor = baseUrl + "/Collection/deleFollowUser";
    //点赞文章
    public final static String givePraiseToNews = baseUrl + "/Collection/addPostRecommend";
    //点赞评论
    public final static String givePraiseToNewsComment = baseUrl + "/Collection/addCommentRecommend";
    //上传文章评论
    public final static String uploadComment = baseUrl + "/Home/publishComment";


    /**
     * ********************************************************个人中心模块************************************************
     */
    //用户收藏列表
    public final static String userCollectionList = baseUrl + "/UserCenter/getCollectionList";
    //用户浏览历史列表
    public final static String userHistoryList = baseUrl + "/UserCenter/historyList";
    //用户关注列表
    public final static String userCareList = baseUrl + "/UserCenter/getFollowList";
    //作者列表
    public final static String authorList = baseUrl + "/UserCenter/authorList";
    //意见反馈
    public final static String feedback = baseUrl + "/Home/feedBack";
    //邀请好友
    public final static String inviteFriends = baseUrl + "/InvitingFriends/index";
    //以太秘籍
    public final static String goToETStar = baseUrl + "/InvitingFriends/etaiStar";
    //用户登录协议
    public final static String goLoginAgreemnt = baseUrl + "/InvitingFriends/registrationAgreement";
    //获取日常签到信息
    public final static String getDailySignInfo = baseUrl + "/UserCenter/getSignInInfo";
    //签到
    public final static String dailySign = baseUrl + "/Com/sendUserIntegral";
    //判断用户今天是否签过到
    public final static String ifUserDailySignedToday = baseUrl + "/UserCenter/checkUserIsSign";
    //获取奖励，暂时是和签到共用一个接口
    public final static String getReward = baseUrl + "/Com/sendUserIntegral";
    //用户交流群
    public final static String joinUs = baseUrl + "/InvitingFriends/contactus";


    /**
     * *****************************************************************以太星球********************************************************
     */
    /*** 获取以太星球相关信息*/
    public final static String getEtStarInfo = baseUrl + "/GetDig/getUserDigInfo";
    /*** 获取收取一个以太星球能量*/
    public final static String getOneEtDiamond = baseUrl + "/GetDig/addUserEtaiNum";
    /*** 获取以太和算力值排行*/
    public final static String getRainkingOfETAI = baseUrl + "/GetDig/getTotalEtaiRank";
    /*** 获取用户以太资产信息*/
    public final static String getUserAssetInfo = baseUrl + "/GetDig/getEtaiAssetInfo";
//    public final static String getUserAssetInfo = baseUrl + "/v1/UsersCenter/etaiPassport";//新的接口



    /*** 获取用户星币明细信息*/
    public final static String getEtDetailList = baseUrl + "/Search/getIntegralLog";
    /*** 获取活动列表*/
    public final static String getEtActivityList = baseUrl + "/v1/TestQuestions/getActivityList";
    /*** 获取答题活动个人信息*/
    public final static String getAnsQuesActUserInfo = baseUrl + "/v1/TestQuestions/getUserTestInfo";
    /*** 获取活动题目列表*/
    public final static String getEtQuestionList = baseUrl + "/v1/TestQuestions/getTestPaperInfo";

    /**
     * ======================================== 地址模块
     */
    /*** 获取钱包地址列表 */
    public final static String getWalletAddressList = baseUrl + "/v1/UsersCenter/walletAddr";
    /*** 获取收货地址列表 */
    public final static String getLiveAddressList = baseUrl + "/v1/UsersCenter/collectAddr";
    /*** 获取单个钱包地址 */
    public final static String getWalletAddress = baseUrl + "/v1/UsersCenter/getWalletInfo";
    /*** 获取单个收货地址 */
    public final static String getLiveAddress = baseUrl + "/v1/UsersCenter/getCollectInfo";
    /*** 获取虚拟币列表 */
    public final static String getCoinType = baseUrl + "/v1/UsersCenter/getProductList";
    /*** 上传钱包地址 */
    public final static String postWalletAddress = baseUrl + "/v1/UsersCenter/editWallet";
    /*** 上传居住地址 */
    public final static String postLiveAddress = baseUrl + "/v1/UsersCenter/editCollect";
    /*** 删除钱包地址 */
    public final static String deleteWalletAddress = baseUrl + "/v1/UsersCenter/walletDel";
    /*** 删除居住地址 */
    public final static String deleteLiveAddress = baseUrl + "/v1/UsersCenter/collectDel";
    /*** 设置默认居住地址 */
    public final static String setDefaultLiveAddress = baseUrl + "/v1/UsersCenter/setDefaultAddr";
    /*** 兑换商品 */
    public final static String exchangeProduct = baseUrl + "/v1/UsersCenter/submitExchange";
    /*** 获取作者相关信息 */
    public final static String getAuthorInfo = baseUrl + "/Home/getPersonalArticle";

    /*** 以太商城 H5 地址*/
    public final static String etaiShop = baseUrl + "/InvitingFriends/etaiAssets";
    /*** 精彩活动 H5 地址*/
    public final static String etaiWdfAct = baseUrl + "/v1/TestQuestions/activityList";

    /**
     * *************************************************************获取T快讯列表，外部接口****************************************************
     */
    public final static String newsFlashListOut = "http://api.coindog.com/live/list";

    public final static String testInterface = baseUrl + "/test/test";

}

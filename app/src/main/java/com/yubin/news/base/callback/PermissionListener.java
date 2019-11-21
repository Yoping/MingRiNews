package com.yubin.news.base.callback;

import java.util.List;

/**
 * @author:YUBIN
 * @create at:2018/7/19.
 * @description:
 */

public interface PermissionListener {
    //授权成功
    void onGranted();

    //授权部分
    void onGranted(List<String> grantedPermission);

    //拒绝授权
    void onDenied(List<String> deniedPermission);

}

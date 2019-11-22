package com.yubin.news.model.palyAndroidApi;


import com.yubin.news.utils.LogUtil;

public class DataBean<T> {
    private T data;
    private int  errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void printString(){
        LogUtil.debug("errorCode====",errorCode+"");
        LogUtil.debug("errorMsg====",errorMsg+"");
        LogUtil.debug("data====",data.toString());
    }

}

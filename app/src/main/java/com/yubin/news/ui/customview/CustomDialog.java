package com.yubin.news.ui.customview;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.Window;

import com.yubin.news.R;

/**
 * Created by YUBIN on 2017/6/8.
 */

public class CustomDialog {

    private static AlertDialog loadingDialog;
    public static void showProgressDialog(Context context){
        if(context==null){
            Log.i("===showProgressDialog==","context=null");
            return;
        }
        if(loadingDialog==null){
            loadingDialog=new AlertDialog.Builder(context).create();
        }
        loadingDialog.show();
        Window window=loadingDialog.getWindow();
        window.setContentView(R.layout.custom_loading_dialog);
        loadingDialog.setCancelable(true);
    }
    public static void hideProgressDialog(){
        if(loadingDialog!=null){
            loadingDialog.cancel();
            loadingDialog.dismiss();
        }
    }
}

package com.yubin.news.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.ui.activity.MainActivity;
import com.yubin.news.ui.customview.AvatarImageView;
import com.yubin.news.utils.Bimp;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.SharedPreferencesUtil;


/**
 * Created by YUBIN on 2017/4/1.
 */

public class MyFragment extends Fragment {

    private View rootView;
    private AvatarImageView avatarImageView;
    private TextView tvIconFunc1;
    private TextView tvIconFunc1go;
    private TextView tvIconFunc2;
    private TextView tvIconFunc2go;
    private TextView tvIconFunc3;
    private TextView tvIconFunc3go;
    private TextView tvIconFunc4;
    private TextView tvIconFunc4go;
    private TextView tvIconFunc5;
    private TextView tvIconFunc5go;

    public static final int SELECT_IMAGE_ACTION_CODE = 1234;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my, null);
        initview();
        setListener();

        return rootView;
    }

    private void initview() {
        avatarImageView = (AvatarImageView) rootView.findViewById(R.id.avatar_view_f_wode);
        tvIconFunc1 = (TextView) rootView.findViewById(R.id.tv_icon_f_wode_function1);
        tvIconFunc1go = (TextView) rootView.findViewById(R.id.tv_icon_f_wode_function1_go);
        tvIconFunc2 = (TextView) rootView.findViewById(R.id.tv_icon_f_wode_function2);
        tvIconFunc2go = (TextView) rootView.findViewById(R.id.tv_icon_f_wode_function2_go);
        tvIconFunc3 = (TextView) rootView.findViewById(R.id.tv_icon_f_wode_function3);
        tvIconFunc3go = (TextView) rootView.findViewById(R.id.tv_icon_f_wode_function3_go);
        tvIconFunc4 = (TextView) rootView.findViewById(R.id.tv_icon_f_wode_function4);
        tvIconFunc4go = (TextView) rootView.findViewById(R.id.tv_icon_f_wode_function4_go);
        tvIconFunc5 = (TextView) rootView.findViewById(R.id.tv_icon_f_wode_function5);
        tvIconFunc5go = (TextView) rootView.findViewById(R.id.tv_icon_f_wode_function5_go);


        tvIconFunc1.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvIconFunc1go.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvIconFunc2.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvIconFunc2go.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvIconFunc3.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvIconFunc3go.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvIconFunc4.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvIconFunc4go.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvIconFunc5.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvIconFunc5go.setTypeface(((MainActivity) getActivity()).getTypeface());

        tvIconFunc1.setText("\ue608");
        tvIconFunc1go.setText("\ue61f");
        tvIconFunc2.setText("\ue606");
        tvIconFunc2go.setText("\ue61f");
        tvIconFunc3.setText("\ue60e");
        tvIconFunc3go.setText("\ue61f");
        tvIconFunc4.setText("\ue647");
        tvIconFunc4go.setText("\ue61f");
        tvIconFunc5.setText("\ue642");
        tvIconFunc5go.setText("\ue61f");
        loadAvatar();
    }

    private void loadAvatar(){
        if(!TextUtils.isEmpty(SharedPreferencesUtil.getString(Constants.PERSONAL_AVATAR,""))){
            String avatarPath=SharedPreferencesUtil.getString(Constants.PERSONAL_AVATAR,"");
            try{
                LogUtil.i("=============loadAvatar="+avatarPath);
                setAvatar(avatarPath);
            }catch (Exception ex){
                ex.printStackTrace();
                LogUtil.i("=============获取图片异常");
            }
        }
    }

    private void setListener() {
        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用系统相册选取图片
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                //必须要加上getActivity()，不然无法返回争取的请求码
                intent.setType("image/*");

                getActivity().startActivityForResult(intent, MyFragment.SELECT_IMAGE_ACTION_CODE);
            }
        });
    }

    public void setAvatar(String imagePath) {
        try {
            avatarImageView.setImageBitmap(Bimp.revitionImageSize(imagePath));
            SharedPreferencesUtil.putString(Constants.PERSONAL_AVATAR,imagePath);
            LogUtil.i("=============setAvatar="+imagePath);
        } catch (Exception ex) {
            ex.printStackTrace();
            LogUtil.i("=============保存图片异常");
        }
    }


//    if (!WorkerUtil.isNullOrEmpty(selectPath)) {
//        try {
//            avatar.setImageBitmap(com.migu.util.Bimp
//                    .revitionImageSize(selectPath));
//            PreferencesUtils.saveString(getActivity(),
//                    Constant.PERSONAL_AVATAR, selectPath);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}

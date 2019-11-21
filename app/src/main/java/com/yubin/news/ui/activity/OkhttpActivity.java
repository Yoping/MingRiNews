package com.yubin.news.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yubin.news.R;
import com.yubin.news.http.okhttp.Listener;
import com.yubin.news.http.okhttp.OkhttpManager;

public class OkhttpActivity extends AppCompatActivity {

    private Button btnGetData;
    private TextView tvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        btnGetData=(Button)findViewById(R.id.btn_okhttp_get_data);
        tvData=(TextView)findViewById(R.id.tv_okhttp_data);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkhttpManager.getDataNewThread(OkhttpManager.url, new Listener() {
                    @Override
                    public void onResult(final String data) {

//                        tvData.setText(data);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvData.setText(data);
                            }
                        });

                    }

                    @Override
                    public void onError(String errorInfo) {
                        tvData.setText(errorInfo);
                    }
                });
            }
        });
    }
}

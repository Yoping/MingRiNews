package com.yubin.news.ui.activity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.yubin.news.R;
import com.yubin.news.base.BaseActivity;
import com.yubin.news.ui.fragment.KnowledgeFragment;

public class KnowledgeActivity extends BaseActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private KnowledgeFragment knowledgeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_knowledge);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initview() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        knowledgeFragment = KnowledgeFragment.newInstance();
        transaction.add(R.id.knowledge_frame, knowledgeFragment);
        transaction.commit();
    }

    @Override
    public void setListener() {
    }

    @Override
    public void beginToWork() {

    }
}

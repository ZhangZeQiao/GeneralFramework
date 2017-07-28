package com.xq.mvp.generalframework.ui.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xq.mvp.R;

/**
 * @author 小侨
 * @time 2017/7/28  15:28
 * @desc music页面
 */

public class MusicActivity extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager mVp;

    private void initView() {
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initView();
    }
}

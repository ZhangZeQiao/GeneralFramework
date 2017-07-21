package com.xq.mvprxre.generalframework.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xq.mvprxre.R;
import com.xq.mvprxre.generalframework.ui.movie.MovieActivity;

public class MvpRxReMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onJump(View view) {
        Intent intent = new Intent(this, MovieActivity.class);
        startActivity(intent);
    }
}

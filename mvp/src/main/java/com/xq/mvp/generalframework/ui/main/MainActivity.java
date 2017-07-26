package com.xq.mvp.generalframework.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xq.mvp.R;
import com.xq.mvp.generalframework.ui.movie.MovieActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MovieActivity.class);
        startActivity(intent);
    }

    public void onJump(View view) {
        Intent intent = new Intent(this, MovieActivity.class);
        startActivity(intent);
    }
}

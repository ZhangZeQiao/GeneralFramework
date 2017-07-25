package com.xq.mvprxremd.generalframework.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xq.mvprxremd.R;
import com.xq.mvprxremd.generalframework.ui.movie.MovieActivity;

public class MvprxremdMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MovieActivity.class);
        startActivity(intent);
    }
}

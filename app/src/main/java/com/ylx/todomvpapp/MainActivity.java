package com.ylx.todomvpapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ylx.todomvpapp.ui.activity.CallPhoneActivity;
import com.ylx.todomvpapp.ui.activity.ToolbarActivity;

public class MainActivity extends AppCompatActivity {

    private TextView btn_call,btn_toolbar;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 设置toolbar
         */
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        btn_call = (TextView) findViewById(R.id.btn_call);
        btn_toolbar = (TextView) findViewById(R.id.btn_toolbar);

        initListener();
    }

    private void initListener() {
        /**
         * 进入打电话页
         */
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpActivity(CallPhoneActivity.class);
            }
        });

        /**
         * 进入toolbar菜单设置
         */
        btn_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpActivity(ToolbarActivity.class);
            }
        });
    }

    private void jumpActivity(Class<?> clazz){
        Intent intent = new Intent(MainActivity.this,clazz);
        startActivity(intent);
    }
}

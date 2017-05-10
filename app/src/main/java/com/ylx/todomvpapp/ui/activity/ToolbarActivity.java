package com.ylx.todomvpapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ylx.todomvpapp.R;

public class ToolbarActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(ToolbarActivity.this,"你点击了backup按钮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(ToolbarActivity.this,"你点击了delete按钮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(ToolbarActivity.this,"你点击了settings按钮",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }
}

package com.ylx.todomvpapp.ui.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ylx.todomvpapp.R;

/**
 * 滑动菜单DrawerLayout
 */
public class DrawerLayoutActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawer_layout.openDrawer(GravityCompat.START); //方法里面的参数START要和第二个控件或布局中的layout_gravity的值保持一致
                break;
            default:
                break;
        }
        return true;
    }
}

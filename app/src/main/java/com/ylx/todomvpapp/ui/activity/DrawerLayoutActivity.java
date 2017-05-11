package com.ylx.todomvpapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.ylx.todomvpapp.R;

/**
 * 滑动菜单DrawerLayout
 */
public class DrawerLayoutActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private DrawerLayout drawer_layout;

    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        /**
         * 设置图标有两种方式 参见：http://www.th7.cn/Program/Android/201501/345448.shtml
         */
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mNavigationView.setCheckedItem(R.id.nav_call);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_call:
                        Toast.makeText(DrawerLayoutActivity.this,"你点击了" + getResources().getString(R.string.nav_callphone),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_friends:
                        Toast.makeText(DrawerLayoutActivity.this,"你点击了" + getResources().getString(R.string.nav_friends),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_location:
                        Toast.makeText(DrawerLayoutActivity.this,"你点击了" + getResources().getString(R.string.nav_location),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_mail:
                        Toast.makeText(DrawerLayoutActivity.this,"你点击了" + getResources().getString(R.string.nav_email),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_task:
                        Toast.makeText(DrawerLayoutActivity.this,"你点击了" + getResources().getString(R.string.nav_task),Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                drawer_layout.closeDrawers();
                return true;
            }
        });
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

package com.ylx.todomvpapp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ylx.todomvpapp.R;

/**
 * 打电话-运行时权限
 */
public class CallPhoneActivity extends AppCompatActivity {

    private TextView mCallPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);
        mCallPhone = (TextView) findViewById(R.id.call_phone);

        initListener();
    }

    private void initListener() {
        mCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(CallPhoneActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){ //判断是否拥有打电话权限
                    ActivityCompat.requestPermissions(CallPhoneActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                } else {
                    call();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                } else {
                    Toast.makeText(CallPhoneActivity.this,"你取消了打电话权限",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 打电话
     */
    private void call() {
        try{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:18638583607"));
            startActivity(intent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

package com.xbx.album.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xbx.album.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CODE_ASK_CALL_PHONE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.browserByLocateTv).setOnClickListener(this);
        findViewById(R.id.browserByNetTv).setOnClickListener(this);
        findViewById(R.id.dialogTestTv).setOnClickListener(this);
        findViewById(R.id.testRecyclerTv).setOnClickListener(this);
        findViewById(R.id.emojiShowTv).setOnClickListener(this);
        findViewById(R.id.refreshLoadRvTv).setOnClickListener(this);
        String mtyb = android.os.Build.BRAND;// 手机品牌
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.browserByLocateTv:
                checkSDPermission();
                break;
            case R.id.browserByNetTv:
                startActivity(new Intent(this, BoxBrowserActivity.class).putExtra("picFlag", BoxBrowserActivity.LOAD_PIC_NET));
                break;
            case R.id.dialogTestTv:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case R.id.testRecyclerTv:
                startActivity(new Intent(this, SuspensionRecyclerActivity.class));
                break;
            case R.id.emojiShowTv:
                startActivity(new Intent(this, EmojiShowActivity.class));
                break;
            case R.id.refreshLoadRvTv:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
        }
    }

    /**
     * 检查SD卡权限
     */
    private void checkSDPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_CALL_PHONE);
                return;
            } else {
                startActivity(new Intent(this, BoxBrowserActivity.class).putExtra("picFlag", BoxBrowserActivity.LOAD_PIC_LOCATE));
            }
        } else {
            startActivity(new Intent(this, BoxBrowserActivity.class).putExtra("picFlag", BoxBrowserActivity.LOAD_PIC_LOCATE));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //权限访问的回调
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(this, BoxBrowserActivity.class).putExtra("picFlag", BoxBrowserActivity.LOAD_PIC_LOCATE));
                } else {
                    Toast.makeText(MainActivity.this, "您拒绝了SD卡权限~", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private void getImageFileName() {

    }
}

package com.xbx.album.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xbx.album.R;
import com.xbx.album.adapter.BrowserVpAdapter;
import com.xbx.album.view.PhotoBrowserVP;

import java.util.List;

/**
 * Created by Eric on 2017/7/18.
 */

public class ImageBrowserActivity extends AppCompatActivity {
    private PhotoBrowserVP vpPhotoView;
    //本地或者网络加载的标识
    private int picLoadFlag;
    //图片地址的数据源
    private List<String> picList = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browser);
        vpPhotoView = (PhotoBrowserVP) findViewById(R.id.vpPhotoView);
        picLoadFlag = getIntent().getIntExtra("picFlag", 0);
        picList = getIntent().getExtras().getStringArrayList("BrowserPicList");
        vpPhotoView.setAdapter(new BrowserVpAdapter(this, picList, picLoadFlag));
        vpPhotoView.setCurrentItem(getIntent().getIntExtra("CurrentPosition", 0));
    }
}

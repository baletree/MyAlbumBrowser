package com.xbx.album.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xbx.album.R;
import com.xbx.album.adapter.BoxBrowserAdapter;
import com.xbx.album.bean.PhotoGroupInfo;
import com.xbx.album.bean.PhotoInfo;
import com.xbx.album.utils.PhotoDealUtil;
import com.xbx.album.utils.PicUtilTools;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Eric on 2017/7/18.
 */

public class BoxBrowserActivity extends AppCompatActivity {
    public static final int LOAD_PIC_LOCATE = 10001, LOAD_PIC_NET = 10002;
    private RecyclerView boxBrowserRv;
    private NestedScrollView nestScroll;
    private BoxBrowserAdapter boxBrowserAdapter = null;
    private int picFlag;
    private List<String> picList = null;
    private List<PhotoGroupInfo> groupInfoList = null;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LOAD_PIC_LOCATE:
                    if (boxBrowserAdapter != null) {
                        boxBrowserRv.setAdapter(boxBrowserAdapter);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_browser);
        picFlag = getIntent().getIntExtra("picFlag", 0);
        boxBrowserRv = (RecyclerView) findViewById(R.id.boxBrowserRv);
        nestScroll = (NestedScrollView) findViewById(R.id.nestScroll);
        LinearLayoutManager llM = new LinearLayoutManager(this);
        llM.setSmoothScrollbarEnabled(true);
        llM.setAutoMeasureEnabled(true);
        boxBrowserRv.setLayoutManager(llM);
        boxBrowserRv.setHasFixedSize(true);
        boxBrowserRv.setNestedScrollingEnabled(false);
        new Thread() {
            @Override
            public void run() {
                setPicResource();
            }
        }.start();
    }

    private void setPicResource() {
        switch (picFlag) {
            case LOAD_PIC_LOCATE:
                picList = PicUtilTools.getInstance().getImagePathFromSD();
                List<PhotoInfo> photoInfoList = PicUtilTools.getInstance().getLocalPhotoInfo(picList);
                groupInfoList = PhotoDealUtil.getInstance().groupByTime(photoInfoList, this);
                break;
        }
        boxBrowserAdapter = new BoxBrowserAdapter(this, groupInfoList);
        handler.sendEmptyMessage(LOAD_PIC_LOCATE);
    }
}

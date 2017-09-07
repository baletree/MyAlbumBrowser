package com.xbx.album.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.xbx.album.R;
import com.xbx.album.adapter.ListAdapter;
import com.xbx.album.utils.PicUtilTools;
import com.xbx.album.view.customrv.RefreshLoadListener;
import com.xbx.album.view.customrv.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 2017/9/6.
 */

public class RecyclerViewActivity extends AppCompatActivity implements RefreshLoadListener {
    private RefreshRecyclerView myRefreshRv;
    private List<String> dataList = null;
    private ListAdapter listAdapter = null;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    myRefreshRv.onRefreshComplete();
                    break;
                case 1:
                    myRefreshRv.onLoadMoreComplete();
                    PicUtilTools.XLog("handleMessage what = 1");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        myRefreshRv = (RefreshRecyclerView) findViewById(R.id.myRefreshRv);
        myRefreshRv.setLayoutManager(new LinearLayoutManager(this));
        myRefreshRv.setSupportRefresh(true);
        myRefreshRv.setSupportLoad(true);
        initData();
    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataList.add("item num " + i);
        }
        listAdapter = new ListAdapter(this, dataList);
        myRefreshRv.setAdapter(listAdapter);
        myRefreshRv.setOnRefreshLoadListener(this);
    }

    @Override
    public void onRefreshListener() {
        handler.sendEmptyMessageDelayed(0, 1500);
    }

    @Override
    public void onLoadMoreListener() {
        handler.sendEmptyMessageDelayed(1, 1500);
    }
}

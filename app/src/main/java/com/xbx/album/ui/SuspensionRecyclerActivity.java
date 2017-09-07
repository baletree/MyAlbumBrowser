package com.xbx.album.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.xbx.album.R;
import com.xbx.album.adapter.SuspenRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peng on 2017/8/17.
 */

public class SuspensionRecyclerActivity extends AppCompatActivity {
    private RecyclerView boxBrowserRv;
    private List<String> dataList = null;
    private SuspenRecyclerAdapter recyclerAdapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_browser);
        boxBrowserRv = (RecyclerView) findViewById(R.id.boxBrowserRv);
        boxBrowserRv.setLayoutManager(new LinearLayoutManager(this));
        setListData();
    }

    private void setListData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dataList.add("My is item " + i);
        }
        recyclerAdapter = new SuspenRecyclerAdapter(this, dataList);
        boxBrowserRv.setAdapter(recyclerAdapter);
        boxBrowserRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int vPos = ((LinearLayoutManager) boxBrowserRv.getLayoutManager()).findFirstVisibleItemPosition();
                Log.i("xbxAlbum", "vPos = " + vPos);
            }
        });

    }
}

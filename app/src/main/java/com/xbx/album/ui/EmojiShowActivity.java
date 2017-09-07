package com.xbx.album.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xbx.album.R;
import com.xbx.album.adapter.EmojiShowAdapter;
import com.xbx.album.utils.PagingScrollHelper;
import com.xbx.album.view.HorizontalPageLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by peng on 2017/8/21.
 */

public class EmojiShowActivity extends AppCompatActivity implements PagingScrollHelper.onPageChangeListener, View.OnClickListener {
    private RecyclerView emojiShowRv;
    private LinearLayout scrollPointLl;
    private ImageView showEmojiImg;
    private LinearLayout emojiSelectLl;
    private EditText chatInputEt;
    private PagingScrollHelper scrollHelper = null;
    private EmojiShowAdapter showAdapter = null;
    private HorizontalPageLayoutManager hPageLayoutManager = null;
    private List<Integer> emojiList = null;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    emojiSelectLl.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    emojiSelectLl.setVisibility(View.GONE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji_show);
        emojiShowRv = (RecyclerView) findViewById(R.id.emojiShowRv);
        scrollPointLl = (LinearLayout) findViewById(R.id.scrollPointLl);
        showEmojiImg = (ImageView) findViewById(R.id.showEmojiImg);
        emojiSelectLl = (LinearLayout) findViewById(R.id.emojiSelectLl);
        chatInputEt = (EditText) findViewById(R.id.chatInputEt);
        emojiSelectLl.setVisibility(View.GONE);
        showEmojiImg.setOnClickListener(this);
        chatInputEt.setOnClickListener(this);
        initData();
    }

    private void initData() {
        emojiList = new ArrayList<>();
        scrollHelper = new PagingScrollHelper();
        scrollHelper.setOnPageChangeListener(this);
        hPageLayoutManager = new HorizontalPageLayoutManager(3, 7);
        emojiShowRv.setLayoutManager(hPageLayoutManager);
        scrollHelper.setUpRecycleView(emojiShowRv);
        int[] emojiListInt = getResources().getIntArray(R.array.emojiUnicode);
        for (int i = 0; i < emojiListInt.length; i++) {
            emojiList.add(emojiListInt[i]);
        }
        emojiList.add(20, R.mipmap.lioatian_icon_shanchubiaoqing);
        emojiList.add(41, R.mipmap.lioatian_icon_shanchubiaoqing);
        emojiList.add(62, R.mipmap.lioatian_icon_shanchubiaoqing);
        showAdapter = new EmojiShowAdapter(this, emojiList);
        emojiShowRv.setAdapter(showAdapter);
        initPointLayout();
    }

    private void initPointLayout() {
        int pointCount = 0;
        if (emojiList.size() % 21 == 0)
            pointCount = emojiList.size() / 21;
        else
            pointCount = emojiList.size() / 21 + 1;
        for (int i = 0; i < pointCount; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.icon_lunbodian2);
            if (i == 0)
                imageView.setImageResource(R.mipmap.icon_lunbodian1);
            imageView.setPadding(10, 0, 10, 0);
            scrollPointLl.addView(imageView);
        }
    }

    @Override
    public void onPageChange(int index) {
        for (int i = 0; i < scrollPointLl.getChildCount(); i++) {
            ((ImageView) scrollPointLl.getChildAt(i)).setImageResource(R.mipmap.icon_lunbodian2);
        }
        ((ImageView) scrollPointLl.getChildAt(index)).setImageResource(R.mipmap.icon_lunbodian1);
    }

    /**
     * ***打开系统软键盘****
     */
    public void openInputMethod(final EditText editText) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) editText
                        .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, 200);
    }

    /**
     * *******关闭系统软键盘*****
     */
    public void closeInputMethod(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
        } finally {
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showEmojiImg:
                if (emojiSelectLl.getVisibility() == View.VISIBLE) {
                    emojiSelectLl.setVisibility(View.GONE);
                } else {
                    closeInputMethod(showEmojiImg);
                    handler.sendEmptyMessageDelayed(0, 150);
                }
                break;
            case R.id.chatInputEt:
                if (emojiSelectLl.getVisibility() == View.VISIBLE) {
//                    openInputMethod(chatInputEt);
                    handler.sendEmptyMessageDelayed(1, 50);
                }
                break;
        }
    }
}

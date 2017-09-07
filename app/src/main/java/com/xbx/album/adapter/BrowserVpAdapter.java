package com.xbx.album.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.xbx.album.R;
import com.xbx.album.ui.BoxBrowserActivity;

import java.io.File;
import java.util.List;

import uk.co.senab.photoview.PhotoView;


/**
 * Created by Eric on 2017/5/3.
 */

public class BrowserVpAdapter extends PagerAdapter {
    private List<String> imageUrls;
    private Context mContext;
    private int browserType;

    public BrowserVpAdapter(Context mContext, List<String> imageUrls, int browserType) {
        this.mContext = mContext;
        this.imageUrls = imageUrls;
        this.browserType = browserType;
    }

    @Override
    public int getCount() { // 获得size
        return imageUrls.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String imageUrl = imageUrls.get(position);
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.item_image_browser, null);
        final PhotoView img = (PhotoView) view.findViewById(R.id.item_photoView);
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.item_progressBar);
        //设置错误监听
        RequestListener<String, GlideDrawable> errorListener = new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        };
        switch (browserType) {
            case BoxBrowserActivity.LOAD_PIC_LOCATE:
                progressBar.setVisibility(View.GONE);
                Glide.with(mContext).load(Uri.fromFile(new File(imageUrl))).error(R.mipmap.loading_default).into(img);
                break;
            case BoxBrowserActivity.LOAD_PIC_NET:
                Glide.with(mContext).load(imageUrl).listener(errorListener).into(img);
                break;
        }
        container.addView(view);
        return view;
    }
}

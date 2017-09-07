package com.xbx.album.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.xbx.album.R;
import com.xbx.album.bean.PhotoGroupInfo;
import com.xbx.album.bean.PhotoInfo;
import com.xbx.album.ui.BoxBrowserActivity;
import com.xbx.album.ui.ImageBrowserActivity;
import com.xbx.album.utils.PicUtilTools;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.lankton.flowlayout.FlowLayout;

/**
 * Created by Eric on 2017/7/18.
 * 九宫格浏览图片
 */

public class BoxBrowserAdapter extends RecyclerView.Adapter<BoxBrowserAdapter.BaseViewHolder> {
    private List<PhotoGroupInfo> groupInfoList;
    private Context context;

    public BoxBrowserAdapter(Context context, List<PhotoGroupInfo> groupInfoList) {
        this.context = context;
        this.groupInfoList = groupInfoList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = new BaseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        final PhotoGroupInfo groupInfo = groupInfoList.get(position);
        holder.itemPbAlbumParentTime.setText(groupInfo.getTime());
        /*final RecyclerView rcl = holder.itemPbAlbumChildRv;
        rcl.setLayoutManager(new GridLayoutManager(context, 4));
        BoxChildBrowserAdapter childBrowserAdapter = new BoxChildBrowserAdapter(context, groupInfo.getPhotos());
        rcl.setAdapter(childBrowserAdapter);*/
        holder.itemPbAlbumChildFl.removeAllViews();
        PicUtilTools.XLog("start Time:" + PicUtilTools.getInstance().getTimeStampToStr(System.currentTimeMillis()));
        for (int i = 0; i < groupInfo.getPhotos().size(); i++) {
            ImageView imageView = dealBoxSize(groupInfo.getPhotos().get(i), i);
            holder.itemPbAlbumChildFl.addView(imageView);
        }
        PicUtilTools.XLog("end Time:" + PicUtilTools.getInstance().getTimeStampToStr(System.currentTimeMillis()));
    }

    @Override
    public int getItemCount() {
        return groupInfoList.size();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        TextView itemPbAlbumParentTime;
        FlowLayout itemPbAlbumChildFl;

        public BaseViewHolder(View itemView) {
            super(itemView);
            itemPbAlbumParentTime = (TextView) itemView.findViewById(R.id.itemPbAlbumParentTime);
            itemPbAlbumChildFl = (FlowLayout) itemView.findViewById(R.id.itemPbAlbumChildFl);
        }
    }

    private ImageView dealBoxSize(PhotoInfo photoInfo, int picPos) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(R.mipmap.load_default);
        Glide.with(context).load(Uri.fromFile(new File(photoInfo.getPhotoPath()))).error(R.mipmap.loading_default).into(imageView);
        getItemSize(4, imageView, picPos);
        return imageView;
    }

    private void getItemSize(int imageNum, ImageView itemRcv, int picPos) {
        int widthScreen = PicUtilTools.getScreenWidth(context);
        int ivWidth = widthScreen / imageNum;
        if (picPos % imageNum == imageNum - 1)
            itemRcv.setPadding(0, PicUtilTools.getInstance().dip2px(context, 3f), 0, 0);
        else
            itemRcv.setPadding(0, PicUtilTools.getInstance().dip2px(context, 3f), PicUtilTools.getInstance().dip2px(context, 3f), 0);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ivWidth, ivWidth);
        itemRcv.setLayoutParams(params);
    }
}

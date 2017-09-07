package com.xbx.album.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.xbx.album.R;
import com.xbx.album.bean.PhotoGroupInfo;
import com.xbx.album.bean.PhotoInfo;
import com.xbx.album.utils.PicUtilTools;

import java.io.File;
import java.util.List;

/**
 * Created by Eric on 2017/7/18.
 * 九宫格浏览图片
 */

public class BoxChildBrowserAdapter extends RecyclerView.Adapter<BoxChildBrowserAdapter.BaseViewHolder> {
    private List<PhotoInfo> photoInfoList;
    private Context context;

    public BoxChildBrowserAdapter(Context context, List<PhotoInfo> photoInfoList) {
        this.context = context;
        this.photoInfoList = photoInfoList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = new BaseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_box_child, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        PicUtilTools.getInstance().getImageHorizontalSize(context, holder.itemBoxBroImg, 4, position);
        Glide.with(context).load(Uri.fromFile(new File(photoInfoList.get(position).getPhotoPath()))).error(R.mipmap.loading_default).into(holder.itemBoxBroImg);
        holder.itemBoxBroImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                /*bundle.putSerializable("BrowserPicList", (Serializable) picUrlList);
                context.startActivity(new Intent(context, ImageBrowserActivity.class).putExtra("picFlag", loadFlag)
                        .putExtras(bundle).putExtra("CurrentPosition", position));*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoInfoList.size();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        ImageView itemBoxBroImg;

        public BaseViewHolder(View itemView) {
            super(itemView);
            itemBoxBroImg = (ImageView) itemView.findViewById(R.id.itemBoxBroImg);
        }
    }
}

package com.xbx.album.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xbx.album.R;
import com.xbx.album.ui.BoxBrowserActivity;
import com.xbx.album.ui.ImageBrowserActivity;
import com.xbx.album.utils.PicUtilTools;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Eric on 2017/7/18.
 * 九宫格浏览图片
 */

public class SuspenRecyclerAdapter extends RecyclerView.Adapter<SuspenRecyclerAdapter.BaseViewHolder> {
    private Context context;
    private List<String> dataList;

    public SuspenRecyclerAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = new BaseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_suspen_recy, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        ((TextView) holder.itemTxtTv).setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        TextView itemTxtTv;

        public BaseViewHolder(View itemView) {
            super(itemView);
            itemTxtTv = (TextView) itemView.findViewById(R.id.itemTxtTv);
        }
    }
}

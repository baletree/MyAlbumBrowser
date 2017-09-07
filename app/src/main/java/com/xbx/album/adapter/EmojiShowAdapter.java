package com.xbx.album.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xbx.album.R;

import java.util.List;

/**
 * Created by peng on 2017/8/24.
 */

public class EmojiShowAdapter extends RecyclerView.Adapter<EmojiShowAdapter.EmojiViewHolder> {
    private Context context;
    private List<Integer> emojiList;

    public EmojiShowAdapter(Context context, List<Integer> emojiList) {
        this.context = context;
        this.emojiList = emojiList;
    }

    @Override
    public EmojiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EmojiViewHolder holder = new EmojiViewHolder(LayoutInflater.from(context).inflate(R.layout.item_emoji, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(EmojiViewHolder holder, int position) {
        holder.clearEmojiImg.setVisibility(View.GONE);
        if (position != 0 && (position + 1) % 21 == 0) {
            holder.clearEmojiImg.setBackgroundResource(R.mipmap.lioatian_icon_shanchubiaoqing);
            holder.clearEmojiImg.setVisibility(View.VISIBLE);
            Log.i("xbxAlbum", "position = " + position);
        } else {
            String emojiStr = new String(Character.toChars(emojiList.get(position)));
            holder.itemEmojiShowTv.setText(emojiStr);
        }
    }

    @Override
    public int getItemCount() {
        return emojiList.size();
    }

    public class EmojiViewHolder extends RecyclerView.ViewHolder {
        private TextView itemEmojiShowTv;
        private ImageView clearEmojiImg;

        public EmojiViewHolder(View itemView) {
            super(itemView);
            itemEmojiShowTv = (TextView) itemView.findViewById(R.id.itemEmojiShowTv);
            clearEmojiImg = (ImageView) itemView.findViewById(R.id.clearEmojiImg);
        }
    }
}

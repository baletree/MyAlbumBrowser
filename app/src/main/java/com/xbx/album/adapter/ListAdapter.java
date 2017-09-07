package com.xbx.album.adapter;

import android.content.Context;
import android.widget.TextView;

import com.xbx.album.R;
import com.xbx.album.view.customrv.BaseAdapterCus;

import java.util.List;

/**
 * Created by Eric on 2017/9/6.
 */

public class ListAdapter extends BaseAdapterCus {
    public ListAdapter(Context context, List dataList) {
        super(context, dataList);
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_list_ada;
    }

    @Override
    public void bindViewData(BaseViewHolder holder, int position) {
        ((TextView) holder.getView(R.id.itemListTv)).setText(dataList.get(position) + "");
    }
}

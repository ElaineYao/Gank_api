package com.elainedv.Classification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elainedv.Gank.R;

import java.util.List;

/**
 * Created by Elaine on 18/2/18.
 */

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.ViewHolder> {

    List<ResponseBean.ResultBean> contents;
    Context mContext;

    public AdapterRV(List<ResponseBean.ResultBean> contents) {
        this.contents = contents;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResponseBean.ResultBean content = contents.get(position);
        holder.tv.setText(content.getDesc());
        if(content.getImages()!=null){
            Glide.with(mContext).load(content.getImages().get(0)).into(holder.image);
        }else {
            holder.image.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.class_iv);
            tv = (TextView) itemView.findViewById(R.id.class_tv);
        }
    }
}

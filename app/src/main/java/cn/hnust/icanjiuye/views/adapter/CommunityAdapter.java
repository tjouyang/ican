package cn.hnust.icanjiuye.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.hnust.icanjiuye.R;
import cn.hnust.icanjiuye.entities.Community;
import cn.hnust.icanjiuye.views.PeriscopeLayout;

/**
 * Created by tjouyang on 2017/6/3.
 * 爱心社区
 */

public class CommunityAdapter extends RecyclerView.Adapter {
    private List<Community> data;

    public CommunityAdapter(List<Community> pData) {
        data = pData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new Holder(layoutInflater.inflate(R.layout.item_community, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Holder h = (Holder) holder;
        Community c = data.get(position);
        h.title.setText(c.getTitle());
        h.content.setText(c.getContent());
        h.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PeriscopeLayout)h.itemView).addFavor();
                h.heart.setImageResource(R.mipmap.heart_pressed);
            }
        });
    }

    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    private class Holder extends RecyclerView.ViewHolder{
        TextView content;
        TextView title;
        ImageView heart;

        Holder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.content);
            title = (TextView) itemView.findViewById(R.id.title);
            heart = (ImageView) itemView.findViewById(R.id.heart);

        }
    }

    public void refreshData(List<Community> pData){
        data.clear();
        data.addAll(pData);
        notifyDataSetChanged();
    }
}

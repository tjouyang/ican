package cn.hnust.icanjiuye.views.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.hnust.icanjiuye.DetailActivity;
import cn.hnust.icanjiuye.R;
import cn.hnust.icanjiuye.entities.StartJobInfo;

import static cn.hnust.icanjiuye.utils.Paras.startJobUrl;

/**
 * Created by tjouyang on 2017/6/2.
 * 创业适配器
 */

public class StartJobAdapter extends RecyclerView.Adapter {
    private final String HEAD = "【";
    private final String TAIL = "】";
    private List<StartJobInfo> data;

    public StartJobAdapter(List<StartJobInfo> pData) {
        data = pData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new Holder(layoutInflater.inflate(R.layout.item_start_job, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder h = (Holder) holder;
        final StartJobInfo sji = data.get(position);
        h.flag.setText(addTAG(sji.getFlag()));
        TextPaint tp = h.flag.getPaint();
        tp.setFakeBoldText(true);

        h.title.setText(sji.getTitle());
        h.come.setText(sji.getCome());
        h.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查看详情
                Intent i = new Intent();
                i.setClass(view.getContext(), DetailActivity.class);
                i.putExtra(startJobUrl, sji.getUrl());
                view.getContext().startActivity(i);
            }
        });
    }

    private String addTAG(String flag) {
        return HEAD + flag + TAIL;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    private class Holder extends RecyclerView.ViewHolder {
        TextView flag;
        TextView title;
        TextView come;
        Holder(View view) {
            super(view);
            flag = (TextView) view.findViewById(R.id.flag);
            title = (TextView) view.findViewById(R.id.title);
            come = (TextView) view.findViewById(R.id.come);
        }
    }

    public void refreshData(List<StartJobInfo> pData){
        data.clear();
        data.addAll(pData);
        notifyDataSetChanged();
    }
}

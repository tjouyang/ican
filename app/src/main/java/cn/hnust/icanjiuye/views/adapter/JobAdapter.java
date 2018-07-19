package cn.hnust.icanjiuye.views.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.hnust.icanjiuye.JobDetailActivity;
import cn.hnust.icanjiuye.R;
import cn.hnust.icanjiuye.entities.JobInfo;
import cn.hnust.icanjiuye.entities.StartJobInfo;

import static cn.hnust.icanjiuye.utils.Paras.jobContent;
import static cn.hnust.icanjiuye.utils.Paras.jobTitle;

/**
 * Created by tjouyang on 2017/6/3.
 * 求职适配
 */

public class JobAdapter extends RecyclerView.Adapter {
    private List<JobInfo> data;

    public JobAdapter(List<JobInfo> pData){
        data = pData;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new Holder(layoutInflater.inflate(R.layout.item_job_info, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder h = (Holder) holder;
        final JobInfo j = data.get(position);
        h.title.setText(j.getTitle());
        h.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), JobDetailActivity.class);
                intent.putExtra(jobContent, j.getContent());
                intent.putExtra(jobTitle, j.getTitle());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    private class Holder extends RecyclerView.ViewHolder{
        TextView title;
        Holder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    public void refreshData(List<JobInfo> pData){
        data.clear();
        data.addAll(pData);
        notifyDataSetChanged();
    }
}

package com.example.ady.findingyourgitbubprofile;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.ady.findingyourgitbubprofile.model.github.Item;

import java.util.ArrayList;
import java.util.List;



public class Recycleadapter extends RecyclerView.Adapter<Recycleadapter.ViewHolder> {
    List<Item> list = new ArrayList<>();



    public Recycleadapter(List<Item> list) {
        this.list = list;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleviewlayout,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (list.get(position) != null);
        holder.recycleDisplay.setText(list.get(position).getRepo());
        holder.full_name.setText(list.get(position).getFull_name());
        holder.id.setText(list.get(position).getId());


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView recycleDisplay;
        private final TextView full_name;
        private final TextView id;
        public ViewHolder(View itemView) {
            super(itemView);
            recycleDisplay = itemView.findViewById(R.id.displayList);
            full_name = itemView.findViewById(R.id.fullname);
            id = itemView.findViewById(R.id.id);

        }
    }


}

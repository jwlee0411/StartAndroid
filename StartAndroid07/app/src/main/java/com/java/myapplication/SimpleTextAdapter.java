package com.java.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ItemViewHolder> {

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView);
        }
    }
    private ArrayList<String> mData = null;
    SimpleTextAdapter(ArrayList<String> list){
        mData = list;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_item,parent,false);
        return new SimpleTextAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleTextAdapter.ItemViewHolder holder, int position) {
        String text = mData.get(position);
        holder.textView1.setText(text);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

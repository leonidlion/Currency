package com.currency.dev.leo.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {
    protected List<T> adapterData;

    protected BaseRecyclerAdapter() {
        adapterData = new ArrayList<>();
    }

    public void addItem(T item){
        adapterData.add(item);
        notifyItemInserted(adapterData.size() - 1);
    }

    public void addItem(List<T> itemList){
        int size = adapterData.size();
        adapterData.addAll(itemList);
        notifyItemRangeInserted(size, adapterData.size());
    }

    public void clearData(){
        int size = adapterData.size();
        adapterData.clear();
        notifyItemRangeRemoved(0, size);
    }

    protected T getItem(VH holder){
        return adapterData.get(holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return adapterData.size();
    }
}

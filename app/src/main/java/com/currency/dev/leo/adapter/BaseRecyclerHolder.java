package com.currency.dev.leo.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

abstract class BaseRecyclerHolder<B extends ViewDataBinding, T> extends RecyclerView.ViewHolder {
    B binding;

    BaseRecyclerHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    protected abstract void onBind(T data);
}

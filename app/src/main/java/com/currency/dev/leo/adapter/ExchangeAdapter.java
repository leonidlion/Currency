package com.currency.dev.leo.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.currency.dev.leo.R;
import com.currency.dev.leo.data.model.Rate;
import com.currency.dev.leo.databinding.ItemExchangeBinding;

public class ExchangeAdapter extends BaseRecyclerAdapter<ExchangeAdapter.ExchangeHolder, Rate> {

    @NonNull
    @Override
    public ExchangeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ExchangeHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exchange, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExchangeHolder exchangeHolder, int i) {
        exchangeHolder.onBind(adapterData.get(i));
    }

    class ExchangeHolder extends BaseRecyclerHolder<ItemExchangeBinding, Rate>{

        ExchangeHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Rate data) {
            binding.setRate(data);
            binding.executePendingBindings();
        }
    }
}

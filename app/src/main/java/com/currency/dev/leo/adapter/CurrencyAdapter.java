package com.currency.dev.leo.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.currency.dev.leo.R;
import com.currency.dev.leo.data.local.entity.Currency;
import com.currency.dev.leo.databinding.ItemCurrencyBinding;

import java.util.ArrayList;

public class CurrencyAdapter extends BaseRecyclerAdapter<CurrencyAdapter.CurrencyHolder, Currency> {
    private ArrayList<String> selectedCurrency = new ArrayList<>();

    @NonNull
    @Override
    public CurrencyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CurrencyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_currency, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyHolder currencyHolder, int i) {
        currencyHolder.onBind(adapterData.get(i));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public ArrayList<String> getSelectedCurrency() {
        return selectedCurrency;
    }

    class CurrencyHolder extends BaseRecyclerHolder<ItemCurrencyBinding, Currency>{
        CurrencyHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Currency data) {
            binding.setCurrency(data);
            binding.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) selectedCurrency.add(data.getCurrencyName());
                else selectedCurrency.remove(data.getCurrencyName());
            });
            binding.executePendingBindings();
        }
    }
}

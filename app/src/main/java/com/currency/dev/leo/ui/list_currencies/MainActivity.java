package com.currency.dev.leo.ui.list_currencies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.currency.dev.leo.R;
import com.currency.dev.leo.adapter.CurrencyAdapter;
import com.currency.dev.leo.databinding.ActivityMainBinding;
import com.currency.dev.leo.enums.BundleKeys;
import com.currency.dev.leo.ui.BaseActivity;
import com.currency.dev.leo.ui.exchange.ExchangeActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    private CurrencyAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showLoadingProgress();

        initRecycler();

        viewModel.getCurrencyLiveData().observe(this, currencies -> {
            if (currencies != null)
                adapter.addItem(currencies);
            hideLoadingProgress();
        });
    }

    private void initRecycler(){
        if (adapter == null)
            adapter = new CurrencyAdapter();
        binding.setCurrencyAdapter(adapter);
        binding.setActivity(this);
    }

    public void onInfoClick(ArrayList<String> currencies){
        if (currencies.isEmpty()){
            showToast(R.string.choice_at_least);
            return;
        }
        startExchangeActivity(currencies);
    }

    private void startExchangeActivity(ArrayList<String> currencies){
        Intent intent = new Intent(this, ExchangeActivity.class);
        intent.putStringArrayListExtra(BundleKeys.LIST_CURRENCY.name(), currencies);
        startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }
}

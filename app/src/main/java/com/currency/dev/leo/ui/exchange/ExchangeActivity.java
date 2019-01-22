package com.currency.dev.leo.ui.exchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.currency.dev.leo.R;
import com.currency.dev.leo.adapter.ExchangeAdapter;
import com.currency.dev.leo.databinding.ActivityExchangeBinding;
import com.currency.dev.leo.enums.BundleKeys;
import com.currency.dev.leo.ui.BaseActivity;

import java.util.List;

public class ExchangeActivity extends BaseActivity<ActivityExchangeBinding, ExchangeViewModel> {
    private ExchangeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showLoadingProgress();

        initAdapter();

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent().getExtras() != null)
            parseIntent(getIntent().getExtras());
    }

    private void initAdapter(){
        adapter = new ExchangeAdapter();
        binding.setAdapter(adapter);
    }

    private void parseIntent(Bundle extras){
        List<String> currencies = extras.getStringArrayList(BundleKeys.LIST_CURRENCY.name());
        if (currencies != null && !currencies.isEmpty())
            viewModel.loadExchangeRates(currencies).observe(this, rates -> {
                if (rates != null && !rates.isEmpty())
                    adapter.addItem(rates);
                hideLoadingProgress();
            });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_exchange;
    }

    @Override
    protected Class<ExchangeViewModel> getViewModelClass() {
        return ExchangeViewModel.class;
    }
}

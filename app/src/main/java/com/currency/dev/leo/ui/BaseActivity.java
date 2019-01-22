package com.currency.dev.leo.ui;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.currency.dev.leo.R;

public abstract class BaseActivity<B extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {
    @Nullable
    protected Toolbar toolbar;

    protected VM viewModel;
    protected B binding;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading));

        binding = DataBindingUtil.setContentView(this, getLayoutRes());

        viewModel = ViewModelProviders.of(this).get(getViewModelClass());

        if (userToolbar() && getToolbarId() != 0) {
            if (findViewById(getToolbarId()) instanceof Toolbar) {
                toolbar = findViewById(getToolbarId());
                setSupportActionBar(toolbar);
            }
        }
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract Class<VM> getViewModelClass();

    protected boolean userToolbar(){
        return false;
    }

    @IdRes
    protected int getToolbarId(){
        return 0;
    }

    protected void showLoadingProgress(){
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    protected void hideLoadingProgress(){
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

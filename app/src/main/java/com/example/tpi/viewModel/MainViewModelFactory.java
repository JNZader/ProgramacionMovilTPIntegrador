package com.example.tpi.viewModel;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private Context context;

    public MainViewModelFactory(Context context) {
        this.context = context;
    }
}

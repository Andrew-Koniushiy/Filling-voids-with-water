package com.example.fillingvoidswithwater;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.fillingvoidswithwater.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        
        ViewModelProvider provider = new ViewModelProvider(this);
        DataViewModel viewModel = provider.get(DataViewModel.class);
        binding.content.setData(viewModel);
        setSupportActionBar(binding.toolbar);
        binding.fab.setOnClickListener(view -> provider.get(DataViewModel.class).generateBlocks());
    }
    
}

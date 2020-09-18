package com.gmail.ivan.morozyk.cocktailbar.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.ivan.morozyk.cocktailbar.R;
import com.gmail.ivan.morozyk.cocktailbar.ui.adapter.FilterAdapter;

public class FiltersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        RecyclerView filterRecycler = findViewById(R.id.cocktail_filters_recycler);
        filterRecycler.setAdapter(new FilterAdapter());

        findViewById(R.id.apply_filters_button).setOnClickListener(view -> onBackPressed());
    }
}

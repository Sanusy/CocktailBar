package com.gmail.ivan.morozyk.cocktailbar.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.ivan.morozyk.cocktailbar.R;
import com.gmail.ivan.morozyk.cocktailbar.data.entity.Filter;

import java.util.Objects;

public class FilterHolder extends RecyclerView.ViewHolder {

    @Nullable
    private TextView filterTitle;

    @Nullable
    private ImageView filterChecked;

    @Nullable
    private Filter filter;

    public FilterHolder(@NonNull View itemView) {
        super(itemView);

        filterTitle = itemView.findViewById(R.id.filter_title);

        filterChecked = itemView.findViewById(R.id.filter_checked_image);

        itemView.setOnClickListener(view -> {
            filter.setChecked(!filter.isChecked());
            Objects.requireNonNull(filterChecked).setVisibility(filter.isChecked() ? View.VISIBLE : View.INVISIBLE);
        });
    }

    public void bind(@NonNull Filter filter) {
        this.filter = filter;

        Objects.requireNonNull(filterTitle).setText(filter.getFilterTitle());
        Objects.requireNonNull(filterChecked).setVisibility(filter.isChecked() ? View.VISIBLE : View.INVISIBLE);
    }
}

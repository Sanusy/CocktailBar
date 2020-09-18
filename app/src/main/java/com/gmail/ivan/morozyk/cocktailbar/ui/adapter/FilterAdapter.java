package com.gmail.ivan.morozyk.cocktailbar.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.ivan.morozyk.cocktailbar.data.FilterContainer;
import com.gmail.ivan.morozyk.cocktailbar.R;
import com.gmail.ivan.morozyk.cocktailbar.data.entity.Filter;
import com.gmail.ivan.morozyk.cocktailbar.ui.holder.FilterHolder;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterHolder> {

    @NonNull
    private final List<Filter> filters = FilterContainer.getInstance().getFilters();

    @NonNull
    @Override
    public FilterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new FilterHolder(inflater.inflate(R.layout.item_filter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilterHolder holder, int position) {
        holder.bind(filters.get(position));
    }

    @Override
    public int getItemCount() {
        return filters.size();
    }
}

package com.gmail.ivan.morozyk.cocktailbar.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.ivan.morozyk.cocktailbar.R;
import com.gmail.ivan.morozyk.cocktailbar.data.entity.ItemType;
import com.gmail.ivan.morozyk.cocktailbar.ui.holder.BaseHolder;
import com.gmail.ivan.morozyk.cocktailbar.ui.holder.CocktailsHolder;
import com.gmail.ivan.morozyk.cocktailbar.ui.holder.GroupTitleViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CocktailsAdapter extends RecyclerView.Adapter<BaseHolder> {

    @NonNull
    private final List<ItemType> cocktails = new ArrayList<>();

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == ItemType.Item.FILTER) {
            return new GroupTitleViewHolder(inflater.inflate(R.layout.item_cocktail_group_title, parent, false));
        } else {
            return new CocktailsHolder(inflater.inflate(R.layout.item_cocktail, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        holder.bind(cocktails.get(position));
    }

    @Override
    public int getItemCount() {
        return cocktails.size();
    }

    @ItemType.Item
    @Override
    public int getItemViewType(int position) {
        return cocktails.get(position)
                .getType();
    }

    public void add(@NonNull List<ItemType> items) {
        cocktails.addAll(items);
        notifyDataSetChanged();
    }

    public void clear() {
        cocktails.clear();
        notifyDataSetChanged();
    }
}

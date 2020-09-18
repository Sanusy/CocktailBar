package com.gmail.ivan.morozyk.cocktailbar.ui.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.ivan.morozyk.cocktailbar.data.entity.ItemType;

public abstract class BaseHolder extends RecyclerView.ViewHolder {

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(@NonNull ItemType item);
}

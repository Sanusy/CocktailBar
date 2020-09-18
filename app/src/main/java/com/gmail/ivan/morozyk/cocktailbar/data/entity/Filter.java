package com.gmail.ivan.morozyk.cocktailbar.data.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Filter implements ItemType {

    @NonNull
    @SerializedName("strCategory")
    private final String filterTitle;

    private boolean checked = true;

    public Filter(@NotNull String filterTitle) {
        this.filterTitle = filterTitle;
    }

    @NotNull
    public String getFilterTitle() {
        return filterTitle;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int getType() {
        return Item.FILTER;
    }
}
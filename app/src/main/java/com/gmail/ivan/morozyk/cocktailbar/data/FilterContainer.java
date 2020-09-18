package com.gmail.ivan.morozyk.cocktailbar.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gmail.ivan.morozyk.cocktailbar.data.entity.Filter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FilterContainer {

    @Nullable
    private static FilterContainer filterContainer;

    @NonNull
    private final List<Filter> filters = new ArrayList<>();

    private FilterContainer() {
    }

    @NotNull
    public List<Filter> getFilters() {
        return filters;
    }

    @NonNull
    public static FilterContainer getInstance() {
        if (filterContainer == null) {
            filterContainer = new FilterContainer();
        }
        return filterContainer;
    }
}

package com.gmail.ivan.morozyk.cocktailbar.ui.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gmail.ivan.morozyk.cocktailbar.R;
import com.gmail.ivan.morozyk.cocktailbar.data.entity.Filter;
import com.gmail.ivan.morozyk.cocktailbar.data.entity.ItemType;

import java.util.Objects;

public class GroupTitleViewHolder extends BaseHolder {

    @Nullable
    private TextView groupTitle;

    public GroupTitleViewHolder(@NonNull View itemView) {
        super(itemView);

        groupTitle = itemView.findViewById(R.id.cocktail_group_title_text);
    }

    @Override
    public void bind(@NonNull ItemType item) {
        String title = ((Filter) item).getFilterTitle();
        Objects.requireNonNull(groupTitle).setText(title);
    }
}

package com.gmail.ivan.morozyk.cocktailbar.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.gmail.ivan.morozyk.cocktailbar.R;
import com.gmail.ivan.morozyk.cocktailbar.data.entity.Cocktail;
import com.gmail.ivan.morozyk.cocktailbar.data.entity.ItemType;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CocktailsHolder extends BaseHolder {

    @Nullable
    private ImageView cocktailPicture;

    @Nullable
    private TextView cocktailName;

    public CocktailsHolder(@NonNull View itemView) {
        super(itemView);

        cocktailPicture = itemView.findViewById(R.id.cocktail_picture);
        cocktailName = itemView.findViewById(R.id.cocktail_title);
    }

    @Override
    public void bind(@NotNull ItemType item) {
        Cocktail cocktail = (Cocktail) item;

        Glide.with(itemView).load(cocktail.getCocktailPictureLink()).into(Objects.requireNonNull(cocktailPicture));

        Objects.requireNonNull(cocktailName).setText(cocktail.getCocktailName());
    }
}

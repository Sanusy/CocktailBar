package com.gmail.ivan.morozyk.cocktailbar.data.entity;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Cocktail implements ItemType {

    @NonNull
    @SerializedName("strDrink")
    private final String cocktailName;

    @NonNull
    @SerializedName("strDrinkThumb")
    private final String cocktailPictureLink;

    public Cocktail(@NotNull String cocktailName, @NotNull String cocktailPictureLink) {
        this.cocktailName = cocktailName;
        this.cocktailPictureLink = cocktailPictureLink;
    }

    @NotNull
    public String getCocktailName() {
        return cocktailName;
    }

    @NotNull
    public String getCocktailPictureLink() {
        return cocktailPictureLink;
    }

    @Override
    public int getType() {
        return Item.COCKTAIL;
    }

}

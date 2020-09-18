package com.gmail.ivan.morozyk.cocktailbar.data.entity;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface ItemType {

    @Item
    int getType();

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Item.FILTER, Item.COCKTAIL})
    @interface Item {
        int FILTER = 0;
        int COCKTAIL = 1;
    }
}

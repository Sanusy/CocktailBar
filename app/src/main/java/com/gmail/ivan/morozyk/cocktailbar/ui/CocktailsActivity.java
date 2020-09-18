package com.gmail.ivan.morozyk.cocktailbar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.ivan.morozyk.cocktailbar.R;
import com.gmail.ivan.morozyk.cocktailbar.ui.adapter.CocktailsAdapter;
import com.gmail.ivan.morozyk.cocktailbar.data.FilterContainer;
import com.gmail.ivan.morozyk.cocktailbar.data.entity.Cocktail;
import com.gmail.ivan.morozyk.cocktailbar.data.entity.Filter;
import com.gmail.ivan.morozyk.cocktailbar.data.entity.ItemType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CocktailsActivity extends AppCompatActivity {

    @NonNull
    private static final String TAG = CocktailsActivity.class.getSimpleName();

    @NonNull
    private static final String QUERY = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=";

    @Nullable
    private CocktailsAdapter cocktailsAdapter;

    @Nullable
    private List<Filter> activeFilters;

    private int filterNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails);

        RecyclerView cocktailsRecycler = findViewById(R.id.cocktails_recycler);
        cocktailsAdapter = new CocktailsAdapter();
        Objects.requireNonNull(cocktailsRecycler).setAdapter(cocktailsAdapter);


        cocktailsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && (filterNumber + 1) <= Objects.requireNonNull(activeFilters).size()) {
                    getPage();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Objects.requireNonNull(cocktailsAdapter).clear();
        filterNumber = 0;
        activeFilters = new ArrayList<>();
        for (Filter filter : FilterContainer.getInstance().getFilters()) {
            if (filter.isChecked()) {
                activeFilters.add(filter);
            }
        }

        getPage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cocktails_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.show_filters_item) {
            startActivity(new Intent(this, FiltersActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getPage() {
        OkHttpClient okHttpClient = new OkHttpClient();

        Filter filter = Objects.requireNonNull(activeFilters).get(filterNumber++);

        Request request = new Request.Builder()
                .url(QUERY + filter.getFilterTitle())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (Reader reader = new InputStreamReader(new ByteArrayInputStream(Objects.requireNonNull(response.body()).string().getBytes()), StandardCharsets.UTF_8)) {
                    Gson gson = new GsonBuilder().create();

                    List<ItemType> items = new ArrayList<>();

                    JsonElement rootElement = gson.fromJson(reader, JsonObject.class).get("drinks");
                    Cocktail[] cocktail = gson.fromJson(rootElement, Cocktail[].class);

                    items.add(filter);
                    items.addAll(Arrays.asList(cocktail));
                    runOnUiThread(() -> Objects.requireNonNull(cocktailsAdapter).add(items));
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "Failed to load cocktails: \n", e);
            }
        });
    }
}

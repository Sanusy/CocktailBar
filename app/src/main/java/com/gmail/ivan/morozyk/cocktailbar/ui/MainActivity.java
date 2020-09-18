package com.gmail.ivan.morozyk.cocktailbar.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gmail.ivan.morozyk.cocktailbar.R;
import com.gmail.ivan.morozyk.cocktailbar.data.FilterContainer;
import com.gmail.ivan.morozyk.cocktailbar.data.entity.Filter;
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
import java.util.Arrays;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @NonNull
    private static final String TAG = MainActivity.class.getSimpleName();

    @NonNull
    private final OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            getFilters();
        } else {
            findViewById(R.id.no_internet_text).setVisibility(View.VISIBLE);
            findViewById(R.id.loading_progress_bar).setVisibility(View.GONE);
        }
    }

    private void getFilters() {
        Request request = new Request.Builder()
                .url("https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (Reader reader = new InputStreamReader(new ByteArrayInputStream(Objects.requireNonNull(response.body()).string().getBytes()), StandardCharsets.UTF_8)) {
                    Gson gson = new GsonBuilder().create();

                    JsonElement rootElement = gson.fromJson(reader, JsonObject.class).get("drinks");
                    Filter[] filters = gson.fromJson(rootElement, Filter[].class);
                    for (Filter filter : filters) {
                        filter.setChecked(true);
                    }
                    FilterContainer.getInstance().getFilters().addAll(Arrays.asList(filters));

                    startActivity(new Intent(MainActivity.this, CocktailsActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "Failed to load filters: \n", e);
            }
        });
    }
}
package com.example.com01.githubevents.Api;

import android.content.Context;

import com.example.com01.githubevents.R;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Apis {
    Context context;
    static String BASE_URL;
    public Apis(Context context) {
        this.context = context;
        BASE_URL = context.getString(R.string.main_host_url);
    }
    public static ApiService apiService() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ApiService.class);
    }
}

package com.example.mobdev12;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static RetrofitFactory mInstance;
    private RetrofitFactory(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitFactory getInstance(){
        if(mInstance == null) mInstance = new RetrofitFactory();
        return mInstance;
    }

    public PlaceholderAPI getAPI(){
        return retrofit.create(PlaceholderAPI.class);
    }
}

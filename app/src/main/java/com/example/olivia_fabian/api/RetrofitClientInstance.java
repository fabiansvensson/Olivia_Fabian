package com.example.olivia_fabian.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://www.v2msoft.com/clientes/lasalle/android/";

    public void onStart(final Callback<List<RetroFlats>> listener){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FlatsService api = retrofit.create(FlatsService.class);

        Call<List<RetroFlats>> call = api.getFlats();
        call.enqueue(listener);
    }

}

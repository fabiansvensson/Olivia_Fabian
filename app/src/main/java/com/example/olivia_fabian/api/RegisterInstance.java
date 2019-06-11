package com.example.olivia_fabian.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://www.v2msoft.com/clientes/lasalle/android/";
    //private static final String BASE_URL = "https://hookb.in/1g7b99PBM2";

    public void onStart(String name, String email, String password, final Callback<PostUser> listener){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        UserService api = retrofit.create(UserService.class);

        Call<PostUser> call = api.createUser(name, email, password);
        call.enqueue(listener);
    }

}

package com.example.olivia_fabian.api;

import com.example.olivia_fabian.json.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlatsService {
    @GET("flat.php")
    Call<List<RetroFlats>> getFlats();
}

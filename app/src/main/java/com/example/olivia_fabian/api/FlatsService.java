package com.example.olivia_fabian.api;

import com.example.olivia_fabian.pojo.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

public interface FlatsService {
    @GET("flat.php")
    Call<List<RetroFlats>> getFlats();
    @POST("user.php")
    @FormUrlEncoded
    //@Headers("Content-Type: application/x-www-form-urlencoded")
    Call<PostUser> createUser(@Field("name") String name,
                          @Field("email") String email,
                          @Field("password") String password);

    @POST("login.php")
    @FormUrlEncoded
    //@Headers("Content-Type: application/x-www-form-urlencoded")
    Call<PostLogin> findUser(@Field("email") String email,
                          @Field("passwork") String password);

    /*@POST("user.php")
    @FormUrlEncoded
    Call<User> createUser(@Body User user);
    */
}

package com.example.olivia_fabian.api;



import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST("user.php")
    @FormUrlEncoded
    //@Headers("Content-Type: application/x-www-form-urlencoded")
    Call<PostUser> createUser(@Field("name") String name,
                              @Field("email") String email,
                              @Field("password") String password);

}

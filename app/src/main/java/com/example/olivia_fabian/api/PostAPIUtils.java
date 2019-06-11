package com.example.olivia_fabian.api;

public class PostAPIUtils {
    private PostAPIUtils() {}

    public static final String BASE_URL = "https://www.v2msoft.com/clientes/lasalle/android/";

    public static FlatsService getAPIService() {

        return PostRetrofitClient.getClient(BASE_URL).create(FlatsService.class);
    }
}

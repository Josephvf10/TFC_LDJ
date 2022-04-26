package com.dam.proyectotfc.retrofit;

import com.dam.proyectotfc.model.Results;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIRestService {
    public static final String BASE_URL = "https://www.giantbomb.com/api/";
    public static final String KEY = "540b7d99b6387edb7ea6d491c2a5948fe6bb1cc7";
    public static final String FORMAT = "json";
    public static final String RESOURCES = "game";
    @Headers("content-type: application/json")
    @GET("search/")
    Call<ArrayList<Results>> getJuegos(@Query("api_key") String key, @Query("format")String format,
                                       @Query("resources")String resources, @Query("query")String query);

}

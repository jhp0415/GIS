package com.example.myretrofit01.data.remote;

import com.example.myretrofit01.data.repo.GISApiRepo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface GISApiService {
    @Headers({"Authorization:Bearer eb142d9027f84d51a4a20df8490e44bcf6fc7ef4dea64cae96a7fca282ebd8cc02764651",
            "Accept: application/json"})
    @GET("search/v1.0/pois/{id}")
    Call<GISApiRepo> retrievePOI(@Path("id") String id);

    @GET("search/v1.0/pois")
    Call<GISApiRepo> poiSearch(@QueryMap Map<String, String> option);

    @FormUrlEncoded
    @POST("search/v1.0/pois")
    Call<GISApiRepo> poiSearchDetail(@Field("name") String name);

}

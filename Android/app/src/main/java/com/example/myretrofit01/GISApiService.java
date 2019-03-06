package com.example.myretrofit01;

import com.example.myretrofit01.repo.GISApiRepo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface GISApiService {
    // GET 방식
    @GET("search/v1.0/pois/")
    Call<GISApiRepo> retrievePOI(@Path("id") String id);

    @GET("search/v1.0/pois")
    Call<GISApiRepo> poiSearch(@QueryMap Map<String, String> option);

    @FormUrlEncoded
    @POST("search/v1.0/pois")
    Call<GISApiRepo> poiSearchDetail(@Field("name") String name);

}

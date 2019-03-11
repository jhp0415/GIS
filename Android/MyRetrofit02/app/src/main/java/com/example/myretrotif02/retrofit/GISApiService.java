package com.example.myretrotif02.retrofit;


import com.example.myretrotif02.data.place.Place;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface GISApiService {
    final String BASE_URL = "https://gis.kt.com/";

    @Headers({"Authorization:Bearer eb142d9027f84d51a4a20df8490e44bcf6fc7ef4dea64cae96a7fca282ebd8cc02764651",
            "Accept: application/json"})
    @GET("search/v1.0/pois/{id}")
    Call<Place> retrievePOI(@Path("id") String id);

    @Headers({"Authorization:Bearer eb142d9027f84d51a4a20df8490e44bcf6fc7ef4dea64cae96a7fca282ebd8cc02764651",
            "Accept: application/json"})
    @GET("search/v1.0/pois")
    Call<Place> poiSearch(@QueryMap HashMap<String, String> parameters);
}

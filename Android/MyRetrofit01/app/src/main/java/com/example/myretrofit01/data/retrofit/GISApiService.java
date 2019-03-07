package com.example.myretrofit01.data.retrofit;

import com.example.myretrofit01.data.RequestBody.RequestBody;
import com.example.myretrofit01.data.repo.poiSearchDetailRepo.PoiSearchDetailRepo;
import com.example.myretrofit01.data.repo.poiSearchRepo.PoiSearchRepo;
import com.example.myretrofit01.data.repo.retrievePOIRepo.RetrievePOIRepo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface GISApiService {
    final String BASE_URL = "https://gis.kt.com/";
    @Headers({"Authorization:Bearer eb142d9027f84d51a4a20df8490e44bcf6fc7ef4dea64cae96a7fca282ebd8cc02764651",
            "Accept: application/json"})
    @GET("search/v1.0/pois/{id}")
    Call<RetrievePOIRepo> retrievePOI(@Path("id") String id);

    @Headers({"Authorization:Bearer eb142d9027f84d51a4a20df8490e44bcf6fc7ef4dea64cae96a7fca282ebd8cc02764651",
            "Accept: application/json"})
    @GET("search/v1.0/pois")
    Call<PoiSearchRepo> poiSearch(@QueryMap HashMap<String, String> parameters);

    @Headers({"Authorization:Bearer eb142d9027f84d51a4a20df8490e44bcf6fc7ef4dea64cae96a7fca282ebd8cc02764651",
            "Accept: application/json"})
//    @FormUrlEncoded
    @POST("search/v1.0/pois")
    //Call<PoiSearchDetailRepo> poiSearchDetail(@Body HashMap<String, Object> parameters);
    Call<PoiSearchDetailRepo> poiSearchDetail(@Body RequestBody parameters);

}

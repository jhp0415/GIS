package com.example.myretrofit01.data.retrofit;

import android.util.Log;

import com.example.myretrofit01.data.RequestBody.RequestBody;
import com.example.myretrofit01.data.repo.poiSearchDetailRepo.PoiSearchDetailRepo;
import com.example.myretrofit01.data.repo.poiSearchRepo.PoiSearchRepo;
import com.example.myretrofit01.data.repo.retrievePOIRepo.RetrievePOIRepo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GISApiClient {
    // retrofit Singleton Class
    private static GISApiClient GISApiClientInstance = new GISApiClient();
    private static Retrofit retrofit = null;
    private static RetrievePOIRepo retrievePOIRepo;
    private static String TAG = "ddd";

    private GISApiClient() {}

    public static GISApiClient getInstance() {
        return GISApiClientInstance;
    }

    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(GISApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static GISApiService getGisApiService() {
        getRetrofitClient();
        return retrofit.create(GISApiService.class);
    }

    public static void getRetrievePOI(String id, final GISApiCallback gisApiCallback) {
        getGisApiService().retrievePOI(id).enqueue(new Callback<RetrievePOIRepo>() {
            @Override
            public void onResponse(Call<RetrievePOIRepo> call, Response<RetrievePOIRepo> response) {
                if (response.isSuccessful()) {
                    gisApiCallback.onSuccess(response.code(), response.body());

                } else {
                    Log.d(TAG, "getRetrievePOI : response.isSuccessful() error...");
                    gisApiCallback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<RetrievePOIRepo> call, Throwable t) {
                Log.d(TAG, "onFailure().....");
                gisApiCallback.onError(t);
            }
        });
    }

    public static void getPOISearch(HashMap<String, String> parameters, final GISApiCallback gisApiCallback) {
        getGisApiService().poiSearch(parameters).enqueue(new Callback<PoiSearchRepo>() {
            @Override
            public void onResponse(Call<PoiSearchRepo> call, Response<PoiSearchRepo> response) {
                if (response.isSuccessful()) {
                    gisApiCallback.onSuccess(response.code(), response.body());

                } else {
                    Log.d(TAG, "getPOISearch() : response.isSuccessful() error...");
                    gisApiCallback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<PoiSearchRepo> call, Throwable t) {
                Log.d(TAG, "getPOISearch() : onFailure().....");
                gisApiCallback.onError(t);
            }
        });
    }

//    public static void getPOISearchDetail(HashMap<String, Object> parameters, final GISApiCallback gisApiCallback) {
    public static void getPOISearchDetail(RequestBody parameters, final GISApiCallback gisApiCallback) {
        getGisApiService().poiSearchDetail(parameters).enqueue(new Callback<PoiSearchDetailRepo>() {
            @Override
            public void onResponse(Call<PoiSearchDetailRepo> call, Response<PoiSearchDetailRepo> response) {
                if (response.isSuccessful()) {
                    gisApiCallback.onSuccess(response.code(), response.body());

                } else {
                    Log.d(TAG, "getPOISearchDetail() : response.isSuccessful() error...");
                    gisApiCallback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<PoiSearchDetailRepo> call, Throwable t) {
                Log.d(TAG, "getPOISearchDetail() : onFailure().....");
                gisApiCallback.onError(t);
            }
        });
    }
}

package com.example.myretrotif02.retrofit;

import android.util.Log;

import com.example.myretrotif02.data.place.Place;

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
    //private static RetrievePOIRepo retrievePOIRepo;
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

//    public static void getRetrievePOI(String id, final GISApiCallback gisApiCallback) {
//        getGisApiService().retrievePOI(id).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    gisApiCallback.onSuccess(response.code(), response.body());
//
//                } else {
//                    Log.d(TAG, "getRetrievePOI : response.isSuccessful() error...");
//                    gisApiCallback.onFailure(response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d(TAG, "onFailure().....");
//                gisApiCallback.onError(t);
//            }
//        });
//    }

//    public static void getPOISearch(HashMap<String, String> parameters, final GISApiCallback gisApiCallback) {
//        getGisApiService().poiSearch(parameters).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    gisApiCallback.onSuccess(response.code(), response.body());
//
//                } else {
//                    Log.d(TAG, "getPOISearch() : response.isSuccessful() error...");
//                    gisApiCallback.onFailure(response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d(TAG, "getPOISearch() : onFailure().....");
//                gisApiCallback.onError(t);
//            }
//        });
//    }

    public static void getPOISearch(HashMap<String, String> parameters, final GISApiCallback gisApiCallback) {
        getGisApiService().poiSearch(parameters).enqueue(new Callback<Place>() {
            @Override
            public void onResponse(Call<Place> call, Response<Place> response) {
                if (response.isSuccessful()) {
                    gisApiCallback.onSuccess(response.code(), response.body());

                } else {
                    Log.d(TAG, "getPOISearch() : response.isSuccessful() error...");
                    gisApiCallback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<Place> call, Throwable t) {
                Log.d(TAG, "getPOISearch() : onFailure().....");
                gisApiCallback.onError(t);
            }
        });
    }
}

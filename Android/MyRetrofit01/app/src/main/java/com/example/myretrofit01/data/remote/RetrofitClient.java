package com.example.myretrofit01.data.remote;

import android.util.Log;

import com.example.myretrofit01.data.repo.GISApiRepo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    // retrofit Singleton Class
    private static Retrofit retrofit = null;
    private static GISApiService gisApiService;
    private static GISApiRepo gisApiRepo;
    private static String TAG = "ddd";


    public static void getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        gisApiService = retrofit.create(GISApiService.class);

        //return gisApiService;
    }

    public static GISApiRepo getRetrievePOI(String id) {
        //gisApiService = GISApiUtil.getGISApiService();
        gisApiService.retrievePOI(id).enqueue(new Callback<GISApiRepo>() {
            @Override
            public void onResponse(Call<GISApiRepo> call, Response<GISApiRepo> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "response.isSuccessful() error...");
                }

                gisApiRepo = response.body();
                Log.d(TAG, gisApiRepo.toString());
                //리스너에게 알리기
            }

            @Override
            public void onFailure(Call<GISApiRepo> call, Throwable t) {
                Log.d(TAG, "onFailure().....");
            }
        });
        return gisApiRepo;
    }




    public static GISApiRepo getPOISearch(Map map) {
        gisApiService.poiSearch(map).enqueue(new Callback<GISApiRepo>() {
            @Override
            public void onResponse(Call<GISApiRepo> call, Response<GISApiRepo> response) {
                if (!response.isSuccessful()) {
                    gisApiRepo = response.body();
                }
            }

            @Override
            public void onFailure(Call<GISApiRepo> call, Throwable t) {
                Log.d(TAG, "onFailure().....");
            }
        });
        return gisApiRepo;
    }


}

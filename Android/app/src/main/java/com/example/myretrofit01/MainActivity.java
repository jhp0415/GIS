package com.example.myretrofit01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myretrofit01.repo.GISApiRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://gis.kt.com/";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        getRetrievePOI();
    }

    public void getRetrievePOI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GISApiService retrofitService = retrofit.create(GISApiService.class);
        Call<GISApiRepo> call = retrofitService.retrievePOI("c0396a4a-5d79-3aae-9c2f-2f18d7b9ee5e");
        call.enqueue(new Callback<GISApiRepo>() {
            @Override
            public void onResponse(Call<GISApiRepo> call, Response<GISApiRepo> response) {
                GISApiRepo repo = response.body();
                textView.setText(repo.getPois().toString());
            }

            @Override
            public void onFailure(Call<GISApiRepo> call, Throwable t) {

            }
        });
    }

}

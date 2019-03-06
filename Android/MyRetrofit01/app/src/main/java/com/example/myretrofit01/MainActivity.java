package com.example.myretrofit01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.myretrofit01.data.remote.RetrofitClient;
import com.example.myretrofit01.data.repo.GISApiRepo;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://gis.kt.com/";
    private GISApiRepo repo;
    private String searchID = "c0396a4a-5d79-3aae-9c2f-2f18d7b9ee5e";
    private TextView textView;
    private String TAG = "ddd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        RetrofitClient.getClient(BASE_URL);

        getRetrievePOI();

    }

    public void getRetrievePOI() {
        repo = RetrofitClient.getRetrievePOI(searchID);

        textView.setText(repo.getPois().get(0).getName());
    }

}

package com.example.myretrofit01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.myretrofit01.data.RequestBody.Address;
import com.example.myretrofit01.data.RequestBody.Point;
import com.example.myretrofit01.data.RequestBody.RequestBody;
import com.example.myretrofit01.data.retrofit.GISApiCallback;
import com.example.myretrofit01.data.retrofit.GISApiClient;
import com.example.myretrofit01.data.repo.poiSearchDetailRepo.PoiSearchDetailRepo;
import com.example.myretrofit01.data.repo.poiSearchRepo.PoiSearchRepo;
import com.example.myretrofit01.data.repo.retrievePOIRepo.RetrievePOIRepo;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private String TAG = "ddd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        String searchID = "c0396a4a-5d79-3aae-9c2f-2f18d7b9ee5e";
        gisRetrievePOI(searchID);

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("terms", "중흥마을2단지");
        gisPOISearch(parameters);



        RequestBody parameters2 = new RequestBody("서울시 서초구 우면동 17",
                new Point( 37.471360933458826,
                127.0294042193268 ),
                new Address(
                "대한민국",
                "서울특별시",
                "서초구",
                "우면동",
                "string",
                17,
                "태봉로",
                151,
                "1100000000 ('서울특별시')")
                );
        gisPOISearchDetail(parameters2);
    }

    public void gisRetrievePOI(String searchID) {
        GISApiClient.getInstance().getRetrievePOI(searchID, new GISApiCallback() {
            @Override
            public void onSuccess(int code, Object receivedData) {
                RetrievePOIRepo repo = (RetrievePOIRepo) receivedData;
                textView1.setText(repo.getPois().get(0).getAddress().getSiDo() + " "
                + repo.getPois().get(0).getAddress().getStreet() + " "
                + repo.getPois().get(0).getAddress().getStreetNumber() + " "
                + repo.getPois().get(0).getName());
            }

            @Override
            public void onFailure(int code) {
                Log.d(TAG, "Error Code : " + code);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

    public void gisPOISearch(HashMap parameters) {
        GISApiClient.getInstance().getPOISearch(parameters, new GISApiCallback() {
            @Override
            public void onSuccess(int code, Object receivedData) {
                PoiSearchRepo repo = (PoiSearchRepo) receivedData;
                textView2.setText(repo.getPois().get(0).getAddress().getSiDo() + " "
                        + repo.getPois().get(0).getAddress().getStreet() + " "
                        + repo.getPois().get(0).getAddress().getStreetNumber() + " "
                        + repo.getPois().get(0).getName());
            }

            @Override
            public void onFailure(int code) {
                Log.d(TAG, "Error Code : " + code);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

    //public void gisPOISearchDetail(HashMap parameters) {
    public void gisPOISearchDetail(RequestBody parameters) {
        GISApiClient.getInstance().getPOISearchDetail(parameters, new GISApiCallback() {
            @Override
            public void onSuccess(int code, Object receivedData) {
                PoiSearchDetailRepo repo = (PoiSearchDetailRepo) receivedData;
                textView3.setText(repo.getResidentialAddress().get(0).getRoadAddress().get(0).getCountry() + " "
                + repo.getResidentialAddress().get(0).getRoadAddress().get(0).getSiDo() + " "
                + repo.getResidentialAddress().get(0).getRoadAddress().get(0).getSiGunGu() + " "
                + repo.getResidentialAddress().get(0).getRoadAddress().get(0).getStreet() + " "
                + repo.getResidentialAddress().get(0).getRoadAddress().get(0).getStreetNumber() + " ");
            }

            @Override
            public void onFailure(int code) {
                Log.d(TAG, "gisPOISearchDetail Error Code : " + code);
            }

            @Override
            public void onError(Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

}

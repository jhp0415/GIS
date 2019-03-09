package com.example.myretrotif02.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.myretrotif02.R;

public class RetrievePOIActivity extends AppCompatActivity {
    private TextView textView1;
    private String TAG = "ddd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_poi);

        textView1 = (TextView) findViewById(R.id.tv_id_value);

        String searchID = "c0396a4a-5d79-3aae-9c2f-2f18d7b9ee5e";
        gisRetrievePOI(searchID);
    }

    public void gisRetrievePOI(String searchID) {
//        GISApiClient.getInstance().getRetrievePOI(searchID, new GISApiCallback() {
//            @Override
//            public void onSuccess(int code, Object receivedData) {
//                ResponseBody repo = (ResponseBody) receivedData;
//                textView1.setText(repo.getPois().get(0).getAddress().getSiDo() + " "
//                        + repo.getPois().get(0).getAddress().getStreet() + " "
//                        + repo.getPois().get(0).getAddress().getStreetNumber() + " "
//                        + repo.getPois().get(0).getName());
//            }
//
//            @Override
//            public void onFailure(int code) {
//                Log.d(TAG, "Error Code : " + code);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                Log.d(TAG, t.toString());
//            }
//        });
    }

}

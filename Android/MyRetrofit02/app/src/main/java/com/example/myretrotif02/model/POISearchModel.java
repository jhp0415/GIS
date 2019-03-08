package com.example.myretrotif02.model;

import android.util.Log;

import com.example.myretrotif02.CallbackListener;
import com.example.myretrotif02.construct.POISearchConstruct;
import com.example.myretrotif02.data.place.Place;
import com.example.myretrotif02.data.response.ResponseBody;
import com.example.myretrotif02.retrofit.GISApiCallback;
import com.example.myretrotif02.retrofit.GISApiClient;

import java.util.HashMap;

public class POISearchModel {
    private POISearchConstruct.Presenter presenter;
    private String TAG = "ddd";

    public POISearchModel(POISearchConstruct.Presenter presenter) {
        this.presenter = presenter;
    }

    public void getResult(HashMap<String, String> parameter, final CallbackListener callbackListener) {
        GISApiClient.getInstance().getPOISearch(parameter, new GISApiCallback() {
            @Override
            public void onSuccess(int code, Object receivedData) {
                ResponseBody repo = (ResponseBody) receivedData;

                // 데이터 가공 : PoiSearchRepo -> ResponseBody 모델링
                Place place = ConvertResponseToPlace(repo);

                // presenter에 전달
                callbackListener.onReceived(place);
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

    public Place ConvertResponseToPlace(ResponseBody responseBody) {
        Place place = new Place();

        return place;
    }
}

package com.example.placesapi03.presenter;

import android.content.Context;
import android.util.Log;

import com.example.placesapi03.contract.CurrentPlaceContract;
import com.example.placesapi03.model.CurrentPlaceModel;
import com.google.android.libraries.places.api.model.PlaceLikelihood;

import java.util.ArrayList;

public class CurrentPlacePresenter implements CurrentPlaceContract.Presenter {
    private CurrentPlaceContract.View view;
    private CurrentPlaceContract.Model model;
    private Context context;
    private String TAG = "DEBUG";

    public CurrentPlacePresenter(CurrentPlaceContract.View view, Context context){
        this.view = view;
        this.context = context;
        model = new CurrentPlaceModel(this, this.context);
    }

    // View -> Presenter -> Model 호출
    // Model에서 현재 위치 받아오는 함수 실행
    @Override
    public void loadResult() {
        Log.d(TAG, "presenter : loadResult 실행");
        model.getResult();
    }

    // Model -> View로 데이터 업데이트
    @Override
    public void callback(ArrayList<PlaceLikelihood> arrayList) {
        view.updateView(arrayList);
    }
}

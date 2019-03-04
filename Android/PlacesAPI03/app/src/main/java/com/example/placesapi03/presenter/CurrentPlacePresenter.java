package com.example.placesapi03.presenter;

import android.content.Context;
import android.util.Log;

import com.example.placesapi03.contract.CurrentPlaceContract;
import com.example.placesapi03.model.CurrentPlaceModel;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;

public class CurrentPlacePresenter implements CurrentPlaceContract.Presenter {
    private CurrentPlaceContract.View view;
    private CurrentPlaceContract.Model model;
    private String TAG = "DEBUG";
    private PlacesClient placesClient;
    private Context context;

    public CurrentPlacePresenter(CurrentPlaceContract.View view){
        this.view = view;
        model = new CurrentPlaceModel(this);
    }

    // View -> Presenter -> Model 호출
    // Model에서 현재 위치 받아오는 함수 실행
    @Override
    public void loadResult() {
        model.getResult();
    }


    // Model -> View로 데이터 업데이트
    @Override
    public void modelToViewCallback(ArrayList<PlaceLikelihood> arrayList) {
        view.updateView(arrayList);
    }

    @Override
    public void viewToModelCallback(PlacesClient placesClient, Context context) {
        model.setParameter(placesClient, context);
    }
}

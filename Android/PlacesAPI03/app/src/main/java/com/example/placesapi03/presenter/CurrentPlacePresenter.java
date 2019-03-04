package com.example.placesapi03.presenter;

import android.util.Log;

import com.example.placesapi03.contract.CurrentPlaceContract;
import com.example.placesapi03.model.CurrentPlaceModel;
import com.google.android.libraries.places.api.model.PlaceLikelihood;

import java.util.ArrayList;

public class CurrentPlacePresenter implements CurrentPlaceContract.Presenter {
    private CurrentPlaceContract.View view;
    private CurrentPlaceModel model;
    private String TAG = "DEBUG";

    public CurrentPlacePresenter(CurrentPlaceContract.View view, CurrentPlaceModel model){
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadResult();
    }

    @Override
    public void loadResult() {
        callback(model.getResult());
    }

    @Override
    public void callback(ArrayList<PlaceLikelihood> arrayList) {
        if (arrayList.size() != 0) {
            Log.d(TAG, "presenter : callback() 실행");
            view.updateView(arrayList);
        } else {
            Log.d(TAG, "ArrayList<PlaceLikelihood is null...");
        }
    }
}

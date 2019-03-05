package com.example.placesapi03.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.placesapi03.MyEventListener;
import com.example.placesapi03.contract.CurrentPlaceContract;
import com.example.placesapi03.model.CurrentPlaceModel;
import com.example.placesapi03.model.PlacePhotosModel;
import com.google.android.libraries.places.api.model.Place;
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
        model.getResult(new MyEventListener() {
            @Override
            public void onRecivedEvent(ArrayList<PlaceLikelihood> arrayList) {
                callback(arrayList);
            }

            @Override
            public void onRecivedEvent(Place place) {

            }

            @Override
            public void onRecivedEvent(Place place, Bitmap bitmap) {

            }
        });
    }

    @Override
    public void callback(ArrayList<PlaceLikelihood> arrayList) {
        view.updateView(arrayList);
    }









}

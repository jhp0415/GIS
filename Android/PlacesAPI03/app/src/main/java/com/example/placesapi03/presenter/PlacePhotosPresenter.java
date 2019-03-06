package com.example.placesapi03.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.placesapi03.MyEventListener;
import com.example.placesapi03.contract.PlacePhotosContract;
import com.example.placesapi03.model.PlacePhotosModel;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;

import java.util.ArrayList;

public class PlacePhotosPresenter implements PlacePhotosContract.Presenter {
    private PlacePhotosContract.View view;
    private PlacePhotosModel model;

    private String TAG = "DEBUG";

    public PlacePhotosPresenter(PlacePhotosContract.View view, PlacePhotosModel model) {
        this.view = view;
        this.model = model;
        this.view.setPresenter(this);
    }

    @Override
    public void start(Place place) {
        loadResult(place);
    }

    @Override
    public void loadResult(Place place) {
        model.setPlaceId(place);
        model.getResult(new MyEventListener() {
            @Override
            public void onRecivedEvent(ArrayList<PlaceLikelihood> arrayList) {

            }

            @Override
            public void onRecivedEvent(Place place) {

            }

            @Override
            public void onRecivedEvent(Place place, Bitmap bitmap) {
                callback(place, bitmap);
            }
        });
    }

    @Override
    public void callback(Place place, Bitmap bitmap) {
        this.view.updateView(place, bitmap);
    }
}

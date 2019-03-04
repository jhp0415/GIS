package com.example.placesapi03.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.placesapi03.contract.PlacePhotosContract;
import com.example.placesapi03.model.PlacePhotosModel;
import com.google.android.libraries.places.api.model.Place;

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
        callback(model.getResult(place), place);
    }

    @Override
    public void callback(Bitmap bitmap, Place place) {
        if (bitmap != null && place != null) {
            this.view.updateView(bitmap, place);
        } else {
            Log.d(TAG, "bitmap or place is null...");
        }

    }
}

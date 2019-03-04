package com.example.placesapi03.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.placesapi03.contract.PlacePhotosContract;
import com.example.placesapi03.model.PlacePhotosModel;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;

public class PlacePhotosPresenter implements PlacePhotosContract.Presenter {
    private PlacePhotosContract.View view;
    private PlacePhotosContract.Model model;
    private PlacesClient placesClient;

    private String TAG = "DEBUG";

    public PlacePhotosPresenter(PlacePhotosContract.View view, PlacesClient placesClient) {
        this.view = view;
        this.placesClient = placesClient;
        model = new PlacePhotosModel(this, this.placesClient);
    }

    @Override
    public void loadResult(Place place) {
        Log.d(TAG, "PlacePhotosPresenter loadResult() 실행");
        this.model.getResult(place);
    }

    @Override
    public void callback(Bitmap bitmap, Place place) {
        Log.d(TAG, "PlacePhotosPresenter callback() 실행");
        this.view.updateView(bitmap, place);
    }
}

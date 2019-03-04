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

    public PlacePhotosPresenter(PlacePhotosContract.View view) {
        this.view = view;
        model = new PlacePhotosModel(this);
    }

    @Override
    public void loadResult(Place place) {
        this.model.getResult(place);
    }

    @Override
    public void modelToViewCallback(Bitmap bitmap, Place place) {
        this.view.updateView(bitmap, place);
    }

    @Override
    public void viewToModelCallback(PlacesClient placesClient) {
        model.setPlaceClient(placesClient);
    }
}

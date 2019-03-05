package com.example.placesapi03.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.placesapi03.MyEventListener;
import com.example.placesapi03.contract.PlaceAutocompleteContract;
import com.example.placesapi03.model.PlaceAutocompleteModel;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class PlaceAutocompletePresenter implements PlaceAutocompleteContract.Presenter {
    private PlaceAutocompleteContract.View view;
    private PlaceAutocompleteModel model;
    private String TAG = "DEBUG";

    public PlaceAutocompletePresenter(PlaceAutocompleteContract.View view, PlaceAutocompleteModel model) {
        this.view = checkNotNull(view);
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadResult();
    }

    @Override
    public void loadResult() {
        //callback(model.getResult());
        model.getResult(new MyEventListener() {
            @Override
            public void onRecivedEvent(ArrayList<PlaceLikelihood> arrayList) {

            }

            @Override
            public void onRecivedEvent(Place place) {
                callback(place);
            }

            @Override
            public void onRecivedEvent(Place place, Bitmap bitmap) {

            }
        });
    }

    @Override
    public void callback(Place place) {
        view.updateView(place);
        view.getPlaceResult(place);
    }
}

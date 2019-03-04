package com.example.placesapi03.presenter;

import android.util.Log;

import com.example.placesapi03.contract.PlaceAutocompleteContract;
import com.example.placesapi03.model.PlaceAutocompleteModel;
import com.google.android.libraries.places.api.model.Place;

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
        callback(model.getResult());
    }

    @Override
    public void callback(Place place) {
        if(place != null)
        {
            view.updateView(place);
            view.getPlaceResult(place);
        } else {
            Log.d(TAG, "PlaceAutocompletePresenter : callback -> place 가 null");
        }

    }

}

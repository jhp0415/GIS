package com.example.placesapi03.presenter;

import android.util.Log;

import com.example.placesapi03.contract.PlaceAutocompleteContract;
import com.example.placesapi03.model.PlaceAutocompleteModel;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

public class PlaceAutocompletePresenter implements PlaceAutocompleteContract.Presenter {
    private PlaceAutocompleteContract.View view;
    private PlaceAutocompleteContract.Model model;
    private AutocompleteSupportFragment autocompleteSupportFragment;
    private String TAG = "DEBUG";

    private Place place;
    private Status status = Status.RESULT_TIMEOUT;

    public PlaceAutocompletePresenter(PlaceAutocompleteContract.View view, AutocompleteSupportFragment autocompleteSupportFragment){
        this.view = view;
        this.autocompleteSupportFragment = autocompleteSupportFragment;
        model = new PlaceAutocompleteModel(this);
    }

    @Override
    public void loadResult() {
        model.getResult(this.autocompleteSupportFragment);
    }

    @Override
    public void callback(Place place) {
        view.updateView(place);
        view.getPlaceResult(place);
    }
}

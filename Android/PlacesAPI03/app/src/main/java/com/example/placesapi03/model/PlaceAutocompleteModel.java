package com.example.placesapi03.model;

import android.util.Log;

import com.example.placesapi03.contract.PlaceAutocompleteContract;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

public class PlaceAutocompleteModel implements PlaceAutocompleteContract.Model {
    private PlaceAutocompleteContract.Presenter presenter;
    private AutocompleteSupportFragment autocompleteSupportFragment;
    private String TAG = "DEBUG";

    public PlaceAutocompleteModel(PlaceAutocompleteContract.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void setAutocompleteSupportFragment(AutocompleteSupportFragment autocompleteSupportFragment) {
        this.autocompleteSupportFragment = autocompleteSupportFragment;
    }

    @Override
    public void getResult() {
        // 자동 완성 예제
        List<Place.Field> arrays = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.ID, Place.Field.LAT_LNG);

        // Setup Autocomplete Support Fragment
        autocompleteSupportFragment.setPlaceFields(arrays);
        autocompleteSupportFragment.setOnPlaceSelectedListener(
                new PlaceSelectionListener() {
                    @Override
                    public void onPlaceSelected(Place place) {
                        Log.d(TAG, "Autocomplete getResult() : " + place.getName() + ", " + place.getAddress() + ", " + place.getId());
                        //결과 반영
                        presenter.modelToViewCallback(place);
                    }
                    @Override
                    public void onError(Status status) {
                        Log.d(TAG, "An error occurred: " + status);
                    }
                });
    }
}

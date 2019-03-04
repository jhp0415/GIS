package com.example.placesapi03.model;

import android.util.Log;

import com.example.placesapi03.contract.PlaceAutocompleteContract;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

import static com.google.android.gms.common.api.Status.RESULT_SUCCESS;

public class PlaceAutocompleteModel implements PlaceAutocompleteContract.Model {
    private PlaceAutocompleteContract.Presenter presenter;
    private String TAG = "DEBUG";

    public PlaceAutocompleteModel(PlaceAutocompleteContract.Presenter presenter){
        this.presenter = presenter;

    }

    @Override
    public void getResult(AutocompleteSupportFragment autocompleteSupportFragment) {
        // 자동 완성 예제
        List<Place.Field> arrays;arrays = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.ID, Place.Field.LAT_LNG);

        // Setup Autocomplete Support Fragment
        autocompleteSupportFragment.setPlaceFields(arrays);
        autocompleteSupportFragment.setOnPlaceSelectedListener(
                new PlaceSelectionListener() {
                    @Override
                    public void onPlaceSelected(Place place) {
                        Log.d(TAG, "Autocomplete getResult() : " + place.getName() + ", " + place.getAddress() + ", " + place.getId());
                        //결과 반영

                        presenter.callback(place);
                    }

                    @Override
                    public void onError(Status status) {
                        Log.d(TAG, "An error occurred: " + status);

                        //결과 반영
                        //presenter.callback(null);
                    }
                });

    }
}

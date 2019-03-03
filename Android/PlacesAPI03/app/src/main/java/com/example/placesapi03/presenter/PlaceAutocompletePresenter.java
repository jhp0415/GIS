package com.example.placesapi03.presenter;

import android.util.Log;

import com.example.placesapi03.contract.PlaceAutocompleteContract;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

public class PlaceAutocompletePresenter implements PlaceAutocompleteContract.Presenter {
    private PlaceAutocompleteContract.View view;
    private AutocompleteSupportFragment autocompleteSupportFragment;
    private String TAG = "DEBUG";
    private String result ="";
    private List<Place.Field> arrays;

    public PlaceAutocompletePresenter(PlaceAutocompleteContract.View view, AutocompleteSupportFragment autocompleteSupportFragment){
        this.view = view;
        this.autocompleteSupportFragment = autocompleteSupportFragment;
    }

    @Override
    public void placeAutocomplete() {
        // 데이터 처리
        // 자동 완성 예제
        arrays = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.ID, Place.Field.LAT_LNG);

        // Setup Autocomplete Support Fragment
        autocompleteSupportFragment.setPlaceFields(arrays);
        autocompleteSupportFragment.setOnPlaceSelectedListener(
                new PlaceSelectionListener() {
                    @Override
                    public void onPlaceSelected(Place place) {
                        Log.d(TAG, "Place: " + place.getName() + ", " + place.getAddress() + ", " + place.getId());
                        result = "Place: " + place.getName() + ", " + place.getAddress() + ", " + place.getId();
                        //결과 반영
                        view.updateView(result);
                    }

                    @Override
                    public void onError(Status status) {
                        Log.d(TAG, "An error occurred: " + status);
                        result = "Autocomplete Error : Null Data";
                        //결과 반영
                        view.updateView(result);
                    }
                });


    }
}

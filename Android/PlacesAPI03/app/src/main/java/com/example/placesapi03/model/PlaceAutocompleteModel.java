package com.example.placesapi03.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class PlaceAutocompleteModel {
    private AutocompleteSupportFragment autocompleteSupportFragment;
    private PlacesClient placesClient;
    private Context context;
    private String TAG = "DEBUG";
    private Place result;

    public PlaceAutocompleteModel(Context context, @NonNull AutocompleteSupportFragment autocompleteSupportFragment) {
        this.context = context;
        this.autocompleteSupportFragment = checkNotNull(autocompleteSupportFragment);

        // Initialize Places.
        Places.initialize(this.context, "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I");
        // Create a new Places client instance.
        placesClient = Places.createClient(this.context);
        Log.d(TAG, "PlaceAutocompleteModel : 생성자 실행");
    }

    public Place getResult() {
        Log.d(TAG, "PlaceAutocompleteModel : getResult() 실행");
        Log.d(TAG, autocompleteSupportFragment.toString());
        // 자동 완성 예제
        List<Place.Field> arrays = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.ID, Place.Field.LAT_LNG);

        // Setup Autocomplete Support Fragment
        autocompleteSupportFragment.setPlaceFields(arrays);
        autocompleteSupportFragment.setOnPlaceSelectedListener(
                new PlaceSelectionListener() {
                    @Override
                    public void onPlaceSelected(Place place) {
                        Log.d(TAG, "Autocomplete getResult() : " + place.getName() + ", " + place.getAddress() + ", " + place.getId());
                        result = place;
                    }
                    @Override
                    public void onError(Status status) {
                        Log.d(TAG, "An error occurred: " + status);
                    }
                });
        return result;
    }
}

package com.example.placesapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

public class PlaceAutocompleteActivity extends AppCompatActivity {
    private static final String TAG = "DEBUG";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_autocomplete);

        textView = (TextView) findViewById(R.id.place_autocomplete_result_textview);

        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I");

        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);

        List<Place.Field> arrays = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS);


        // Setup Autocomplete Support Fragment
        final AutocompleteSupportFragment autocompleteSupportFragment =
                (AutocompleteSupportFragment)
                        getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteSupportFragment.setPlaceFields(arrays);
        autocompleteSupportFragment.setOnPlaceSelectedListener(
                new PlaceSelectionListener() {
                    @Override
                    public void onPlaceSelected(Place place) {
                        Log.d(TAG, "자동완성 됐나?????");
                        Log.d(TAG, "Place: " + place.getName() + ", " + place.getId());
                        textView.setText("Place: " + place.getName() + ", " + place.getId());
                    }

                    @Override
                    public void onError(Status status) {
                        Log.d(TAG, "자동완성 에러!!!!!!");
                        Log.d(TAG, "An error occurred: " + status);
                    }
                });
    }
}

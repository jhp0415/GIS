package com.example.placesapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

public class PlacePickerActivity extends AppCompatActivity {
    private static final String TAG = "DEBUG";
    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_picker);

        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I");
        PlacesClient placesClient = Places.createClient(this);

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            Log.d(TAG, "onCreate error : ");
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.d(TAG, "onCreate error : ");
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "requestCode : " + requestCode);
        Log.d(TAG, "resultCode : " + resultCode);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                Log.d(TAG, String.format("Place: %s", place.getName()));
            }
        }
    }
}

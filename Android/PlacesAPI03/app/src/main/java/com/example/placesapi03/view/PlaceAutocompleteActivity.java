package com.example.placesapi03.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.placesapi03.R;
import com.example.placesapi03.contract.PlaceAutocompleteContract;
import com.example.placesapi03.presenter.PlaceAutocompletePresenter;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

public class PlaceAutocompleteActivity extends AppCompatActivity implements PlaceAutocompleteContract.View {
    private PlaceAutocompleteContract.Presenter presenter;
    private AutocompleteSupportFragment autocompleteSupportFragment;
    private TextView textView;
    private String TAG = "DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_autocomplete);

        init();

        presenter = new PlaceAutocompletePresenter(this, autocompleteSupportFragment);
        presenter.placeAutocomplete();
    }

    @Override
    public void init() {
        textView = (TextView) findViewById(R.id.place_autocomplete_result_textview);
        // Setup Autocomplete Support Fragment
        autocompleteSupportFragment =
                (AutocompleteSupportFragment)
                        getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);


        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I");
        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);
    }

    @Override
    public void updateView(String result) {
        // UI 갱신
        Log.d(TAG, "MainActivity updateView : " + result);
        textView.setText(result);
    }
}

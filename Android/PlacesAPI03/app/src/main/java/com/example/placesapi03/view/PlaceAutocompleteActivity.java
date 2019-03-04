package com.example.placesapi03.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.placesapi03.R;
import com.example.placesapi03.contract.PlaceAutocompleteContract;
import com.example.placesapi03.presenter.PlaceAutocompletePresenter;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

public class PlaceAutocompleteActivity extends AppCompatActivity implements PlaceAutocompleteContract.View {
    private PlaceAutocompleteContract.Presenter presenter;
    private AutocompleteSupportFragment autocompleteSupportFragment;
    private PlacesClient placesClient;
    private TextView textView;
    private String TAG = "DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_autocomplete);

        viewInit();
        presenterInit();
        getPlaceAutocompleteResult();
    }

    public void presenterInit(){
        presenter = new PlaceAutocompletePresenter(this);
        //presenter.setAutocompleteSupportFragment(autocompleteSupportFragment);
        presenter.viewToModelCallback(autocompleteSupportFragment);
    }

    public void getPlaceAutocompleteResult() {
        presenter.loadResult();
    }

    @Override
    public void viewInit() {
        textView = (TextView) findViewById(R.id.place_autocomplete_result_textview);

        // Setup Autocomplete Support Fragment
        autocompleteSupportFragment =
                (AutocompleteSupportFragment)
                        getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I");
        // Create a new Places client instance.
        placesClient = Places.createClient(this);
    }

    @Override
    public void updateView(Place place) {
        // UI 갱신
        Log.d(TAG, "MainActivity : updateView 실행" );
        textView.setText(place.getName() + place.getAddress() + place.getId() + place.getLatLng().latitude + place.getLatLng().longitude);
    }

    @Override
    public void getPlaceResult(Place place) {

    }
}

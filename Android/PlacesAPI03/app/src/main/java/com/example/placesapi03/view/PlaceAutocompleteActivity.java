package com.example.placesapi03.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.placesapi03.R;
import com.example.placesapi03.contract.PlaceAutocompleteContract;
import com.example.placesapi03.model.PlaceAutocompleteModel;
import com.example.placesapi03.presenter.PlaceAutocompletePresenter;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

public class PlaceAutocompleteActivity extends AppCompatActivity implements PlaceAutocompleteContract.View {
    private PlaceAutocompleteContract.Presenter presenter;
    private static AutocompleteSupportFragment autocompleteSupportFragment;
    private TextView textView;
    private String TAG = "DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_autocomplete);

        viewInit();
        presenterInit();
    }

    @Override
    public void viewInit() {
        textView = (TextView) findViewById(R.id.place_autocomplete_result_textview);

        // Setup Autocomplete Support Fragment
        autocompleteSupportFragment =
                (AutocompleteSupportFragment)
                        getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
    }

    public void presenterInit(){
        presenter = new PlaceAutocompletePresenter(
                this,
               new PlaceAutocompleteModel(getApplicationContext(), autocompleteSupportFragment));
    }

    @Override
    public void setPresenter(PlaceAutocompleteContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
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

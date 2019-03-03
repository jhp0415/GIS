package com.example.placesapi03.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.placesapi03.R;
import com.example.placesapi03.contract.CurrentPlaceContract;
import com.example.placesapi03.presenter.CurrentPlacePresenter;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

public class CurrentPlaceActivity extends AppCompatActivity implements CurrentPlaceContract.View {
    private CurrentPlaceContract.Presenter presenter;
    private String TAG = "DEBUG";
    private TextView textView;
    private static final int PERMISSIONS_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_place);

        presenter = new CurrentPlacePresenter(this);
    }

    @Override
    public void init() {
        textView = (TextView) findViewById(R.id.current_place_textview);

        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I");

        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);
    }
}

package com.example.placesapi03.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.placesapi03.R;
import com.example.placesapi03.contract.PlaceAutocompleteContract;
import com.example.placesapi03.contract.PlacePhotosContract;
import com.example.placesapi03.presenter.PlaceAutocompletePresenter;
import com.example.placesapi03.presenter.PlacePhotosPresenter;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

public class PlacePhotosActivity extends AppCompatActivity
        implements PlacePhotosContract.View, PlaceAutocompleteContract.View {

    private PlacePhotosContract.Presenter presenterPhotos;
    private PlaceAutocompleteContract.Presenter presenterAuto;
    private AutocompleteSupportFragment autocompleteSupportFragment;
    private String TAG = "DEBUG";

    private PlacesClient placesClient;
    private ImageView imageView;
    private TextView nameTextView;
    private TextView addressTextView;
    private TextView latlngTextView;
    private TextView idTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_photos);

        init();

        presenterPhotos = new PlacePhotosPresenter(this, placesClient);

        presenterAuto = new PlaceAutocompletePresenter(this, autocompleteSupportFragment);
        presenterAuto.loadResult();

    }

    @Override
    public void init() {
        imageView = (ImageView) findViewById(R.id.place_photos_imageview);
        nameTextView = (TextView) findViewById(R.id.photos_name_textview);
        addressTextView = (TextView) findViewById(R.id.photos_address_textview);
        latlngTextView = (TextView) findViewById(R.id.photos_latlng_textview);
        idTextView = (TextView) findViewById(R.id.photos_id_textview);

        // Setup Autocomplete Support Fragment
        autocompleteSupportFragment =
                (AutocompleteSupportFragment)
                        getSupportFragmentManager().findFragmentById(R.id.place_photo_and_autocomplete_fragment);

        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I");
        // Create a new Places client instance.
        placesClient = Places.createClient(this);
    }


    @Override
    public void updateView(Bitmap bitmap, Place place) {
        imageView.setImageBitmap(bitmap);
        nameTextView.setText(place.getName());
        addressTextView.setText(place.getAddress());
        latlngTextView.setText(place.getLatLng().toString());
        idTextView.setText(place.getId());
    }

    @Override
    public void updateView(Place place) {
        // NO Action
    }

    @Override
    public void getPlaceResult(Place place) {
        presenterPhotos.loadResult(place);
    }
}

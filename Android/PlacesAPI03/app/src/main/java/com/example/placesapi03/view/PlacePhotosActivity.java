package com.example.placesapi03.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.placesapi03.R;
import com.example.placesapi03.contract.PlaceAutocompleteContract;
import com.example.placesapi03.contract.PlacePhotosContract;
import com.example.placesapi03.model.PlaceAutocompleteModel;
import com.example.placesapi03.model.PlacePhotosModel;
import com.example.placesapi03.presenter.PlaceAutocompletePresenter;
import com.example.placesapi03.presenter.PlacePhotosPresenter;
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

        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I");
        // Create a new Places client instance.
        placesClient = Places.createClient(this);

        viewInit();
        presenterInit();
    }

    @Override
    public void viewInit() {
        imageView = (ImageView) findViewById(R.id.place_photos_imageview);
        nameTextView = (TextView) findViewById(R.id.photos_name_textview);
        addressTextView = (TextView) findViewById(R.id.photos_address_textview);
        latlngTextView = (TextView) findViewById(R.id.photos_latlng_textview);
        idTextView = (TextView) findViewById(R.id.photos_id_textview);

        // Setup Autocomplete Support Fragment
        autocompleteSupportFragment =
                (AutocompleteSupportFragment)
                        getSupportFragmentManager().findFragmentById(R.id.place_photo_and_autocomplete_fragment);
    }

    public void presenterInit(){
        presenterPhotos = new PlacePhotosPresenter(this,
                new PlacePhotosModel(getApplicationContext()));

        presenterAuto  = new PlaceAutocompletePresenter(
                this,
                new PlaceAutocompleteModel(getApplicationContext(), autocompleteSupportFragment));
    }

    @Override
    public void setPresenter(PlaceAutocompleteContract.Presenter presenter) {
        this.presenterAuto = presenter;
    }

    @Override
    public void setPresenter(PlacePhotosContract.Presenter presenter) {
        this.presenterPhotos = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenterAuto.start();
    }

    @Override
    public void updateView(Place place, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        nameTextView.setText(place.getName());
        addressTextView.setText(place.getAddress());
        latlngTextView.setText(place.getLatLng().toString());
        idTextView.setText(place.getId());
    }

    @Override
    public void getPlaceResult(Place place) {
        presenterPhotos.start(place);
    }

    @Override
    public void updateView(Place place) {
        // NO Action
    }
}

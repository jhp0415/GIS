package com.example.placesapi;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

public class PlacePhotosActivity extends AppCompatActivity {

    private static final String TAG = "DEBUG";
    private ImageView imageView;
    private TextView nameTextView;
    private TextView addressTextView;
    private TextView latlngTextView;
    private TextView idTextView;
    private PlacesClient placesClient;
    private Place result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_photos);

        init();
        placeAutocomplete();

    }

    void init(){
        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I");
        // Create a new Places client instance.
        placesClient = Places.createClient(this);

        imageView = (ImageView) findViewById(R.id.place_photos_imageview);
        nameTextView = (TextView) findViewById(R.id.photos_name_textview);
        addressTextView = (TextView) findViewById(R.id.photos_address_textview);
        latlngTextView = (TextView) findViewById(R.id.photos_latlng_textview);
        idTextView = (TextView) findViewById(R.id.photos_id_textview);

    }

    private void placeAutocomplete() {
        List<Place.Field> arrays = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.ID, Place.Field.LAT_LNG);

        // Setup Autocomplete Support Fragment
        final AutocompleteSupportFragment autocompleteSupportFragment =
                (AutocompleteSupportFragment)
                        getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteSupportFragment.setPlaceFields(arrays);
        autocompleteSupportFragment.setOnPlaceSelectedListener(
                new PlaceSelectionListener() {
                    @Override
                    public void onPlaceSelected(Place place) {
                        result = place;
                        Log.d(TAG, "자동완성 됐나?????");
                        Log.d(TAG, "Place: " + place.getName() + ", " + place.getAddress() + ", " + place.getId());
                        placePhotos(result);
                        setView(result);
                    }

                    @Override
                    public void onError(Status status) {
                        Log.d(TAG, "자동완성 에러!!!!!!");
                        Log.d(TAG, "An error occurred: " + status);
                        result = null;
                    }
                });


    }

    public void placePhotos(Place mplace){
        // Define a Place ID. 장소 ID를 입력하시오
        String placeId = mplace.getId().toString();

        // Specify fields. Requests for photos must always have the PHOTO_METADATAS field.
        List<Place.Field> fields = Arrays.asList(Place.Field.PHOTO_METADATAS);

        // Get a Place object (this example uses fetchPlace(), but you can also use findCurrentPlace())
        FetchPlaceRequest placeRequest = FetchPlaceRequest.builder(placeId, fields).build();

        placesClient.fetchPlace(placeRequest).addOnSuccessListener((response) -> {
            Place place = response.getPlace();

            // Get the photo metadata.
            PhotoMetadata photoMetadata = place.getPhotoMetadatas().get(0);

            // Get the attribution text.
            String attributions = photoMetadata.getAttributions();

            // Create a FetchPhotoRequest.
            FetchPhotoRequest photoRequest = FetchPhotoRequest.builder(photoMetadata)
                    .setMaxWidth(500) // Optional.
                    .setMaxHeight(300) // Optional.
                    .build();
            placesClient.fetchPhoto(photoRequest).addOnSuccessListener((fetchPhotoResponse) -> {
                Bitmap bitmap = fetchPhotoResponse.getBitmap();
                imageView.setImageBitmap(bitmap);
            }).addOnFailureListener((exception) -> {
                if (exception instanceof ApiException) {
                    ApiException apiException = (ApiException) exception;
                    int statusCode = apiException.getStatusCode();
                    // Handle error with given status code.
                    Log.d(TAG, "Place not found: " + exception.getMessage());
                }
            });
        });


    }

    public void setView(Place mplace){
        nameTextView.setText(mplace.getName());
        addressTextView.setText(mplace.getAddress());
        latlngTextView.setText(mplace.getLatLng().toString());
        idTextView.setText(mplace.getId());
    }
}

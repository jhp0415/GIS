package com.example.placesapi03.model;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.placesapi03.contract.PlacePhotosContract;
import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

import java.util.Arrays;
import java.util.List;

public class PlacePhotosModel implements PlacePhotosContract.Model {
    private PlacePhotosContract.Presenter presenter;
    private PlacesClient placesClient;
    private String TAG = "DEBUG";

    public PlacePhotosModel(PlacePhotosContract.Presenter presenter, PlacesClient placesClient){
        this.presenter = presenter;
        this.placesClient = placesClient;
    }

    @Override
    public void getResult(Place mplace) {
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
                //imageView.setImageBitmap(bitmap);

                // 콜백
                presenter.callback(bitmap, mplace);

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
}

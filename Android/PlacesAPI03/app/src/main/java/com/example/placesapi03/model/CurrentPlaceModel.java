package com.example.placesapi03.model;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.placesapi03.MyEventListener;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class CurrentPlaceModel{
    private String TAG = "DEBUG";
    private ArrayList<PlaceLikelihood> arrayList;
    private PlacesClient placesClient;
    private Context context;

    public CurrentPlaceModel(Context context){
        this.context = context;
        arrayList = new ArrayList<PlaceLikelihood>();

        // Initialize Places.
        Places.initialize(this.context, "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I");
        // Create a new Places client instance.
        placesClient = Places.createClient(this.context);
        Log.d(TAG, "PlaceAutocompleteModel : 생성자 실행");

    }

    public void getResult(MyEventListener myEventListener) {
        Log.d(TAG, "Model : getResult() 실행");
        // Use fields to define the data types to return.
        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.ID, Place.Field.LAT_LNG);

        // Use the builder to create a FindCurrentPlaceRequest.
        FindCurrentPlaceRequest request =
                FindCurrentPlaceRequest.builder(placeFields).build();

        // Call findCurrentPlace and handle the response (first check that the user has granted permission).
        if (ContextCompat.checkSelfPermission(this.context, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // 이미 퍼미션 권한을 가지고 있는 경우
            Task<FindCurrentPlaceResponse> placeResponse = placesClient.findCurrentPlace(request);
            placeResponse.addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    FindCurrentPlaceResponse response = task.getResult();
                    for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
                        Log.d(TAG, String.format("Place '%s' has likelihood: %f",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getLikelihood()));
                        arrayList.add(placeLikelihood);
                    }
                    if (arrayList.size() != 0){
                        myEventListener.onRecivedEvent(arrayList);
                    }
                } else {
                    Exception exception = task.getException();
                    if (exception instanceof ApiException) {
                        ApiException apiException = (ApiException) exception;
                        Log.d(TAG, "Place not found: " + apiException.getStatusCode());
                    }
                }
            });
        }

    }
}

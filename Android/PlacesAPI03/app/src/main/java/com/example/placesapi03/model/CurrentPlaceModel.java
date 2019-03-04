package com.example.placesapi03.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.example.placesapi03.contract.CurrentPlaceContract;
import com.example.placesapi03.view.CurrentPlaceActivity;
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

public class CurrentPlaceModel implements CurrentPlaceContract.Model {
    private CurrentPlaceContract.Presenter presenter;
    private Context context;
    private String TAG = "DEBUG";
    private ArrayList<PlaceLikelihood> arrayList;

    public CurrentPlaceModel(CurrentPlaceContract.Presenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
        arrayList = new ArrayList<PlaceLikelihood>();
    }

    @Override
    public void getResult() {
        Log.d(TAG, "model : getResult 실행");

        // Initialize Places.
        Places.initialize(context, "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I");

        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(context);

        // Use fields to define the data types to return.
        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.ID, Place.Field.LAT_LNG);

        // Use the builder to create a FindCurrentPlaceRequest.
        FindCurrentPlaceRequest request =
                FindCurrentPlaceRequest.builder(placeFields).build();

        // Call findCurrentPlace and handle the response (first check that the user has granted permission).
        if (ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
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

                    // presenter에 데이터 전달 -> view로 데이터 업데이트
                    presenter.callback(arrayList);

                } else {
                    Exception exception = task.getException();
                    if (exception instanceof ApiException) {
                        ApiException apiException = (ApiException) exception;
                        Log.d(TAG, "Place not found: " + apiException.getStatusCode());
                    }
                }
            });
        } else {
            // 퍼미션 권한을 가지고 있지 않은 경우, 요청한다.
            // A local method to request required permissions;
            // See https://developer.android.com/training/permissions/requesting
            Log.d(TAG, "퍼미션 없음");
            //getLocationPermission();
        }
    }


}

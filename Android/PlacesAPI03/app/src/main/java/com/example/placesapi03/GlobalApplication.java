package com.example.placesapi03;

import android.app.Application;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.List;

public class GlobalApplication extends Application {

    private static PlacesClient placesClient;
    private static List<Place.Field> placeFields;
    public static GlobalApplication obj;

    @Override
    public void onCreate() {
        super.onCreate();
        Places.initialize(this, GlobalApplication.getApiKey());
        placesClient = Places.createClient(this);

        // Use fields to define the data types to return.
        placeFields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.ID, Place.Field.LAT_LNG);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static PlacesClient getPlacesClient(){
        return placesClient;
    }

    public static String getApiKey() {
        return "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I";
    }

    public static List getPlaceFieldList(){
        return placeFields;
    }
    public static GlobalApplication getGlobalApplicationContext(){
        return obj;
    }

}

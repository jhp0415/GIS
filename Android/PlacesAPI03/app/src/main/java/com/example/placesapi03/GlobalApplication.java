package com.example.placesapi03;

import android.app.Application;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

public class GlobalApplication extends Application {

    private static PlacesClient placesClient;
    public static GlobalApplication obj;

    @Override
    public void onCreate() {
        super.onCreate();
        obj = this;
        Places.initialize(this, GlobalApplication.getApiKey());
        placesClient = Places.createClient(this);
    }

    public static PlacesClient getPlacesClient(){
        return placesClient;
    }

    public static String getApiKey() {
        return "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I";
    }

    public static GlobalApplication getGlobalApplicationContext(){
        return obj;
    }
}

package com.example.placesapi03;

import android.graphics.Bitmap;

import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;

import java.util.ArrayList;

public interface MyEventListener {
    void onRecivedEvent(ArrayList<PlaceLikelihood> arrayList);
    void onRecivedEvent(Place place);
    void onRecivedEvent(Place place, Bitmap bitmap);
}

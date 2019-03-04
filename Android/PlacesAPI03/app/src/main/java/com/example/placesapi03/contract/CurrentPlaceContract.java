package com.example.placesapi03.contract;

import android.content.Context;

import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

import java.util.ArrayList;

public interface CurrentPlaceContract {
    interface View {
        void viewInit();
        void updateView(ArrayList<PlaceLikelihood> arrayList);
    }

    interface Presenter {
        void viewToModelCallback(PlacesClient placesClient, Context context);
        void loadResult();
        void modelToViewCallback(ArrayList<PlaceLikelihood> arrayList);
    }

    interface Model {
        void getResult();
        void setParameter(PlacesClient placesClient, Context context);
    }
}

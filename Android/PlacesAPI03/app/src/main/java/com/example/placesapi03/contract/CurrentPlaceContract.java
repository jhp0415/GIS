package com.example.placesapi03.contract;

import com.google.android.libraries.places.api.model.PlaceLikelihood;

import java.util.ArrayList;

public interface CurrentPlaceContract {
    interface View {
        void init();
        void updateView(ArrayList<PlaceLikelihood> arrayList);
    }

    interface Presenter {
        void loadResult();
        void callback(ArrayList<PlaceLikelihood> arrayList);
    }

    interface Model {
        void getResult();
    }

}

package com.example.placesapi03.contract;

import com.google.android.libraries.places.api.model.PlaceLikelihood;

import java.util.ArrayList;

public interface CurrentPlaceContract {
    interface View {
        void viewInit();
        void updateView(ArrayList<PlaceLikelihood> arrayList);
        void setPresenter(CurrentPlaceContract.Presenter presenter);
    }

    interface Presenter {
        void start();
        void loadResult();
        void callback(ArrayList<PlaceLikelihood> arrayList);
    }
}

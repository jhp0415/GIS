package com.example.placesapi03.contract;

import com.google.android.libraries.places.api.model.Place;

public interface PlaceAutocompleteContract {
    interface View {
        void viewInit();
        void updateView(Place place);
        void getPlaceResult(Place place);
        void setPresenter(PlaceAutocompleteContract.Presenter presenter);
    }

    interface Presenter {
        void start();
        void loadResult();
        void callback(Place place);
    }
}

package com.example.placesapi03.contract;

import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

public interface PlaceAutocompleteContract {
    interface View {
        void init();
        void updateView(Place place);
        void getPlaceResult(Place place);
    }

    interface Presenter {
        void loadResult();
        void callback(Place place);
    }

    interface Model {
        void getResult(AutocompleteSupportFragment autocompleteSupportFragment);
    }
}

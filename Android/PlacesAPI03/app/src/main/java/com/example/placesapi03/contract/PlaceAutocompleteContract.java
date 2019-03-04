package com.example.placesapi03.contract;

import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

public interface PlaceAutocompleteContract {
    interface View {
        void viewInit();
        void updateView(Place place);
        void getPlaceResult(Place place);
    }

    interface Presenter {
        void setAutocompleteSupportFragment(AutocompleteSupportFragment autocompleteSupportFragment);
        AutocompleteSupportFragment getAutocompleteSupportFragment();
        void loadResult();
        void modelToViewCallback(Place place);
        void viewToModelCallback(AutocompleteSupportFragment autocompleteSupportFragment);
    }

    interface Model {
        void getResult();
        void setAutocompleteSupportFragment(AutocompleteSupportFragment autocompleteSupportFragment);
    }
}

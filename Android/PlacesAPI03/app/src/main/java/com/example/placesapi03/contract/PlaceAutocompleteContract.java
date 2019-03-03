package com.example.placesapi03.contract;

public interface PlaceAutocompleteContract {
    interface View {
        void init();
        void updateView(String str);
    }

    interface Presenter {
        void placeAutocomplete();
    }

    interface Model {

    }
}

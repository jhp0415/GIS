package com.example.placesapi03.contract;

import android.graphics.Bitmap;

import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

public interface PlacePhotosContract {
    interface View {
        void viewInit();
        void updateView(Bitmap bitmap, Place place);
    }

    interface Presenter {
        void loadResult(Place place);       // 찾는 장소의 ID를 넘겨야 한다.
        void modelToViewCallback(Bitmap bitmap, Place place);
        void viewToModelCallback(PlacesClient placesClient);
    }

    interface Model {
        void getResult(Place place);        // ID를 받아오기 위해
        void setPlaceClient(PlacesClient placeClient);
    }
}

package com.example.placesapi03.contract;

import android.graphics.Bitmap;

import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

public interface PlacePhotosContract {
    interface View {
        void init();
        void updateView(Bitmap bitmap, Place place);
    }

    interface Presenter {
        void loadResult(Place place);       // 찾는 장소의 ID를 넘겨야 한다.
        void callback(Bitmap bitmap, Place place);
    }

    interface Model {
        void getResult(Place place);        // ID를 받아오기 위해
    }
}

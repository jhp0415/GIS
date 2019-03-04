package com.example.placesapi03.contract;

import android.graphics.Bitmap;

import com.google.android.libraries.places.api.model.Place;

public interface PlacePhotosContract {
    interface View {
        void viewInit();
        void updateView(Bitmap bitmap, Place place);
        void setPresenter(PlacePhotosContract.Presenter presenter);
    }

    interface Presenter {
        void start(Place place);
        void loadResult(Place place);       // 찾는 장소의 ID를 넘겨야 한다.
        void callback(Bitmap bitmap, Place place);
    }
}

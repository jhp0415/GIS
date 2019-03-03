package com.example.placesapi03.contract;

public interface CurrentPlaceContract {
    interface View {
        void init();
        void updateView(String str);
    }

    interface Presenter {
        void loadData();
    }

    interface Model {

    }

}

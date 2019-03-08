package com.example.myretrotif02.construct;

import com.example.myretrotif02.data.place.Place;

import java.util.HashMap;

public interface POISearchConstruct {
    interface View {
        void viewInit();
        void updateView(Place place);
        void setPresenter(POISearchConstruct.Presenter presenter);
        void userRequest(String userData);
    }

    interface Presenter {
        void start(String userData);
        void loadResult(HashMap<String, String> map);
        void viewCallback(Place place);
    }
}

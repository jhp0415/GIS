package com.example.myretrotif02.presenter;

import com.example.myretrotif02.CallbackListener;
import com.example.myretrotif02.construct.POISearchConstruct;
import com.example.myretrotif02.data.place.Place;
import com.example.myretrotif02.model.POISearchModel;

import java.util.HashMap;

public class POISearchPresenter implements POISearchConstruct.Presenter {
    private POISearchConstruct.View view;
    private POISearchModel model;

    public POISearchPresenter(POISearchConstruct.View view, POISearchModel model) {
        this.view = view;
        this.model = model;
        this.view.setPresenter(this);
    }

    @Override
    public void start(String userData) {
        // 사용자 입력 데이터 파싱 -> 파라미터화
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("terms", userData);

        loadResult(parameters);
    }

    @Override
    public void loadResult(HashMap<String, String> parameters) {
        model.getResult(parameters, new CallbackListener() {
            @Override
            public void onReceived(Place place) {
                // 최종 결과 데이터 받음
                // View Update
                viewCallback(place);
            }
        });
    }

    @Override
    public void viewCallback(Place place) {
        view.updateView(place);
    }
}

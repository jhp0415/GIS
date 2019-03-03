package com.example.placesapi03.presenter;

import com.example.placesapi03.contract.CurrentPlaceContract;

public class CurrentPlacePresenter implements CurrentPlaceContract.Presenter {
    private CurrentPlaceContract.View view;
    private String TAG = "DEBUG";

    public CurrentPlacePresenter(CurrentPlaceContract.View view){

    }

}

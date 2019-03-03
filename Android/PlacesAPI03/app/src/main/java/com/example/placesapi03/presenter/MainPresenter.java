package com.example.placesapi03.presenter;

import com.example.placesapi03.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private String TAG = "DEBUG";

    public MainPresenter(MainContract.View view){
        this.view = view;
    }

}
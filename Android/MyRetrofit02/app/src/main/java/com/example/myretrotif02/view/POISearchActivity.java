package com.example.myretrotif02.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myretrotif02.R;
import com.example.myretrotif02.construct.POISearchConstruct;
import com.example.myretrotif02.data.place.Place;
import com.example.myretrotif02.model.POISearchModel;
import com.example.myretrotif02.presenter.POISearchPresenter;

public class POISearchActivity extends AppCompatActivity
        implements POISearchConstruct.View {

    private POISearchConstruct.Presenter presenter;
    private EditText editText;
    private TextView tv_id;
    private TextView tv_name;
    private TextView tv_address_parcel;
    private TextView tv_address_road;
    private TextView tv_point;
    private TextView tv_phones;
    private TextView tv_theme;
    private TextView tv_photoURL;
    private TextView tv_homepageURL;
    private TextView tv_opeingHours;

    private Button button;
    private String TAG = "ddd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poisearch);

        viewInit();
        presenterInit();
    }

    public void presenterInit(){
        presenter = new POISearchPresenter(this,
                new POISearchModel(presenter));
    }

    @Override
    public void viewInit() {
        tv_id = (TextView) findViewById(R.id.tv_id_value);
        tv_name = (TextView) findViewById(R.id.tv_name_value);
        tv_address_parcel = (TextView) findViewById(R.id.tv_address_parcel_value);
        tv_address_road = (TextView) findViewById(R.id.tv_address_road_value);
        tv_point = (TextView) findViewById(R.id.tv_point_value);
        tv_phones = (TextView) findViewById(R.id.tv_phones_value);
        tv_theme = (TextView) findViewById(R.id.tv_theme_value);
        tv_photoURL = (TextView) findViewById(R.id.tv_photoURL_value);
        tv_homepageURL = (TextView) findViewById(R.id.tv_homepageURL_value);
        tv_opeingHours = (TextView) findViewById(R.id.tv_openingHours_value);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.search_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userData = editText.getText().toString();
                presenter.start(userData);
            }
        });
    }

    @Override
    public void userRequest(String userData) {
        presenter.start(userData);
    }

    @Override
    public void updateView(Place place) {
//        tv_id.setText(place.getPois().get(0).getId());
//
//        tv_name.setText(place.getPois().get(0).getName());
//
//        tv_address.setText(place.getPois().get(0).getAddress().getSiDo() + " "
//                + place.getPois().get(0).getAddress().getStreet() + " "
//                + place.getPois().get(0).getAddress().getStreetNumber() + " "
//                + place.getPois().get(0).getName());
//
//        tv_point.setText(place.getPois().get(0).getPoint().getLat().toString() + ", " + place.getPois().get(0).getPoint().getLng().toString());

        for(int index = 0; index < place.getNumberOfPois() % 10; index++) {
            Log.d(TAG, String.valueOf(place.getNumberOfPois()));
            Log.d(TAG, place.getId());
            tv_id.setText(place.getId());
            Log.d(TAG, place.getName());
            tv_name.setText(place.getName());
            Log.d(TAG, place.getAddressParcel());
            tv_address_parcel.setText(place.getAddressRoad());
            Log.d(TAG, place.getLat() + ", " + place.getLng());
            tv_point.setText(place.getLat() + ", " + place.getLng());
            Log.d(TAG, place.getPhones());
            tv_phones.setText(place.getPhones());
            Log.d(TAG, place.getPhones());
            tv_theme.setText(place.getTheme());
            Log.d(TAG, place.getPhotoURL());
            tv_photoURL.setText(place.getPhotoURL());
            Log.d(TAG, place.getHomepageURL());
            tv_homepageURL.setText(place.getHomepageURL());
            Log.d(TAG, place.getOpeningHours());
            tv_opeingHours.setText(place.getOpeningHours());
        }
    }

    @Override
    public void setPresenter(POISearchConstruct.Presenter presenter) {
        this.presenter = presenter;
    }

}

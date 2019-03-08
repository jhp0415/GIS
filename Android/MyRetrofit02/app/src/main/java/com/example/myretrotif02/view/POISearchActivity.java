package com.example.myretrotif02.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private TextView tv_address;
    private TextView tv_point;
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
        tv_address = (TextView) findViewById(R.id.tv_address_value);
        tv_point = (TextView) findViewById(R.id.tv_point_value);

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
        tv_id.setText(place.getPois().get(0).getId());

        tv_name.setText(place.getPois().get(0).getName());

        tv_address.setText(place.getPois().get(0).getAddress().getSiDo() + " "
                + place.getPois().get(0).getAddress().getStreet() + " "
                + place.getPois().get(0).getAddress().getStreetNumber() + " "
                + place.getPois().get(0).getName());

        tv_point.setText(place.getPois().get(0).getPoint().getLat().toString() + ", " + place.getPois().get(0).getPoint().getLng().toString());
    }

    @Override
    public void setPresenter(POISearchConstruct.Presenter presenter) {
        this.presenter = presenter;
    }

}

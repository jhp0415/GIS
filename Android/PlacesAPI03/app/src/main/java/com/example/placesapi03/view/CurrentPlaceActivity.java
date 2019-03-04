package com.example.placesapi03.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.placesapi03.Adapter.ListViewAdapter;
import com.example.placesapi03.R;
import com.example.placesapi03.contract.CurrentPlaceContract;
import com.example.placesapi03.data.ListItem;
import com.example.placesapi03.presenter.CurrentPlacePresenter;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;

public class CurrentPlaceActivity extends AppCompatActivity implements CurrentPlaceContract.View {
    private CurrentPlaceContract.Presenter presenter;
    private String TAG = "DEBUG";
    private TextView textView;
    private PlacesClient placesClient;

    ListView listView;
    ListViewAdapter listViewAdapter;
    ArrayList<ListItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_place);

        viewInit();
        presenterInit();
        getCurrentPlaceResult();
    }

    public void presenterInit(){
        presenter = new CurrentPlacePresenter(this);
        presenter.viewToModelCallback(placesClient, getApplicationContext());
    }

    public void getCurrentPlaceResult(){
        presenter.loadResult();
    }

    @Override
    public void viewInit() {
        textView = (TextView) findViewById(R.id.current_place_textview);
        listView = (ListView) findViewById(R.id.current_place_listview);

        arrayList = new ArrayList<ListItem>();
        listViewAdapter = new ListViewAdapter(CurrentPlaceActivity.this, arrayList);
        listView.setAdapter(listViewAdapter);

        // Initialize Places.
        Places.initialize(this, "AIzaSyDSAwlWaFJ2s9hOYzNCNcItMqFt_-NNB8I");
        // Create a new Places client instance.
        placesClient = Places.createClient(this);
    }

    @Override
    public void updateView(ArrayList<PlaceLikelihood> result) {
        textView.setText(result.get(0).getPlace().getName() + ", " + result.get(0).getLikelihood() + "\n" +
                result.get(0).getPlace().getAddress() + result.get(0).getPlace().getLatLng().latitude + result.get(0).getPlace().getLatLng().longitude);

        for (PlaceLikelihood temp : result) {
            arrayList.add(new ListItem(temp.getPlace().getName(), temp.getPlace().getAddress() +
                    temp.getPlace().getLatLng().latitude + temp.getPlace().getLatLng().longitude + temp.getLikelihood()));
        }
    }
}

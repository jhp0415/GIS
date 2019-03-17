package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.gms.maps.model.LatLng;
import com.kt.place.sdk.listener.OnSuccessListener;
import com.kt.place.sdk.net.PoiRequest;
import com.kt.place.sdk.net.PoiResponse;
import com.kt.place.sdk.util.Client;

public class SearchActivity extends AppCompatActivity {
    private RecyclerAdapter mAdapter;
    private Toolbar myToolbar;
    private LatLng currentPoint;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private Client client;
    private PoiResponse result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
    }

    private void initView() {
//        getIntentDate();
        client = new Client();

        // Toolbar 초기화
        myToolbar = (Toolbar) findViewById(R.id.main_search_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);    // 타이틀 안보이게 하기

        // RecyclerView 초기화
        recyclerView = (RecyclerView) findViewById(R.id.notice_search_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(mAdapter);
    }

    private void getIntentDate(){
        Intent intent = getIntent();
//        currentPoint = (LatLng) intent.getSerializableExtra("currentPoint");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.poi_search_menu, menu);
        searchView = (SearchView)menu.findItem(R.id.action_poi_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("검색어를 입력하세요.");
//        SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        searchView.onActionViewExpanded(); //바로 검색 할 수 있도록

        // SearchView 검색어 입력/검색 이벤트 처리리
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("onQueryTextChange", newText);
                requestPoiSearch(newText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("onQueryTextSubmit", query);
                requestPoiSearch(query);
                return true;
            }
        });
        return true;
    }

    public void requestPoiSearch(final String terms) {
        PoiRequest request = new PoiRequest.PoiRequestBuilder(terms)
                .setLat(37.47171799315875)
                .setLng(127.02825885458788)
                .build();

        client.getPoiSearch(request, new OnSuccessListener<PoiResponse>() {
            @Override
            public void onSuccess(@NonNull PoiResponse poiResponse) {
                if(poiResponse.getNumberOfPois() > 0) {
                    Log.d("ddd", "success " + poiResponse.getPois().get(0).getName());
                    result = poiResponse;
                    mAdapter.setFilter(result.getPois());
                }
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                Log.d("ddd", throwable.getMessage());
            }
        });
    }


}

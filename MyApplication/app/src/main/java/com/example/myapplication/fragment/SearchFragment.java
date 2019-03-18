package com.example.myapplication.fragment;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.AutocompleteRecyclerAdapter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.kt.place.sdk.listener.OnSuccessListener;
import com.kt.place.sdk.model.Poi;
import com.kt.place.sdk.model.Suggest;
import com.kt.place.sdk.net.AutocompleteRequest;
import com.kt.place.sdk.net.AutocompleteResponse;
import com.kt.place.sdk.net.PoiRequest;
import com.kt.place.sdk.net.PoiResponse;
import com.kt.place.sdk.util.Client;

public class SearchFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener {
    OnSearchedCallbackListener listener;

    private Toolbar myToolbar;
    private RecyclerAdapter mAdapter;
    private AutocompleteRecyclerAdapter mAutocompleteAdapter;
    private LatLng currentPoint;
    private double currentPointLat;
    private double currentPointLng;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private RecyclerView autocompleteRecyclerView;
    private Client client;
    private PoiResponse poiResult;
    private AutocompleteResponse autocompleteResult;
    private SwipeRefreshLayout swipeRefreshLayout;

    private boolean isLoading = false;
    private int mCurrentPage = 0; // 현재 페이지 번호
    private int mTotalPage = 0; // 전체 페이지 번호
    private int mItemCount= 10; // 화면에 보여줄 항목 수

    private int mLastKnowIndex = -1; // 현재까지 보여준 poi 인덱스
    private int mNextStartIndex = 0;    // 다음 호출할 poi 번호
    private int mTotalIndex = 0;
    private int numberOfResults = 10;

    private int refresh = 0;
    private String finalTerms = "";
    private Location currentLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, parentViewGroup, false);

        setHasOptionsMenu(true);

        client = new Client();

        // RecyclerView 초기화
        recyclerView = (RecyclerView) view.findViewById(R.id.poi_search_recycler2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);

        // Autocomplete RecyclerView 초기화
        autocompleteRecyclerView = (RecyclerView) view.findViewById(R.id.autocomplete_search_recycler2);
        autocompleteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        autocompleteRecyclerView.setHasFixedSize(true);
        mAutocompleteAdapter = new AutocompleteRecyclerAdapter(getActivity());
        autocompleteRecyclerView.setAdapter(mAutocompleteAdapter);

        // SwipeRefreshLayout
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh_layout2);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;

                if (lastVisibleItemPosition == itemTotalCount) {
                    // 다음 리스트 받아오기
                    mLastKnowIndex = mLastKnowIndex + numberOfResults;
                    mNextStartIndex = mLastKnowIndex + 1;
                    Log.d("ddd", mNextStartIndex + " 번째 호출하기");
                    requestPoiSearch(finalTerms, mNextStartIndex);
                    recyclerView.scrollToPosition(0);
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getIntentDate();
    }

    // 메인에서 현재위치 받아오기
    private void getIntentDate(){
        currentLocation = ((MainActivity)getActivity()).mLastKnownLocation;
        currentPoint = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
    }

    // 메인 지도에 결과 전달하기
    public void setIntentData(Poi poi) {
        ((MainActivity)getActivity()).onFragmentResult(poi);
    }

    public void setAutocompleteIntentData(Suggest suggest) {
        ((MainActivity)getActivity()).onFragmentResultAutocomplete(suggest);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.poi_search_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);

        searchView = (SearchView)menu.findItem(R.id.action_poi_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("검색어를 입력하세요.");
        searchView.onActionViewExpanded(); //바로 검색 할 수 있도록

        // SearchView 검색어 입력/검색 이벤트 처리리
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("onQueryTextChange", newText);
                finalTerms = newText;
                requestPoiSearch(newText, mNextStartIndex);
                requestAutocomplete(newText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("onQueryTextSubmit", query);
                requestPoiSearch(query, mNextStartIndex);
                requestAutocomplete(query);
                return true;
            }
        });
    }

    public void requestPoiSearch(final String terms, int nextStartIndex) {
        PoiRequest request = new PoiRequest.PoiRequestBuilder(terms)
                .setLat(currentPoint.latitude)
                .setLng(currentPoint.longitude)
                .setStart(nextStartIndex)
                .setNumberOfResults(10)
                .build();

        client.getPoiSearch(request, new OnSuccessListener<PoiResponse>() {
            @Override
            public void onSuccess(@NonNull PoiResponse poiResponse) {
                if(poiResponse.getPois().size() > 0) {
                    Log.d("ddd", "POI " + poiResponse.getPois().get(0).getName());
                    poiResult = poiResponse;
                    mAdapter.setFilter(poiResult.getPois());
                }
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                Log.d("ddd", throwable.getMessage());
            }
        });
    }

    public void requestAutocomplete(final String terms) {
        AutocompleteRequest request = new AutocompleteRequest.AutocompleteRequestBuilder(terms)
                .setLat(currentPoint.latitude)
                .setLng(currentPoint.longitude)
                .build();

        client.getAutocomplete(request, new OnSuccessListener<AutocompleteResponse>() {
            @Override
            public void onSuccess(@NonNull AutocompleteResponse autocompleteResponse) {
                if(autocompleteResponse.getSuggestList().size() > 0) {
                    Log.d("ddd", "Autocomplete " + autocompleteResponse.getSuggestList().size());
                    autocompleteResult = autocompleteResponse;
                    mAutocompleteAdapter.setFilter(autocompleteResult.getSuggestList());
                }
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                Log.d("ddd", throwable.getMessage());
            }
        });
    }

    @Override
    public void onRefresh() {
        // 새로고침 코드
        // mNextStartIndex = 0 으로 다시 호출
        requestPoiSearch(finalTerms, 0);
        mNextStartIndex = 0;
        mLastKnowIndex = -1;
        // 새로고침 종료
        swipeRefreshLayout.setRefreshing(false);
        Log.d("ddd", "새로고침");
    }

    public void setOnSearchedCallbackListener(Activity activity) {
        listener = activity;
    }

    // Container Activity must implement this interface
    public interface OnSearchedCallbackListener {
        void onDataCallback(int position);
        void onGPSCurrentLocation(Location location);
    }
}

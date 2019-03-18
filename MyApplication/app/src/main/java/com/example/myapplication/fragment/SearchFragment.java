package com.example.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.AutocompleteRecyclerAdapter;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerAdapter;

public class SearchFragment extends Fragment {
    private RecyclerAdapter mAdapter;
    private AutocompleteRecyclerAdapter mAutocompleteAdapter;
    private Toolbar myToolbar;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private RecyclerView autocompleteRecyclerView;
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, parentViewGroup, false);

        // Toolbar 초기화
        myToolbar = (Toolbar) view.findViewById(R.id.main_search_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);    // 타이틀 안보이게 하기

        // RecyclerView 초기화
        recyclerView = (RecyclerView) view.findViewById(R.id.poi_search_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(mAdapter);

        // Autocomplete RecyclerView 초기화
        autocompleteRecyclerView = (RecyclerView) view.findViewById(R.id.autocomplete_search_recycler);
        autocompleteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        autocompleteRecyclerView.setHasFixedSize(true);
        mAutocompleteAdapter = new AutocompleteRecyclerAdapter(this);
        autocompleteRecyclerView.setAdapter(mAutocompleteAdapter);

        // SwipeRefreshLayout
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh_layout);
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
}

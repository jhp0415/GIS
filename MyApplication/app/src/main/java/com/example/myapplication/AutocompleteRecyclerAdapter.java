package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kt.place.sdk.model.Suggest;

import java.util.ArrayList;
import java.util.List;

public class AutocompleteRecyclerAdapter extends RecyclerView.Adapter<AutocompleteRecyclerAdapter.ViewHolder> {

    private List<Suggest> items = new ArrayList<>();
    private Context mContext;

    public AutocompleteRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.autocomplete_recycler_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    //이부분 중요!! 검색 리스트를 나오게하기 위해 꼭 필요
    public void setFilter(List<Suggest> items) {
        this.items.clear();
//        this.items.addAll(items);
        if(items.size() >= 3) {
            this.items.add(items.get(0));
            this.items.add(items.get(1));
            this.items.add(items.get(2));
        } else {
            this.items.addAll(items);
        }
        notifyDataSetChanged();     // 데이터 업데이트
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = items.get(position);
        holder.mTitleText.setText(items.get(position).getTerms());
//        holder.mDescriptionText.setText(items.get(position).getPoiId());
//        holder.mDistance.setText(items.get(position).getDistance().toString());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인텐트 호출
                ((MainActivity) mContext).onFragmentResultAutocomplete(holder.mItem);

            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public Suggest mItem;
        public final View mView;
        public final TextView mTitleText;
//        public final TextView mDescriptionText;
//        public final TextView mDistance;

        public ViewHolder(View view) {
            super(view);
            mView = view;       // View 초기화
            mTitleText = (TextView) view.findViewById(R.id.suggest);
//            mDescriptionText = (TextView) view.findViewById(R.id.description);
//            mDistance = (TextView) view.findViewById(R.id.distance);
        }
    }
}

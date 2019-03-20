package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.fragment.SearchFragment;
import com.kt.place.sdk.model.Poi;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Poi> items = new ArrayList<>();
    private Context mContext;


    public RecyclerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    //이부분 중요!! 검색 리스트를 나오게하기 위해 꼭 필요
    public void setFilter(List<Poi> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();     // 데이터 업데이트
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = items.get(position);
        holder.mTitleText.setText(items.get(position).getName() + " " + items.get(position).getBranch());
        holder.mDescriptionText.setText(items.get(position).getAddress().getFullAddressParcel());
        holder.mDistance.setText(String.valueOf((int) Math.round(items.get(position).getDistance())));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 아이템 클릭 리스너
                // 추후 아이템 클릭시 이전 액티비티로 이동해 지도에 마커 찍기 구현
                // intent
                Toast.makeText(mContext, String.format("%d번 아이템 선택", position), Toast.LENGTH_SHORT).show();
                // 인텐트 호출
                ((MainActivity) mContext).onFragmentResult(holder.mItem);


            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public Poi mItem;
        public final View mView;
        public final TextView mTitleText;
        public final TextView mDescriptionText;
        public final TextView mDistance;

        public ViewHolder(View view) {
            super(view);
            mView = view;       // View 초기화
            mTitleText = (TextView) view.findViewById(R.id.title);
            mDescriptionText = (TextView) view.findViewById(R.id.description);
            mDistance = (TextView) view.findViewById(R.id.distance);
        }
    }
}

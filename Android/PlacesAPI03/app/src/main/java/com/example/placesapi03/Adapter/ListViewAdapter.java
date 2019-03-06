package com.example.placesapi03.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.placesapi03.R;
import com.example.placesapi03.data.ListItem;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<ListItem> arrayList;
    List<ListItem> items;
    TextView title;
    TextView description;

    public ListViewAdapter(Context context, java.util.ArrayList<ListItem> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem,null);
        }

        title = (TextView)convertView.findViewById(R.id.current_place_item_title);
        description = (TextView)convertView.findViewById(R.id.current_place_item_description);

        title.setText(arrayList.get(position).getTitle());
        description.setText(arrayList.get(position).getDescription());

        return convertView;
    }
}

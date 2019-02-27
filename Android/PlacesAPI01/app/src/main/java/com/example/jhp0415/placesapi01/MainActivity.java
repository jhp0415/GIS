package com.example.jhp0415.placesapi01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jhp0415.placesapi01.currentplace.CurrentPlaceActivity;
import com.example.jhp0415.placesapi01.searchplace.PlaceSearchActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1=(Button)findViewById(R.id.current_place_btn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentPlaceActivity.class);
                startActivity(intent);
            }
        });

        Button button2=(Button)findViewById(R.id.Search_btn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlaceSearchActivity.class);
                startActivity(intent);
            }
        });





    }


}

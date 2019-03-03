package com.example.placesapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

        Button button2=(Button)findViewById(R.id.place_autocomplete_btn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,  PlaceAutocompleteActivity.class);
                startActivity(intent);
            }
        });

        Button button3=(Button)findViewById(R.id.place_photos_btn);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,  PlacePhotosActivity.class);
                startActivity(intent);
            }
        });

        Button button4=(Button)findViewById(R.id.place_picker_btn);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,  PlacePickerActivity.class);
                startActivity(intent);
            }
        });

    }


}

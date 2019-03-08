package com.example.myretrotif02.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.myretrotif02.R;

public class MainActivity extends AppCompatActivity
            implements View.OnClickListener{
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.button1:
                getApplicationContext().startActivity(new Intent(this, POISearchActivity.class));
                break;
            case R.id.button2:
                getApplicationContext().startActivity(new Intent(this, RetrievePOIActivity.class));
                break;
        }
    }

}

package com.example.placesapi03.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.placesapi03.R;
import com.example.placesapi03.contract.MainContract;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;
    private String TAG = "DEBUG";
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    private static final int PERMISSIONS_REQUEST_CODE = 100;
    private View mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentPlaceActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,  PlaceAutocompleteActivity.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,  PlacePhotosActivity.class);
                startActivity(intent);
            }
        });

        getLocationPermission();

    }

    @Override
    public void init() {
        button1=(Button)findViewById(R.id.current_place_btn);
        button2=(Button)findViewById(R.id.place_autocomplete_btn);
        button3=(Button)findViewById(R.id.place_photos_btn);
    }

    private void getLocationPermission() {
        Log.d(TAG, "MainActivity : getLocationPermission 실행 --> 권한 얻기");
        // 2-2. 퍼미션이 없다면, 펴미션 요청을 한다. 2가지 경우가 있다. (3-1, 4-1)
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우 -> 스낵바를 사용해 퍼미션 허용을 요청한다.
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있다.
                Snackbar.make(mLayout, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.",
                        Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // 3-3. 사용자에 퍼미션 요청을 한다. 요청 결과는 onRequestPermissionResult에서 수신된다.
                        // 퍼미션 요청의 결과를 리턴받는 메소드가 따로 존재한다.
                        Log.d(TAG, "onMapReady : 사용자에게 퍼미션 요청중...");
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                PERMISSIONS_REQUEST_CODE);
                    }
                }).show();

            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우. -> 펴미션 요청을 바로 한다.
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_CODE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult : 퍼미션 요청 결과");

        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면,
            boolean check_result = true;

            // 1. 모든 퍼미션을 허용했는지 체크한다.
            for(int result : grantResults) {
                if(result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;       // 모두 허용되지 않았다.
                    break;
                }
            }
            if (check_result) {
                // 2-1. 퍼미션이 모두 허용된 경우
                // 3. 위치 업데이트 시작
                //startLocationUpdates();

            } else {    // 2-2. 퍼미션이 모두 허용되지 않은 경우
                // 3. 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료한다. 2가지 경우가 있다.
                if ( ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                    // 4-1. 사용자가 거부만 선택한 경우에는 앱을 다시 실행하여 허용을 선택하면 앱을 사용할 수 있다.
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    }).show();
                } else {
                    // 4-2. "다시 묻지 않음"을 사용자가 체크하고 거부를 선택한 경우에는 설정(앱 정보)에서 퍼미션을 허용해야 앱을 사용ㅎㄹ 수 있다.
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    }).show();
                }
            }
        }
    }


}

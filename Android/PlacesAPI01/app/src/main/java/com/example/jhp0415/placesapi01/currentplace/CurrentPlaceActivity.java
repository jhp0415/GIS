package com.example.jhp0415.placesapi01.currentplace;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jhp0415.placesapi01.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CurrentPlaceActivity extends AppCompatActivity
        implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap mGoogleMap = null;
    private Marker currentMarker = null;

    private static final String TAG = "GPS";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int UPDATE_INTERVAL_MS = 1000;     //1초
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500;  //0.5초

    //onRequestPermissionsResult에서 수신된 결과에서 ActivityCompat.requestPermission를 사용한 퍼미션 요청을 구별하기 위해 사용된다.
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    boolean needRequest = false;

    //앱을 실행하기 위해 필요한 퍼미션을 정의한다.
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    Location mCurrentLocation;
    LatLng currentPosition;

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private Location location;
    //LocationCallback locationCallback;

    private View mLayout;       //Snackbar 사용하기 위해서는 View가 필요하다


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_place);

        mLayout = findViewById(R.id.current_place_layout);

        //결과객체 생성
        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL_MS)
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);

        //결과객체와 실제 위치를 받아올 객체 간의 연동기
        LocationSettingsRequest.Builder builder =
                new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);

        //내 위치를 받아오기 위한 객체와 지도 객체 생성
        Log.d(TAG, "onCreate : FusedLocationClient 초기화");
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        MapFragment mapFragment = (MapFragment)getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    //지도 준비사항 -> 초기 위치 설정, 위피 퍼미션 설정
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady : 실행");
        mGoogleMap = googleMap;

        //런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에
        //지도의 초기위치를 서울로 이동한다.
        Log.d(TAG, "onMapReady : setDefaultLoction() 실행");
        setDefaultLocation();

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크한다.
        Log.d(TAG, "onMapReady : 퍼미션 여부 확인중....");
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
            // 2-1. 이미 퍼미션을 가지고 있다면

            // 3. 위치 업데이트 시작
            Log.d(TAG, "onMapReady : 퍼미션을 가지고 있음");
            Log.d(TAG, "onMapReady : startLocationUpdates() 실행 -> 위치 업데이트 시작");
            startLocationUpdates();

        }else { // 2-2. 퍼미션이 없다면, 펴미션 요청을 한다. 2가지 경우가 있다. (3-1, 4-1)
            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우 -> 스낵바를 사용해 퍼미션 허용을 요청한다.
            Log.d(TAG, "onMapReady : 퍼미션 없음");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {
                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있다.
                Snackbar.make(mLayout, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.",
                        Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // 3-3. 사용자에 퍼미션 요청을 한다. 요청 결과는 onRequestPermissionResult에서 수신된다.
                        // 퍼미션 요청의 결과를 리턴받는 메소드가 따로 존재한다.
                        Log.d(TAG, "onMapReady : 사용자에게 퍼미션 요청중...");
                        ActivityCompat.requestPermissions(CurrentPlaceActivity.this, REQUIRED_PERMISSIONS,
                                PERMISSIONS_REQUEST_CODE);
                    }
                }).show();

                // 요청한 퍼미션이 모두 허용되면
                // onRequestPermissionsResult() 에서 startLocationUpdetes()를 실행해 위치 업데이트를 시작한다.

            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우. -> 펴미션 요청을 바로 한다.
                // 요청 결과는 onRequestPermissionResult에서 수신된다.
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    Log.d(TAG, "onMapClick : ");
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult : 사용자에게 퍼미션을 요청한다");
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grantResults.length == REQUIRED_PERMISSIONS.length) {
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
                startLocationUpdates();

            } else {    // 2-2. 퍼미션이 모두 허용되지 않은 경우
                // 3. 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료한다. 2가지 경우가 있다.
                if ( ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

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

    //초기 지도의 위치를 서울로 이동시킨다.
    public void setDefaultLocation() {
        //디폴트 위치, 서울
        //마커 옵션 설정
        Log.d(TAG, "setDeaultLocation : 초기 위치 서울로 지정");
        LatLng DEFAULT_LOCATION = new LatLng(37.56,126.97);
        String markerTitle = "위치정보 가져올 수 없음";
        String markerSnippet = "위치 퍼미션과 GPS 활성 여부 확인하세요";

        if(currentMarker != null) currentMarker.remove();       //마커 초기화

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mGoogleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15);
        mGoogleMap.moveCamera(cameraUpdate);
    }

    //위치 업데이트 메소드
    private void startLocationUpdates() {
        Log.d(TAG, "startLocationUpdates : 위치 업데이트 메소드 실행");

        // 1. GPS, NETOWRK 활성화 여부 체크하기
        Log.d(TAG, "startLocationUpdetes : GPS 활성화 여부 확인하기...");
        if (!checkLocationServiceStatus()) {
            //2-1. GPS가 활성화 되어 있지 않다면
            Log.d(TAG, "startLocationUpdates : GPS 활성화 안되어 있음");
            Log.d(TAG, "startLocationUpdates : call showDialogForLocationServiceSetting");

            // 3. GPS 활성화 시킨다.
            showDialogForLocationServiceSetting();
        } else {
            Log.d(TAG, "startLocationUpdates : GPS 활성화 되어 있음");
            // 2-2. GPS가 활성화 되어 있다면
            int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);

            // 3-1. 다시 퍼미션 권한을 체크해본다.
            if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                    hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "startLocationUpdates : 퍼미션 안가지고 있음");
                // 4-1. 퍼미션 없음 -> 에러
                return;
            }

            // 3-2. 퍼미션을 정상적으로 가지고 있다면
            // 4. 위치를 업데이트 한다.
            Log.d(TAG, "startLocationUpdetes : 위치 업데이트");
            Log.d(TAG, "startLocationUpdates : call mFusedLocationClient.requestLocationUpdates");
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

            if (checkPermission())
                mGoogleMap.setMyLocationEnabled(true);
        }
    }

    private boolean checkPermission() {
        Log.d(TAG, "checkPermission : 퍼미션 체크");
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GPS_ENABLE_REQUEST_CODE:
                // 사용자가 GPS 활성 시켰는지 검사
                if(checkLocationServiceStatus()) {
                    if (checkLocationServiceStatus()) {
                        Log.d(TAG, "onActivityResult : GPS 활성화 되있음");
                        needRequest = true;
                        return;
                    }
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

        // 퍼미션 권한 등 모든것이 갖춰져 있을 경우, 바로 위치를 받아온다
        if (checkPermission()) {
            Log.d(TAG, "onStart : call mFusedLocationClient.requestLocationUpdates");
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);

            // 맵 카메라를 현재위치로 이동
            if (mGoogleMap != null)
                mGoogleMap.setMyLocationEnabled(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 앱을 종료하는 경우
        if(mFusedLocationClient != null) {
            Log.d(TAG, "onStop : call stopLocationUpdates");
            mFusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }

    //GPS와 NETWORK 활성화 여부 체크하기
    private boolean checkLocationServiceStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    //GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {
        Log.d(TAG, "showDialogForLoactionServiceSetting : GPS 활성화 시키기");
        AlertDialog.Builder builder = new AlertDialog.Builder(CurrentPlaceActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent callGPSSettingIntent
                        = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    //------------------------------- 기본 설정 끝 -----------------------------------------------
    //------------------------------- 이제 부터 위치 받기 -----------------------------------------

    //위치 정보 수신하는 콜백함수
    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            Log.d(TAG, "onLocationResult : 위치 정보 수신하는 콜백함수");
            List<Location> locationList = locationResult.getLocations();        //위치 받기

            if (locationList.size() > 0) {
                location = locationList.get(locationList.size() - 1);       // 가장 마지막 위치 꺼내기
                currentPosition
                        = new LatLng(location.getLatitude(), location.getLongitude());  //위도, 경도 추출

                // 지오코더를 사용해 GPS -> 주소로 변환
                String markerTitle = getCurrentAddress(currentPosition);
                String markerSnippet = "위도:" + String.valueOf(location.getLatitude())
                        + " 경도:" + String.valueOf(location.getLongitude());     //설명

                Log.d(TAG, "onLocationResult : " + markerSnippet);

                //현재 위치에 마커 생성하고 이동
                setCurrentLocation(location, markerTitle, markerSnippet);
                mCurrentLocation = location;
            }
        }
    };

    // 위치 -> 주소 변환
    // 주소 변환 실패하면 실패 메시지 리턴, 성공하면 성공한 주소 String을 리턴
    public String getCurrentAddress(LatLng latlng) {
        //지오코더 : GPS(위도, 경도)를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(
                    latlng.latitude,
                    latlng.longitude,
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            // GPS 오류
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";
        }

        // 주소 문제
        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";
        } else {    // 변환된 주소 리턴하기
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }
    }

    // 지도에 현재 위치 마커로 표시하기
    private void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {
        if (currentMarker != null)
            currentMarker.remove();     // 마커 초기화하기

        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);

        currentMarker = mGoogleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
        mGoogleMap.moveCamera(cameraUpdate);
    }



}

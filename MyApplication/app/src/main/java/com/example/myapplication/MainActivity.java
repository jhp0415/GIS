package com.example.myapplication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kt.place.sdk.listener.OnSuccessListener;
import com.kt.place.sdk.net.PoiRequest;
import com.kt.place.sdk.net.PoiResponse;
import com.kt.place.sdk.util.Client;
import com.kt.place.sdk.util.Manager;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        LocationListener {

    private Toolbar myToolbar;
    private GoogleMap mGoogleMap = null;
    private Location mLastKnownLocation = null;
    private LatLng defaultPoint = new LatLng(37.57248123626738, 126.97783713788459);        //kT광화문west
    private String defaultTitle = "위치정보 가져올 수 없음";
    private String defaultSnippet = "위치 퍼미션과 GPS 활성 여부 확인";
    private MarkerOptions defaultMarkerOption = null;

    // GPS
    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private boolean mLocationPermissionGranted = false;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private int ZOOM_LEVEL = 13;

    private Client client;
    private final String TAG = "ddd";
    private LocationManager locationManager;
    // 현재 GPS 사용유무
    boolean isGPSEnabled = false;
    // 네트워크 사용유무
    boolean isNetworkEnabled = false;
    // 최소 GPS 정보 업데이트 거리 1미터
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    // 최소 GPS 정보 업데이트 시간 밀리세컨(1분)
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void initView() {
        // 구글맵 객체 생성
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // 추가된 소스, Toolbar를 생성한다.
        myToolbar = (Toolbar) findViewById(R.id.main_search_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 왼쪽 버튼 사용 여부 true
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);  // 왼쪽 버튼 이미지 설정
        getSupportActionBar().setDisplayShowTitleEnabled(false);    // 타이틀 안보이게 하기


        defaultMarkerOption = new MarkerOptions();
        defaultMarkerOption.position(defaultPoint);
        defaultMarkerOption.title("초기위치");
        defaultMarkerOption.snippet("초기위치 마커");
        defaultMarkerOption.draggable(true);

        Manager.initialize(getApplicationContext(),
                "Bearer eb142d9027f84d51a4a20df8490e44bcf6fc7ef4dea64cae96a7fca282ebd8cc02764651");
        client = new Client();
//        gps = new GPSInfo(getApplicationContext());
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        // GPS 정보 가져오기
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 현재 네트워크 상태 값 알아오기
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    //추가된 소스, ToolBar에 menu.xml을 인플레이트함
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_search_menu, menu);
        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_main_plus:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("currentPoint.lat", mLastKnownLocation.getLatitude());
                intent.putExtra("currentPoint.lng", mLastKnownLocation.getLongitude());
                startActivityForResult(intent, 1);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    String resultId = data.getExtras().getString("resultId");
                    LatLng resultPoint = new LatLng(data.getExtras().getDouble("resultLat"),
                            data.getExtras().getDouble("resultLng"));
                    setLocationMarker(resultPoint, resultId, "Search 결과");
            }
        }
    }

    //지도 준비사항 -> 초기 위치 설정, 위피 퍼미션 설정
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        // 권한 요청 전에 초기 셋팅
        updateLocationUI();

        // 초기 디바이스 위치 셋팅
        getDeviceLocation();

        // 현재 위치 이동
        searchCurrentPlaces();
    }

    private void updateLocationUI() {
        if (mGoogleMap == null) {
            return;
        }
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        }

        if (mLocationPermissionGranted) {
            initializeLocation();

        } else {
            mGoogleMap.setMyLocationEnabled(false);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
            mLastKnownLocation = null;
        }
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation() {
        /*
         * Before getting the device location, you must check location
         * permission, as described earlier in the tutorial. Then:
         * Get the best and most recent location of the device, which may be
         * null in rare cases when a location is not available.
         */
        if (mLocationPermissionGranted) {
            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                if (locationManager != null) {
                    mLastKnownLocation = locationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
            }
            if (isGPSEnabled) {
                if (mLastKnownLocation == null) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        mLastKnownLocation = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                }
            }
        }

        // Set the map's camera position to the current location of the device.
        if (mLastKnownLocation != null) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mLastKnownLocation.getLatitude(),
                            mLastKnownLocation.getLongitude()), ZOOM_LEVEL));
        } else {
            Log.d(TAG, "Current location is null. Using defaults.");
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultPoint, ZOOM_LEVEL));
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
    }

    @SuppressLint("MissingPermission")
    public void initializeLocation() {
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);        // 지도에 현재위치 버튼 보이게
        mGoogleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                searchCurrentPlaces();
                return false;
            }
        });
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
             @Override
             public void onMapClick(LatLng latLng) {
                 // 현재 위도와 경도에서 화면 포인트를 알려준다
                 Point screenPt = mGoogleMap.getProjection().toScreenLocation(latLng);
                 // 현재 화면에 찍힌 포인트로 부터 위도와 경도를 알려준다.
                 LatLng clickPoint = mGoogleMap.getProjection().fromScreenLocation(screenPt);
                 Log.d(TAG, "좌표: 위도(" + String.valueOf(clickPoint.latitude) + "), 경도("
                         + String.valueOf(clickPoint.longitude) + ")");
                 searchPoiPlaces(clickPoint);
             }
         });

        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);        // 지도에 줌버튼 보이게
        mGoogleMap.getUiSettings().setZoomGesturesEnabled(true);
        mGoogleMap.getUiSettings().setCompassEnabled(true);               //나침반이 나타나도록 설정
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(ZOOM_LEVEL));     // 매끄럽게 이동함
        setLocationMarker(defaultPoint, defaultTitle, defaultSnippet);
    }

    public void setLocationMarker(LatLng latLng, String title, String snippet) {
        mGoogleMap.clear();
        mGoogleMap.addMarker(new MarkerOptions().position(latLng).title(title).snippet(snippet).draggable(true));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.setMinZoomPreference(ZOOM_LEVEL);
    }

    // 위도 경도로 정확한 POI 검색이 안된다. GeoCode로 바꿔봐야 할 듯.
    private void searchCurrentPlaces() {
        if (mGoogleMap == null) {
            return;
        }
        if (mLocationPermissionGranted) {
            // Get the likely places - that is, the businesses and other points of interest that
            // are the best match for the device's current location.
            PoiRequest request = new PoiRequest.PoiRequestBuilder("")
                    .setLat(mLastKnownLocation.getLatitude())
                    .setLng(mLastKnownLocation.getLongitude())
                    .build();

            Log.d(TAG, "searchCurrentPlaces: mLastKnownLocation -> " + mLastKnownLocation.getLatitude() + ", " +
                    mLastKnownLocation.getLongitude());

            LatLng latLng = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());

            callPoiSearch(request, latLng);

        } else {
            // Add a default marker, because the user hasn't selected a place.
            setLocationMarker(defaultPoint, defaultTitle, defaultSnippet);
            // 다시 권한 요청
            getDeviceLocation();
        }
    }

    public void searchPoiPlaces(LatLng clickPoint) {
        if (mGoogleMap == null) {
            return;
        }
        if (mLocationPermissionGranted) {
            // Get the likely places - that is, the businesses and other points of interest that
            // are the best match for the device's current location.
            PoiRequest request = new PoiRequest.PoiRequestBuilder("")
                    .setLat(clickPoint.latitude)
                    .setLng(clickPoint.longitude)
                    .build();

            Log.d(TAG, "searchPoiPlaces: mLastKnownLocation -> " + mLastKnownLocation.getLatitude() + ", " +
                    mLastKnownLocation.getLongitude());

            callPoiSearch(request, clickPoint);

        } else {
            // Add a default marker, because the user hasn't selected a place.
            setLocationMarker(defaultPoint, defaultTitle, defaultSnippet);
            // 다시 권한 요청
            getDeviceLocation();
        }
    }

    public void callPoiSearch(PoiRequest request, final LatLng latLng) {
        client.getPoiSearch(request, new OnSuccessListener<PoiResponse>() {
            @Override
            public void onSuccess(@NonNull PoiResponse poiResponse) {
                if(poiResponse.getPois().size() > 0) {
                    setLocationMarker(latLng,
                            poiResponse.getPois().get(0).getName(),
                            poiResponse.getPois().get(0).getAddress().getFullAddressParcel());
                    return;
                }
                setLocationMarker(defaultPoint, defaultTitle, defaultSnippet);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                Log.d(TAG, throwable.getMessage());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            isAccessFineLocation = true;
        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            isAccessCoarseLocation = true;
        }
        if (isAccessFineLocation && isAccessCoarseLocation) {
            mLocationPermissionGranted = true;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastKnownLocation = location;
        Log.d("ddd", "onLocationChanged: mLastKnownLocation -> " + mLastKnownLocation.getLatitude() + ", " +
                mLastKnownLocation.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

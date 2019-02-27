package com.example.jhp0415.placesapi01.searchplace;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jhp0415.placesapi01.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;

import java.io.IOException;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class PlaceSearchActivity extends AppCompatActivity
        implements OnMapReadyCallback {
    private GoogleMap mGoogleMap;
    private Geocoder geocoder;
    private Button button;
    private EditText editText;
    private Marker currentMarker = null;
    private static final String TAG = "SEARCH";
    private AutocompleteSupportFragment autocompleteSupportFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_search);

        editText = (EditText) findViewById(R.id.editText);
        button=(Button)findViewById(R.id.button);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        geocoder = new Geocoder(this);

        //지도의 초기위치를 서울로 이동한다.
        Log.d(TAG, "onMapReady : setDefaultLoction() 실행");
        setDefaultLocation();

        // 맵 터치 이벤트 구현 //
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions mOptions = new MarkerOptions();
                // 마커 타이틀
                mOptions.title("마커 좌표");
                Double latitude = point.latitude; // 위도
                Double longitude = point.longitude; // 경도
                // 마커의 스니펫(간단한 텍스트) 설정
                mOptions.snippet(latitude.toString() + ", " + longitude.toString());
                // LatLng: 위도 경도 쌍을 나타냄
                mOptions.position(new LatLng(latitude, longitude));
                // 마커(핀) 추가
                mGoogleMap.addMarker(mOptions);
            }
        });

        // 버튼 이벤트
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                String inputAddr = editText.getText().toString();
                List<Address> addressList = null;
                try {
                    // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                    //getFromLocationName() : 주소 -> 위도, 경도
                    addressList = geocoder.getFromLocationName(
                            inputAddr, // 주소
                            10); // 최대 검색 결과 개수
                }
                catch (IOException e) {
                    e.printStackTrace();        // 주소를 받아올때는 에러처리 해줘야 한다.
                }

                String address = addressList.get(0).getAddressLine(0);
                Double latitude = addressList.get(0).getLatitude();
                Double longitude = addressList.get(0).getLongitude();

                Log.d("debugging", "address : " + address);
                Log.d("debugging", "latitude : " + latitude);
                Log.d("debugging", "longitude : " + longitude);

                LatLng point = new LatLng(latitude, longitude);
                MarkerOptions mOptions2 = new MarkerOptions();
                mOptions2.title("search result");
                mOptions2.snippet(address);
                mOptions2.position(point);
                // 마커 추가, 해당 좌표로 화면 줌
                mGoogleMap.addMarker(mOptions2);
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));
            }
        });
    }

    private void setDefaultLocation() {
        //디폴트 위치, 서울
        //마커 옵션 설정
        Log.d(TAG, "setDeaultLocation : 초기 위치 서울로 지정");
        LatLng DEFAULT_LOCATION = new LatLng(37.56,126.97);
        String markerTitle = "서울";
        String markerSnippet = "위도:37.56, 경도:126.97";

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

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {
        super.onActivityResult(i, i1, intent);
    }



}

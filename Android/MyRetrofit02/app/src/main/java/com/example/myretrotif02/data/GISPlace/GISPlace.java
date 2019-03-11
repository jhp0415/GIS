package com.example.myretrotif02.data.GISPlace;

import com.example.myretrotif02.data.GISPlace.Address;
import com.example.myretrotif02.data.GISPlace.Extension;
import com.example.myretrotif02.data.GISPlace.Phones;
import com.example.myretrotif02.data.place.Place;
import com.example.myretrotif02.data.GISPlace.Poi;
import com.example.myretrotif02.data.GISPlace.Point;
import com.example.myretrotif02.data.GISPlace.Theme;

import java.util.List;

public class GISPlace {

    private String id = "";
    private String name = "";
    private String addressParcel = "";
    private String addressRoad = "";
    private Point point = null;
    private Double distance = null;
    private Theme theme = null;
    //private Extension extension = null;
    private String photoURL = null;
    private String homepageURL = "";
    private List<String> phones = null;

    private GISPlace gisPlace = new GISPlace();

    public GISPlace() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressParcel() {
        return addressParcel;
    }

    public void setAddressParcel(String addressParcel) {
        this.addressParcel = addressParcel;
    }

    public String getAddressRoad() {
        return addressRoad;
    }

    public void setAddressRoad(String addressRoad) {
        this.addressRoad = addressRoad;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getHomepageURL() {
        return homepageURL;
    }

    public void setHomepageURL(String homepageURL) {
        this.homepageURL = homepageURL;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public void ConvertPlaceData(Place place) {
        gisPlace.setId(place.getPois().get(0).getId());
        gisPlace.setName(place.getPois().get(0).getName());
        //gisPlace.setAddressParcel(place.getPois().get(0).getAddress().);
        gisPlace.setDistance(place.getPois().get(0).getDistance());
        gisPlace.point.setLat(place.getPois().get(0).getPoint().getLat());
        gisPlace.point.setLng(place.getPois().get(0).getPoint().getLng());
//        gisPlace.phones.setPhones(place.getPois().get(0).getPhones());
        gisPlace.setPhotoURL(place.getPois().get(0).getExtension().getPhotoURL());
        gisPlace.setHomepageURL(place.getPois().get(0).getExtension().getHomepageURL());
        gisPlace.theme.setCode((place.getPois().get(0).getTheme().get(0).getCode()));
        gisPlace.theme.setName((place.getPois().get(0).getTheme().get(0).getName()));
    }
}

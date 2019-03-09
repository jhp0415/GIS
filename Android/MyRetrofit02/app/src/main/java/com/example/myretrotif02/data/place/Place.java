
package com.example.myretrotif02.data.place;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Place {
    @SerializedName("numberOfPois")
    @Expose
    private int numberOfPois;
    @SerializedName("pois")
    @Expose
    private List<Poi> pois = null;

    public Place() {
    }

    public Place(int numberOfPois, List<Poi> pois) {
        super();
        this.numberOfPois = numberOfPois;
        this.pois = pois;
    }

    public int getNumberOfPois() {
        return numberOfPois;
    }

    public void setNumberOfPois(int numberOfPois) {
        this.numberOfPois = numberOfPois;
    }

    public List<Poi> getPois() {
        return pois;
    }

    public void setPois(List<Poi> pois) {
        this.pois = pois;
    }

    public String getName() {
        return getPois().get(0).getName();
    }

    public String getId() {
        return getPois().get(0).getId();
    }

    public String getAddressParcel() {
        return getPois().get(0).getAddress().getSiDo() + " "
                + getPois().get(0).getAddress().getSiGunGu() + " "
                + getPois().get(0).getAddress().getEupMyeonDong() + " "
                + getPois().get(0).getAddress().getHouseNumber();
    }

    public String getAddressRoad() {
        return getPois().get(0).getAddress().getSiDo() + " "
                + getPois().get(0).getAddress().getStreet() + " "
                + getPois().get(0).getAddress().getStreetNumber() + " "
                + getPois().get(0).getAddress().getStreetNumber();
    }

    public Double getLat() {
        return getPois().get(0).getPoint().getLat();
    }

    public Double getLng() {
        return getPois().get(0).getPoint().getLng();
    }

    public Double getDistance() {
        return getPois().get(0).getDistance();
    }

    public String getPhones() {
        return getPois().get(0).getPhones().getRepresentation().get(0);
    }

    public String getTheme() {
        return getPois().get(0).getTheme().get(0).getCode() + ", " + getPois().get(0).getTheme().get(0).getName();
    }

    public String getPhotoURL() {
        return getPois().get(0).getExtension().getPhotoURL();
    }

    public String getHomepageURL() {
        return getPois().get(0).getExtension().getHomepageURL();
    }

    public String getOpeningHours() {
        return getPois().get(0).getExtension().getOpeningHours().get(0);
    }

}

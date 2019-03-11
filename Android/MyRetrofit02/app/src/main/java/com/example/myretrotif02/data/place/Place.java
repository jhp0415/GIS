
package com.example.myretrotif02.data.place;

import com.example.myretrotif02.DataUtil;
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

    private DataUtil u = new DataUtil();

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

////////////////////////////////////////////////////////////////////////////////////////

    public String getName() {
        if (!u.isNull(getPois()))
            if (!u.isNull(getPois().get(0)))
                if (!u.isNull(getPois().get(0).getName()))
                    return getPois().get(0).getName();
        return "";
    }

    public String getId() {
        if (!u.isNull(getPois()))
            if (!u.isNull(getPois().get(0)))
                if (!u.isNull(getPois().get(0).getId()))
                    return getPois().get(0).getId();
        return "";
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
        if (!u.isNull(getPois()))
            if (!u.isNull(getPois().get(0)))
                if (!u.isNull(getPois().get(0).getPoint()))
                    if (!u.isNull(getPois().get(0).getPoint().getLat()))
                    return getPois().get(0).getPoint().getLat();
        return 0.0;
    }

    public Double getLng() {
        if (!u.isNull(getPois()))
            if (!u.isNull(getPois().get(0)))
                if (!u.isNull(getPois().get(0).getPoint()))
                    if (!u.isNull(getPois().get(0).getPoint().getLng()))
                        return getPois().get(0).getPoint().getLng();
        return 0.0;
    }

    public Double getDistance() {
        if (!u.isNull(getPois()))
            if (!u.isNull(getPois().get(0)))
                if (!u.isNull(getPois().get(0).getDistance()))
                    return getPois().get(0).getDistance();
        return 0.0;
    }

    public String getPhones() {
        if (!u.isNull(getPois()))
            if (!u.isNull(getPois().get(0)))
                if (!u.isNull(getPois().get(0).getPhones()))
                    if (!u.isNull(getPois().get(0).getPhones().getRepresentation()))
                        if (!u.isNull(getPois().get(0).getPhones().getRepresentation().get(0)))
                            return getPois().get(0).getPhones().getRepresentation().get(0);
        return "";
    }

    public Theme getTheme() {
        if (!u.isNull(getPois()))
            if (!u.isNull(getPois().get(0)))
                if (!u.isNull(getPois().get(0).getTheme()))
                    if (!u.isNull(getPois().get(0).getTheme().get(0)))
                        if (!u.isNull(getPois().get(0).getTheme().get(0)))
                                return getPois().get(0).getTheme().get(0);
        return null;
    }

    public String getPhotoURL() {
        if (!u.isNull(getPois()))
            if (!u.isNull(getPois().get(0)))
                if (!u.isNull(getPois().get(0).getExtension()))
                    if (!u.isNull(getPois().get(0).getExtension().getPhotoURL()))
                        return getPois().get(0).getExtension().getPhotoURL();
        return "";
    }

    public String getHomepageURL() {
        if (!u.isNull(getPois()))
            if (!u.isNull(getPois().get(0)))
                if (!u.isNull(getPois().get(0).getExtension()))
                    if (!u.isNull(getPois().get(0).getExtension().getHomepageURL()))
                        return getPois().get(0).getExtension().getHomepageURL();
        return "";
    }

//    public OpeningHours getOpeningHours() {
//        if (!u.isNull(getPois()))
//            if (!u.isNull(getPois().get(0)))
//                if (!u.isNull(getPois().get(0).getExtension()))
//                    if (!u.isNull(getPois().get(0).getExtension().getOpeningHours()))
//                        if (!u.isNull(getPois().get(0).getExtension().getOpeningHours().get(0)))
//                            return getPois().get(0).getExtension().getOpeningHours().get(0);
//        return null;
//    }
}

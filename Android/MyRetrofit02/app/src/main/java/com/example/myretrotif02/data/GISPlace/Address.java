
package com.example.myretrotif02.data.GISPlace;

import com.example.myretrotif02.data.place.Place;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {
    private String siDo = "";
    private String siGunGu = "";
    private String eupMyeonDong = "";
    private String haengJeongDong = "";
    private String ri = "";
    private String houseNumber = "";
    private String street = "";
    private String streetNumber = "";

    public Address() {
    }

    public Address getAddress() {
        return this;
    }

    public String getAddressParcel() {
        String result = "";

        return "";
    }

    public String getAddressRoad() {
        return "";
    }
}

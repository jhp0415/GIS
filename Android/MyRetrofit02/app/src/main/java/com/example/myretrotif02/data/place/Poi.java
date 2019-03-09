
package com.example.myretrotif02.data.place;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Poi {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("phones")
    @Expose
    private Phones phones;
    @SerializedName("theme")
    @Expose
    private List<Theme> theme = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("extension")
    @Expose
    private Extension extension;
    @SerializedName("point")
    @Expose
    private Point point;

    public Poi() {
    }

    public Poi(String id, Address address, Double distance, Phones phones, List<Theme> theme, String name, Extension extension, Point point) {
        super();
        this.id = id;
        this.address = address;
        this.distance = distance;
        this.phones = phones;
        this.theme = theme;
        this.name = name;
        this.extension = extension;
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Phones getPhones() {
        return phones;
    }

    public void setPhones(Phones phones) {
        this.phones = phones;
    }

    public List<Theme> getTheme() {
        return theme;
    }

    public void setTheme(List<Theme> theme) {
        this.theme = theme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

}

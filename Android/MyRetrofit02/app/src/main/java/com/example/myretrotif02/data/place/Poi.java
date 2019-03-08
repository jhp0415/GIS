
package com.example.myretrotif02.data.place;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Poi {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("direction")
    @Expose
    private Integer direction;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("phones")
    @Expose
    private Phones phones;
    @SerializedName("children")
    @Expose
    private List<Child> children = null;
    @SerializedName("theme")
    @Expose
    private List<Theme> theme = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("extension")
    @Expose
    private Extension extension;
    @SerializedName("point")
    @Expose
    private Point point;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Poi() {
    }

    /**
     * 
     * @param extension
     * @param id
     * @param point
     * @param category
     * @param distance
     * @param address
     * @param direction
     * @param name
     * @param theme
     * @param branch
     * @param children
     * @param phones
     */
    public Poi(String id, Address address, Integer direction, Double distance, Phones phones, List<Child> children, List<Theme> theme, String name, String branch, Category category, Extension extension, Point point) {
        super();
        this.id = id;
        this.address = address;
        this.direction = direction;
        this.distance = distance;
        this.phones = phones;
        this.children = children;
        this.theme = theme;
        this.name = name;
        this.branch = branch;
        this.category = category;
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

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
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

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

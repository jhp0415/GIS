
package com.example.myretrofit01.repo;

import java.util.List;

import com.example.myretrofit01.repo.Category;
import com.example.myretrofit01.repo.Child;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Poi {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("phones")
    @Expose
    private Phones phones;
    @SerializedName("children")
    @Expose
    private List<Child> children = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("point")
    @Expose
    private Point point;

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

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

}

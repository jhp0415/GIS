
package com.example.myretrofit01.data.repo.poiSearchDetailRepo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shape {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("coordinates")
    @Expose
    private List<List<List<List<Double>>>> coordinates = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Shape() {
    }

    /**
     * 
     * @param type
     * @param coordinates
     */
    public Shape(String type, List<List<List<List<Double>>>> coordinates) {
        super();
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<List<List<Double>>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<List<Double>>>> coordinates) {
        this.coordinates = coordinates;
    }

}

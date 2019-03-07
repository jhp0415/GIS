
package com.example.myretrofit01.data.repo.poiSearchDetailRepo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UtmkPoint {

    @SerializedName("x")
    @Expose
    private Double x;
    @SerializedName("y")
    @Expose
    private Double y;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UtmkPoint() {
    }

    /**
     * 
     * @param y
     * @param x
     */
    public UtmkPoint(Double x, Double y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

}

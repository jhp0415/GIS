
package com.example.myretrofit01.data.RequestBody;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Polygon {

    @SerializedName("points")
    @Expose
    private List<List<Point>> points = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Polygon() {
    }

    /**
     * 
     * @param points
     */
    public Polygon(List<List<Point>> points) {
        super();
        this.points = points;
    }

    public List<List<Point>> getPoints() {
        return points;
    }

    public void setPoints(List<List<Point>> points) {
        this.points = points;
    }

}

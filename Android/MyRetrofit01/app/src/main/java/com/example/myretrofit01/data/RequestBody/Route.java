
package com.example.myretrofit01.data.RequestBody;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route {

    @SerializedName("points")
    @Expose
    private List<Point> points = null;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("side")
    @Expose
    private String side;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Route() {
    }

    /**
     * 
     * @param distance
     * @param side
     * @param points
     */
    public Route(List<Point> points, Integer distance, String side) {
        super();
        this.points = points;
        this.distance = distance;
        this.side = side;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

}

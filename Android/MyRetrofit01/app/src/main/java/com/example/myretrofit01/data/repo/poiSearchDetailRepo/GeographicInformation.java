
package com.example.myretrofit01.data.repo.poiSearchDetailRepo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeographicInformation {

    @SerializedName("utmkPoint")
    @Expose
    private UtmkPoint utmkPoint;
    @SerializedName("point")
    @Expose
    private Point point;
    @SerializedName("shape")
    @Expose
    private Shape shape;
    @SerializedName("area")
    @Expose
    private List<List<Double>> area = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GeographicInformation() {
    }

    /**
     * 
     * @param point
     * @param utmkPoint
     * @param area
     * @param shape
     */
    public GeographicInformation(UtmkPoint utmkPoint, Point point, Shape shape, List<List<Double>> area) {
        super();
        this.utmkPoint = utmkPoint;
        this.point = point;
        this.shape = shape;
        this.area = area;
    }

    public UtmkPoint getUtmkPoint() {
        return utmkPoint;
    }

    public void setUtmkPoint(UtmkPoint utmkPoint) {
        this.utmkPoint = utmkPoint;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public List<List<Double>> getArea() {
        return area;
    }

    public void setArea(List<List<Double>> area) {
        this.area = area;
    }

}

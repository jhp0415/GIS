
package com.example.myretrofit01.data.RequestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestBody {

    @SerializedName("terms")
    @Expose
    private String terms;
    @SerializedName("bound")
    @Expose
    private Bound bound;
    @SerializedName("route")
    @Expose
    private Route route;
    @SerializedName("polygon")
    @Expose
    private Polygon polygon;
    @SerializedName("range")
    @Expose
    private Range range;
    @SerializedName("point")
    @Expose
    private Point point;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("theme")
    @Expose
    private Theme theme;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("zipCode")
    @Expose
    private Integer zipCode;
    @SerializedName("airportcode")
    @Expose
    private String airportcode;
    @SerializedName("phone")
    @Expose
    private Phone phone;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RequestBody() {
    }

    /**
     * 
     * @param airportcode
     * @param polygon
     * @param point
     * @param phone
     * @param category
     * @param range
     * @param address
     * @param route
     * @param zipCode
     * @param theme
     * @param terms
     * @param bound
     */
//    public RequestBody(String terms, Bound bound, Route route, Polygon polygon, Range range, Point point, Category category, Theme theme, Address address, Integer zipCode, String airportcode, Phone phone) {
//        super();
//        this.terms = terms;
//        this.bound = bound;
//        this.route = route;
//        this.polygon = polygon;
//        this.range = range;
//        this.point = point;
//        this.category = category;
//        this.theme = theme;
//        this.address = address;
//        this.zipCode = zipCode;
//        this.airportcode = airportcode;
//        this.phone = phone;
//    }

    public RequestBody(String terms, Point point, Address address) {
        super();
        this.terms = terms;
        this.point = point;
        this.address = address;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Bound getBound() {
        return bound;
    }

    public void setBound(Bound bound) {
        this.bound = bound;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getAirportcode() {
        return airportcode;
    }

    public void setAirportcode(String airportcode) {
        this.airportcode = airportcode;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

}

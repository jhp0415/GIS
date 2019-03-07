
package com.example.myretrofit01.data.RequestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bound {

    @SerializedName("left")
    @Expose
    private Double left;
    @SerializedName("top")
    @Expose
    private Double top;
    @SerializedName("right")
    @Expose
    private Double right;
    @SerializedName("bottom")
    @Expose
    private Double bottom;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Bound() {
    }

    /**
     * 
     * @param bottom
     * @param left
     * @param right
     * @param top
     */
    public Bound(Double left, Double top, Double right, Double bottom) {
        super();
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public Double getTop() {
        return top;
    }

    public void setTop(Double top) {
        this.top = top;
    }

    public Double getRight() {
        return right;
    }

    public void setRight(Double right) {
        this.right = right;
    }

    public Double getBottom() {
        return bottom;
    }

    public void setBottom(Double bottom) {
        this.bottom = bottom;
    }

}

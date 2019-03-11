
package com.example.myretrotif02.data.GISPlace;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Phones {
    @SerializedName("representation")
    @Expose
    private List<String> representation = null;
    @SerializedName("fax")
    @Expose
    private List<String> fax = null;
    @SerializedName("normal")
    @Expose
    private List<String> normal = null;

    public Phones() {
    }

    public Phones(List<String> representation, List<String> fax, List<String> normal) {
        super();
        this.representation = representation;
        this.fax = fax;
        this.normal = normal;
    }

    public List<String> getRepresentation() {
        return representation;
    }

    public void setRepresentation(List<String> representation) {
        this.representation = representation;
    }

    public List<String> getFax() {
        return fax;
    }

    public void setFax(List<String> fax) {
        this.fax = fax;
    }

    public List<String> getNormal() {
        return normal;
    }

    public void setNormal(List<String> normal) {
        this.normal = normal;
    }

}

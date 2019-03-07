
package com.example.myretrofit01.data.RequestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phone {

    @SerializedName("representation")
    @Expose
    private String representation;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("normal")
    @Expose
    private String normal;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Phone() {
    }

    /**
     * 
     * @param fax
     * @param representation
     * @param normal
     */
    public Phone(String representation, String fax, String normal) {
        super();
        this.representation = representation;
        this.fax = fax;
        this.normal = normal;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

}

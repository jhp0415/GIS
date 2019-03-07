
package com.example.myretrofit01.data.repo.poiSearchRepo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phones {

    @SerializedName("representation")
    @Expose
    private List<String> representation = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Phones() {
    }

    /**
     * 
     * @param representation
     */
    public Phones(List<String> representation) {
        super();
        this.representation = representation;
    }

    public List<String> getRepresentation() {
        return representation;
    }

    public void setRepresentation(List<String> representation) {
        this.representation = representation;
    }

}


package com.example.myretrofit01.data.repo.retrievePOIRepo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phones {

    @SerializedName("representation")
    @Expose
    private List<String> representation = null;

    public Phones(List<String> representation) {
        this.representation = representation;
    }

    public List<String> getRepresentation() {
        return representation;
    }

    public void setRepresentation(List<String> representation) {
        this.representation = representation;
    }

}

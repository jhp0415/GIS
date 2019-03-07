
package com.example.myretrofit01.data.repo.poiSearchDetailRepo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResidentialAddress {

    @SerializedName("parcelAddress")
    @Expose
    private List<ParcelAddress> parcelAddress = null;
    @SerializedName("roadAddress")
    @Expose
    private List<RoadAddress> roadAddress = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResidentialAddress() {
    }

    /**
     * 
     * @param parcelAddress
     * @param roadAddress
     */
    public ResidentialAddress(List<ParcelAddress> parcelAddress, List<RoadAddress> roadAddress) {
        super();
        this.parcelAddress = parcelAddress;
        this.roadAddress = roadAddress;
    }

    public List<ParcelAddress> getParcelAddress() {
        return parcelAddress;
    }

    public void setParcelAddress(List<ParcelAddress> parcelAddress) {
        this.parcelAddress = parcelAddress;
    }

    public List<RoadAddress> getRoadAddress() {
        return roadAddress;
    }

    public void setRoadAddress(List<RoadAddress> roadAddress) {
        this.roadAddress = roadAddress;
    }

}

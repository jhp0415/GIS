
package com.example.myretrotif02.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBody {

    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("distanceUnit")
    @Expose
    private String distanceUnit;
    @SerializedName("numberOfPois")
    @Expose
    private Integer numberOfPois;
    @SerializedName("numberOfAddress")
    @Expose
    private Integer numberOfAddress;
    @SerializedName("providerId")
    @Expose
    private String providerId;
    @SerializedName("pois")
    @Expose
    private List<Poi> pois = null;
    @SerializedName("residentialAddress")
    @Expose
    private List<Object> residentialAddress = null;

    /**
     * No args constructor for use in serialization
     */
    public ResponseBody() {
    }

    /**
     * @param numberOfAddress
     * @param transactionId
     * @param numberOfPois
     * @param pois
     * @param providerId
     * @param distanceUnit
     * @param residentialAddress
     */
    public ResponseBody(String transactionId, String distanceUnit, Integer numberOfPois, Integer numberOfAddress, String providerId, List<Poi> pois, List<Object> residentialAddress) {
        super();
        this.transactionId = transactionId;
        this.distanceUnit = distanceUnit;
        this.numberOfPois = numberOfPois;
        this.numberOfAddress = numberOfAddress;
        this.providerId = providerId;
        this.pois = pois;
        this.residentialAddress = residentialAddress;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public Integer getNumberOfPois() {
        return numberOfPois;
    }

    public void setNumberOfPois(Integer numberOfPois) {
        this.numberOfPois = numberOfPois;
    }

    public Integer getNumberOfAddress() {
        return numberOfAddress;
    }

    public void setNumberOfAddress(Integer numberOfAddress) {
        this.numberOfAddress = numberOfAddress;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public List<Poi> getPois() {
        return pois;
    }

    public void setPois(List<Poi> pois) {
        this.pois = pois;
    }

    public List<Object> getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(List<Object> residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

}

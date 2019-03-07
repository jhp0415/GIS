
package com.example.myretrofit01.data.repo.poiSearchDetailRepo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PoiSearchDetailRepo {

    @SerializedName("transactionId")
    @Expose
    private String transactionId;
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
    private List<Object> pois = null;
    @SerializedName("residentialAddress")
    @Expose
    private List<ResidentialAddress> residentialAddress = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PoiSearchDetailRepo() {
    }

    /**
     * 
     * @param numberOfAddress
     * @param transactionId
     * @param numberOfPois
     * @param pois
     * @param providerId
     * @param residentialAddress
     */
    public PoiSearchDetailRepo(String transactionId, Integer numberOfPois, Integer numberOfAddress, String providerId, List<Object> pois, List<ResidentialAddress> residentialAddress) {
        super();
        this.transactionId = transactionId;
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

    public List<Object> getPois() {
        return pois;
    }

    public void setPois(List<Object> pois) {
        this.pois = pois;
    }

    public List<ResidentialAddress> getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(List<ResidentialAddress> residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

}

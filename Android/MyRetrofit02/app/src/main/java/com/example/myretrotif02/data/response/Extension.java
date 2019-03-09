
package com.example.myretrotif02.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extension {

    @SerializedName("extensionSize")
    @Expose
    private Integer extensionSize;
    @SerializedName("homepageURL")
    @Expose
    private String homepageURL;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("reservationService")
    @Expose
    private Boolean reservationService;
    @SerializedName("parkingService")
    @Expose
    private Boolean parkingService;
    @SerializedName("creditcardService")
    @Expose
    private Boolean creditcardService;
    @SerializedName("packagingService")
    @Expose
    private Boolean packagingService;
    @SerializedName("parkingSize")
    @Expose
    private Integer parkingSize;
    @SerializedName("cashReceipt")
    @Expose
    private Boolean cashReceipt;
    @SerializedName("infantFacility")
    @Expose
    private Boolean infantFacility;
    @SerializedName("hours24")
    @Expose
    private Boolean hours24;
    @SerializedName("disabledService")
    @Expose
    private Boolean disabledService;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Extension() {
    }

    /**
     * 
     * @param creditcardService
     * @param packagingService
     * @param homepageURL
     * @param extensionSize
     * @param parkingService
     * @param infantFacility
     * @param size
     * @param disabledService
     * @param reservationService
     * @param parkingSize
     * @param cashReceipt
     * @param hours24
     */
    public Extension(Integer extensionSize, String homepageURL, String size, Boolean reservationService,
                     Boolean parkingService, Boolean creditcardService, Boolean packagingService,
                     Integer parkingSize, Boolean cashReceipt, Boolean infantFacility,
                     Boolean hours24, Boolean disabledService) {
        super();
        this.extensionSize = extensionSize;
        this.homepageURL = homepageURL;
        this.size = size;
        this.reservationService = reservationService;
        this.parkingService = parkingService;
        this.creditcardService = creditcardService;
        this.packagingService = packagingService;
        this.parkingSize = parkingSize;
        this.cashReceipt = cashReceipt;
        this.infantFacility = infantFacility;
        this.hours24 = hours24;
        this.disabledService = disabledService;
    }

    public Integer getExtensionSize() {
        return extensionSize;
    }

    public void setExtensionSize(Integer extensionSize) {
        this.extensionSize = extensionSize;
    }

    public String getHomepageURL() {
        return homepageURL;
    }

    public void setHomepageURL(String homepageURL) {
        this.homepageURL = homepageURL;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getReservationService() {
        return reservationService;
    }

    public void setReservationService(Boolean reservationService) {
        this.reservationService = reservationService;
    }

    public Boolean getParkingService() {
        return parkingService;
    }

    public void setParkingService(Boolean parkingService) {
        this.parkingService = parkingService;
    }

    public Boolean getCreditcardService() {
        return creditcardService;
    }

    public void setCreditcardService(Boolean creditcardService) {
        this.creditcardService = creditcardService;
    }

    public Boolean getPackagingService() {
        return packagingService;
    }

    public void setPackagingService(Boolean packagingService) {
        this.packagingService = packagingService;
    }

    public Integer getParkingSize() {
        return parkingSize;
    }

    public void setParkingSize(Integer parkingSize) {
        this.parkingSize = parkingSize;
    }

    public Boolean getCashReceipt() {
        return cashReceipt;
    }

    public void setCashReceipt(Boolean cashReceipt) {
        this.cashReceipt = cashReceipt;
    }

    public Boolean getInfantFacility() {
        return infantFacility;
    }

    public void setInfantFacility(Boolean infantFacility) {
        this.infantFacility = infantFacility;
    }

    public Boolean getHours24() {
        return hours24;
    }

    public void setHours24(Boolean hours24) {
        this.hours24 = hours24;
    }

    public Boolean getDisabledService() {
        return disabledService;
    }

    public void setDisabledService(Boolean disabledService) {
        this.disabledService = disabledService;
    }
}


package com.example.myretrofit01.data.repo.poiSearchDetailRepo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParcelAddress {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("eupMyeonDong")
    @Expose
    private String eupMyeonDong;
    @SerializedName("badmId")
    @Expose
    private String badmId;
    @SerializedName("geographicInformation")
    @Expose
    private GeographicInformation geographicInformation;
    @SerializedName("siDo")
    @Expose
    private String siDo;
    @SerializedName("siGunGu1")
    @Expose
    private String siGunGu1;
    @SerializedName("houseNumber")
    @Expose
    private String houseNumber;
    @SerializedName("isMountain")
    @Expose
    private Boolean isMountain;
    @SerializedName("isFromBName")
    @Expose
    private Boolean isFromBName;
    @SerializedName("fullAddress")
    @Expose
    private String fullAddress;
    @SerializedName("pnucode")
    @Expose
    private String pnucode;
    @SerializedName("siGunGu")
    @Expose
    private String siGunGu;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ParcelAddress() {
    }

    /**
     * 
     * @param fullAddress
     * @param isFromBName
     * @param pnucode
     * @param eupMyeonDong
     * @param geographicInformation
     * @param siDo
     * @param siGunGu1
     * @param houseNumber
     * @param isMountain
     * @param badmId
     * @param siGunGu
     * @param country
     */
    public ParcelAddress(String country, String eupMyeonDong, String badmId, GeographicInformation geographicInformation, String siDo, String siGunGu1, String houseNumber, Boolean isMountain, Boolean isFromBName, String fullAddress, String pnucode, String siGunGu) {
        super();
        this.country = country;
        this.eupMyeonDong = eupMyeonDong;
        this.badmId = badmId;
        this.geographicInformation = geographicInformation;
        this.siDo = siDo;
        this.siGunGu1 = siGunGu1;
        this.houseNumber = houseNumber;
        this.isMountain = isMountain;
        this.isFromBName = isFromBName;
        this.fullAddress = fullAddress;
        this.pnucode = pnucode;
        this.siGunGu = siGunGu;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEupMyeonDong() {
        return eupMyeonDong;
    }

    public void setEupMyeonDong(String eupMyeonDong) {
        this.eupMyeonDong = eupMyeonDong;
    }

    public String getBadmId() {
        return badmId;
    }

    public void setBadmId(String badmId) {
        this.badmId = badmId;
    }

    public GeographicInformation getGeographicInformation() {
        return geographicInformation;
    }

    public void setGeographicInformation(GeographicInformation geographicInformation) {
        this.geographicInformation = geographicInformation;
    }

    public String getSiDo() {
        return siDo;
    }

    public void setSiDo(String siDo) {
        this.siDo = siDo;
    }

    public String getSiGunGu1() {
        return siGunGu1;
    }

    public void setSiGunGu1(String siGunGu1) {
        this.siGunGu1 = siGunGu1;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Boolean getIsMountain() {
        return isMountain;
    }

    public void setIsMountain(Boolean isMountain) {
        this.isMountain = isMountain;
    }

    public Boolean getIsFromBName() {
        return isFromBName;
    }

    public void setIsFromBName(Boolean isFromBName) {
        this.isFromBName = isFromBName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPnucode() {
        return pnucode;
    }

    public void setPnucode(String pnucode) {
        this.pnucode = pnucode;
    }

    public String getSiGunGu() {
        return siGunGu;
    }

    public void setSiGunGu(String siGunGu) {
        this.siGunGu = siGunGu;
    }

}

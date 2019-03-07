
package com.example.myretrofit01.data.repo.retrievePOIRepo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    public Category(String masterCode, String masterName, String middleCode, String middleName, String subCode, String subName) {
        this.masterCode = masterCode;
        this.masterName = masterName;
        this.middleCode = middleCode;
        this.middleName = middleName;
        this.subCode = subCode;
        this.subName = subName;
    }

    @SerializedName("masterCode")
    @Expose
    private String masterCode;
    @SerializedName("masterName")
    @Expose
    private String masterName;
    @SerializedName("middleCode")
    @Expose
    private String middleCode;
    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("subCode")
    @Expose
    private String subCode;
    @SerializedName("subName")
    @Expose
    private String subName;

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMiddleCode() {
        return middleCode;
    }

    public void setMiddleCode(String middleCode) {
        this.middleCode = middleCode;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

}

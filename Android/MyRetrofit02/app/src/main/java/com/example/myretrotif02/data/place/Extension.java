
package com.example.myretrotif02.data.place;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extension {
    @SerializedName("homepageURL")
    @Expose
    private String homepageURL;
    @SerializedName("photoURL")
    @Expose
    private String photoURL;
//    @SerializedName("openingHours")
//    @Expose
//    private List<OpeningHours> openingHours;


    public Extension() {
    }

    public Extension(String homepageURL, String photoURL) {
        super();
        this.homepageURL = homepageURL;
        this.photoURL = photoURL;
    }

//    public Extension(String homepageURL, String photoURL, List<OpeningHours> openingHours) {
//        super();
//        this.homepageURL = homepageURL;
//        this.photoURL = photoURL;
//        this.openingHours = openingHours;
//    }

    public String getHomepageURL() {
        return homepageURL;
    }

    public void setHomepageURL(String homepageURL) {
        this.homepageURL = homepageURL;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

//    public List<OpeningHours> getOpeningHours() {
//        return openingHours;
//    }
//
//    public void setOpeningHours(List<OpeningHours> openingHours) {
//        this.openingHours = openingHours;
//    }
}

package com.example.myretrofit01.data;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Place implements Parcelable {
//    public static final int PRICE_LEVEL_MIN_VALUE = 0;
//    public static final int PRICE_LEVEL_MAX_VALUE = 4;
//    public static final double RATING_MIN_VALUE = 1.0D;
//    public static final double RATING_MAX_VALUE = 5.0D;

    public Place() {

    }

    public abstract String getAddress();
    public abstract List<String> gettAttributions();
    public abstract String getId();
    public abstract LatLng getLatLng();
    public abstract String getName();
    public abstract OpenigHours getOpeningHours();
    public abstract String getPhoneNumber();
    public abstract List<PhotoMetadata> getPhtoMetadatas();
    public abstract PlusCode getPlusCode();
    public abstract Double getRating();
    public abstract List<Place.Type> getTypes();
    public abstract Intenger getUserPatingsTotal();
    public abstract LatLngBounds getViewport();
    public abstract Uri getWebsiteUri();

    public static enum Type implements Parcelable {

    }

    public static final Creator<Place.Type> CREATOR = new ct();

    private static enum Type implements Parcelable {
        // 데이터 타입 종류
        OTHER,
        ACCOUNTING,



        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name());
        }
    }

    public static enum Field implements Parcelable {
        ADDRESS,
        ID,
        LAT_LNG,
        NAME,
        OPENING_HOURS,
        PHONE_NUMBER,
        PHOTO_METADATAS,
        PLUS_CODE,
        PRICE_LEVEL,
        RATING,
        TYPES,
        USER_RATINGS_TOTAL,
        VIEWPORT,
        WEBSITE_URI;

        public static final Creator<Place.Field> CREATOR = new cs();

        private Field() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name());
        }
    }



}


package com.example.myretrotif02.data.place;

import com.example.myretrotif02.data.response.ResponseBody;

import java.util.List;


public class Place {
    private ResponseBody responseBody;

    public Place(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public String getName() {
        if (responseBody.getPois().get(0).getName() != null) {
            return responseBody.getPois().get(0).getName();
        } else {
            return "";
        }
    }

    public String getId() {
        if (responseBody.getPois().get(0).getId() != null) {
            return responseBody.getPois().get(0).getId();
        } else {
            return "";
        }

    }

    public String getAddressParcel() {
        if (responseBody.getPois().get(0).getAddress().getHaengJeongDong() != null) {
            return responseBody.getPois().get(0).getAddress().getSiDo()
                    + responseBody.getPois().get(0).getAddress().getSiGunGu()
                    + responseBody.getPois().get(0).getAddress().getHaengJeongDong()
                    + responseBody.getPois().get(0).getAddress().getHouseNumber();
        } else {

        }
    }


}

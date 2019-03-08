package com.example.myretrotif02.retrofit;

import java.util.Map;

public class GISApiRequest {
    private String id;
    private Map map;
    private Map detailMap;

    public GISApiRequest() {}

    private String getiD() {
        return this.id;
    }

    private void setiD(String id) {
        this.id = id;
    }

    private Map getMap() {
        return this.map;
    }

    private void setMap(Map map) {
        this.map = map;
    }

    private Map getDetailMap() {
        return this.detailMap;
    }

    private void setDetailMap(Map map) {
        this.detailMap = map;
    }
}

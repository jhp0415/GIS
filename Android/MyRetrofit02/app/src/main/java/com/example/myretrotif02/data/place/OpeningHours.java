
package com.example.myretrotif02.data.place;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "0",
    "1",
    "2",
    "3",
    "4",
    "5",
    "6"
})
public class OpeningHours {

    @JsonProperty("0")
    private _0 _0;
    @JsonProperty("5")
    private _5 _5;
    @JsonProperty("6")
    private _6 _6;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("0")
    public _0 get0() {
        return _0;
    }

    @JsonProperty("0")
    public void set0(_0 _0) {
        this._0 = _0;
    }

    @JsonProperty("5")
    public _5 get5() {
        return _5;
    }

    @JsonProperty("5")
    public void set5(_5 _5) {
        this._5 = _5;
    }

    @JsonProperty("6")
    public _6 get6() {
        return _6;
    }

    @JsonProperty("6")
    public void set6(_6 _6) {
        this._6 = _6;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

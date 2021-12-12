package model_json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sys {
    @JsonProperty("country")
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    String country;

    @JsonProperty("sunrise")
    public int getSunrise() {
        return this.sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    int sunrise;

    @JsonProperty("sunset")
    public int getSunset() {
        return this.sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    int sunset;
}

package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main {
    @JsonProperty("temp")
    public double getTemp() {
        return this.temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    double temp;

    @JsonProperty("feels_like")
    public double getFeels_like() {
        return this.feels_like;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    double feels_like;

    @JsonProperty("temp_min")
    public double getTemp_min() {
        return this.temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    double temp_min;

    @JsonProperty("temp_max")
    public double getTemp_max() {
        return this.temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    double temp_max;

    @JsonProperty("pressure")
    public int getPressure() {
        return this.pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    int pressure;

    @JsonProperty("humidity")
    public int getHumidity() {
        return this.humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    int humidity;

    @JsonProperty("sea_level")
    public int getSea_level() {
        return this.sea_level;
    }

    public void setSea_level(int sea_level) {
        this.sea_level = sea_level;
    }

    int sea_level;

    @JsonProperty("grnd_level")
    public int getGrnd_level() {
        return this.grnd_level;
    }

    public void setGrnd_level(int grnd_level) {
        this.grnd_level = grnd_level;
    }

    int grnd_level;
}

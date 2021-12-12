package model_json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind {
    @JsonProperty("speed")
    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    double speed;

    @JsonProperty("deg")
    public int getDeg() {
        return this.deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    int deg;

    @JsonProperty("gust")
    public double getGust() {
        return this.gust;
    }

    public void setGust(double gust) {
        this.gust = gust;
    }

    double gust;
}
